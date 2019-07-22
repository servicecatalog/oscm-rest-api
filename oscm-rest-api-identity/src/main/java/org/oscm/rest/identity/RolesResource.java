/**
 * *****************************************************************************
 *
 * <p>Copyright FUJITSU LIMITED 2019
 *
 * <p>Creation Date: 10-04-2019
 *
 * <p>*****************************************************************************
 */
package org.oscm.rest.identity;

import lombok.AccessLevel;
import lombok.Setter;
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

@Since(CommonParams.VERSION_1)
@Path(CommonParams.PATH_VERSION + "/users/{userId}/userroles")
@Produces(MediaType.APPLICATION_JSON)
@Stateless
public class RolesResource extends RestResource {

  @EJB
  @Setter(value = AccessLevel.PROTECTED)
  UserBackend ub;

  @GET
  public Response getUserRoles(@Context UriInfo uriInfo, @BeanParam UserParameters params)
      throws Exception {
    return get(uriInfo, ub.getRoles(), params, false);
  }

  @PUT
  @Consumes(MediaType.APPLICATION_JSON)
  public Response setUserRoles(
      @Context UriInfo uriInfo, RolesRepresentation content, @BeanParam UserParameters params)
      throws Exception {
    return put(uriInfo, ub.putRoles(), content, params);
  }
}
