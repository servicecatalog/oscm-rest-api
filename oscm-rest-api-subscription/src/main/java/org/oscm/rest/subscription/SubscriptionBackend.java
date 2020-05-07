/**
 * *****************************************************************************
 *
 * <p>Copyright FUJITSU LIMITED 2019
 *
 * <p>Creation Date: 10-04-2019
 *
 * <p>*****************************************************************************
 */
package org.oscm.rest.subscription;

import org.apache.commons.lang3.StringUtils;
import org.oscm.internal.intf.AccountService;
import org.oscm.internal.intf.ServiceProvisioningService;
import org.oscm.internal.intf.SubscriptionService;
import org.oscm.internal.intf.SubscriptionServiceInternal;
import org.oscm.internal.types.enumtypes.PerformanceHint;
import org.oscm.internal.types.exception.DomainObjectException;
import org.oscm.internal.types.exception.ObjectNotFoundException;
import org.oscm.internal.vo.*;
import org.oscm.rest.common.PostResponseBody;
import org.oscm.rest.common.RestBackend;
import org.oscm.rest.common.representation.*;
import org.oscm.rest.common.requestparameters.SubscriptionParameters;
import org.oscm.rest.common.validator.RequiredFieldValidator;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.*;
import java.util.stream.Collectors;

@Stateless
public class SubscriptionBackend {

  @EJB SubscriptionServiceInternal ssi;
  @EJB SubscriptionService ss;
  @EJB ServiceProvisioningService sps;
  @EJB AccountService as;

  private RequiredFieldValidator validator = new RequiredFieldValidator();

  public RestBackend.GetCollection<SubscriptionRepresentation, SubscriptionParameters>
      getCollection() {
    return params -> {
      List<? extends VOSubscription> subs;
      if (params.getUserId() != null) {
        VOUser u = new VOUser();
        u.setUserId(params.getUserId());
        subs = ssi.getSubscriptionsForUser(u, PerformanceHint.ONLY_FIELDS_FOR_LISTINGS);
      } else {
        subs = ssi.getSubscriptionsForOrganization(PerformanceHint.ONLY_FIELDS_FOR_LISTINGS);
      }

      Collection<SubscriptionRepresentation> subscriptionRepresentations =
          subs.stream().map(SubscriptionRepresentation::new).collect(Collectors.toList());

      return new RepresentationCollection<>(subscriptionRepresentations);
    };
  }

  public RestBackend.Delete<SubscriptionParameters> delete() {
    return params -> ss.unsubscribeFromService(params.getId());
  }

  public RestBackend.Get<SubscriptionDetailsRepresentation, SubscriptionParameters> get() {
    return params -> {
      VOSubscriptionDetails sub = ss.getSubscriptionDetails(params.getId());
      return new SubscriptionDetailsRepresentation(sub);
    };
  }

  public RestBackend.Post<SubscriptionCreationRepresentation, SubscriptionParameters> post() {

    return (content, params) -> {
      VOService vo = new VOService();
      vo.setKey(Long.parseLong(content.getServiceId()));
      VOServiceDetails service = sps.getServiceDetails(vo);

      boolean isChargeable = service.getPriceModel().isChargeable();
      VOPaymentInfo paymentInfo = null;
      VOBillingContact billingContact = null;

      Map<String, String> parameters = content.getParameters();
      List<VOParameter> serviceParameters = service.getParameters();
      processParameters(serviceParameters, parameters);

      List<VOUdaDefinition> udaDefinitions = as.getUdaDefinitionsForCustomer(service.getSellerId());
      Map<String, String> requestedUdas = content.getUdas();
      List<VOUda> udas = processUdas(udaDefinitions, requestedUdas);

      if (isChargeable) {
        validator.validateNotBlank("paymentInfoId", content.getPaymentInfoId());
        validator.validateNotBlank("billingContactId", content.getBillingContactId());

        Optional<VOPaymentInfo> foundPayment =
            as.getPaymentInfos().stream()
                .filter(info -> info.getKey() == content.getPaymentInfoId())
                .findAny();

        if (!foundPayment.isPresent()) {
          throw new ObjectNotFoundException(
              DomainObjectException.ClassEnum.PAYMENT_INFO,
              String.valueOf(content.getPaymentInfoId()));
        }

        Optional<VOBillingContact> foundContact =
            as.getBillingContacts().stream()
                .filter(contact -> contact.getKey() == content.getBillingContactId())
                .findAny();

        if (!foundContact.isPresent()) {
          throw new ObjectNotFoundException(
              DomainObjectException.ClassEnum.BILLING_CONTACT,
              String.valueOf(content.getBillingContactId()));
        }
        paymentInfo = foundPayment.get();
        billingContact = foundContact.get();
      }

      VOSubscription sub =
          ss.subscribeToService(content.getVO(), service, null, paymentInfo, billingContact, udas);

      return PostResponseBody.of()
          .createdObjectId(String.valueOf(sub.getKey()))
          .createdObjectName(sub.getSubscriptionId())
          .build();
    };
  }

  private List<VOUda> processUdas(List<VOUdaDefinition> udaDefinitions, Map<String, String> udas) {

    List<VOUda> voUdas = new ArrayList<>();

    // processes only udas for subscription which are configurable(USER_OPTION=true)
    udaDefinitions.stream()
        .filter(udaDefinition -> udaDefinition.getTargetType().equals("CUSTOMER_SUBSCRIPTION"))
        .filter(
            udaDefinition ->
                udaDefinition.getConfigurationType().toString().startsWith("USER_OPTION"))
        .forEach(
            udaDefinition -> {
              String udaValue = udas.get(udaDefinition.getUdaId());
              if (udaDefinition.getConfigurationType().isMandatory()) {
                validator.validateNotBlank(udaDefinition.getUdaId() + " uda", udaValue);
              }
              VOUda voUda = new VOUda();
              voUda.setUdaDefinition(udaDefinition);
              if (StringUtils.isBlank(udaValue)) {
                udaValue = udaDefinition.getDefaultValue();
              }
              voUda.setUdaValue(udaValue);
              voUdas.add(voUda);
            });

    return voUdas;
  }

  private void processParameters(
      List<VOParameter> serviceParameters, Map<String, String> parameters) {

    // processes only parameter which are configurable for given service
    serviceParameters.stream()
        .filter(VOParameter::isConfigurable)
        .forEach(
            serviceParameter -> {
              VOParameterDefinition serviceParameterDefinition =
                  serviceParameter.getParameterDefinition();
              String serviceParameterId = serviceParameterDefinition.getParameterId();
              String parameterValue = parameters.get(serviceParameterId);
              if (serviceParameterDefinition.isMandatory()) {
                validator.validateNotBlank(serviceParameterId + " parameter", parameterValue);
              }
              if (parameterValue == null) {
                parameterValue = serviceParameterDefinition.getDefaultValue();
              }
              serviceParameter.setValue(parameterValue);
            });
  }

  public RestBackend.Put<SubscriptionCreationRepresentation, SubscriptionParameters> put() {
    return (content, params) -> {
      // content.getService().update();
      // content.getUdaRepresentations().forEach(UdaRepresentation::update);

      VOSubscription result =
          ss.modifySubscription(
              content.getVO(),
              null /*content.getVOService().getParameters()*/,
              null /*content.getUdas()*/);
      return result != null;
    };
  }
}
