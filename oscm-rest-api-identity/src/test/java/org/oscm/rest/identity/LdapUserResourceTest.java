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
import org.oscm.rest.common.representation.UserRepresentation;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

@ExtendWith(MockitoExtension.class)
public class LdapUserResourceTest {

  @Mock private UserBackend userBackend;
  @InjectMocks @Spy private LdapUserResource userResource;

  private UserRepresentation userRepresentation;
  private UriInfo uriInfo;
  private UserParameters parameters;
  private Response result;

  @BeforeEach
  public void setUp() {
    userRepresentation = createUserRepresentation();
    uriInfo = SampleTestDataUtility.createUriInfo();
    parameters = createParameters();
  }

  @AfterEach
  public void cleanUp() {
    result = null;
  }

  @Test
  public void shouldGetLdapUsers() {
    try {
      result = userResource.getLdapUsers(uriInfo, parameters);
    } catch (Exception e) {
      fail(e);
    }

    System.out.println(result);

    assertThat(result).isNotNull();
    assertThat(result)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.NOT_IMPLEMENTED.getStatusCode());
    assertThat(result).extracting(Response::hasEntity).isEqualTo(false);
  }

  @Test
  public void shouldCreateLdapUser() {
    try {
      result = userResource.createLdapUser(uriInfo, userRepresentation, parameters);
    } catch (Exception e) {
      fail(e);
    }

    assertThat(result).isNotNull();
    assertThat(result)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.NOT_IMPLEMENTED.getStatusCode());
  }

  private UserRepresentation createUserRepresentation() {
    UserRepresentation userRepresentation = new UserRepresentation();
    userRepresentation.setUserId("userId");
    return userRepresentation;
  }

  private UserParameters createParameters() {
    return new UserParameters();
  }
}
