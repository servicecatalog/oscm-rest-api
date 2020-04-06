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

import constants.AccountConstants;
import constants.CommonConstants;
import constants.DocDescription;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import lombok.AccessLevel;
import lombok.Setter;
import org.oscm.rest.common.CommonParams;
import org.oscm.rest.common.RestResource;
import org.oscm.rest.common.Since;
import org.oscm.rest.common.representation.PaymentInfoRepresentation;
import org.oscm.rest.common.requestparameters.AccountParameters;

@Path(CommonParams.PATH_VERSION + "/paymentinfos")
@Stateless
public class PaymentInfoResource extends RestResource {

  @EJB
  @Setter(value = AccessLevel.PROTECTED)
  AccountBackend ab;

  @GET
  @Since(CommonParams.VERSION_1)
  @Produces(CommonParams.JSON)
  @Operation(
      summary = "Retrieves all payment information of the organization",
      tags = {"paymentinfos"},
      description =
          "Returns all payment information of the organization. The organization is considered to be the one which client is currently authorized as user of",
      responses = {
        @ApiResponse(
            responseCode = "200",
            description = "Payment information list",
            content =
                @Content(
                    mediaType = "application/json",
                    examples = {
                      @ExampleObject(AccountConstants.PAYMENT_INFO_LIST_EXAMPLE_RESPONSE)
                    }))
      })
  public Response getPaymentInfos(
      @Context UriInfo uriInfo,
      @Parameter(description = DocDescription.ENDPOINT_VERSION)
          @DefaultValue("v1")
          @PathParam(value = "version")
          String version)
      throws Exception {
    AccountParameters params = new AccountParameters();
    params.setEndpointVersion(version);
    return getCollection(uriInfo, ab.getPaymentInfoCollection(), params);
  }

  @GET
  @Since(CommonParams.VERSION_1)
  @Path(CommonParams.PATH_ID)
  @Produces(CommonParams.JSON)
  @Operation(
      summary = "Retrieves a single payment information",
      tags = {"paymentinfos"},
      description = "Returns a single payment information based on the provided object id",
      responses = {
        @ApiResponse(
            responseCode = "200",
            description = "A single payment information",
            content =
                @Content(
                    mediaType = "application/json",
                    examples = {
                      @ExampleObject(value = AccountConstants.PAYMENT_INFO_EXAMPLE_RESPONSE)
                    }))
      })
  public Response getPaymentInfo(
      @Context UriInfo uriInfo,
      @Parameter(description = DocDescription.ENDPOINT_VERSION)
          @DefaultValue("v1")
          @PathParam(value = "version")
          String version,
      @Parameter(description = DocDescription.OBJECT_ID) @PathParam(value = "id") String id)
      throws Exception {
    AccountParameters params = new AccountParameters();
    params.setEndpointVersion(version);
    params.setId(Long.valueOf(id));
    return get(uriInfo, ab.getPaymentInfo(), params, true);
  }

  @PUT
  @Since(CommonParams.VERSION_1)
  @Path(CommonParams.PATH_ID)
  @Operation(
      summary = "Updates a single payment information",
      tags = {"paymentinfos"},
      description =
          "Updates a single payment info based on given id of the object and request data",
      requestBody =
          @RequestBody(
              description = "JSON representing billing contact to be updated",
              required = true,
              content =
                  @Content(
                      mediaType = "application/json",
                      schema = @Schema(implementation = PaymentInfoRepresentation.class),
                      examples = {
                        @ExampleObject(
                            name = CommonConstants.EXAMPLE_PUT_REQUEST_BODY_DESCRIPTION,
                            value = AccountConstants.PAYMENT_INFO_UPDATE_EXAMPLE_REQUEST,
                            summary = CommonConstants.EXAMPLE_REQUEST_BODY_SUMMARY)
                      })),
      responses = {
        @ApiResponse(responseCode = "204", description = "Payment info updated successfully")
      })
  public Response updatePaymentInfo(
      @Context UriInfo uriInfo,
      PaymentInfoRepresentation content,
      @Parameter(description = DocDescription.ENDPOINT_VERSION)
          @DefaultValue("v1")
          @PathParam(value = "version")
          String version,
      @Parameter(description = DocDescription.OBJECT_ID) @PathParam(value = "id") String id)
      throws Exception {
    AccountParameters params = new AccountParameters();
    params.setEndpointVersion(version);
    params.setId(Long.valueOf(id));
    content.setId(params.getId());
    return put(uriInfo, ab.putPaymentInfo(), content, params);
  }

  @DELETE
  @Since(CommonParams.VERSION_1)
  @Path(CommonParams.PATH_ID)
  @Operation(
      summary = "Delete a single payment information",
      tags = {"paymentinfos"},
      description = "Deletes a single payment information based on given id of the object",
      responses = {
        @ApiResponse(responseCode = "204", description = "Payment info deleted successfully")
      })
  public Response deletePaymentInfo(
      @Context UriInfo uriInfo,
      @Parameter(description = DocDescription.ENDPOINT_VERSION)
          @DefaultValue("v1")
          @PathParam(value = "version")
          String version,
      @Parameter(description = DocDescription.OBJECT_ID) @PathParam(value = "id") String id)
      throws Exception {
    AccountParameters params = new AccountParameters();
    params.setEndpointVersion(version);
    params.setId(Long.valueOf(id));
    return delete(uriInfo, ab.deletePaymentInfo(), params);
  }
}
