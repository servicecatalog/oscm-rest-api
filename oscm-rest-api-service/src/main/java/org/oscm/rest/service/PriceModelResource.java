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
import lombok.AccessLevel;
import lombok.Setter;
import org.oscm.rest.common.CommonParams;
import org.oscm.rest.common.RestResource;
import org.oscm.rest.common.Since;
import org.oscm.rest.service.data.PriceModelRepresentation;

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
@Path(CommonParams.PATH_VERSION + "/services" + CommonParams.PATH_ID + "/pricemodel")
@Stateless
public class PriceModelResource extends RestResource {

  @EJB
  @Setter(value = AccessLevel.PROTECTED)
  PriceModelBackend pmb;

  @GET
  @Operation(summary = "Get generic price model for the service",
          tags = {"services"},
          description = "Returns generic price model for the service",
          responses = {
                  @ApiResponse(responseCode = "200",
                          description = "Price model item",
                          content = @Content(
                                  mediaType = "application/json",
                                  schema = @Schema(implementation = PriceModelRepresentation.class)
                          ))
          })
  public Response get(@Context UriInfo uriInfo, @BeanParam ServiceParameters params)
      throws Exception {
    return get(uriInfo, pmb.get(), params, true);
  }

  @PUT
  @Operation(summary = "Update generic price model for the service",
          tags = {"services"},
          description = "Updates generic price model for the service",
          requestBody = @RequestBody(
                  description = "PriceModelRepresentation object to be updated",
                  required = true,
                  content = @Content(
                          schema = @Schema(implementation = PriceModelRepresentation.class),
                          examples = {
                                  @ExampleObject(
                                          name = CommonConstants.EXAMPLE_MINIMUM_BODY_NAME,
                                          value= ServiceConstants.SERVICE_PRICE_MODEL_MINIMUM_BODY,
                                          summary = CommonConstants.EXAMPLE_MINIMUM_BODY_SUMMARY),
                                  @ExampleObject(
                                          name = CommonConstants.EXAMPLE_MAXIMUM_BODY_NAME,
                                          value= ServiceConstants.SERVICE_PRICE_MODEL_MAXIMUM_BODY,
                                          summary = CommonConstants.EXAMPLE_MAXIMUM_BODY_SUMMARY)
                          })),
          responses = {
                  @ApiResponse(responseCode = "204", description = "Price model updated successfully")
          })
  public Response update(
          @Context UriInfo uriInfo,
          PriceModelRepresentation content,
          @BeanParam ServiceParameters params)
          throws Exception {
    return put(uriInfo, pmb.put(), content, params);
  }

  @GET
  @Path("/customer/{orgKey}")
  @Operation(summary = "Get customer-specific price model for service",
          tags = {"services"},
          description = "Returns customer-specific price model for the service"
                  + ServiceConstants.PRICE_MODEL_GET_PRE_STEPS,
          responses = {
                  @ApiResponse(responseCode = "200", description = "Price model for the service", content = @Content(
                          schema = @Schema(implementation = PriceModelRepresentation.class)
                  ))
          })
  public Response getForCustomer(@Context UriInfo uriInfo, @BeanParam ServiceParameters params)
          throws Exception {
    return get(uriInfo, pmb.getForCustomer(), params, true);
  }

  @PUT
  @Path("/customer/{orgKey}")
  @Operation(summary = "Update customer-specific price model for service",
          tags = {"services"},
          description = "Updates customer-specific price model for service",
          requestBody = @RequestBody(
                  description = "PriceModelRepresentation object to be updated",
                  required = true,
                  content = @Content(
                          schema = @Schema(implementation = PriceModelRepresentation.class),
                          examples = {
                                  @ExampleObject(
                                          name = CommonConstants.EXAMPLE_MINIMUM_BODY_NAME + ". "
                                                  + ServiceConstants.PRICE_MODEL_PUT_PRE_STEPS,
                                          value= ServiceConstants.CUSTOMER_PRICE_MODEL_MINIMUM_BODY,
                                          summary = CommonConstants.EXAMPLE_MINIMUM_BODY_SUMMARY),
                                  @ExampleObject(
                                          name = CommonConstants.EXAMPLE_MAXIMUM_BODY_NAME + ". "
                                                  + ServiceConstants.PRICE_MODEL_PUT_PRE_STEPS,
                                          value= ServiceConstants.CUSTOMER_PRICE_MODEL_MAXIMUM_BODY,
                                          summary = CommonConstants.EXAMPLE_MAXIMUM_BODY_SUMMARY)
                          })),
          responses = {
                  @ApiResponse(responseCode = "204", description = "Price model updated successfully")
          })
  public Response updateForCustomer(
      @Context UriInfo uriInfo,
      PriceModelRepresentation content,
      @BeanParam ServiceParameters params)
      throws Exception {
    return put(uriInfo, pmb.putForCustomer(), content, params);
  }
}
