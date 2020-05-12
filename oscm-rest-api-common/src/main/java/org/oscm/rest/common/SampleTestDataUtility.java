/**
 * *****************************************************************************
 *
 * <p>Copyright FUJITSU LIMITED 2019
 *
 * <p>Creation Date: June 28, 2019
 *
 * <p>*****************************************************************************
 */
package org.oscm.rest.common;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import javax.ws.rs.core.*;
import lombok.Generated;
import org.oscm.internal.types.enumtypes.OrganizationRoleType;
import org.oscm.internal.types.enumtypes.Salutation;
import org.oscm.internal.types.enumtypes.UserAccountStatus;
import org.oscm.internal.types.enumtypes.UserRoleType;
import org.oscm.internal.vo.*;
import org.oscm.rest.common.representation.*;
import org.oscm.rest.common.requestparameters.*;

/** Utility class to create some sample data for testing purposes */
@Generated
public class SampleTestDataUtility {

  /**
   * Creates sample URIInfo object
   *
   * @return sample URIInfo
   */
  public static UriInfo createUriInfo() {
    return new UriInfo() {
      @Override
      public String getPath() {
        return null;
      }

      @Override
      public String getPath(boolean b) {
        return null;
      }

      @Override
      public List<PathSegment> getPathSegments() {
        return null;
      }

      @Override
      public List<PathSegment> getPathSegments(boolean b) {
        return null;
      }

      @Override
      public URI getRequestUri() {
        return null;
      }

      @Override
      public UriBuilder getRequestUriBuilder() {
        return null;
      }

      @Override
      public URI getAbsolutePath() {
        return null;
      }

      @Override
      public UriBuilder getAbsolutePathBuilder() {
        return null;
      }

      @Override
      public URI getBaseUri() {
        return null;
      }

      @Override
      public UriBuilder getBaseUriBuilder() {
        return null;
      }

      @Override
      public MultivaluedMap<String, String> getPathParameters() {
        return new MultivaluedHashMap<String, String>() {
          {
            put("version", Collections.singletonList("v1"));
          }
        };
      }

      @Override
      public MultivaluedMap<String, String> getPathParameters(boolean b) {
        return null;
      }

      @Override
      public MultivaluedMap<String, String> getQueryParameters() {
        return null;
      }

      @Override
      public MultivaluedMap<String, String> getQueryParameters(boolean b) {
        return null;
      }

      @Override
      public List<String> getMatchedURIs() {
        return null;
      }

      @Override
      public List<String> getMatchedURIs(boolean b) {
        return null;
      }

      @Override
      public List<Object> getMatchedResources() {
        return null;
      }

      @Override
      public URI resolve(URI uri) {
        return null;
      }

      @Override
      public URI relativize(URI uri) {
        return null;
      }
    };
  }

  public static AccountParameters createAccountParameters() {
    AccountParameters parameters = new AccountParameters();
    parameters.setId(TestContants.LONG_VALUE);
    return parameters;
  }

  public static BillingContactRepresentation createBillingContactRepresentation() {
    return new BillingContactRepresentation();
  }

  public static PaymentInfoRepresentation createPaymentInfoRepresentation() {
    return new PaymentInfoRepresentation();
  }

  public static VOBillingContact createBillingContactVO() {
    VOBillingContact vo = new VOBillingContact();
    vo.setKey(TestContants.LONG_VALUE);
    vo.setId("con@tact.com");
    return vo;
  }

  public static VOPaymentInfo createPaymentInfoVO() {
    VOPaymentInfo vo = new VOPaymentInfo();
    vo.setKey(TestContants.LONG_VALUE);
    vo.setPaymentType(new VOPaymentType());
    return vo;
  }

  public static VOOperatorOrganization createOperatorOrgVO() {
    VOOperatorOrganization vo = new VOOperatorOrganization();
    vo.setOrganizationId(TestContants.STRING_VALUE);
    return vo;
  }

