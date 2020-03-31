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

public class ServiceParameters extends RequestParameters {

  private Long orgKey;

  private String orgId;

  @QueryParam("serviceName")
  private String serviceName;

  @QueryParam("locale")
  private String locale;

  @QueryParam("marketplaceId")
  private String marketPlaceId;

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

  public String getServiceName() {
    return serviceName;
  }

  public void setServiceName(String serviceName) {
    this.serviceName = serviceName;
  }

  public String getLocale() {
    return locale;
  }

  public void setLanguage(String locale) {
    this.locale = locale;
  }

  public String getMarketPlaceId() {
    return marketPlaceId;
  }

  public void setMarketPlaceId(String marketPlaceId) {
    this.marketPlaceId = marketPlaceId;
  }

  public int eTagToVersion() {
    if (getETag() == null) {
      return 0;
    }
    return getETag().intValue();
  }
}
