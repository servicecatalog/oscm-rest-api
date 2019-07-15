/**
 * *****************************************************************************
 *
 * <p>Copyright FUJITSU LIMITED 2019
 *
 * <p>Creation Date: 10-04-2019
 *
 * <p>*****************************************************************************
 */
package org.oscm.rest.common.representation;

import com.google.common.collect.Sets;
import org.junit.jupiter.api.Test;
import org.oscm.internal.types.enumtypes.OrganizationRoleType;
import org.oscm.internal.types.enumtypes.Salutation;
import org.oscm.internal.types.enumtypes.UserAccountStatus;
import org.oscm.internal.types.enumtypes.UserRoleType;
import org.oscm.internal.vo.VOUserDetails;
import org.oscm.rest.common.SampleTestDataUtility;

import static org.assertj.core.api.Assertions.assertThat;

public class UserRepresentationTest {

  @Test
  public void shouldUpdateVOUserDetails() {
    UserRepresentation representation = SampleTestDataUtility.createUserRepresentation();

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
    VOUserDetails voUserDetails = SampleTestDataUtility.createVOUserDetails();

    UserRepresentation representation = new UserRepresentation(voUserDetails);
    representation.convert();
    assertThat(representation)
        .extracting(UserRepresentation::getAdditionalName)
        .isEqualTo(voUserDetails.getAdditionalName());
    assertThat(representation)
        .extracting(UserRepresentation::getAddress)
        .isEqualTo(voUserDetails.getAddress());
    assertThat(representation)
        .extracting(UserRepresentation::getEmail)
        .isEqualTo(voUserDetails.getEMail());
    assertThat(representation)
        .extracting(UserRepresentation::getFirstName)
        .isEqualTo(voUserDetails.getFirstName());
    assertThat(representation)
        .extracting(UserRepresentation::getId)
        .isEqualTo((long) voUserDetails.getKey());
    assertThat(representation)
        .extracting(UserRepresentation::getLastName)
        .isEqualTo(voUserDetails.getLastName());
    assertThat(representation)
        .extracting(UserRepresentation::getLocale)
        .isEqualTo(voUserDetails.getLocale());
    assertThat(representation)
        .extracting(UserRepresentation::getOrganizationId)
        .isEqualTo(voUserDetails.getOrganizationId());
    assertThat(representation)
        .extracting(UserRepresentation::getOrganizationRoles)
        .isEqualTo(voUserDetails.getOrganizationRoles());
    assertThat(representation)
        .extracting(UserRepresentation::getPhone)
        .isEqualTo(voUserDetails.getPhone());
    assertThat(representation)
        .extracting(UserRepresentation::getRealmUserId)
        .isEqualTo(voUserDetails.getRealmUserId());
    assertThat(representation)
        .extracting(UserRepresentation::isRemoteLdapActive)
        .isEqualTo(voUserDetails.isRemoteLdapActive());
    assertThat(representation)
        .extracting(UserRepresentation::getSalutation)
        .isEqualTo(voUserDetails.getSalutation());
    assertThat(representation)
        .extracting(UserRepresentation::getStatus)
        .isEqualTo(voUserDetails.getStatus());
    assertThat(representation)
        .extracting(UserRepresentation::getETag)
        .isEqualTo((long) voUserDetails.getVersion());
    assertThat(representation)
        .extracting(UserRepresentation::getUserId)
        .isEqualTo(voUserDetails.getUserId());
    assertThat(representation)
        .extracting(UserRepresentation::getUserRoles)
        .isEqualTo(voUserDetails.getUserRoles());
  }
}
