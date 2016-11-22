package org.oscm.rest.subscription;

import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;

import org.oscm.rest.common.RequestParameters;

public class SubscriptionParameters extends RequestParameters {

    @QueryParam("userId")
    private String userId;

    @PathParam("licKey")
    private Long licKey;

    @Override
    public void validateParameters() throws WebApplicationException {

    }

    @Override
    public void update() {

    }

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
