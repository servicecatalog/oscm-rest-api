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

import org.oscm.rest.common.*;
import org.oscm.rest.identity.data.UserRepresentation;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Since(CommonParams.VERSION_1)
@Path(CommonParams.PATH_VERSION + "/ldapusers")
@Produces(MediaType.APPLICATION_JSON)
@Stateless
public class LdapUserResource extends RestResource {

  @EJB UserBackend ub;

  @GET
  public Response getLdapUsers(@Context UriInfo uriInfo, @BeanParam UserParameters params)
      throws Exception {
    //FIXME: LDAP endpoints are disabled for now as the functionality is currently not supported
    //return getCollection(uriInfo, ub.getLdapUsers(), params);
    return ResponseFactory.getInstance(ResponseType.NOT_IMPLEMENTED);
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public Response createLdapUser(
      @Context UriInfo uriInfo, UserRepresentation content, @BeanParam UserParameters params)
      throws Exception {
    //FIXME: LDAP endpoints are disabled for now as the functionality is currently not supported
//    return post(uriInfo, ub.postLdapUser(), content, params);
    return ResponseFactory.getInstance(ResponseType.NOT_IMPLEMENTED);
  }
}
