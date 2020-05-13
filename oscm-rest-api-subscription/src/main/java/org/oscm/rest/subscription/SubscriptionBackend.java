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
import org.oscm.internal.types.enumtypes.ParameterModificationType;
import org.oscm.internal.types.enumtypes.PerformanceHint;
import org.oscm.internal.types.enumtypes.ServiceStatus;
import org.oscm.internal.types.exception.DomainObjectException;
import org.oscm.internal.types.exception.ObjectNotFoundException;
import org.oscm.internal.vo.*;
import org.oscm.rest.common.PostResponseBody;
import org.oscm.rest.common.RestBackend;
import org.oscm.rest.common.errorhandling.ErrorResponse;
import org.oscm.rest.common.representation.*;
import org.oscm.rest.common.requestparameters.SubscriptionParameters;
import org.oscm.rest.common.validator.ParameterValidator;
import org.oscm.rest.common.validator.RequiredFieldValidator;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.BadRequestException;
import java.util.*;
import java.util.stream.Collectors;

@Stateless
public class SubscriptionBackend {

  @EJB SubscriptionServiceInternal ssi;
  @EJB SubscriptionService ss;
  @EJB ServiceProvisioningService sps;
  @EJB AccountService as;

  private RequiredFieldValidator validator = new RequiredFieldValidator();
  private ParameterValidator parameterValidator = new ParameterValidator();

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
      vo.setKey(Long.parseLong(content.getServiceKey()));
      VOServiceDetails service = sps.getServiceDetails(vo);

      if (!service.getStatus().equals(ServiceStatus.ACTIVE)) {
        throw new BadRequestException(
            ErrorResponse.provider()
                .build()
                .badRequest(
                    "Referenced service (serviceKey="
                        + content.getServiceKey()
                        + ") is inaccessible"));
      }

      VOPaymentInfo paymentInfo = null;
      VOBillingContact billingContact = null;

      Map<String, String> parameters = content.getParameters();
      List<VOParameter> serviceParameters = service.getParameters();
      processParametersForCreation(serviceParameters, parameters);

      List<VOUdaDefinition> udaDefinitions = as.getUdaDefinitionsForCustomer(service.getSellerId());
      Map<String, String> requestedUdas = content.getUdas();
      List<VOUda> udas = processUdasForCreation(udaDefinitions, requestedUdas);

      boolean isChargeable = service.getPriceModel().isChargeable();
      if (isChargeable) {
        validator.validateNotBlank("paymentInfoId", content.getPaymentInfoId());
        validator.validateNotBlank("billingContactId", content.getBillingContactId());

        paymentInfo = preparePaymentInfo(content.getPaymentInfoId());
        billingContact = prepareBillingContact(content.getBillingContactId());
      }

      VOSubscription sub =
          ss.subscribeToService(content.getVO(), service, null, paymentInfo, billingContact, udas);

