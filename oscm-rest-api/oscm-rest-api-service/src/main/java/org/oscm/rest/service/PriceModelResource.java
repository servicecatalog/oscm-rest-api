package org.oscm.rest.service;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.oscm.rest.common.CommonParams;
import org.oscm.rest.common.RestResource;
import org.oscm.rest.common.Since;
import org.oscm.rest.service.data.PriceModelRepresentation;

@Path(CommonParams.PATH_VERSION + "/services" + CommonParams.PATH_ID
        + "/pricemodel")
@Stateless
public class PriceModelResource extends RestResource {

    @EJB
    PriceModelBackend pmb;

    @Since(CommonParams.VERSION_1)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@Context UriInfo uriInfo,
            @BeanParam ServiceParameters params) throws Exception {
        return get(uriInfo, pmb.get(), params, true);
    }

    @Since(CommonParams.VERSION_1)
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@Context UriInfo uriInfo,
            PriceModelRepresentation content,
            @BeanParam ServiceParameters params) throws Exception {
        return put(uriInfo, pmb.put(), content, params);
    }

    @Since(CommonParams.VERSION_1)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/customer/{orgKey}")
    public Response getForCustomer(@Context UriInfo uriInfo,
            @BeanParam ServiceParameters params) throws Exception {
        return get(uriInfo, pmb.getForCustomer(), params, true);
    }

    @Since(CommonParams.VERSION_1)
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/customer/{orgKey}")
    public Response updateForCustomer(@Context UriInfo uriInfo,
            PriceModelRepresentation content,
            @BeanParam ServiceParameters params) throws Exception {
        return put(uriInfo, pmb.putForCustomer(), content, params);
    }

}