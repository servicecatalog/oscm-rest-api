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

public class AccountParameters extends RequestParameters {

  @QueryParam("marketplaceId")
  private String marketplaceId;

  @QueryParam("orgId")
  private String orgId;

  @Override
  public void validateParameters() throws WebApplicationException {}

  @Override
  public void update() {}

  public String getMarketplaceId() {
    return marketplaceId;
  }

  public void setMarketplaceId(String marketplaceId) {
    this.marketplaceId = marketplaceId;
  }

  public String getOrgId() {
    return orgId;
  }

  public void setOrgId(String orgId) {
    this.orgId = orgId;
  }

  @Override
  public void validateId() throws WebApplicationException {}
}
