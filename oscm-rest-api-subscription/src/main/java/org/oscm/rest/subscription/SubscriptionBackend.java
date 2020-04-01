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

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.google.common.collect.Lists;
import org.oscm.internal.intf.SubscriptionService;
import org.oscm.internal.intf.SubscriptionServiceInternal;
import org.oscm.internal.types.enumtypes.PerformanceHint;
import org.oscm.internal.vo.VOSubscription;
import org.oscm.internal.vo.VOSubscriptionDetails;
import org.oscm.internal.vo.VOUser;
import org.oscm.rest.common.PostResponseBody;
import org.oscm.rest.common.RestBackend;
import org.oscm.rest.common.representation.*;
import org.oscm.rest.common.requestparameters.SubscriptionParameters;

@Stateless
public class SubscriptionBackend {

  @EJB SubscriptionServiceInternal ssi;

  @EJB SubscriptionService ss;

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
              subs.stream()
                      .map(SubscriptionRepresentation::new)
                      .collect(Collectors.toList());

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
      content.getService().update();
      content.getUdaRepresentations().forEach(UdaRepresentation::update);

      VOSubscription sub =
          ss.subscribeToService(
              content.getVO(),
              content.getVOService(),
              content.getUsageLicenses(),
              content.getPaymentInfo(),
              content.getBillingContact(),
              content.getUdas());
      if (sub == null) {
        return null;
      }

      return PostResponseBody.of()
          .createdObjectId(String.valueOf(sub.getKey()))
          .createdObjectName(sub.getSubscriptionId())
          .build();
    };
  }

  public RestBackend.Put<SubscriptionCreationRepresentation, SubscriptionParameters> put() {
    return (content, params) -> {
      content.getService().update();
      content.getUdaRepresentations().forEach(UdaRepresentation::update);

      VOSubscription result =
          ss.modifySubscription(
              content.getVO(), content.getVOService().getParameters(), content.getUdas());
      return result != null;
    };
  }
}
