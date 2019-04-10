package org.oscm.rest.identity;

import org.oscm.rest.common.CommonParams;
import org.oscm.rest.common.RestResource;
import org.oscm.rest.common.Since;
import org.oscm.rest.identity.data.RolesRepresentation;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path(CommonParams.PATH_VERSION + "/users/{userId}/userroles")
@Stateless
public class RolesResource extends RestResource {

        @EJB
        UserBackend ub;

        @Since(CommonParams.VERSION_1)
        @GET
        @Produces(MediaType.APPLICATION_JSON)
        public Response getUserRoles(@Context UriInfo uriInfo,
                @BeanParam UserParameters params)
                throws Exception {
                return get(uriInfo, ub.getRoles(), params, false);
        }

        @Since(CommonParams.VERSION_1)
        @PUT
        @Produces(MediaType.APPLICATION_JSON)
        public Response setUserRoles(
                @Context UriInfo uriInfo, RolesRepresentation content,
                @BeanParam UserParameters params)
                throws Exception {
                return put(uriInfo, ub.putRoles(), content, params);
        }
}
