package org.oscm.rest.identity;

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

import org.oscm.rest.common.CommonParams;
import org.oscm.rest.common.RestResource;
import org.oscm.rest.common.Since;
import org.oscm.rest.identity.data.UserRepresentation;

@Path(CommonParams.PATH_VERSION + "/ldapusers")
@Stateless
public class LdapUserResource extends RestResource {

    @EJB
    UserBackend ub;

    @Since(CommonParams.VERSION_1)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLdapUsers(@Context UriInfo uriInfo,
            @BeanParam UserParameters params) throws Exception {
        return getCollection(uriInfo, ub.getLdapUsers(), params);
    }

    @Since(CommonParams.VERSION_1)
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response createLdapUser(@Context UriInfo uriInfo,
            UserRepresentation content, @BeanParam UserParameters params)
            throws Exception {
        // UserResource.class, "getUser"
        return post(uriInfo, ub.postLdapUser(), content, params);
    }

}
