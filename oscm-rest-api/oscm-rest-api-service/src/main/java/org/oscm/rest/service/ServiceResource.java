package org.oscm.rest.service;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.BeanParam;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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
import org.oscm.rest.service.data.ServiceDetailsRepresentation;
import org.oscm.rest.service.data.StatusRepresentation;

@Path(CommonParams.PATH_VERSION + "/services")
@Stateless
public class ServiceResource extends RestResource {

    @EJB
    ServiceBackend sb;

    @Since(CommonParams.VERSION_1)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getServices(@Context UriInfo uriInfo,
            @BeanParam ServiceParameters params) throws Exception {
        return getCollection(uriInfo, sb.getCollection(), params);
    }

    @Since(CommonParams.VERSION_1)
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response createService(@Context UriInfo uriInfo,
            ServiceDetailsRepresentation content,
            @BeanParam ServiceParameters params) throws Exception {
        return post(uriInfo, sb.post(), content, params);
    }

    @Since(CommonParams.VERSION_1)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path(CommonParams.PATH_ID)
    public Response getService(@Context UriInfo uriInfo,
            @BeanParam ServiceParameters params) throws Exception {
        return get(uriInfo, sb.get(), params, true);
    }

    @Since(CommonParams.VERSION_1)
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Path(CommonParams.PATH_ID)
    public Response updateService(@Context UriInfo uriInfo,
            ServiceDetailsRepresentation content,
            @BeanParam ServiceParameters params) throws Exception {
        return put(uriInfo, sb.put(), content, params);
    }

    @Since(CommonParams.VERSION_1)
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path(CommonParams.PATH_ID)
    public Response deleteService(@Context UriInfo uriInfo,
            @BeanParam ServiceParameters params) throws Exception {
        return delete(uriInfo, sb.delete(), params);
    }

    @Since(CommonParams.VERSION_1)
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Path(CommonParams.PATH_ID + "/status")
    public Response setServiceState(@Context UriInfo uriInfo,
            StatusRepresentation content, @BeanParam ServiceParameters params)
            throws Exception {
        return put(uriInfo, sb.putStatus(), content, params);
    }

}
