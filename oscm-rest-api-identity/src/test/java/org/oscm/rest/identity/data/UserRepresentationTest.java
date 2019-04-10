/**
 * *****************************************************************************
 *
 * <p>Copyright FUJITSU LIMITED 2019
 *
 * <p>Creation Date: 10-04-2019
 *
 * <p>*****************************************************************************
 */
package org.oscm.rest.identity.data;

import static org.assertj.core.api.Assertions.assertThat;

import com.google.common.collect.Sets;
import org.junit.jupiter.api.Test;
import org.oscm.internal.types.enumtypes.OrganizationRoleType;
import org.oscm.internal.types.enumtypes.Salutation;
import org.oscm.internal.types.enumtypes.UserAccountStatus;
import org.oscm.internal.types.enumtypes.UserRoleType;
import org.oscm.internal.vo.VOUserDetails;

public class UserRepresentationTest {

  @Test
  public void shouldUpdateVOUserDetails() {
    UserRepresentation representation = new UserRepresentation();
    representation.setAdditionalName("additionalName");
    representation.setAddress("address");
    representation.setEmail("email");
    representation.setFirstName("fName");
    representation.setId(1234L);
    representation.setLastName("lName");
    representation.setLocale("en_US");
    representation.setOrganizationId("orgId");
    representation.setOrganizationRoles(Sets.newHashSet(OrganizationRoleType.SUPPLIER));
    representation.setPhone("111111111");
    representation.setRealmUserId("realmUserId");
    representation.setRemoteLdapActive(true);
    representation.setSalutation(Salutation.MR);
    representation.setStatus(UserAccountStatus.ACTIVE);
    representation.setUserId("userId");
    representation.setUserRoles(Sets.newHashSet(UserRoleType.SERVICE_MANAGER));
    representation.setETag(4567L);

    representation.update();
    VOUserDetails result = representation.getVO();

    assertThat(result).isNotNull();
    assertThat(result)
        .extracting(VOUserDetails::getAdditionalName)
        .isEqualTo(representation.getAdditionalName());
    assertThat(result).extracting(VOUserDetails::getAddress).isEqualTo(representation.getAddress());
    assertThat(result).extracting(VOUserDetails::getEMail).isEqualTo(representation.getEmail());
    assertThat(result)
        .extracting(VOUserDetails::getAdditionalName)
        .isEqualTo(representation.getAdditionalName());
    assertThat(result)
        .extracting(VOUserDetails::getFirstName)
        .isEqualTo(representation.getFirstName());
    assertThat(result)
        .extracting(VOUserDetails::getKey)
        .isEqualTo(representation.getId().longValue());
    assertThat(result)
        .extracting(VOUserDetails::getLastName)
        .isEqualTo(representation.getLastName());
    assertThat(result).extracting(VOUserDetails::getLocale).isEqualTo(representation.getLocale());
    assertThat(result)
        .extracting(VOUserDetails::getOrganizationId)
        .isEqualTo(representation.getOrganizationId());
    assertThat(result)
        .extracting(VOUserDetails::getOrganizationRoles)
        .isEqualTo(representation.getOrganizationRoles());
    assertThat(result).extracting(VOUserDetails::getPhone).isEqualTo(representation.getPhone());
    assertThat(result)
        .extracting(VOUserDetails::getRealmUserId)
        .isEqualTo(representation.getRealmUserId());
    assertThat(result)
        .extracting(VOUserDetails::isRemoteLdapActive)
        .isEqualTo(representation.isRemoteLdapActive());
    assertThat(result)
        .extracting(VOUserDetails::getSalutation)
        .isEqualTo(representation.getSalutation());
    assertThat(result).extracting(VOUserDetails::getStatus).isEqualTo(representation.getStatus());
    assertThat(result)
        .extracting(VOUserDetails::getUserRoles)
        .isEqualTo(representation.getUserRoles());
    assertThat(result)
        .extracting(VOUserDetails::getVersion)
        .isEqualTo(representation.getETag().intValue());
  }

  @Test
  public void shouldConvertVOToRepresentation() {
    VOUserDetails userDetails = new VOUserDetails();
    userDetails.setAdditionalName("additionalName");
    userDetails.setAddress("address");
    userDetails.setEMail("email");
    userDetails.setFirstName("fName");
    userDetails.setKey(1234L);
    userDetails.setLastName("lName");
    userDetails.setLocale("en_US");
    userDetails.setOrganizationId("orgId");
    userDetails.setOrganizationRoles(Sets.newHashSet(OrganizationRoleType.SUPPLIER));
    userDetails.setPhone("111111111");
    userDetails.setRealmUserId("rUserId");
    userDetails.setRemoteLdapActive(true);
    userDetails.setSalutation(Salutation.MR);
    userDetails.setStatus(UserAccountStatus.ACTIVE);
    userDetails.setVersion(456);
    userDetails.setUserId("userId");
    userDetails.setUserRoles(Sets.newHashSet(UserRoleType.SERVICE_MANAGER));

    UserRepresentation representation = new UserRepresentation(userDetails);
    representation.convert();
    assertThat(representation)
        .extracting(UserRepresentation::getAdditionalName)
        .isEqualTo(userDetails.getAdditionalName());
    assertThat(representation)
        .extracting(UserRepresentation::getAddress)
        .isEqualTo(userDetails.getAddress());
    assertThat(representation)
        .extracting(UserRepresentation::getEmail)
        .isEqualTo(userDetails.getEMail());
    assertThat(representation)
        .extracting(UserRepresentation::getFirstName)
        .isEqualTo(userDetails.getFirstName());
    assertThat(representation)
        .extracting(UserRepresentation::getId)
        .isEqualTo((long) userDetails.getKey());
    assertThat(representation)
        .extracting(UserRepresentation::getLastName)
        .isEqualTo(userDetails.getLastName());
    assertThat(representation)
        .extracting(UserRepresentation::getLocale)
        .isEqualTo(userDetails.getLocale());
    assertThat(representation)
        .extracting(UserRepresentation::getOrganizationId)
        .isEqualTo(userDetails.getOrganizationId());
    assertThat(representation)
        .extracting(UserRepresentation::getOrganizationRoles)
        .isEqualTo(userDetails.getOrganizationRoles());
    assertThat(representation)
        .extracting(UserRepresentation::getPhone)
        .isEqualTo(userDetails.getPhone());
    assertThat(representation)
        .extracting(UserRepresentation::getRealmUserId)
        .isEqualTo(userDetails.getRealmUserId());
    assertThat(representation)
        .extracting(UserRepresentation::isRemoteLdapActive)
        .isEqualTo(userDetails.isRemoteLdapActive());
    assertThat(representation)
        .extracting(UserRepresentation::getSalutation)
        .isEqualTo(userDetails.getSalutation());
    assertThat(representation)
        .extracting(UserRepresentation::getStatus)
        .isEqualTo(userDetails.getStatus());
    assertThat(representation)
        .extracting(UserRepresentation::getETag)
        .isEqualTo((long) userDetails.getVersion());
    assertThat(representation)
        .extracting(UserRepresentation::getUserId)
        .isEqualTo(userDetails.getUserId());
    assertThat(representation)
        .extracting(UserRepresentation::getUserRoles)
        .isEqualTo(userDetails.getUserRoles());
  }
}
