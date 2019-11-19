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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.oscm.internal.intf.IdentityService;
import org.oscm.internal.vo.VOUser;
import org.oscm.internal.vo.VOUserDetails;
import org.oscm.rest.common.PostResponseBody;
import org.oscm.rest.common.RestBackend;
import org.oscm.rest.common.representation.OnBehalfUserRepresentation;
import org.oscm.rest.common.representation.RepresentationCollection;
import org.oscm.rest.common.representation.RolesRepresentation;
import org.oscm.rest.common.representation.UserRepresentation;
import org.oscm.rest.common.requestparameters.UserParameters;

@Stateless
public class UserBackend {

  @EJB IdentityService is;

  public RestBackend.GetCollection<UserRepresentation, UserParameters> getLdapUsers() {
    return params -> {
      Collection<UserRepresentation> list =
          UserRepresentation.convert(is.searchLdapUsers(params.getPattern()));
      return new RepresentationCollection<>(list);
    };
  }

  public RestBackend.Post<UserRepresentation, UserParameters> postLdapUser() {
    return (content, params) -> {
      VOUserDetails vo = content.getVO();
      is.importLdapUsers(Collections.singletonList(vo), params.getMarketplaceId());
      return vo.getUserId();
    };
  }

  public RestBackend.GetCollection<UserRepresentation, UserParameters> getUsers() {
    return params -> {
      Collection<UserRepresentation> list =
          UserRepresentation.convert(is.getUsersForOrganization());
      return new RepresentationCollection<>(list);
    };
  }

  public RestBackend.Post<UserRepresentation, UserParameters> postUser() {
    return (content, params) -> {
      VOUserDetails vo = content.getVO();
      vo = is.createUser(vo, new ArrayList<>(vo.getUserRoles()), params.getMarketplaceId());
      if (vo == null) {
        return null;
      }
      return PostResponseBody.of()
          .createdObjectId(String.valueOf(vo.getKey()))
          .createdObjectName(vo.getUserId())
          .build();
    };
  }

  public RestBackend.Get<UserRepresentation, UserParameters> getUser() {
    return params -> {
      VOUser vo = new VOUser();
      vo.setUserId(params.getUserId());
      return new UserRepresentation(is.getUserDetails(vo));
    };
  }

  public RestBackend.Put<UserRepresentation, UserParameters> putUser() {
    return (content, params) -> {
      // TODO: createResponse id change?
      is.updateUser(content.getVO());
      return true;
    };
  }

  public RestBackend.Delete<UserParameters> deleteUser() {
    return params -> {
      VOUser vo = new VOUser();
      vo.setUserId(params.getUserId());
      vo.setKey(Long.valueOf(params.getUserId()));
      vo.setVersion(Integer.MAX_VALUE);
      is.deleteUser(vo, params.getMarketplaceId());
      return true;
    };
  }

  public RestBackend.Get<RolesRepresentation, UserParameters> getRoles() {
    return params -> {
      VOUser vo = new VOUser();
      vo.setUserId(params.getUserId());
      return new RolesRepresentation(is.getUserDetails(vo));
    };
  }

  public RestBackend.Put<RolesRepresentation, UserParameters> putRoles() {
    return (content, params) -> {
      VOUserDetails vo = content.getVO();
      vo.setUserId(params.getUserId());
      is.setUserRoles(vo, new ArrayList<>(content.getUserRoles()));
      return true;
    };
  }

  public RestBackend.Post<OnBehalfUserRepresentation, UserParameters> postOnBehalfUser() {
    return (content, params) -> {
      VOUserDetails userDetails =
          is.createOnBehalfUser(content.getOrganizationId(), content.getPassword());
      return PostResponseBody.of()
          .createdObjectId(String.valueOf(userDetails.getKey()))
          .createdObjectName(userDetails.getUserId())
          .build();
    };
  }

  public RestBackend.Delete<UserParameters> deleteOBehalfUser() {
    return params -> {
      is.cleanUpCurrentUser();
      return true;
    };
  }
}
