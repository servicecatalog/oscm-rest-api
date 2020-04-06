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
import lombok.AccessLevel;
import lombok.Setter;
import org.oscm.rest.common.CommonParams;
import org.oscm.rest.common.RestResource;
import org.oscm.rest.common.Since;
import org.oscm.rest.common.representation.RepresentationCollection;
import org.oscm.rest.common.representation.ServiceRepresentation;
import org.oscm.rest.common.requestparameters.ServiceParameters;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path(CommonParams.PATH_VERSION + "/services" + CommonParams.PATH_ID + "/compatibleservices")
@Stateless
public class CompatibleServiceResource extends RestResource {

  @EJB
  @Setter(value = AccessLevel.PROTECTED)
  ServiceBackend sb;

  @GET
  @Since(CommonParams.VERSION_1)
  @Produces(CommonParams.JSON)
  @Operation(
      summary = "Retrieves all services compatible to selected service",
      tags = {"services"},
      description = "Returns all services compatible to selected service ",
      responses = {
        @ApiResponse(
            responseCode = "200",
            description = "Services list",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ServiceRepresentation.class)))
      })
  public Response getCompatibleServices(
      @Context UriInfo uriInfo,
      @Parameter(description = DocDescription.ENDPOINT_VERSION)
          @DefaultValue("v1")
          @PathParam(value = "version")
          String version,
      @Parameter(description = DocDescription.SERVICE_ID) @PathParam(value = "id") String id)
      throws Exception {
    ServiceParameters params = new ServiceParameters();
    params.setEndpointVersion(version);
    params.setId(Long.valueOf(id));
    return getCollection(uriInfo, sb.getCompatibles(), params);
  }

  @PUT
  @Since(CommonParams.VERSION_1)
  @Operation(
      summary = "Update service's compatibility relation",
      tags = {"services"},
      description = "Updates service's compatibility relation",
      requestBody =
          @RequestBody(
              description = "JSON representing compatible services objects to be set",
              required = true,
              content =
                  @Content(
                      mediaType = "application/json",
                      schema = @Schema(implementation = ServiceRepresentation.class),
                      examples = {
                        @ExampleObject(
                            name = CommonConstants.EXAMPLE_PUT_REQUEST_BODY_DESCRIPTION,
                            value = ServiceConstants.COMPATIBLE_SERVICE_MAXIMUM_BODY,
                            summary = CommonConstants.EXAMPLE_REQUEST_BODY_SUMMARY)
                      })),
      responses = {
        @ApiResponse(
            responseCode = "204",
            description = "Service's compatibility relation updated successfully")
      })
  public Response setCompatibleServices(
      @Context UriInfo uriInfo,
      RepresentationCollection<ServiceRepresentation> content,
      @Parameter(description = DocDescription.ENDPOINT_VERSION)
          @DefaultValue("v1")
          @PathParam(value = "version")
          String version,
      @Parameter(description = DocDescription.SERVICE_ID) @PathParam(value = "id") String id)
      throws Exception {
    ServiceParameters params = new ServiceParameters();
    params.setEndpointVersion(version);
    params.setId(Long.valueOf(id));
    params.setEtag(content.getETag());
    return put(uriInfo, sb.putCompatibles(), content, params);
  }
}
