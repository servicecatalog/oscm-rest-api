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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

import com.google.common.collect.Lists;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.oscm.rest.common.PostResponseBody;
import org.oscm.rest.common.SampleTestDataUtility;
import org.oscm.rest.common.representation.RepresentationCollection;
import org.oscm.rest.common.representation.UserCreateRepresentation;
import org.oscm.rest.common.representation.UserRepresentation;

@ExtendWith(MockitoExtension.class)
public class UserResourceTest {

  @Mock private UserBackend userBackend;
  @InjectMocks @Spy private UserResource userResource;

  private UserRepresentation userRepresentation;
  private UserCreateRepresentation userCreateRepresentation;
  private UriInfo uriInfo;
  private Response result;

  private final String API_VERSION = "v1";
  private final String USER_ID = "userId";

  @BeforeEach
  public void setUp() {
    userRepresentation = SampleTestDataUtility.createUserRepresentation();
    userCreateRepresentation = SampleTestDataUtility.createUserCreateRepresentation();
    uriInfo = SampleTestDataUtility.createUriInfo();
  }

  @AfterEach
  public void cleanUp() {
    result = null;
  }

  @Test
  public void shouldGetUsers() {

    when(userBackend.getUsers())
        .thenReturn(
            userParameters ->
                new RepresentationCollection<>(Lists.newArrayList(userRepresentation)));

    try {
      result = userResource.getUsers(uriInfo, API_VERSION);
    } catch (Exception e) {
      fail(e);
    }

    assertThat(result).isNotNull();
    assertThat(result)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.OK.getStatusCode());
    assertThat(result)
        .extracting(
            r -> {
              RepresentationCollection<UserRepresentation> representationCollection =
                  (RepresentationCollection<UserRepresentation>) r.getEntity();
              return representationCollection.getItems().toArray()[0];
            })
        .isEqualTo(userRepresentation);
  }

  @Test
  public void shouldCreateUser() {
    when(userBackend.postUser())
        .thenReturn(
            (userRepresentation1, userParameters) ->
                PostResponseBody.of().createdObjectId("id").createdObjectName("name").build());

    try {
      result = userResource.createUser(uriInfo, userCreateRepresentation, API_VERSION);
    } catch (Exception e) {
      fail(e);
    }

    assertThat(result).isNotNull();
    assertThat(result)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.CREATED.getStatusCode());
    assertThat(result).extracting(Response::hasEntity).isEqualTo(true);
    assertThat((PostResponseBody) result.getEntity())
        .extracting(PostResponseBody::getCreatedObjectId)
        .isNotNull();
    assertThat((PostResponseBody) result.getEntity())
        .extracting(PostResponseBody::getCreatedObjectName)
        .isNotNull();
  }

  @Test
  public void shouldGetSingleUser() {
    when(userBackend.getUser()).thenReturn(userParameters -> userRepresentation);

    try {
      result = userResource.getUser(uriInfo, API_VERSION, USER_ID);
    } catch (Exception e) {
      fail(e);
    }

    assertThat(result).isNotNull();
    assertThat(result)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.OK.getStatusCode());
    assertThat(result).extracting(Response::getEntity).isEqualTo(userRepresentation);
  }

  @Test
  public void shouldUpdateUser() {
    when(userBackend.putUser()).thenReturn((userRepresentation1, userParameters) -> true);

    try {
      result = userResource.updateUser(uriInfo, userRepresentation, API_VERSION, USER_ID);
    } catch (Exception e) {
      fail(e);
    }

    assertThat(result).isNotNull();
    assertThat(result)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.NO_CONTENT.getStatusCode());
  }

  @Test
  public void shouldDeleteUser() {
    when(userBackend.deleteUser()).thenReturn(userParameters -> true);

    try {
      result = userResource.deleteUser(uriInfo, API_VERSION, USER_ID);
    } catch (Exception e) {
      fail(e);
    }

    assertThat(result).isNotNull();
    assertThat(result)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.NO_CONTENT.getStatusCode());
  }
}
