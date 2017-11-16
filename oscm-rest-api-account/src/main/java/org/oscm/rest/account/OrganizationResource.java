package org.oscm.rest.account;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.oscm.rest.account.data.AccountRepresentation;
import org.oscm.rest.common.CommonParams;
import org.oscm.rest.common.RestResource;
import org.oscm.rest.common.Since;

@Path(CommonParams.PATH_VERSION + "/organizations")
@Stateless
public class OrganizationResource extends RestResource {

    @EJB
    AccountBackend ab;

    @Since(CommonParams.VERSION_1)
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response createOrganization(@Context UriInfo uriInfo,
            AccountRepresentation content, @BeanParam AccountParameters params)
            throws Exception {
        return post(uriInfo, ab.postOrganization(), content, params);
    }

    @Since(CommonParams.VERSION_1)
    @GET
    @Path("/{orgId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOrganization(@Context UriInfo uriInfo,
            @BeanParam AccountParameters params) throws Exception {
        return get(uriInfo, ab.getOrganization(), params, true);
    }

}
