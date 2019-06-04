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

import org.oscm.internal.intf.SubscriptionService;
import org.oscm.internal.intf.SubscriptionServiceInternal;
import org.oscm.internal.types.enumtypes.PerformanceHint;
import org.oscm.internal.vo.VOSubscription;
import org.oscm.internal.vo.VOSubscriptionDetails;
import org.oscm.internal.vo.VOUser;
import org.oscm.rest.common.RepresentationCollection;
import org.oscm.rest.common.RestBackend;
import org.oscm.rest.subscription.data.SubscriptionCreationRepresentation;
import org.oscm.rest.subscription.data.SubscriptionDetailsRepresentation;
import org.oscm.rest.subscription.data.SubscriptionRepresentation;
import org.oscm.rest.subscription.data.UdaRepresentation;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

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
      return SubscriptionRepresentation.toCollection(subs);
    };
  }

  public RestBackend.Delete<SubscriptionParameters> delete() {
    return params -> ss.unsubscribeFromService(params.getId());
  }

  public RestBackend.Get<SubscriptionDetailsRepresentation, SubscriptionParameters> get() {
    return params -> {
      VOSubscriptionDetails sub = ss.getSubscriptionDetails(params.getId().longValue());
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
      return Long.valueOf(sub.getKey());
    };
  }

  public RestBackend.Put<SubscriptionCreationRepresentation, SubscriptionParameters> put() {
    return (content, params) -> {
      VOSubscription result =
          ss.modifySubscription(
              content.getVO(), content.getVOService().getParameters(), content.getUdas());
      return result != null;
    };
  }
}
