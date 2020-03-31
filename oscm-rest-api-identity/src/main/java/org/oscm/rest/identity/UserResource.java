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
import constants.DocDescription;
import constants.IdentityConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AccessLevel;
import lombok.Setter;
import org.oscm.rest.common.CommonParams;
import org.oscm.rest.common.RestResource;
import org.oscm.rest.common.Since;
import org.oscm.rest.common.representation.UserRepresentation;
import org.oscm.rest.common.requestparameters.UserParameters;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path(CommonParams.PATH_VERSION + "/users")
@Stateless
public class UserResource extends RestResource {

  @EJB
  @Setter(value = AccessLevel.PROTECTED)
  UserBackend ub;

  /**
   * Represents the endpoint for getting all users for current organization
   *
   * @param uriInfo uri context information
   * @param version version of the endpoint
   * @return http response object containing json array of users
   * @throws Exception
   */
  @GET
  @Since(CommonParams.VERSION_1)
  @Operation(
      summary = "Retrieves all users.",
      tags = {"users"},
      description =
          "Returns all users of the organization. The organization is considered to be the one which client is currently authorized as user of.",
      responses = {
        @ApiResponse(
            responseCode = "200",
            description = "Users list",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = UserRepresentation.class),
                    examples = {@ExampleObject(IdentityConstants.USER_LIST_EXAMPLE_RESPONSE)}))
      })
  public Response getUsers(
      @Context UriInfo uriInfo,
      @Parameter(description = DocDescription.ENDPOINT_VERSION)
          @DefaultValue("v1")
          @PathParam(value = "version")
          String version)
      throws Exception {
    final UserParameters params = new UserParameters();
    params.setEndpointVersion(version);
    return getCollection(uriInfo, ub.getUsers(), params);
  }

  /**
   * Represents the endpoint for getting a single user
   *
   * @param uriInfo uri context information
   * @param version version of the endpoint
   * @param userId id of the user
   * @return http response object containing json representing the user
   * @throws Exception
   */
  @GET
  @Since(CommonParams.VERSION_1)
  @Path(CommonParams.PATH_USER_ID)
  @Operation(
      summary = "Retrieves a single user",
      tags = {"users"},
      description = "Returns a single user based on the provided user id",
      responses = {
        @ApiResponse(
            responseCode = "200",
            description = "A single user",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = UserRepresentation.class),
                    examples = {@ExampleObject(IdentityConstants.USER_EXAMPLE_RESPONSE)}))
      })
  public Response getUser(
      @Context UriInfo uriInfo,
      @Parameter(description = DocDescription.ENDPOINT_VERSION)
          @DefaultValue("v1")
          @PathParam(value = "version")
          String version,
      @Parameter(description = DocDescription.USER_ID) @PathParam(value = "userId") String userId)
      throws Exception {
    final UserParameters params = new UserParameters();
    params.setEndpointVersion(params.getEndpointVersion());
    params.setUserId(userId);
    return get(uriInfo, ub.getUser(), params, false);
  }

  /**
   * Represents the endpoint for creating a user
   *
   * @param uriInfo uri context information
   * @param content object representing user
   * @param version version of the endpoint
   * @return
   * @throws Exception
   */
  @POST
  @Since(CommonParams.VERSION_1)
  @Operation(
      summary = "Creates a user",
      tags = {"users"},
      description = "Creates a user in current organization based on given request data",
      requestBody =
          @RequestBody(
              description = "JSON representing user to be created",
              required = true,
              content =
                  @Content(
                      mediaType = "application/json",
                      schema = @Schema(implementation = UserRepresentation.class),
                      examples = {
                        @ExampleObject(
                            name = CommonConstants.EXAMPLE_REQUEST_BODY_DESCRIPTION,
                            value = IdentityConstants.USER_CREATE_EXAMPLE_BODY,
                            summary = CommonConstants.EXAMPLE_REQUEST_BODY_SUMMARY)
                      })),
      responses = {
        @ApiResponse(
            responseCode = "201",
            description = "User created successfully. " + CommonConstants.ID_INFO)
      })
  public Response createUser(
      @Context UriInfo uriInfo,
      UserRepresentation content,
      @Parameter(description = DocDescription.ENDPOINT_VERSION)
          @DefaultValue("v1")
          @PathParam(value = "version")
          String version)
      throws Exception {
    final UserParameters params = new UserParameters();
    params.setEndpointVersion(version);
    return post(uriInfo, ub.postUser(), content, params);
  }

  /**
   * Represents the endpoint for creating a user
   *
   * @param uriInfo uri context information
   * @param content object representing user
   * @param version version of the endpoint
   * @return http response with no content
   * @throws Exception
   */
  @PUT
  @Since(CommonParams.VERSION_1)
  @Path(CommonParams.PATH_USER_ID)
  @Operation(
      summary = "Updates a user",
      tags = {"users"},
      description = "Updates a user based on given user's id and request data",
      requestBody =
          @RequestBody(
              description = "JSON representing user to be updated",
              required = true,
              content =
                  @Content(
                      mediaType = "application/json",
                      schema = @Schema(implementation = UserRepresentation.class),
                      examples = {
                        @ExampleObject(
                            name = CommonConstants.EXAMPLE_PUT_REQUEST_BODY_DESCRIPTION,
                            value = IdentityConstants.USER_UPDATE_EXAMPLE_REQUEST,
                            summary = CommonConstants.EXAMPLE_REQUEST_BODY_SUMMARY)
                      })),
      responses = {@ApiResponse(responseCode = "201", description = "User updated successfully")})
  public Response updateUser(
      @Context UriInfo uriInfo,
      UserRepresentation content,
      @Parameter(description = DocDescription.ENDPOINT_VERSION)
          @DefaultValue("v1")
          @PathParam(value = "version")
          String version,
      @Parameter(description = DocDescription.USER_ID) @PathParam(value = "userId") String userId)
      throws Exception {
    final UserParameters params = new UserParameters();
    params.setEndpointVersion(version);
    params.setUserId(userId);
    return put(uriInfo, ub.putUser(), content, params);
  }

  /**
   * Represents the endpoint for deleting a user
   *
   * @param uriInfo uri context information
   * @param version version of the endpoint
   * @param userId id of the user
   * @return http response with no content
   * @throws Exception
   */
  @DELETE
  @Since(CommonParams.VERSION_1)
  @Path(CommonParams.PATH_USER_ID)
  @Operation(
      summary = "Deletes a single user",
      tags = {"users"},
      description = "Deletes a single user based on given user id",
      responses = {@ApiResponse(responseCode = "204", description = "User deleted successfully")})
  public Response deleteUser(
      @Context UriInfo uriInfo,
      @Parameter(description = DocDescription.ENDPOINT_VERSION)
          @DefaultValue("v1")
          @PathParam(value = "version")
          String version,
      @Parameter(description = DocDescription.USER_ID) @PathParam(value = "userId") String userId)
      throws Exception {
    final UserParameters params = new UserParameters();
    params.setEndpointVersion(version);
    params.setUserId(userId);
    return delete(uriInfo, ub.deleteUser(), params);
  }
}
