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

import constants.IdentityConstants;
import io.swagger.v3.oas.annotations.Operation;
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
import org.oscm.rest.identity.data.RolesRepresentation;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Since(CommonParams.VERSION_1)
@Path(CommonParams.PATH_VERSION + "/users/{userId}/userroles")
@Stateless
public class RolesResource extends RestResource {

  @EJB
  @Setter(value = AccessLevel.PROTECTED)
  UserBackend ub;

  @GET
  @Operation(summary = "Get all roles of user",
          tags = {"roles"},
          description = "Returns all roles of user",
          responses = {
                  @ApiResponse(responseCode = "200",
                          description = "Roles list",
                          content = @Content(
                                  mediaType = "application/json",
                                  schema = @Schema(implementation = RolesRepresentation.class)
                          ))
          })
  public Response getUserRoles(@Context UriInfo uriInfo, @BeanParam UserParameters params)
      throws Exception {
    return get(uriInfo, ub.getRoles(), params, false);
  }

  @PUT
  @Operation(summary = "Update a single role of user",
          tags = {"roles"},
          description = "Updates a single role of user",
          requestBody = @RequestBody(
                  description = "RolesRepresentation object to be updated",
                  required = true,
                  content = @Content(
                          schema = @Schema(implementation = RolesRepresentation.class),
                          examples = {
                                  @ExampleObject(
                                          name = "Only required parameters",
                                          value= IdentityConstants.ROLE_EXAMPLE_BODY,
                                          summary = "Minimum body example"),
                                  @ExampleObject(
                                          name = "All possible parameters",
                                          value= IdentityConstants.ROLE_EXAMPLE_BODY,
                                          summary = "Maximum body example")
                          })),
          responses = {
                  @ApiResponse(responseCode = "204", description = "Role updated successfully")
          })
  public Response setUserRoles(
      @Context UriInfo uriInfo, RolesRepresentation content, @BeanParam UserParameters params)
      throws Exception {
    return put(uriInfo, ub.putRoles(), content, params);
  }
}
