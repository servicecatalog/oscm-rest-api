/**
 * *****************************************************************************
 *
 * <p>Copyright FUJITSU LIMITED 2019
 *
 * <p>Creation Date: 10-04-2019
 *
 * <p>*****************************************************************************
 */
package org.oscm.rest.account;

import lombok.AccessLevel;
import lombok.Setter;
import org.oscm.rest.account.data.AccountRepresentation;
import org.oscm.rest.common.CommonParams;
import org.oscm.rest.common.RestResource;
import org.oscm.rest.common.Since;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path(CommonParams.PATH_VERSION + "/organizations")
@Stateless
public class OrganizationResource extends RestResource {

  @EJB @Setter(value = AccessLevel.PROTECTED) AccountBackend ab;

  @Since(CommonParams.VERSION_1)
  @POST
  @Produces(MediaType.APPLICATION_JSON)
  public Response createOrganization(
      @Context UriInfo uriInfo, AccountRepresentation content, @BeanParam AccountParameters params)
      throws Exception {
    return post(uriInfo, ab.postOrganization(), content, params);
  }

  @Since(CommonParams.VERSION_1)
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response getOrganization(@Context UriInfo uriInfo, @BeanParam AccountParameters params)
      throws Exception {
    return get(uriInfo, ab.getOrganization(), params, true);
  }
}
