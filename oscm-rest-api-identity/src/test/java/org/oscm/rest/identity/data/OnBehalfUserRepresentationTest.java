package org.oscm.rest.identity.data;

import org.junit.Test;
import org.oscm.internal.vo.VOUserDetails;

import static org.assertj.core.api.Assertions.assertThat;

public class OnBehalfUserRepresentationTest {

  @Test
  public void shouldCreateOnBehalfUserRepresentation_givenVOUserDetails() {
    VOUserDetails input = new VOUserDetails();
    input.setKey(1234567L);
    input.setOrganizationId("orgId");
    input.setVersion(7654321);
    input.setUserId("userId");

    OnBehalfUserRepresentation result = new OnBehalfUserRepresentation(input);

    assertThat(result).isNotNull();
    assertThat(result).extracting(r -> r.getId()).isEqualTo(input.getKey());
    assertThat(result).extracting(r -> r.getOrganizationId()).isEqualTo(input.getOrganizationId());
    assertThat(result).extracting(r -> r.getETag()).isEqualTo((long) input.getVersion());
    assertThat(result).extracting(r -> r.getUserId()).isEqualTo(input.getUserId());
  }

  @Test
  public void shouldSetPassword() {
    String passwordToSet = "password";
    OnBehalfUserRepresentation result = new OnBehalfUserRepresentation();
    result.setPassword(passwordToSet);

    assertThat(result).extracting(r -> r.getPassword()).isEqualTo(passwordToSet);
  }
}