  public static AccountRepresentation createAccountRepresentation(
      Optional<TestContants.OrganizationRegistrationMode> mode) {
    AccountRepresentation representation = new AccountRepresentation();
    representation.setOrganization(new OrganizationRepresentation());
    representation.setUser(new UserRepresentation());

    if (mode.isPresent()) {
      if (!TestContants.OrganizationRegistrationMode.KNOWN_CUSTOMER.equals(mode)) {
        representation.setOrganizationRoles(
            new OrganizationRoleType[] {OrganizationRoleType.SUPPLIER});
      }
      if (TestContants.OrganizationRegistrationMode.SELF_REGISTRATION.equals(mode))
        representation.setPassword(TestContants.STRING_VALUE);
    }

    return representation;
  }

  public static CreateOrganizationRepresentation createOrgCreateRepresentation() {
    CreateOrganizationRepresentation representation = new CreateOrganizationRepresentation();
    representation.setOrganization(new OrganizationRepresentation());
    representation.setUser(new UserRepresentation());
    representation.setOrganizationRoles(new OrganizationRoleType[] {OrganizationRoleType.SUPPLIER});
    return representation;
  }

  public static OrganizationRepresentation createOrgRepresentation() {
    OrganizationRepresentation representation = new OrganizationRepresentation();
    representation.setOrganizationId(TestContants.STRING_VALUE);
    return representation;
  }

  public static PaymentInfoRepresentation createPIRepresentation() {
    PaymentInfoRepresentation representation = new PaymentInfoRepresentation();
    return representation;
  }

  public static EventParameters createEventParameters() {
    return new EventParameters();
  }

  public static EventRepresentation createEventRepresentation(boolean hasSubscriptionKeySet) {
    EventRepresentation representation = new EventRepresentation();
    representation.setETag(TestContants.LONG_VALUE);
    if (hasSubscriptionKeySet) representation.setSubscriptionKey(TestContants.LONG_VALUE);
    return representation;
  }

  public static UserRepresentation createUserRepresentation() {
    UserRepresentation representation = new UserRepresentation();
    representation.setAdditionalName(TestContants.STRING_VALUE);
    representation.setAddress(TestContants.STRING_VALUE);
    representation.setEmail(TestContants.STRING_VALUE);
    representation.setFirstName(TestContants.STRING_VALUE);
    representation.setId(TestContants.LONG_VALUE);
    representation.setLastName(TestContants.STRING_VALUE);
    representation.setLocale(TestContants.STRING_VALUE);
    representation.setOrganizationId(TestContants.STRING_VALUE);
    representation.setOrganizationRoles(Sets.newHashSet(OrganizationRoleType.SUPPLIER));
    representation.setPhone(TestContants.STRING_NUM_VALUE);
    representation.setRealmUserId(TestContants.STRING_VALUE);
    representation.setRemoteLdapActive(true);
    representation.setSalutation(Salutation.MR);
    representation.setStatus(UserAccountStatus.ACTIVE);
    representation.setUserId(TestContants.STRING_VALUE);
    representation.setUserRoles(Sets.newHashSet(UserRoleType.SERVICE_MANAGER));
    representation.setETag(TestContants.LONG_VALUE);
    return representation;
  }

  public static UserCreateRepresentation createUserCreateRepresentation() {
    UserCreateRepresentation representation = new UserCreateRepresentation();
    representation.setAddress(TestContants.STRING_VALUE);
    representation.setEmail(TestContants.STRING_VALUE);
    representation.setFirstName(TestContants.STRING_VALUE);
    representation.setLastName(TestContants.STRING_VALUE);
    representation.setLocale(TestContants.STRING_VALUE);
    representation.setOrganizationId(TestContants.STRING_VALUE);
    representation.setPhone(TestContants.STRING_NUM_VALUE);
    representation.setSalutation(Salutation.MR);
    representation.setUserId(TestContants.STRING_VALUE);
    representation.setUserRoles(Sets.newHashSet(UserRoleType.SERVICE_MANAGER));
    return representation;
  }

  public static UserParameters createUserParameters() {
    UserParameters parameters = new UserParameters();
    parameters.setUserId(TestContants.STRING_NUM_VALUE);
    return parameters;
  }

  public static OnBehalfUserRepresentation createOBUserRepresentation() {
    OnBehalfUserRepresentation userRepresentation = new OnBehalfUserRepresentation();
    userRepresentation.setUserId(TestContants.STRING_VALUE);
    return userRepresentation;
  }

  public static RolesRepresentation createRolesRepresentation() {
    return new RolesRepresentation();
  }

