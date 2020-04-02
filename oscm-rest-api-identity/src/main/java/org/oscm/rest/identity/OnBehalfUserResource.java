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
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.oscm.rest.common.CommonParams;
import org.oscm.rest.common.RestResource;
import org.oscm.rest.common.Since;
import org.oscm.rest.common.representation.OnBehalfUserRepresentation;
import org.oscm.rest.common.requestparameters.UserParameters;

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

@Path(CommonParams.PATH_VERSION + "/onbehalfusers")
@Stateless
public class OnBehalfUserResource extends RestResource {

  @EJB
  @Setter(value = AccessLevel.PROTECTED)
  UserBackend ub;

  @POST
  @Since(CommonParams.VERSION_1)
  @Operation(
      summary = "Creates a single on behalf user",
      tags = {"onbehalfusers"},
      description =
          "Creates a single on behalf user. To create an on behalf user is just possible if the given organization is acting on behalf",
      requestBody =
          @RequestBody(
              description = "JSON representing on behalf user to be updated",
              required = true,
              content =
                  @Content(
                      schema = @Schema(implementation = OnBehalfUserRepresentation.class),
                      examples = {
                        @ExampleObject(
                            name = CommonConstants.EXAMPLE_REQUEST_BODY_DESCRIPTION,
                            value = IdentityConstants.ONBEHALFUSERS_EXAMPLE_REQUEST,
                            summary = CommonConstants.EXAMPLE_REQUEST_BODY_SUMMARY)
                      })),
      responses = {
        @ApiResponse(
            responseCode = "204",
            description = "On behalf user created successfully" + CommonConstants.ID_INFO)
      })
  public Response createOnBehalfUser(
      @Context UriInfo uriInfo,
      OnBehalfUserRepresentation content,
      @Parameter(description = DocDescription.ENDPOINT_VERSION)
          @DefaultValue("v1")
          @PathParam(value = "version")
          String version)
      throws Exception {
    UserParameters params = new UserParameters();
    params.setEndpointVersion(version);
    return post(uriInfo, ub.postOnBehalfUser(), content, params);
  }

  @DELETE
  @Since(CommonParams.VERSION_1)
  @Operation(
      summary = "Deletes a single on behalf user",
      tags = {"onbehalfusers"},
      description = "Deletes a single on behalf user",
      responses = {
        @ApiResponse(responseCode = "204", description = "On behalf user deleted successfully")
      })
  public Response deleteOnBehalfUser(
      @Context UriInfo uriInfo,
      @Parameter(description = DocDescription.ENDPOINT_VERSION)
          @DefaultValue("v1")
          @PathParam(value = "version")
          String version,
      @Parameter(description = DocDescription.ONBEHALFUSER_ID) @PathParam(value = "id") String id)
      throws Exception {
    UserParameters params = new UserParameters();
    params.setEndpointVersion(version);
    params.setId(Long.valueOf(id));
    params.setUserIdRequired(false);
    return delete(uriInfo, ub.deleteOBehalfUser(), params);
  }
}
