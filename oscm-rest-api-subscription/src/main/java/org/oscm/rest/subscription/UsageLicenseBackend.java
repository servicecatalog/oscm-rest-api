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
import org.oscm.internal.vo.VOSubscriptionDetails;
import org.oscm.internal.vo.VOUsageLicense;
import org.oscm.rest.common.RepresentationCollection;
import org.oscm.rest.common.RestBackend;
import org.oscm.rest.subscription.data.UsageLicenseRepresentation;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.Collections;
import java.util.List;

@Stateless
public class UsageLicenseBackend {

  @EJB SubscriptionService ss;

  public RestBackend.Post<UsageLicenseRepresentation, SubscriptionParameters> post() {
    return new RestBackend.Post<UsageLicenseRepresentation, SubscriptionParameters>() {

      @Override
      public Object post(UsageLicenseRepresentation content, SubscriptionParameters params)
          throws Exception {
        VOSubscriptionDetails sub = ss.getSubscriptionDetails(params.getId().longValue());
        boolean added =
            ss.addRevokeUser(
                sub.getSubscriptionId(), Collections.singletonList(content.getVO()), null);
        Long licKey = null;
        if (added) {
          List<VOUsageLicense> lics =
              ss.getSubscriptionDetails(params.getId().longValue()).getUsageLicenses();
          for (VOUsageLicense lic : lics) {
            if (lic.getUser().getUserId().equals(content.getUser().getUserId())) {
              licKey = Long.valueOf(lic.getKey());
              break;
            }
          }
        }
        return licKey;
      }
    };
  }

  public RestBackend.GetCollection<UsageLicenseRepresentation, SubscriptionParameters>
      getCollection() {
    return new RestBackend.GetCollection<UsageLicenseRepresentation, SubscriptionParameters>() {

      @Override
      public RepresentationCollection<UsageLicenseRepresentation> getCollection(
          SubscriptionParameters params) throws Exception {
        VOSubscriptionDetails sub = ss.getSubscriptionDetails(params.getId().longValue());
        List<UsageLicenseRepresentation> lics =
            UsageLicenseRepresentation.convert(sub.getUsageLicenses());
        return new RepresentationCollection<UsageLicenseRepresentation>(lics);
      }
    };
  }

  public RestBackend.Put<UsageLicenseRepresentation, SubscriptionParameters> put() {
    return new RestBackend.Put<UsageLicenseRepresentation, SubscriptionParameters>() {

      @Override
      public boolean put(UsageLicenseRepresentation content, SubscriptionParameters params)
          throws Exception {
        VOSubscriptionDetails sub = ss.getSubscriptionDetails(params.getId().longValue());
        VOUsageLicense vo = content.getVO();
        vo.setKey(params.getLicKey().longValue());
        return ss.addRevokeUser(sub.getSubscriptionId(), Collections.singletonList(vo), null);
      }
    };
  }

  public RestBackend.Delete<SubscriptionParameters> delete() {
    return new RestBackend.Delete<SubscriptionParameters>() {

      @Override
      public boolean delete(SubscriptionParameters params) throws Exception {
        VOSubscriptionDetails sub = ss.getSubscriptionDetails(params.getId().longValue());
        List<VOUsageLicense> lics = sub.getUsageLicenses();
        long licKey = params.getLicKey().longValue();
        boolean result = true;
        for (VOUsageLicense lic : lics) {
          if (lic.getKey() == licKey) {
            result =
                ss.addRevokeUser(
                    sub.getSubscriptionId(), null, Collections.singletonList(lic.getUser()));
            break;
          }
        }
        return result;
      }
    };
  }
}