  public static VOUserDetails createVOUserDetails() {
    VOUserDetails voUserDetails = new VOUserDetails();
    voUserDetails.setAdditionalName(TestContants.STRING_VALUE);
    voUserDetails.setAddress(TestContants.STRING_VALUE);
    voUserDetails.setEMail(TestContants.STRING_VALUE);
    voUserDetails.setFirstName(TestContants.STRING_VALUE);
    voUserDetails.setKey(TestContants.LONG_VALUE);
    voUserDetails.setLastName(TestContants.STRING_VALUE);
    voUserDetails.setLocale(TestContants.STRING_VALUE);
    voUserDetails.setOrganizationId(TestContants.STRING_VALUE);
    voUserDetails.setOrganizationRoles(Sets.newHashSet(OrganizationRoleType.SUPPLIER));
    voUserDetails.setPhone(TestContants.STRING_NUM_VALUE);
    voUserDetails.setRealmUserId(TestContants.STRING_VALUE);
    voUserDetails.setRemoteLdapActive(true);
    voUserDetails.setSalutation(Salutation.MR);
    voUserDetails.setStatus(UserAccountStatus.ACTIVE);
    voUserDetails.setVersion(TestContants.INTEGER_VALUE);
    voUserDetails.setUserId(TestContants.STRING_VALUE);
    voUserDetails.setUserRoles(Sets.newHashSet(UserRoleType.SERVICE_MANAGER));
    return voUserDetails;
  }

  public static MarketplaceParameters createMarketplaceParameters() {
    MarketplaceParameters parameters = new MarketplaceParameters();
    parameters.setId(TestContants.LONG_VALUE);
    parameters.setVersion(TestContants.INTEGER_VALUE);
    parameters.validateETag();
    return parameters;
  }

  public static EntryRepresentation createEntryRepresentation() {
    EntryRepresentation representation = new EntryRepresentation();
    representation.setId(TestContants.LONG_VALUE);
    return representation;
  }

  public static VOServiceDetails createVOServiceDetails() {
    VOServiceDetails voServiceDetails = new VOServiceDetails();
    VOTechnicalService voTechnicalService = new VOTechnicalService();
    voServiceDetails.setTechnicalService(voTechnicalService);
    voServiceDetails.setPriceModel(new VOPriceModel());
    return voServiceDetails;
  }

  public static MarketplaceRepresentation createMarketplaceRepresentation() {
    MarketplaceRepresentation marketplaceRepresentation = new MarketplaceRepresentation();
    marketplaceRepresentation.setMarketplaceId(TestContants.STRING_VALUE);
    return marketplaceRepresentation;
  }

  public static VOMarketplace createVOMarketplace() {
    return new VOMarketplace();
  }

  public static OperationParameters createOperationParameters() {
    OperationParameters parameters = new OperationParameters();
    parameters.setId(TestContants.LONG_VALUE);
    return parameters;
  }

  public static SettingRepresentation createSettingRepresentation() {
    return new SettingRepresentation();
  }

  public static VOConfigurationSetting createVOConfigurationSetting() {
    return new VOConfigurationSetting();
  }

  public static ServiceParameters createServiceParameters() {
    ServiceParameters parameters = new ServiceParameters();
    parameters.setId(TestContants.LONG_VALUE);
    parameters.setOrgKey(TestContants.LONG_VALUE);
    return parameters;
  }

  public static VOOrganization createVOOrganization() {
    VOOrganization vo = new VOOrganization();
    vo.setOrganizationId("orgid");
    vo.setKey(123);
    return vo;
  }

  public static TechnicalServiceRepresentation createTSRepresentation() {
    return new TechnicalServiceRepresentation();
  }

  public static ServiceRepresentation createServiceRepresentation() {
    ServiceRepresentation serviceRepresentation = new ServiceRepresentation();
    serviceRepresentation.setVersion(TestContants.INTEGER_VALUE);
    serviceRepresentation.setETag(TestContants.LONG_VALUE);
    return serviceRepresentation;
  }

  public static StatusRepresentation createStatusRepresentation() {
    return new StatusRepresentation();
  }

