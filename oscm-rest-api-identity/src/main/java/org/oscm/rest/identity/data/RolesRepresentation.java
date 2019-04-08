package org.oscm.rest.identity.data;

import lombok.Generated;
import org.oscm.internal.types.enumtypes.UserRoleType;
import org.oscm.internal.vo.VOUserDetails;
import org.oscm.rest.common.Representation;

import javax.ws.rs.WebApplicationException;
import java.util.HashSet;
import java.util.Set;

public class RolesRepresentation extends Representation {

  private Set<UserRoleType> userRoles = new HashSet<UserRoleType>();

  transient VOUserDetails vo;

  public RolesRepresentation() {
    this(new VOUserDetails());
  }

  public RolesRepresentation(VOUserDetails details) {
    vo = details;
  }

  @Override
  public void validateContent() throws WebApplicationException {}

  @Override
  public void update() {
    vo.setKey(convertIdToKey());
    vo.setUserRoles(getUserRoles());
    vo.setVersion(convertETagToVersion());
  }

  @Override
  public void convert() {
    setId(Long.valueOf(vo.getKey()));
    setETag(Long.valueOf(vo.getVersion()));
    setUserRoles(vo.getUserRoles());
  }

  public Set<UserRoleType> getUserRoles() {
    return userRoles;
  }

  public void setUserRoles(Set<UserRoleType> userRoles) {
    this.userRoles = userRoles;
  }

  public VOUserDetails getVO() {
    return vo;
  }

  // FIXME move to super class
  // FIXME excluded from code coverage due to fixme
  // TODO Remove @Generated annotation when moving to superclass
  @Generated
  protected long convertIdToKey() {
    if (getId() == null) {
      return 0L;
    }
    return getId().longValue();
  }

  // FIXME move to super class
  // FIXME excluded from code coverage due to fixme
  // TODO Remove @Generated annotation when moving to superclass
  @Generated
  protected int convertETagToVersion() {
    if (getETag() == null) {
      return 0;
    }
    return getETag().intValue();
  }
}
