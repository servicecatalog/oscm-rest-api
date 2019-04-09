package org.oscm.rest.identity;

import org.oscm.internal.intf.IdentityService;
import org.oscm.internal.vo.VOUser;
import org.oscm.internal.vo.VOUserDetails;
import org.oscm.rest.common.RepresentationCollection;
import org.oscm.rest.common.RestBackend;
import org.oscm.rest.identity.data.OnBehalfUserRepresentation;
import org.oscm.rest.identity.data.RolesRepresentation;
import org.oscm.rest.identity.data.UserRepresentation;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

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
      return vo.getUserId();
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
      // TODO: handle id change?
      is.updateUser(content.getVO());
      return true;
    };
  }

  public RestBackend.Delete<UserParameters> deleteUser() {
    return params -> {
      VOUser vo = new VOUser();
      vo.setUserId(params.getUserId());
      vo.setVersion(params.convertETagToVersion());
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
    return (content, params) ->
        is.createOnBehalfUser(content.getOrganizationId(), content.getPassword()).getUserId();
  }

  public RestBackend.Delete<UserParameters> deleteOBehalfUser() {
    return params -> {
      is.cleanUpCurrentUser();
      return true;
    };
  }
}
