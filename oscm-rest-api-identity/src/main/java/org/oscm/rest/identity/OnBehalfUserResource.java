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
import org.oscm.rest.common.requestparameters.UserParameters;
import org.oscm.rest.common.representation.OnBehalfUserRepresentation;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path(CommonParams.PATH_VERSION + "/onbehalfusers")
@Stateless
public class OnBehalfUserResource extends RestResource {

  @EJB
  @Setter(value = AccessLevel.PROTECTED)
  UserBackend ub;

  @Since(CommonParams.VERSION_1)
  @POST
  @Produces(MediaType.APPLICATION_JSON)
  public Response createOnBehalfUser(
      @Context UriInfo uriInfo,
      OnBehalfUserRepresentation content,
      @BeanParam UserParameters params)
      throws Exception {
    return post(uriInfo, ub.postOnBehalfUser(), content, params);
  }

  @Since(CommonParams.VERSION_1)
  @DELETE
  @Produces(MediaType.APPLICATION_JSON)
  public Response deleteOnBehalfUser(@Context UriInfo uriInfo, @BeanParam UserParameters params)
      throws Exception {
    params.setUserIdRequired(false);
    return delete(uriInfo, ub.deleteOBehalfUser(), params);
  }
}