      return PostResponseBody.of()
          .createdObjectId(String.valueOf(sub.getKey()))
          .createdObjectName(sub.getSubscriptionId())
          .build();
    };
  }

  public RestBackend.Put<SubscriptionUpdateRepresentation, SubscriptionParameters> put() {
    return (content, params) -> {
      VOSubscriptionDetails subscription = ss.getSubscriptionDetails(params.getId());
      subscription = processPaymentData(subscription, content);

      VOService subscribedService = subscription.getSubscribedService();

      List<VOParameter> subscriptionParameters =
          subscribedService.getParameters().stream()
              .filter(VOParameter::isConfigurable)
              .filter(
                  parameter ->
                      parameter
                          .getParameterDefinition()
                          .getModificationType()
                          .equals(ParameterModificationType.STANDARD))
              .collect(Collectors.toList());
      Map<String, String> requestParameters = content.getParameters();
      processParametersForUpdate(subscriptionParameters, requestParameters);

      String sellerId = subscribedService.getSellerId();
      List<VOUda> subscriptionUdas =
          as.getUdasForCustomer("CUSTOMER_SUBSCRIPTION", params.getId(), sellerId);
      Map<String, String> requestUdas = content.getUdas();
      processUdasForUpdate(subscriptionUdas, requestUdas);

      String requestedSubscriptionId = content.getSubscriptionId();
      if (StringUtils.isNotBlank(requestedSubscriptionId)) {
        subscription.setSubscriptionId(requestedSubscriptionId);
      }
      String requestedReferenceNo = content.getPurchaseOrderNumber();
      if (StringUtils.isNotBlank(requestedReferenceNo)) {
        subscription.setPurchaseOrderNumber(requestedReferenceNo);
      }

      VOSubscription result =
          ss.modifySubscription(subscription, subscriptionParameters, subscriptionUdas);
      return result != null;
    };
  }

  private VOSubscriptionDetails processPaymentData(
      VOSubscriptionDetails subscription, SubscriptionUpdateRepresentation content)
      throws Exception {

    boolean chargeable = subscription.getPriceModel().isChargeable();

    if (chargeable) {
      VOBillingContact billingContact = subscription.getBillingContact();
      VOPaymentInfo paymentInfo = subscription.getPaymentInfo();

      String billingContactId = content.getBillingContactId();
      String paymentInfoId = content.getPaymentInfoId();

      if (StringUtils.isNotBlank(billingContactId) || StringUtils.isNotBlank(paymentInfoId)) {
        if (StringUtils.isNotBlank(billingContactId)) {
          billingContact = prepareBillingContact(billingContactId);
        }
        if (StringUtils.isNotBlank(paymentInfoId)) {
          paymentInfo = preparePaymentInfo(paymentInfoId);
        }
        return ss.modifySubscriptionPaymentData(subscription, billingContact, paymentInfo);
      }
    }
    return subscription;
  }

  private List<VOUda> processUdasForCreation(
      List<VOUdaDefinition> udaDefinitions, Map<String, String> udas) {

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

  private void processParametersForCreation(
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
              parameterValidator.validate(serviceParameterDefinition, parameterValue);
              if (parameterValue == null) {
                parameterValue = serviceParameterDefinition.getDefaultValue();
              }
              serviceParameter.setValue(parameterValue);
            });
  }

  private void processUdasForUpdate(List<VOUda> subscriptionUdas, Map<String, String> requestUdas) {

    requestUdas
        .keySet()
        .forEach(
            udaKey -> {
              Optional<VOUda> currentUda =
                  subscriptionUdas.stream()
                      .filter(uda -> uda.getUdaDefinition().getUdaId().equals(udaKey))
                      .findFirst();
              String requestedUda = requestUdas.get(udaKey);
              if (currentUda.isPresent()) {
                if (currentUda.get().getUdaDefinition().getConfigurationType().isMandatory()) {
                  validator.validateNotBlank(udaKey + " uda", requestedUda);
                }
                currentUda.get().setUdaValue(requestedUda);
              }
            });
  }

  private void processParametersForUpdate(
      List<VOParameter> subscriptionParameters, Map<String, String> requestParameters) {

    requestParameters
        .keySet()
        .forEach(
            parameterKey -> {
              Optional<VOParameter> currentParameter =
                  subscriptionParameters.stream()
                      .filter(
                          parameter ->
                              parameter
                                  .getParameterDefinition()
                                  .getParameterId()
                                  .equals(parameterKey))
                      .findFirst();
              if (currentParameter.isPresent()) {
                String requestedParameter = requestParameters.get(parameterKey);
                parameterValidator.validate(
                    currentParameter.get().getParameterDefinition(), requestedParameter);
                currentParameter.get().setValue(requestedParameter);
              }
            });
  }

  private VOPaymentInfo preparePaymentInfo(String paymentInfoId) throws ObjectNotFoundException {
    Optional<VOPaymentInfo> foundPayment =
        as.getPaymentInfos().stream().filter(info -> info.getId().equals(paymentInfoId)).findAny();

    if (!foundPayment.isPresent()) {
      throw new ObjectNotFoundException(
          DomainObjectException.ClassEnum.PAYMENT_INFO, String.valueOf(paymentInfoId));
    }
    return foundPayment.get();
  }

  private VOBillingContact prepareBillingContact(String billingContactId)
      throws ObjectNotFoundException {
    Optional<VOBillingContact> foundContact =
        as.getBillingContacts().stream()
            .filter(contact -> contact.getId().equals(billingContactId))
            .findAny();

    if (!foundContact.isPresent()) {
      throw new ObjectNotFoundException(
          DomainObjectException.ClassEnum.BILLING_CONTACT, String.valueOf(billingContactId));
    }
    return foundContact.get();
  }
}
