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
import org.oscm.rest.identity.data.OnBehalfUserRepresentation;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OnBehalfUserResourceTest {

  @Mock private UserBackend userBackend;
  @InjectMocks @Spy private OnBehalfUserResource obUserResource;

  private UriInfo uriInfo;
  private Response result;

  @BeforeEach
  public void setUp() {
    uriInfo = SampleTestDataUtility.createUriInfo();
  }

  @AfterEach
  public void cleanUp() {
    result = null;
  }

  @Test
  public void shouldCreateOnBehalfUser() {
    OnBehalfUserRepresentation userRepresentation = createOBUserRepresentation();
    UserParameters parameters = createParameters();

    when(userBackend.postOnBehalfUser())
        .thenReturn((onBehalfUserRepresentation, userParameters) -> "newId");

    try {
      result = obUserResource.createOnBehalfUser(uriInfo, userRepresentation, parameters);
    } catch (Exception e) {
      fail(e);
    }

    assertThat(result).isNotNull();
    assertThat(result)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.CREATED.getStatusCode());
  }

  @Test
  public void shouldDeleteOnBehalfUser() {
    UserParameters parameters = createParametersForDeletion();

    when(userBackend.deleteOBehalfUser()).thenReturn(userParameters -> true);

    try {
      result = obUserResource.deleteOnBehalfUser(uriInfo, parameters);
    } catch (Exception e) {
      fail(e);
    }

    assertThat(result)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.NO_CONTENT.getStatusCode());
  }

  private OnBehalfUserRepresentation createOBUserRepresentation() {
    OnBehalfUserRepresentation userRepresentation = new OnBehalfUserRepresentation();
    userRepresentation.setUserId("userId");
    return userRepresentation;
  }

  private UserParameters createParameters() {
    return new UserParameters();
  }

  private UserParameters createParametersForDeletion() {
    UserParameters parameters = new UserParameters();
    parameters.setUserId("userId");
    return parameters;
  }
}