  public static ServiceDetailsRepresentation createServiceDetailsRepresentation(
      VOServiceDetails voServiceDetails) {
    ServiceDetailsRepresentation representation;
    if (voServiceDetails == null) representation = new ServiceDetailsRepresentation();
    else representation = new ServiceDetailsRepresentation(voServiceDetails);
    representation.setTechnicalService(new TechnicalServiceRepresentation());
    return representation;
  }

  public static ServiceCreateRepresentation createServiceCreateRepresentation() {
    ServiceCreateRepresentation representation = new ServiceCreateRepresentation();
    representation.setTechnicalServiceId("technicalServiceId");
    representation.setServiceId("serviceId");
    return representation;
  }

  public static PriceModelRepresentation createPriceModelRepresentation() {
    return new PriceModelRepresentation();
  }

  public static VOUsageLicense createVOUsageLicense() {
    VOUsageLicense voUsageLicense = new VOUsageLicense();
    VORoleDefinition voRoleDefinition = new VORoleDefinition();
    voUsageLicense.setRoleDefinition(voRoleDefinition);
    VOUser voUser = new VOUser();
    voUsageLicense.setUser(voUser);
    return voUsageLicense;
  }

  public static SubscriptionParameters createSubscriptionParameters() {
    SubscriptionParameters parameters = new SubscriptionParameters();
    parameters.setId(TestContants.LONG_VALUE);
    parameters.setLicKey(TestContants.LONG_VALUE);
    return parameters;
  }

  public static SubscriptionRepresentation createSubscriptionRepresentation() {
    return new SubscriptionRepresentation();
  }

  public static UsageLicenseRepresentation createUsageLicenseRepresentation(
      VOUsageLicense voUsageLicense) {
    UsageLicenseRepresentation usageLicenseRepresentation;
    if (voUsageLicense != null)
      usageLicenseRepresentation = new UsageLicenseRepresentation(voUsageLicense);
    else usageLicenseRepresentation = new UsageLicenseRepresentation();

    UserRepresentation userRepresentation = new UserRepresentation();
    userRepresentation.setUserId(TestContants.STRING_VALUE);
    usageLicenseRepresentation.setUser(userRepresentation);

    return usageLicenseRepresentation;
  }

  public static VOSubscriptionDetails createVOSubscriptionDetails() {
    VOUsageLicense voUsageLicense = new VOUsageLicense();
    voUsageLicense.setRoleDefinition(new VORoleDefinition());

    VOUser voUser = new VOUser();
    voUser.setUserId(TestContants.STRING_VALUE);
    voUsageLicense.setUser(voUser);

    voUsageLicense.setKey(TestContants.LONG_VALUE);

    VOSubscriptionDetails voSubscriptionDetails = new VOSubscriptionDetails();
    voSubscriptionDetails.setUsageLicenses(Lists.newArrayList(voUsageLicense));

    VOService voService = new VOService();
    voSubscriptionDetails.setSubscribedService(voService);
    VOPriceModel voPriceModel = new VOPriceModel();
    voSubscriptionDetails.setPriceModel(voPriceModel);
    return voSubscriptionDetails;
  }

  public static SubscriptionDetailsRepresentation createSubscriptionDetailsRepresentation(
      VOSubscriptionDetails voSubscriptionDetails) {
    return new SubscriptionDetailsRepresentation(voSubscriptionDetails);
  }

  public static SubscriptionCreationRepresentation createSubscriptionCreationRepresentation() {
    SubscriptionCreationRepresentation subscriptionCreationRepresentation =
        new SubscriptionCreationRepresentation();
    subscriptionCreationRepresentation.setService(new ServiceRepresentation());
    subscriptionCreationRepresentation.setUdas(Lists.newArrayList(new UdaRepresentation()));
    return subscriptionCreationRepresentation;
  }

  public static VOUserSubscription createVOUserSubscription() {
    VOUserSubscription voUserSubscription = new VOUserSubscription();

    VOUsageLicense voUsageLicense = new VOUsageLicense();
    voUsageLicense.setRoleDefinition(new VORoleDefinition());
    voUserSubscription.setLicense(voUsageLicense);

    voUsageLicense.setUser(new VOUser());

    return voUserSubscription;
  }

  public static VOSubscription createVOSubscription() {
    return new VOSubscription();
  }
}
