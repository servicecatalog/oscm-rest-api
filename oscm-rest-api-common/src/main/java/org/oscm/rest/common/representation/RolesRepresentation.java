/**
 * *****************************************************************************
 *
 * <p>Copyright FUJITSU LIMITED 2019
 *
 * <p>Creation Date: 10-04-2019
 *
 * <p>*****************************************************************************
 */
package org.oscm.rest.common.representation;

import org.oscm.internal.types.enumtypes.UserRoleType;
import org.oscm.internal.vo.VOUserDetails;

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
}
