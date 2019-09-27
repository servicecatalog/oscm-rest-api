/**
 * *****************************************************************************
 *
 * <p>Copyright FUJITSU LIMITED 2019
 *
 * <p>Creation Date: 10-04-2019
 *
 * <p>*****************************************************************************
 */
package org.oscm.rest.identity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.oscm.rest.common.SampleTestDataUtility;
import org.oscm.rest.common.requestparameters.UserParameters;
import org.oscm.rest.common.representation.RolesRepresentation;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RolesResourceTest {

  @Mock private UserBackend userBackend;
  @InjectMocks @Spy private RolesResource rolesResource;

  private RolesRepresentation rolesRepresentation;
  private UriInfo uriInfo;
  private UserParameters parameters;
  private Response result;

  @BeforeEach
  public void setUp() {
    rolesRepresentation = SampleTestDataUtility.createRolesRepresentation();
    uriInfo = SampleTestDataUtility.createUriInfo();
    parameters = SampleTestDataUtility.createUserParameters();
  }

  @AfterEach
  public void cleanUp() {
    result = null;
  }

  @Test
  public void shouldGetUserRoles() {
    when(userBackend.getRoles()).thenReturn(userParameters -> rolesRepresentation);

    try {
      result = rolesResource.getUserRoles(uriInfo, parameters);
    } catch (Exception e) {
      fail(e);
    }

    assertThat(result).isNotNull();
    assertThat(result)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.OK.getStatusCode());
    assertThat(result).extracting(Response::hasEntity).isEqualTo(true);
    assertThat(result).extracting(Response::getEntity).isEqualTo(rolesRepresentation);
  }

  @Test
  public void shouldSetUserRoles() {
    when(userBackend.putRoles()).thenReturn((rolesRepresentation1, userParameters) -> true);

    try {
      result = rolesResource.setUserRoles(uriInfo, rolesRepresentation, parameters);
    } catch (Exception e) {
      fail(e);
    }

    assertThat(result).isNotNull();
    assertThat(result)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.NO_CONTENT.getStatusCode());
  }
}
