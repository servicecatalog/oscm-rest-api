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

import org.oscm.internal.vo.VOService;
import org.oscm.rest.common.MarketplaceListType;

import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;

public class IdentifiableMarketplaceParameters extends IdentifableRequestParameters {

  @QueryParam("listType")
  private MarketplaceListType listType = MarketplaceListType.OWNED;

  @QueryParam("mId")
  private String marketplaceId;

  @QueryParam("sKey")
  private Long serviceKey;

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

  public MarketplaceListType getListType() {
    return listType;
  }

  public void setListType(MarketplaceListType listType) {
    this.listType = listType;
  }

  public Long getServiceKey() {
    return serviceKey;
  }

  public void setServiceKey(Long serviceKey) {
    this.serviceKey = serviceKey;
  }

  public VOService getService() {
    VOService svc = new VOService();
    if (serviceKey != null) {
      svc.setKey(serviceKey.longValue());
    }
    return svc;
  }
}
