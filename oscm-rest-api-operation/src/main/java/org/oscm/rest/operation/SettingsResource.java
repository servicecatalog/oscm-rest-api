/**
 * *****************************************************************************
 *
 * <p>Copyright FUJITSU LIMITED 2019
 *
 * <p>Creation Date: 10-04-2019
 *
 * <p>*****************************************************************************
 */
package org.oscm.rest.operation;

import constants.CommonConstants;
import constants.DocDescription;
import constants.OperationConstants;
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
import org.oscm.rest.common.representation.SettingRepresentation;
import org.oscm.rest.common.requestparameters.OperationParameters;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path(CommonParams.PATH_VERSION + "/settings")
@Stateless
public class SettingsResource extends RestResource {

  @EJB
  @Setter(value = AccessLevel.PROTECTED)
  SettingsBackend sb;

  @GET
  @Since(CommonParams.VERSION_1)
  @Operation(
      summary = "Retrieves all settings",
      tags = {"settings"},
      description = "Returns all settings",
      responses = {
        @ApiResponse(
            responseCode = "200",
            description = "Settings list",
            content =
                @Content(
                    mediaType = "application/json",
                    examples = {@ExampleObject(OperationConstants.SETTING_LIST_EXAMPLE_RESPONSE)},
                    schema = @Schema(implementation = SettingRepresentation.class)))
      })
  public Response getSettings(
      @Context UriInfo uriInfo,
      @Parameter(description = DocDescription.ENDPOINT_VERSION)
          @DefaultValue("v1")
          @PathParam(value = "version")
          String version)
      throws Exception {
    OperationParameters params = new OperationParameters();
    params.setEndpointVersion(version);
    return getCollection(uriInfo, sb.getCollection(), params);
  }

  @GET
  @Since(CommonParams.VERSION_1)
  @Path(CommonParams.PATH_ID)
  @Operation(
      summary = "Retrieves a single setting",
      tags = {"settings"},
      description = "Returns a single setting based on provided object id",
      responses = {
        @ApiResponse(
            responseCode = "200",
            description = "A single setting",
            content =
                @Content(
                    mediaType = "application/json",
                    examples = {@ExampleObject(OperationConstants.SETTING_EXAMPLE_RESPONSE)},
                    schema = @Schema(implementation = SettingRepresentation.class)))
      })
  public Response getSetting(@Context UriInfo uriInfo, @BeanParam OperationParameters params)
      throws Exception {
    return get(uriInfo, sb.get(), params, true);
  }

  @PUT
  @Since(CommonParams.VERSION_1)
  @Path(CommonParams.PATH_ID)
  @Operation(
      summary = "Updates a single setting",
      tags = {"settings"},
      description = "Updates a single setting based on given request data",
      requestBody =
          @RequestBody(
              description = "JSON representing setting object to be updated",
              required = true,
              content =
                  @Content(
                      mediaType = "application/json",
                      schema = @Schema(implementation = SettingRepresentation.class),
                      examples = {
                        @ExampleObject(
                            name = CommonConstants.EXAMPLE_PUT_REQUEST_BODY_DESCRIPTION,
                            value = OperationConstants.SETTING_UPDATED_EXAMPLE_REQUEST,
                            summary = CommonConstants.EXAMPLE_REQUEST_BODY_SUMMARY)
                      })),
      responses = {
        @ApiResponse(responseCode = "204", description = "Setting updated successfully")
      })
  public Response updateSetting(
      @Context UriInfo uriInfo,
      SettingRepresentation content,
      @BeanParam OperationParameters params)
      throws Exception {
    return put(uriInfo, sb.put(), content, params);
  }

  @DELETE
  @Since(CommonParams.VERSION_1)
  @Path(CommonParams.PATH_ID)
  @Operation(
      summary = "Deletes a single setting",
      tags = {"settings"},
      description = "Deletes a single setting",
      responses = {
        @ApiResponse(responseCode = "204", description = "Setting deleted successfully")
      })
  public Response deleteSetting(@Context UriInfo uriInfo, @BeanParam OperationParameters params)
      throws Exception {
    return delete(uriInfo, sb.delete(), params);
  }
}
