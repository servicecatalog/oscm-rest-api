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
import io.swagger.v3.oas.annotations.Hidden;
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
import org.oscm.rest.common.representation.SettingRepresentation;
import org.oscm.rest.common.requestparameters.OperationParameters;

@Path(CommonParams.PATH_VERSION + "/settings")
@Stateless
public class SettingsResource extends RestResource {

  @EJB
  @Setter(value = AccessLevel.PROTECTED)
  SettingsBackend sb;

  @GET
  @Since(CommonParams.VERSION_1)
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
  @Since(CommonParams.VERSION_1)
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
  @Since(CommonParams.VERSION_1)
  @Hidden //Hidden due to a fact that only updating settings is currently possible. Creating requires major refactoring.
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
  @Since(CommonParams.VERSION_1)
  @Path(CommonParams.PATH_ID)
  @Operation(
      summary = "Update a single setting",
      tags = {"settings"},
      description = "Updates a single setting. Please use get endpoint to see the possible settings. \n" +
              "It is important to include the \"id\" with value in the json body, because the setting to be updated.",
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
  @Since(CommonParams.VERSION_1)
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
