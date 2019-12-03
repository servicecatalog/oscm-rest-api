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

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.WebApplicationException;
import org.oscm.internal.vo.VOUsageLicense;

public class UsageLicenseRepresentation extends Representation {

  private transient VOUsageLicense vo;

  private UserRepresentation user;
  private RoleDefinitionRepresentation role;

  public UsageLicenseRepresentation() {
    this(new VOUsageLicense());
  }

  public UsageLicenseRepresentation(VOUsageLicense lic) {
    vo = lic;
  }

  @Override
  public void validateContent() throws WebApplicationException {}

  @Override
  public void update() {
    if (role != null) {
      role.update();
      vo.setKey(role.convertIdToKey());
      vo.setRoleDefinition(role.getVO());
    }
    if (user != null) {
      user.update();
      vo.setKey(user.convertIdToKey());
      vo.setUser(user.getVO());
    }
  }

  @Override
  public void convert() {
    if (vo.getRoleDefinition() == null) {
      role = new RoleDefinitionRepresentation();
    } else {
      role = new RoleDefinitionRepresentation(vo.getRoleDefinition());
    }
    role.convert();
    user = new UserRepresentation(vo.getUser());
    user.convert();
  }

  public UserRepresentation getUser() {
    return user;
  }

  public void setUser(UserRepresentation user) {
    this.user = user;
  }

  public RoleDefinitionRepresentation getRole() {
    return role;
  }

  public void setRole(RoleDefinitionRepresentation role) {
    this.role = role;
  }

  public VOUsageLicense getVO() {
    return vo;
  }

  public static List<UsageLicenseRepresentation> convert(List<VOUsageLicense> lics) {
    if (lics == null || lics.isEmpty()) {
      return null;
    }
    List<UsageLicenseRepresentation> result = new ArrayList<UsageLicenseRepresentation>();
    for (VOUsageLicense lic : lics) {
      UsageLicenseRepresentation ulr = new UsageLicenseRepresentation(lic);
      ulr.convert();
      result.add(ulr);
    }
    return result;
  }
}
