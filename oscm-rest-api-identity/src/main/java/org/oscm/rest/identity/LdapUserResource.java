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

import org.oscm.rest.common.CommonParams;
import org.oscm.rest.common.RestResource;
import org.oscm.rest.common.Since;
import org.oscm.rest.identity.data.UserRepresentation;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path(CommonParams.PATH_VERSION + "/ldapusers")
@Stateless
public class LdapUserResource extends RestResource {

  @EJB UserBackend ub;

  @Since(CommonParams.VERSION_1)
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response getLdapUsers(@Context UriInfo uriInfo, @BeanParam UserParameters params)
      throws Exception {
    return getCollection(uriInfo, ub.getLdapUsers(), params);
  }

  @Since(CommonParams.VERSION_1)
  @POST
  @Produces(MediaType.APPLICATION_JSON)
  public Response createLdapUser(
      @Context UriInfo uriInfo, UserRepresentation content, @BeanParam UserParameters params)
      throws Exception {
    return post(uriInfo, ub.postLdapUser(), content, params);
  }
}
