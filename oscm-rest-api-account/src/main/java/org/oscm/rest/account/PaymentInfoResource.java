/**
 * *****************************************************************************
 *
 * <p>Copyright FUJITSU LIMITED 2019
 *
 * <p>Creation Date: 10-04-2019
 *
 * <p>*****************************************************************************
 */
package org.oscm.rest.account;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AccessLevel;
import lombok.Setter;
import org.oscm.rest.account.data.PaymentInfoRepresentation;
import org.oscm.rest.common.CommonParams;
import org.oscm.rest.common.RestResource;
import org.oscm.rest.common.Since;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path(CommonParams.PATH_VERSION + "/paymentinfos")
@Produces(MediaType.APPLICATION_JSON)
@Since(CommonParams.VERSION_1)
@Stateless
public class PaymentInfoResource extends RestResource {

  @EJB
  @Setter(value = AccessLevel.PROTECTED)
  AccountBackend ab;

  @GET
  @Operation(summary = "Get all payment info.",
          tags = {"paymentinfo"},
          description = "Returns all payment info.",
          responses = {
                  @ApiResponse(responseCode = "200", description = "Payment info list", content = @Content(
                          schema = @Schema(implementation = PaymentInfoRepresentation.class)
                  ))
          })
  public Response getPaymentInfos(@Context UriInfo uriInfo, @BeanParam AccountParameters params)
      throws Exception {
    return getCollection(uriInfo, ab.getPaymentInfoCollection(), params);
  }

  @GET
  @Path(CommonParams.PATH_ID)
  @Operation(summary = "Get a single payment info.",
          tags = {"paymentinfo"},
          description = "Returns a single payment info.",
          responses = {
                  @ApiResponse(responseCode = "200", description = "A single payment info", content = @Content(
                          schema = @Schema(implementation = PaymentInfoRepresentation.class)
                  ))
          })
  public Response getPaymentInfo(@Context UriInfo uriInfo, @BeanParam AccountParameters params)
      throws Exception {
    return get(uriInfo, ab.getPaymentInfo(), params, true);
  }

  @PUT
  @Path(CommonParams.PATH_ID)
  @Operation(summary = "Update a single payment info.",
          tags = {"paymentinfo"},
          description = "Updates a single payment info.",
          responses = {
                  @ApiResponse(responseCode = "204", description = "Payment info updated successfully.")
          })
  public Response updatePaymentInfo(
      @Context UriInfo uriInfo,
      @RequestBody(description = "BillingContactRepresentation object to be updated.", required = true,
              content = @Content(
                      schema = @Schema(implementation = PaymentInfoRepresentation.class))) PaymentInfoRepresentation content, @BeanParam AccountParameters params)
      throws Exception {
    return put(uriInfo, ab.putPaymentInfo(), content, params);
  }

  @DELETE
  @Path(CommonParams.PATH_ID)
  @Operation(summary = "Delete a single payment info.",
          tags = {"paymentinfo"},
          description = "Deletes a single payment info.",
          responses = {
                  @ApiResponse(responseCode = "204", description = "Payment info deleted successfully.")
          })
  public Response deletePaymentInfo(@Context UriInfo uriInfo, @BeanParam AccountParameters params)
      throws Exception {
    return delete(uriInfo, ab.deletePaymentInfo(), params);
  }
}
