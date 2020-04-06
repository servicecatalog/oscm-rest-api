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
import org.oscm.rest.common.representation.PriceModelRepresentation;
import org.oscm.rest.common.requestparameters.ServiceParameters;

@Path(CommonParams.PATH_VERSION + "/services" + CommonParams.PATH_ID + "/pricemodel")
@Stateless
public class PriceModelResource extends RestResource {

  @EJB
  @Setter(value = AccessLevel.PROTECTED)
  PriceModelBackend pmb;

  @GET
  @Since(CommonParams.VERSION_1)
  @Produces(CommonParams.JSON)
  @Operation(
      summary = "Retrieves price model for the service",
      tags = {"services"},
      description = "Returns price model defined for the given service",
      responses = {
        @ApiResponse(
            responseCode = "200",
            description = "Price model item",
            content =
                @Content(
                    mediaType = "application/json",
                    examples = {@ExampleObject(ServiceConstants.PRICE_MODEL_EXAMPLE_RESPONSE)},
                    schema = @Schema(implementation = PriceModelRepresentation.class)))
      })
  public Response get(
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
    return get(uriInfo, pmb.get(), params, true);
  }

  @GET
  @Since(CommonParams.VERSION_1)
  @Path("/customer/{orgKey}")
  @Produces(CommonParams.JSON)
  @Operation(
      summary = "Retrieves customer-specific price model for service",
      tags = {"services"},
      description = "Returns price model for the service for the specific customer",
      responses = {
        @ApiResponse(
            responseCode = "200",
            description = "Price model for the service",
            content =
                @Content(
                    mediaType = "application/json",
                    examples = {
                      @ExampleObject(ServiceConstants.CUSTOMER_PRICE_MODEL_EXAMPLE_RESPONSE)
                    },
                    schema = @Schema(implementation = PriceModelRepresentation.class)))
      })
  public Response getForCustomer(
      @Context UriInfo uriInfo,
      @Parameter(description = DocDescription.ENDPOINT_VERSION)
          @DefaultValue("v1")
          @PathParam(value = "version")
          String version,
      @Parameter(description = DocDescription.SERVICE_ID) @PathParam(value = "id") String id,
      @Parameter(description = DocDescription.ORGANIZATION_KEY) @PathParam(value = "orgKey")
          String orgKey)
      throws Exception {
    ServiceParameters params = new ServiceParameters();
    params.setEndpointVersion(version);
    params.setId(Long.valueOf(id));
    params.setOrgKey(Long.valueOf(orgKey));
    return get(uriInfo, pmb.getForCustomer(), params, true);
  }

  @PUT
  @Since(CommonParams.VERSION_1)
  @Operation(
      summary = "Updates price model for the service",
      tags = {"services"},
      description = "Updates price model for the service based on given request data",
      requestBody =
          @RequestBody(
              description = "JSON representing price model object to be updated",
              required = true,
              content =
                  @Content(
                      mediaType = "application/json",
                      schema = @Schema(implementation = PriceModelRepresentation.class),
                      examples = {
                        @ExampleObject(
                            name = CommonConstants.EXAMPLE_PUT_REQUEST_BODY_DESCRIPTION,
                            value = ServiceConstants.PRICE_MODEL_UPDATE_EXAMPLE_REQUEST,
                            summary = CommonConstants.EXAMPLE_REQUEST_BODY_SUMMARY)
                      })),
      responses = {
        @ApiResponse(responseCode = "204", description = "Price model updated successfully")
      })
  public Response update(
      @Context UriInfo uriInfo,
      PriceModelRepresentation content,
      @Parameter(description = DocDescription.ENDPOINT_VERSION)
          @DefaultValue("v1")
          @PathParam(value = "version")
          String version,
      @Parameter(description = DocDescription.SERVICE_ID) @PathParam(value = "id") String id)
      throws Exception {

    ServiceParameters params = new ServiceParameters();
    params.setEndpointVersion(version);
    params.setId(Long.valueOf(id));
    return put(uriInfo, pmb.put(), content, params);
  }

  @PUT
  @Since(CommonParams.VERSION_1)
  @Path("/customer/{orgKey}")
  @Operation(
      summary = "Update customer-specific price model for service",
      tags = {"services"},
      description = "Updates customer-specific price model for service based on given request data",
      requestBody =
          @RequestBody(
              description = "JSON representing price model object to be updated",
              required = true,
              content =
                  @Content(
                      mediaType = "application/json",
                      schema = @Schema(implementation = PriceModelRepresentation.class),
                      examples = {
                        @ExampleObject(
                            name = CommonConstants.EXAMPLE_REQUEST_BODY_SUMMARY,
                            value = ServiceConstants.PRICE_MODEL_UPDATE_EXAMPLE_REQUEST,
                            summary = CommonConstants.EXAMPLE_PUT_REQUEST_BODY_DESCRIPTION)
                      })),
      responses = {
        @ApiResponse(responseCode = "204", description = "Price model updated successfully")
      })
  public Response updateForCustomer(
      @Context UriInfo uriInfo,
      PriceModelRepresentation content,
      @Parameter(description = DocDescription.ENDPOINT_VERSION)
          @DefaultValue("v1")
          @PathParam(value = "version")
          String version,
      @Parameter(description = DocDescription.SERVICE_ID) @PathParam(value = "id") String id,
      @Parameter(description = DocDescription.ORGANIZATION_KEY) @PathParam(value = "orgKey")
          String orgKey)
      throws Exception {
    ServiceParameters params = new ServiceParameters();
    params.setEndpointVersion(version);
    params.setId(Long.valueOf(id));
    params.setOrgKey(Long.valueOf(orgKey));
    return put(uriInfo, pmb.putForCustomer(), content, params);
  }
}
