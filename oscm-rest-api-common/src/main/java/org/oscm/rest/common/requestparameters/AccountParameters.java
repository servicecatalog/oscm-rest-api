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

import io.swagger.v3.oas.annotations.Parameter;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;

public class AccountParameters extends RequestParameters {

  @Parameter(description = "Marketplace ID")
  @QueryParam("marketplaceId")
  private String marketplaceId;

  @Parameter(description = "Organization ID")
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
