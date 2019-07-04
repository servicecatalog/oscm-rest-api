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

import com.google.common.collect.Lists;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.oscm.internal.intf.IdentityService;
import org.oscm.internal.vo.VOUserDetails;
import org.oscm.rest.common.RepresentationCollection;
import org.oscm.rest.common.SampleTestDataUtility;
import org.oscm.rest.identity.data.OnBehalfUserRepresentation;
import org.oscm.rest.identity.data.RolesRepresentation;
import org.oscm.rest.identity.data.UserRepresentation;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

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

  @BeforeEach
  public void setUp() {
    userResource = new UserResource();
    onBehalfUserResource = new OnBehalfUserResource();
    rolesResource = new RolesResource();
    userResource.setUb(backend);
    onBehalfUserResource.setUb(backend);
    rolesResource.setUb(backend);

    uriInfo = SampleTestDataUtility.createUriInfo();
    parameters = createParameters();
    userRepresentation = createUserRepresentation();
    onBehalfUserRepresentation = createOnBehalfUserRepresentation();
    rolesRepresentation = createRolesRepresentation();
    vo = createVO();
  }

  @Test
  @SneakyThrows
  public void shouldGetUsers() {
    when(identityService.getUsersForOrganization()).thenReturn(Lists.newArrayList(vo));

    Response response = userResource.getUsers(uriInfo, parameters);

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

    Response response = userResource.getUser(uriInfo, parameters);

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

    Response response = userResource.createUser(uriInfo, userRepresentation, parameters);

    assertThat(response).isNotNull();
    assertThat(response)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.CREATED.getStatusCode());
  }

  @Test
  @SneakyThrows
  public void shouldPutUser() {
    when(identityService.updateUser(any())).thenReturn(vo);

    Response response = userResource.updateUser(uriInfo, userRepresentation, parameters);

    assertThat(response).isNotNull();
    assertThat(response)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.NO_CONTENT.getStatusCode());
  }

  @Test
  @SneakyThrows
  public void shouldDeleteUser() {
    doNothing().when(identityService).deleteUser(any(), any());

    Response response = userResource.deleteUser(uriInfo, parameters);

    assertThat(response).isNotNull();
    assertThat(response)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.NO_CONTENT.getStatusCode());
  }

  @Test
  @SneakyThrows
  public void shouldGetRoles() {
    when(identityService.getUserDetails(any())).thenReturn(vo);

    Response response = rolesResource.getUserRoles(uriInfo, parameters);

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

    Response response = rolesResource.setUserRoles(uriInfo, rolesRepresentation, parameters);

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

  private UserParameters createParameters() {
    UserParameters parameters = new UserParameters();
    parameters.setUserId("123");
    return parameters;
  }

  private UserRepresentation createUserRepresentation() {
    return new UserRepresentation();
  }

  private OnBehalfUserRepresentation createOnBehalfUserRepresentation() {
    return new OnBehalfUserRepresentation();
  }

  private RolesRepresentation createRolesRepresentation() {
    return new RolesRepresentation();
  }

  private VOUserDetails createVO() {
    VOUserDetails voUserDetails = new VOUserDetails();
    voUserDetails.setUserId("userId");
    return voUserDetails;
  }
}
