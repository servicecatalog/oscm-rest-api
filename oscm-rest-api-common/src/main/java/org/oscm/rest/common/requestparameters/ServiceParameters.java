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

import org.oscm.internal.types.enumtypes.PerformanceHint;
import org.oscm.internal.vo.ListCriteria;

public class ServiceParameters extends RequestParameters {

  private Long orgKey;

  private String orgId;

  @QueryParam("searchPhrase")
  private String searchPhrase;

  @QueryParam("locale")
  private String locale;

  @QueryParam("marketplaceId")
  private String marketPlaceId;

  @QueryParam("performanceHint")
  private PerformanceHint performanceHint;

  private ListCriteria listCriteria = new ListCriteria();

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

  public String getSearchPhrase() {
    return searchPhrase;
  }

  public void setSearchPhrase(String searchPhrase) {
    this.searchPhrase = searchPhrase;
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

  public ListCriteria getListCriteria() {
    return listCriteria;
  }

  public void setListCriteria(ListCriteria listCriteria) {
    this.listCriteria = listCriteria;
  }

  public PerformanceHint getPerformanceHint() {
    return performanceHint;
  }

  public void setPerformanceHint(PerformanceHint performanceHint) {
    this.performanceHint = performanceHint;
  }

  public int eTagToVersion() {
    if (getETag() == null) {
      return 0;
    }
    return getETag().intValue();
  }
}
