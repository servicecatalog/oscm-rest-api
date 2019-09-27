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
import constants.OperationConstants;
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
import org.oscm.rest.common.representation.SettingRepresentation;
import org.oscm.rest.common.requestparameters.OperationParameters;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Since(CommonParams.VERSION_1)
@Path(CommonParams.PATH_VERSION + "/settings")
@Stateless
public class SettingsResource extends RestResource {

  @EJB
  @Setter(value = AccessLevel.PROTECTED)
  SettingsBackend sb;

  @GET
  @Operation(
      summary = "Get all settings",
      tags = {"settings"},
      description = "Returns all settings",
      responses = {
        @ApiResponse(
            responseCode = "200",
            description = "Settings list",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = SettingRepresentation.class)))
      })
  public Response getSettings(@Context UriInfo uriInfo, @BeanParam OperationParameters params)
      throws Exception {
    return getCollection(uriInfo, sb.getCollection(), params);
  }

  @GET
  @Path(CommonParams.PATH_ID)
  @Operation(
      summary = "Get a single setting",
      tags = {"settings"},
      description = "Returns a single setting",
      responses = {
        @ApiResponse(
            responseCode = "200",
            description = "A single setting",
            content = @Content(schema = @Schema(implementation = SettingRepresentation.class)))
      })
  public Response getSetting(@Context UriInfo uriInfo, @BeanParam OperationParameters params)
      throws Exception {
    return get(uriInfo, sb.get(), params, true);
  }

  @POST
  @Operation(
      summary = "Create a setting",
      tags = {"settings"},
      description = "Creates a setting",
      requestBody =
          @RequestBody(
              description = "SettingsRepresentation object to be created",
              required = true,
              content =
                  @Content(
                      schema = @Schema(implementation = SettingRepresentation.class),
                      examples = {
                        @ExampleObject(
                            name = CommonConstants.EXAMPLE_MAXIMUM_BODY_NAME,
                            value = OperationConstants.SETTING_POST_BODY,
                            summary = CommonConstants.EXAMPLE_MAXIMUM_BODY_SUMMARY)
                      })),
      responses = {
        @ApiResponse(responseCode = "201", description = "Setting created successfully")
      })
  public Response createSetting(
      @Context UriInfo uriInfo,
      SettingRepresentation content,
      @BeanParam OperationParameters params)
      throws Exception {
    return post(uriInfo, sb.post(), content, params);
  }

  @PUT
  @Path(CommonParams.PATH_ID)
  @Operation(
      summary = "Update a single setting",
      tags = {"settings"},
      description = "Updates a single setting",
      requestBody =
          @RequestBody(
              description = "SettingRepresentation object to be updated",
              required = true,
              content =
                  @Content(
                      schema = @Schema(implementation = SettingRepresentation.class),
                      examples = {
                        @ExampleObject(
                            name = CommonConstants.EXAMPLE_MAXIMUM_BODY_NAME,
                            value = OperationConstants.SETTING_PUT_BODY,
                            summary = CommonConstants.EXAMPLE_MAXIMUM_BODY_SUMMARY)
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
  @Path(CommonParams.PATH_ID)
  @Operation(
      summary = "Delete a single setting",
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
