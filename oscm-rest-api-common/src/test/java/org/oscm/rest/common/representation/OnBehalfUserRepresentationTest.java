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

import org.junit.jupiter.api.Test;
import org.oscm.internal.vo.VOUserDetails;

import static org.assertj.core.api.Assertions.assertThat;

public class OnBehalfUserRepresentationTest {

  @Test
  public void shouldCreateOnBehalfUserRepresentation_givenVOUserDetails() {
    VOUserDetails voUserDetails = createVO();

    OnBehalfUserRepresentation result = new OnBehalfUserRepresentation(voUserDetails);

    assertThat(result).isNotNull();
    assertThat(result).extracting(r -> r.getId()).isEqualTo(voUserDetails.getKey());
    assertThat(result).extracting(r -> r.getOrganizationId()).isEqualTo(voUserDetails.getOrganizationId());
    assertThat(result).extracting(r -> r.getETag()).isEqualTo((long) voUserDetails.getVersion());
    assertThat(result).extracting(r -> r.getUserId()).isEqualTo(voUserDetails.getUserId());
  }

  @Test
  public void shouldSetPassword() {
    String passwordToSet = "password";
    OnBehalfUserRepresentation result = new OnBehalfUserRepresentation();
    result.setPassword(passwordToSet);

    assertThat(result).extracting(OnBehalfUserRepresentation::getPassword).isEqualTo(passwordToSet);
  }

  private VOUserDetails createVO(){
    VOUserDetails voUserDetails = new VOUserDetails();
    voUserDetails.setKey(1234567L);
    voUserDetails.setOrganizationId("orgId");
    voUserDetails.setVersion(7654321);
    voUserDetails.setUserId("userId");
    return voUserDetails;
  }
}
