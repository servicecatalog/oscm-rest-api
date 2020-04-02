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

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.oscm.rest.common.CommonParams;
import org.oscm.rest.common.ResponseFactory;
import org.oscm.rest.common.ResponseType;
import org.oscm.rest.common.RestResource;
import org.oscm.rest.common.Since;
import org.oscm.rest.common.representation.UserRepresentation;
import org.oscm.rest.common.requestparameters.UserParameters;

@Path(CommonParams.PATH_VERSION + "/ldapusers")
@Stateless
public class LdapUserResource extends RestResource {

  @EJB UserBackend ub;

  @GET
  @Since(CommonParams.VERSION_1)
  @Produces(CommonParams.JSON)
  public Response getLdapUsers(@Context UriInfo uriInfo, @BeanParam UserParameters params)
      throws Exception {
    // FIXME: LDAP endpoints are disabled for now as the functionality is currently not supported
    // return getCollection(uriInfo, ub.getLdapUsers(), params);
    return ResponseFactory.getInstance(ResponseType.NOT_IMPLEMENTED);
  }

  @POST
  @Since(CommonParams.VERSION_1)
  public Response createLdapUser(
      @Context UriInfo uriInfo, UserRepresentation content, @BeanParam UserParameters params)
      throws Exception {
    // FIXME: LDAP endpoints are disabled for now as the functionality is currently not supported
    //    return post(uriInfo, ub.postLdapUser(), content, params);
    return ResponseFactory.getInstance(ResponseType.NOT_IMPLEMENTED);
  }
}
