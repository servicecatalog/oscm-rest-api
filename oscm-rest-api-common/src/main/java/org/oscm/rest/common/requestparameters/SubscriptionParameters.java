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
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;

public class SubscriptionParameters extends RequestParameters {

  @QueryParam("userId")
  private String userId;

  @Parameter(description = "Subscription's usage license key", required = true)
  @PathParam("licKey")
  private Long licKey;

  @Override
  public void validateParameters() throws WebApplicationException {}

  @Override
  public void validateETag() throws WebApplicationException {}

  @Override
  public void update() {}

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public Long getLicKey() {
    return licKey;
  }

  public void setLicKey(Long licKey) {
    this.licKey = licKey;
  }
}
