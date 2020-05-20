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

import org.oscm.internal.vo.VOUsageLicense;

import javax.ws.rs.WebApplicationException;
import java.util.ArrayList;
import java.util.List;

public class UsageLicenseRepresentation extends Representation {

  private transient VOUsageLicense vo;

  private UserRepresentation user;

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
    vo.setKey(convertIdToKey());
    if (user != null) {
      user.update();
      vo.setKey(user.convertIdToKey());
      vo.setUser(user.getVO());
    }
  }

  @Override
  public void convert() {
    setId(vo.getKey());
    user = new UserRepresentation(vo.getUser());
    user.convert();
  }

  public UserRepresentation getUser() {
    return user;
  }

  public void setUser(UserRepresentation user) {
    this.user = user;
  }

  public VOUsageLicense getVO() {
    return vo;
  }

  public static List<UsageLicenseRepresentation> convert(List<VOUsageLicense> lics) {
    List<UsageLicenseRepresentation> result = new ArrayList<>();

    for (VOUsageLicense lic : lics) {
      UsageLicenseRepresentation ulr = new UsageLicenseRepresentation(lic);
      ulr.convert();
      result.add(ulr);
    }
    return result;
  }
}
