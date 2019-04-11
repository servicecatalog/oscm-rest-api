/**
 * *****************************************************************************
 *
 * <p>Copyright FUJITSU LIMITED 2019
 *
 * <p>Creation Date: 10-04-2019
 *
 * <p>*****************************************************************************
 */
package org.oscm.rest.subscription.data;

import javax.ws.rs.WebApplicationException;
import org.oscm.internal.vo.VOUser;
import org.oscm.rest.common.Representation;

public class UserRepresentation extends Representation {

  private transient VOUser vo;

  private String userId;

  public UserRepresentation() {
    this(new VOUser());
  }

  public UserRepresentation(VOUser user) {
    vo = user;
  }

  @Override
  public void validateContent() throws WebApplicationException {}

  @Override
  public void update() {
    vo.setKey(convertIdToKey());
    vo.setUserId(getUserId());
    vo.setVersion(convertETagToVersion());
  }

  @Override
  public void convert() {
    setETag(Long.valueOf(vo.getVersion()));
    setId(Long.valueOf(vo.getKey()));
    setUserId(vo.getUserId());
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  // FIXME move to super class
  protected long convertIdToKey() {
    if (getId() == null) {
      return 0L;
    }
    return getId().longValue();
  }

  // FIXME move to super class
  protected int convertETagToVersion() {
    if (getETag() == null) {
      return 0;
    }
    return getETag().intValue();
  }
}
