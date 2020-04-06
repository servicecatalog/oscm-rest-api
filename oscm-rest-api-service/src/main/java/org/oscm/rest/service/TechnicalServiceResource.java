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
import constants.DocDescription;
import constants.ServiceConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
import org.oscm.rest.common.representation.TechnicalServiceRepresentation;
import org.oscm.rest.common.requestparameters.ServiceParameters;

@Path(CommonParams.PATH_VERSION + "/technicalservices")
@Stateless
public class TechnicalServiceResource extends RestResource {

  @EJB
  @Setter(value = AccessLevel.PROTECTED)
  TechnicalServiceBackend tsb;

  @GET
  @Since(CommonParams.VERSION_1)
  @Produces(CommonParams.JSON)
  @Operation(
      summary = "Retrieves all available technical services",
      tags = {"technicalservices"},
      description = "Returns all available technical services",
      responses = {
        @ApiResponse(
            responseCode = "200",
            description = "Technical services list",
            content =
                @Content(
                    mediaType = "application/json",
                    examples = {
                      @ExampleObject(ServiceConstants.TECHNICAL_SERVICE_LIST_EXAMPLE_RESPONSE)
                    },
                    schema = @Schema(implementation = TechnicalServiceRepresentation.class)))
      })
  public Response getTechnicalServices(
      @Context UriInfo uriInfo,
      @Parameter(description = DocDescription.ENDPOINT_VERSION)
          @DefaultValue("v1")
          @PathParam(value = "version")
          String version)
      throws Exception {
    ServiceParameters params = new ServiceParameters();
    params.setEndpointVersion(version);
    return getCollection(uriInfo, tsb.getCollection(), params);
  }

  @POST
  @Since(CommonParams.VERSION_1)
  @Operation(
      summary = "Creates a technical service",
      tags = {"technicalservices"},
      description = "Creates a technical service based on given request data",
      requestBody =
          @RequestBody(
              description = "JSON representing technical service object to be created",
              required = true,
              content =
                  @Content(
                      mediaType = "application/json",
                      schema = @Schema(implementation = TechnicalServiceRepresentation.class),
                      examples = {
                        @ExampleObject(
                            name = CommonConstants.EXAMPLE_REQUEST_BODY_DESCRIPTION,
                            value = ServiceConstants.TECHNICAL_SERVICE_CREATE_EXAMPLE_BODY,
                            summary = CommonConstants.EXAMPLE_REQUEST_BODY_SUMMARY)
                      })),
      responses = {
        @ApiResponse(
            responseCode = "201",
            description = "Technical service created successfully" + CommonConstants.ID_INFO)
      })
  public Response createTechnicalService(
      @Context UriInfo uriInfo,
      @Parameter(description = DocDescription.ENDPOINT_VERSION)
          @DefaultValue("v1")
          @PathParam(value = "version")
          String version,
      TechnicalServiceRepresentation content)
      throws Exception {
    ServiceParameters params = new ServiceParameters();
    params.setEndpointVersion(version);
    return post(uriInfo, tsb.post(), content, params);
  }

  @DELETE
  @Since(CommonParams.VERSION_1)
  @Path(CommonParams.PATH_ID)
  @Operation(
      summary = "Delete a single technical service",
      tags = {"technicalservices"},
      description = "Deletes a single technical service based on given id of the object",
      responses = {
        @ApiResponse(responseCode = "204", description = "Technical service deleted successfully")
      })
  public Response deleteTechnicalService(
      @Context UriInfo uriInfo,
      @Parameter(description = DocDescription.ENDPOINT_VERSION)
          @DefaultValue("v1")
          @PathParam(value = "version")
          String version,
      @Parameter(description = DocDescription.OBJECT_ID) @PathParam(value = "id") String id)
      throws Exception {
    ServiceParameters params = new ServiceParameters();
    params.setEndpointVersion(version);
    params.setId(Long.valueOf(id));
    return delete(uriInfo, tsb.delete(), params);
  }
}
