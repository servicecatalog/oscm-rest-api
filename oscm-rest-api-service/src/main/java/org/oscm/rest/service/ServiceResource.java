/**
 * *****************************************************************************
 *
 * <p>Copyright FUJITSU LIMITED 2019
 *
 * <p>Creation Date: 10-04-2019
 *
 * <p>*****************************************************************************
 */
package org.oscm.rest.service;

import constants.CommonConstants;
import constants.ServiceConstants;
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
import org.oscm.rest.common.representation.ServiceDetailsRepresentation;
import org.oscm.rest.common.representation.ServiceRepresentation;
import org.oscm.rest.common.representation.StatusRepresentation;
import org.oscm.rest.common.requestparameters.ServiceParameters;

@Path(CommonParams.PATH_VERSION + "/services")
@Stateless
public class ServiceResource extends RestResource {

  @EJB
  @Setter(value = AccessLevel.PROTECTED)
  ServiceBackend sb;

  @GET
  @Since(CommonParams.VERSION_1)
  @Operation(
      summary = "Get all services",
      tags = {"services"},
      description = "Returns services available for current user",
      responses = {
        @ApiResponse(
            responseCode = "200",
            description = "Services list",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ServiceRepresentation.class)))
      })
  public Response getServices(@Context UriInfo uriInfo, @BeanParam ServiceParameters params)
      throws Exception {
    return getCollection(uriInfo, sb.getCollection(), params);
  }

  @GET
  @Since(CommonParams.VERSION_1)
  @Path(CommonParams.PATH_ID)
  @Operation(
      summary = "Get a single service",
      tags = {"services"},
      description = "Returns a single service",
      responses = {
        @ApiResponse(
            responseCode = "200",
            description = "A single service",
            content = @Content(schema = @Schema(implementation = ServiceRepresentation.class)))
      })
  public Response getService(@Context UriInfo uriInfo, @BeanParam ServiceParameters params)
      throws Exception {
    return get(uriInfo, sb.get(), params, true);
  }

  @POST
  @Since(CommonParams.VERSION_1)
  @Operation(
      summary = "Create a service",
      tags = {"services"},
      description = "Creates a service",
      requestBody =
          @RequestBody(
              description = "ServiceRepresentation object to be created",
              required = true,
              content =
                  @Content(
                      schema = @Schema(implementation = ServiceRepresentation.class),
                      examples = {
                        @ExampleObject(
                            name = CommonConstants.EXAMPLE_MINIMUM_BODY_NAME,
                            value = ServiceConstants.SERVICE_MINIMUM_BODY,
                            summary = CommonConstants.EXAMPLE_MINIMUM_BODY_SUMMARY),
                        @ExampleObject(
                            name = CommonConstants.EXAMPLE_MAXIMUM_BODY_NAME,
                            value = ServiceConstants.SERVICE_MAXIMUM_BODY,
                            summary = CommonConstants.EXAMPLE_MAXIMUM_BODY_SUMMARY)
                      })),
      responses = {
        @ApiResponse(responseCode = "201", description = "Service created successfully")
      })
  public Response createService(
      @Context UriInfo uriInfo,
      ServiceDetailsRepresentation content,
      @BeanParam ServiceParameters params)
      throws Exception {
    return post(uriInfo, sb.post(), content, params);
  }

  @PUT
  @Since(CommonParams.VERSION_1)
  @Path(CommonParams.PATH_ID)
  @Operation(
      summary = "Update a single service",
      tags = {"services"},
      description = "Updates a single service",
      requestBody =
          @RequestBody(
              description = "ServiceRepresentation object to be updated",
              required = true,
              content =
                  @Content(
                      schema = @Schema(implementation = ServiceRepresentation.class),
                      examples = {
                        @ExampleObject(
                            name = CommonConstants.EXAMPLE_MINIMUM_BODY_NAME,
                            value = ServiceConstants.SERVICE_MINIMUM_BODY,
                            summary = CommonConstants.EXAMPLE_MINIMUM_BODY_SUMMARY),
                        @ExampleObject(
                            name = CommonConstants.EXAMPLE_MAXIMUM_BODY_NAME,
                            value = ServiceConstants.SERVICE_MAXIMUM_BODY,
                            summary = CommonConstants.EXAMPLE_MAXIMUM_BODY_SUMMARY)
                      })),
      responses = {
        @ApiResponse(responseCode = "204", description = "Service updated successfully")
      })
  public Response updateService(
      @Context UriInfo uriInfo,
      ServiceDetailsRepresentation content,
      @BeanParam ServiceParameters params)
      throws Exception {
    return put(uriInfo, sb.put(), content, params);
  }

  @PUT
  @Since(CommonParams.VERSION_1)
  @Path(CommonParams.PATH_ID + "/status")
  @Operation(
      summary = "Update service status",
      tags = {"services"},
      description = "Updates service status",
      requestBody =
          @RequestBody(
              description = "ServiceRepresentation object to be updated",
              required = true,
              content =
                  @Content(
                      schema = @Schema(implementation = ServiceRepresentation.class),
                      examples = {
                        @ExampleObject(
                            name = CommonConstants.EXAMPLE_MINIMUM_BODY_NAME,
                            value = ServiceConstants.SERVICE_MINIMUM_BODY,
                            summary = CommonConstants.EXAMPLE_MINIMUM_BODY_SUMMARY),
                        @ExampleObject(
                            name = CommonConstants.EXAMPLE_MAXIMUM_BODY_NAME,
                            value = ServiceConstants.SERVICE_MAXIMUM_BODY,
                            summary = CommonConstants.EXAMPLE_MAXIMUM_BODY_SUMMARY)
                      })),
      responses = {
        @ApiResponse(responseCode = "204", description = "Service status updated successfully")
      })
  public Response setServiceState(
      @Context UriInfo uriInfo, StatusRepresentation content, @BeanParam ServiceParameters params)
      throws Exception {
    return put(uriInfo, sb.putStatus(), content, params);
  }

  @DELETE
  @Since(CommonParams.VERSION_1)
  @Path(CommonParams.PATH_ID)
  @Operation(
      summary = "Delete a single service",
      tags = {"services"},
      description = "Deletes a single service",
      responses = {
        @ApiResponse(responseCode = "204", description = "Service deleted successfully")
      })
  public Response deleteService(@Context UriInfo uriInfo, @BeanParam ServiceParameters params)
      throws Exception {
    return delete(uriInfo, sb.delete(), params);
  }
}
