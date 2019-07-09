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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AccessLevel;
import lombok.Setter;
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

@Path(CommonParams.PATH_VERSION + "/users")
@Stateless
@Produces(MediaType.APPLICATION_JSON)
@Since(CommonParams.VERSION_1)
public class UserResource extends RestResource {

  private static final String PATH_USERID = "/{userId}";

  @EJB
  @Setter(value = AccessLevel.PROTECTED)
  UserBackend ub;

  @GET
  @Operation(summary = "Get all users.",
          tags = {"identity"},
          description = "Returns all users.",
          responses = {
                  @ApiResponse(responseCode = "200", description = "Users list", content = @Content(
                          schema = @Schema(implementation = UserRepresentation.class)
                  ))
          })
  public Response getUsers(@Context UriInfo uriInfo, @BeanParam UserParameters params)
      throws Exception {
    return getCollection(uriInfo, ub.getUsers(), params);
  }

  @POST
  public Response createUser(
      @Context UriInfo uriInfo, UserRepresentation content, @BeanParam UserParameters params)
      throws Exception {
    return post(uriInfo, ub.postUser(), content, params);
  }

  @GET
  @Path(PATH_USERID)
  public Response getUser(@Context UriInfo uriInfo, @BeanParam UserParameters params)
      throws Exception {
    return get(uriInfo, ub.getUser(), params, false);
  }

  @PUT
  @Path(PATH_USERID)
  public Response updateUser(
      @Context UriInfo uriInfo, UserRepresentation content, @BeanParam UserParameters params)
      throws Exception {
    return put(uriInfo, ub.putUser(), content, params);
  }

  @DELETE
  @Path(PATH_USERID)
  public Response deleteUser(@Context UriInfo uriInfo, @BeanParam UserParameters params)
      throws Exception {
    return delete(uriInfo, ub.deleteUser(), params);
  }
}
