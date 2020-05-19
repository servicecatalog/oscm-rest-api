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

import org.oscm.internal.intf.IdentityService;
import org.oscm.internal.intf.SubscriptionService;
import org.oscm.internal.vo.VOSubscriptionDetails;
import org.oscm.internal.vo.VOUsageLicense;
import org.oscm.internal.vo.VOUser;
import org.oscm.rest.common.PostResponseBody;
import org.oscm.rest.common.RestBackend;
import org.oscm.rest.common.errorhandling.ErrorResponse;
import org.oscm.rest.common.representation.RepresentationCollection;
import org.oscm.rest.common.representation.UsageLicenseCreationRepresentation;
import org.oscm.rest.common.representation.UsageLicenseRepresentation;
import org.oscm.rest.common.requestparameters.SubscriptionParameters;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Stateless
public class UsageLicenseBackend {

  @EJB SubscriptionService ss;
  @EJB IdentityService is;

  public RestBackend.Post<UsageLicenseCreationRepresentation, SubscriptionParameters> post() {
    return (content, params) -> {
      long subscriptionKey = params.getId();
      String userId = content.getUserId();

      VOSubscriptionDetails subscription = ss.getSubscriptionDetails(subscriptionKey);
      List<VOUsageLicense> usageLicenses = subscription.getUsageLicenses();

      Optional<VOUsageLicense> foundLicense =
          usageLicenses.stream()
              .filter(license -> license.getUser().getUserId().equals(userId))
              .findFirst();

      if (foundLicense.isPresent()) {
        throw new BadRequestException(
            ErrorResponse.provider()
                .build()
                .badRequest("User " + userId + " already assigned to the subscription"));
      }

      VOUser voUser = new VOUser();
      voUser.setUserId(userId);
      VOUser user = is.getUser(voUser);
      VOUsageLicense usageLicense = new VOUsageLicense();
      usageLicense.setUser(user);

      boolean added =
          ss.addRevokeUser(
              subscription.getSubscriptionId(), Collections.singletonList(usageLicense), null);
      long licenseKey = 0;

      if (added) {
        List<VOUsageLicense> licenses =
            ss.getSubscriptionDetails(subscriptionKey).getUsageLicenses();
        for (VOUsageLicense lic : licenses) {
          if (lic.getUser().getUserId().equals(userId)) {
            licenseKey = lic.getKey();
            break;
          }
        }
      }
      return PostResponseBody.of().createdObjectId(String.valueOf(licenseKey)).build();
    };
  }

  public RestBackend.GetCollection<UsageLicenseRepresentation, SubscriptionParameters>
      getCollection() {
    return params -> {
      List<VOUsageLicense> licenses = ss.getSubscriptionDetails(params.getId()).getUsageLicenses();
      List<UsageLicenseRepresentation> lics = UsageLicenseRepresentation.convert(licenses);
      return new RepresentationCollection<>(lics);
    };
  }

  public RestBackend.Delete<SubscriptionParameters> delete() {
    return params -> {
      VOSubscriptionDetails sub = ss.getSubscriptionDetails(params.getId());
      List<VOUsageLicense> licenses = sub.getUsageLicenses();

      Long licenseKey = params.getLicKey();
      Optional<VOUsageLicense> foundLicense =
          licenses.stream().filter(license -> licenseKey.equals(license.getKey())).findFirst();

      if (foundLicense.isPresent()) {
        return ss.addRevokeUser(
            sub.getSubscriptionId(), null, Collections.singletonList(foundLicense.get().getUser()));
      } else {

        throw new NotFoundException(
            ErrorResponse.provider()
                .build()
                .notFound(
                    "Usage license (key:"
                        + licenseKey
                        + ") does not exist for given subscription ("
                        + sub.getSubscriptionId()
                        + ")"));
      }
    };
  }
}
