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
import org.oscm.rest.common.representation.RolesRepresentation;
import org.oscm.rest.common.requestparameters.UserParameters;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path(CommonParams.PATH_VERSION + "/users/{userId}/userroles")
@Stateless
public class RolesResource extends RestResource {

  @EJB
  @Setter(value = AccessLevel.PROTECTED)
  UserBackend ub;

  @GET
  @Since(CommonParams.VERSION_1)
  @Operation(
      summary = "Retrieves all roles of the user",
      tags = {"roles"},
      description = "Returns all roles of the given user based on the user id",
      responses = {
        @ApiResponse(
            responseCode = "200",
            description = "User's roles list",
            content =
                @Content(
                    mediaType = "application/json",
                    examples = {@ExampleObject(IdentityConstants.USER_ROLE_EXAMPLE_RESPONSE)}))
      })
  public Response getUserRoles(
      @Context UriInfo uriInfo,
      @Parameter(description = DocDescription.ENDPOINT_VERSION)
          @DefaultValue("v1")
          @PathParam(value = "version")
          String version,
      @Parameter(description = DocDescription.USER_ID) @PathParam(value = "userId") String userId)
      throws Exception {
    UserParameters params = new UserParameters();
    params.setEndpointVersion(version);
    params.setUserId(userId);
    return get(uriInfo, ub.getRoles(), params, false);
  }

  @PUT
  @Since(CommonParams.VERSION_1)
  @Operation(
      summary = "Updates roles of the user",
      tags = {"roles"},
      description = "Updates roles of the given user based on the user id",
      requestBody =
          @RequestBody(
              description = "JSON representing user roles to be updated for the given user",
              required = true,
              content =
                  @Content(
                      mediaType = "application/json",
                      schema = @Schema(implementation = RolesRepresentation.class),
                      examples = {
                        @ExampleObject(
                            name = CommonConstants.EXAMPLE_REQUEST_BODY_DESCRIPTION,
                            value = IdentityConstants.USER_ROLE_UPDATE_EXAMPLE_REQUEST,
                            summary = CommonConstants.EXAMPLE_REQUEST_BODY_SUMMARY)
                      })),
      responses = {@ApiResponse(responseCode = "204", description = "Roles updated successfully")})
  public Response setUserRoles(
      @Context UriInfo uriInfo,
      RolesRepresentation content,
      @Parameter(description = DocDescription.ENDPOINT_VERSION)
          @DefaultValue("v1")
          @PathParam(value = "version")
          String version,
      @Parameter(description = DocDescription.USER_ID) @PathParam(value = "userId") String userId)
      throws Exception {
    UserParameters params = new UserParameters();
    params.setEndpointVersion(version);
    params.setUserId(userId);
    return put(uriInfo, ub.putRoles(), content, params);
  }
}
