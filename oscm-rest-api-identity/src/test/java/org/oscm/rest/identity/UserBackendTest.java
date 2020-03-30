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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.google.common.collect.Lists;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.oscm.internal.intf.IdentityService;
import org.oscm.internal.vo.VOUserDetails;
import org.oscm.rest.common.SampleTestDataUtility;
import org.oscm.rest.common.representation.OnBehalfUserRepresentation;
import org.oscm.rest.common.representation.RepresentationCollection;
import org.oscm.rest.common.representation.RolesRepresentation;
import org.oscm.rest.common.representation.UserRepresentation;
import org.oscm.rest.common.requestparameters.UserParameters;

@ExtendWith(MockitoExtension.class)
public class UserBackendTest {

  @Mock private IdentityService identityService;
  @InjectMocks private UserBackend backend;
  private UserResource userResource;
  private OnBehalfUserResource onBehalfUserResource;
  private RolesResource rolesResource;

  private UriInfo uriInfo;
  private UserParameters parameters;
  private UserRepresentation userRepresentation;
  private OnBehalfUserRepresentation onBehalfUserRepresentation;
  private RolesRepresentation rolesRepresentation;
  private VOUserDetails vo;

  private final String API_VERSION = "v1";
  private final String USER_ID = "userId";
  private final Long USER_KEY = 1000L;

  @BeforeEach
  public void setUp() {
    userResource = new UserResource();
    onBehalfUserResource = new OnBehalfUserResource();
    rolesResource = new RolesResource();
    userResource.setUb(backend);
    onBehalfUserResource.setUb(backend);
    rolesResource.setUb(backend);

    uriInfo = SampleTestDataUtility.createUriInfo();
    parameters = SampleTestDataUtility.createUserParameters();
    userRepresentation = SampleTestDataUtility.createUserRepresentation();
    onBehalfUserRepresentation = SampleTestDataUtility.createOBUserRepresentation();
    rolesRepresentation = SampleTestDataUtility.createRolesRepresentation();
    vo = SampleTestDataUtility.createVOUserDetails();
  }

  @Test
  @SneakyThrows
  public void shouldGetUsers() {
    when(identityService.getUsersForOrganization()).thenReturn(Lists.newArrayList(vo));

    Response response = userResource.getUsers(uriInfo, API_VERSION);

    assertThat(response).isNotNull();
    assertThat(response)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.OK.getStatusCode());
    assertThat(response).extracting(Response::hasEntity).isEqualTo(true);
    assertThat(response)
        .extracting(
            r -> {
              return ((RepresentationCollection) r.getEntity()).getItems().size();
            })
        .isEqualTo(1);
  }

  @Test
  @SneakyThrows
  public void shouldGetUserById() {
    when(identityService.getUserDetails(any())).thenReturn(vo);

    Response response = userResource.getUser(uriInfo, API_VERSION, USER_ID);

    assertThat(response).isNotNull();
    assertThat(response)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.OK.getStatusCode());
    assertThat(response).extracting(Response::hasEntity).isEqualTo(true);
  }

  @Test
  @SneakyThrows
  public void shouldPostUser() {
    when(identityService.createUser(any(), any(), any())).thenReturn(vo);

    Response response = userResource.createUser(uriInfo, userRepresentation, API_VERSION);

    assertThat(response).isNotNull();
    assertThat(response)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.CREATED.getStatusCode());
  }

  @Test
  @SneakyThrows
  public void shouldPutUser() {
    when(identityService.getUserDetails(any())).thenReturn(vo);
    when(identityService.updateUser(any())).thenReturn(vo);

    Response response = userResource.updateUser(uriInfo, userRepresentation, API_VERSION, USER_ID);

    assertThat(response).isNotNull();
    assertThat(response)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.NO_CONTENT.getStatusCode());
  }

  @Test
  @SneakyThrows
  public void shouldDeleteUser() {
    doNothing().when(identityService).deleteUser(any(), any());

    Response response = userResource.deleteUser(uriInfo, API_VERSION, USER_ID);

    assertThat(response).isNotNull();
    assertThat(response)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.NO_CONTENT.getStatusCode());
  }

  @Test
  @SneakyThrows
  public void shouldGetRoles() {
    when(identityService.getUserDetails(any())).thenReturn(vo);

    Response response =
        rolesResource.getUserRoles(
            uriInfo, parameters.getEndpointVersion(), parameters.getUserId());

    assertThat(response).isNotNull();
    assertThat(response)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.OK.getStatusCode());
    assertThat(response).extracting(Response::hasEntity).isEqualTo(true);
  }

  @Test
  @SneakyThrows
  public void shouldPutRoles() {
    doNothing().when(identityService).setUserRoles(any(), any());

    Response response =
        rolesResource.setUserRoles(
            uriInfo, rolesRepresentation, parameters.getEndpointVersion(), parameters.getUserId());

    assertThat(response).isNotNull();
    assertThat(response)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.NO_CONTENT.getStatusCode());
  }

  @Test
  @SneakyThrows
  public void shouldPostOnBehalfUser() {
    when(identityService.createOnBehalfUser(any(), any())).thenReturn(vo);

    Response response =
        onBehalfUserResource.createOnBehalfUser(uriInfo, onBehalfUserRepresentation, parameters);

    assertThat(response).isNotNull();
    assertThat(response)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.CREATED.getStatusCode());
  }

  @Test
  @SneakyThrows
  public void shouldDeleteOnBehalfUser() {
    doNothing().when(identityService).cleanUpCurrentUser();

    Response response = onBehalfUserResource.deleteOnBehalfUser(uriInfo, parameters);

    assertThat(response).isNotNull();
    assertThat(response)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.NO_CONTENT.getStatusCode());
  }

  @Test
  @Disabled("LDAP Functionality is disabled for now")
  public void shouldGetLdapUsers() {}

  @Test
  @Disabled("LDAP Functionality is disabled for now")
  public void shouldPostLdapUsers() {}
}
