package org.oscm.rest.marketplace;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;

import org.oscm.rest.common.CommonParams;
import org.oscm.rest.common.RestResource;
import org.oscm.rest.common.Since;
import org.oscm.rest.marketplace.data.MarketplaceRepresentation;

@Path(CommonParams.PATH_VERSION + "/marketplaces")
@Stateless
public class MarketplaceResource extends RestResource {

    @EJB
    MarketplaceBackend mb;

    @Since(CommonParams.VERSION_1)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMarketplaces(@Context Request request,
            @BeanParam MarketplaceParameters params) throws Exception {
        return getCollection(request, mb.getCollection(), params);
    }

    @Since(CommonParams.VERSION_1)
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createMarketplace(@Context Request request,
            MarketplaceRepresentation content,
            @BeanParam MarketplaceParameters params) throws Exception {
        return post(request, mb.post(), content, params);
    }

    @Since(CommonParams.VERSION_1)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path(CommonParams.PATH_ID)
    public Response getMarketplace(@Context Request request,
            @BeanParam MarketplaceParameters params) throws Exception {
        return get(request, mb.get(), params, true);
    }

    @Since(CommonParams.VERSION_1)
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path(CommonParams.PATH_ID)
    public Response updateMarketplace(@Context Request request,
            MarketplaceRepresentation content,
            @BeanParam MarketplaceParameters params) throws Exception {
        return put(request, mb.put(), content, params);
    }

    @Since(CommonParams.VERSION_1)
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path(CommonParams.PATH_ID)
    public Response deleteMarketplace(@Context Request request,
            @BeanParam MarketplaceParameters params) throws Exception {
        return delete(request, mb.delete(), params);
    }

}
