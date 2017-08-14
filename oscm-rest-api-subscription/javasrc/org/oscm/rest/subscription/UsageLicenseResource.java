package org.oscm.rest.subscription;

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
import org.oscm.rest.subscription.data.UsageLicenseRepresentation;

@Path(CommonParams.PATH_VERSION + "/subscriptions" + CommonParams.PATH_ID
        + "/usagelicenses")
@Stateless
public class UsageLicenseResource extends RestResource {

    @EJB
    UsageLicenseBackend ulb;

    @Since(CommonParams.VERSION_1)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLicenses(@Context Request request,
            @BeanParam SubscriptionParameters params) throws Exception {
        return getCollection(request, ulb.getCollection(), params);
    }

    @Since(CommonParams.VERSION_1)
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createLicense(@Context Request request,
            UsageLicenseRepresentation content,
            @BeanParam SubscriptionParameters params) throws Exception {
        return post(request, ulb.post(), content, params);
    }

    @Since(CommonParams.VERSION_1)
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{licKey}")
    public Response updateLicense(@Context Request request,
            UsageLicenseRepresentation content,
            @BeanParam SubscriptionParameters params) throws Exception {
        return put(request, ulb.put(), content, params);
    }

    @Since(CommonParams.VERSION_1)
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{licKey}")
    public Response deleteLicense(@Context Request request,
            @BeanParam SubscriptionParameters params) throws Exception {
        return delete(request, ulb.delete(), params);
    }

}
