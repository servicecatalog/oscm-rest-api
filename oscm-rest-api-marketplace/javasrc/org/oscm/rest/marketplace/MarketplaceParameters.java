package org.oscm.rest.marketplace;

import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;

import org.oscm.rest.common.RequestParameters;

public class MarketplaceParameters extends RequestParameters {

    @QueryParam("listType")
    private MarketplaceListType listType = MarketplaceListType.OWNED;

    @PathParam("mId")
    private String marketplaceId;

    @Override
    public void validateParameters() throws WebApplicationException {

    }

    @Override
    public void update() {

    }

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

}
