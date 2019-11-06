/**
 * *****************************************************************************
 *
 * <p>Copyright FUJITSU LIMITED 2019
 *
 * <p>Creation Date: 10-04-2019
 *
 * <p>*****************************************************************************
 */
package org.oscm.rest.common.requestparameters;

import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;

public class IdentifiableServiceParameters extends IdentifableRequestParameters {

  @QueryParam("orgKey")
  private Long orgKey;

  @QueryParam("orgId")
  private String orgId;

  @Override
  public void validateParameters() throws WebApplicationException {}

  @Override
  public void validateETag() throws WebApplicationException {}

  @Override
  public void update() {}

  public Long getOrgKey() {
    return orgKey;
  }

  public void setOrgKey(Long orgKey) {
    this.orgKey = orgKey;
  }

  public String getOrgId() {
    return orgId;
  }

  public void setOrgId(String orgId) {
    this.orgId = orgId;
  }

  public int eTagToVersion() {
    if (getETag() == null) {
      return 0;
    }
    return getETag().intValue();
  }
}
