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

import constants.CommonConstants;
import constants.IdentityConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import lombok.AccessLevel;
import lombok.Setter;
import org.oscm.rest.common.CommonParams;
import org.oscm.rest.common.RestResource;
import org.oscm.rest.common.Since;
import org.oscm.rest.common.representation.UserRepresentation;
import org.oscm.rest.common.requestparameters.UserParameters;

@Path(CommonParams.PATH_VERSION + "/users")
@Stateless
public class UserResource extends RestResource {

  private static final String PATH_USERID = "/{userId}";

  @EJB
  @Setter(value = AccessLevel.PROTECTED)
  UserBackend ub;

  @GET
  @Since(CommonParams.VERSION_1)
  @Operation(
      summary = "Get all users.",
      tags = {"users"},
      description = "Returns all users.",
      responses = {
        @ApiResponse(
            responseCode = "200",
            description = "Users list",
            content = @Content(schema = @Schema(implementation = UserRepresentation.class)))
      })
  public Response getUsers(@Context UriInfo uriInfo, @BeanParam UserParameters params)
      throws Exception {
    return getCollection(uriInfo, ub.getUsers(), params);
  }

  @GET
  @Since(CommonParams.VERSION_1)
  @Path(PATH_USERID)
  @Operation(
      summary = "Get a single user",
      tags = {"users"},
      description = "Returns a single user",
      responses = {
        @ApiResponse(
            responseCode = "200",
            description = "A single user",
            content = @Content(schema = @Schema(implementation = UserRepresentation.class)))
      })
  public Response getUser(@Context UriInfo uriInfo, @BeanParam UserParameters params)
      throws Exception {
    return get(uriInfo, ub.getUser(), params, false);
  }

  @POST
  @Since(CommonParams.VERSION_1)
  @Operation(
      summary = "Create a user",
      tags = {"users"},
      description = "Creates a user",
      requestBody =
          @RequestBody(
              description = "UserRepresentation object to be created",
              required = true,
              content =
                  @Content(
                      schema = @Schema(implementation = UserRepresentation.class),
                      examples = {
                        @ExampleObject(
                            name = CommonConstants.EXAMPLE_MINIMUM_BODY_NAME,
                            value = IdentityConstants.USER_MINIMUM_BODY,
                            summary = CommonConstants.EXAMPLE_MINIMUM_BODY_SUMMARY),
                        @ExampleObject(
                            name = CommonConstants.EXAMPLE_MAXIMUM_BODY_NAME,
                            value = IdentityConstants.USER_MAXIMUM_BODY,
                            summary = CommonConstants.EXAMPLE_MAXIMUM_BODY_SUMMARY)
                      })),
      responses = {
        @ApiResponse(
            responseCode = "201",
            description = "User created successfully" + CommonConstants.ID_INFO)
      })
  public Response createUser(
      @Context UriInfo uriInfo, UserRepresentation content, @BeanParam UserParameters params)
      throws Exception {
    return post(uriInfo, ub.postUser(), content, params);
  }

  @PUT
  @Since(CommonParams.VERSION_1)
  @Path(PATH_USERID)
  @Operation(
      summary = "Update a user",
      tags = {"users"},
      description = "Updates a user",
      requestBody =
          @RequestBody(
              description = "UserRepresentation object to be updated",
              required = true,
              content =
                  @Content(
                      schema = @Schema(implementation = UserRepresentation.class),
                      examples = {
                        @ExampleObject(
                            name = CommonConstants.EXAMPLE_MINIMUM_BODY_NAME,
                            value = IdentityConstants.USER_MIN_PUT_BODY,
                            summary = CommonConstants.EXAMPLE_MINIMUM_BODY_SUMMARY),
                        @ExampleObject(
                            name = CommonConstants.EXAMPLE_MAXIMUM_BODY_NAME,
                            value = IdentityConstants.USER_MAX_PUT_BODY,
                            summary = CommonConstants.EXAMPLE_MAXIMUM_BODY_SUMMARY)
                      })),
      responses = {@ApiResponse(responseCode = "201", description = "User created successfully")})
  public Response updateUser(
      @Context UriInfo uriInfo, UserRepresentation content, @BeanParam UserParameters params)
      throws Exception {
    return put(uriInfo, ub.putUser(), content, params);
  }

  @DELETE
  @Since(CommonParams.VERSION_1)
  @Path(PATH_USERID)
  @Operation(
      summary = "Delete a single user",
      tags = {"users"},
      description = "Deletes a single user",
      responses = {@ApiResponse(responseCode = "204", description = "User deleted successfully")})
  public Response deleteUser(@Context UriInfo uriInfo, @BeanParam UserParameters params)
      throws Exception {
    return delete(uriInfo, ub.deleteUser(), params);
  }
}
