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
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import lombok.AccessLevel;
import lombok.Setter;
import org.oscm.rest.common.CommonParams;
import org.oscm.rest.common.RestResource;
import org.oscm.rest.common.Since;
import org.oscm.rest.common.representation.BillingContactRepresentation;
import org.oscm.rest.common.requestparameters.AccountParameters;

@Path(CommonParams.PATH_VERSION + "/billingcontacts")
@Stateless
public class BillingContactResource extends RestResource {

  @EJB
  @Setter(value = AccessLevel.PROTECTED)
  AccountBackend ab;

  @GET
  @Since(CommonParams.VERSION_1)
  @Operation(
      summary = "Retrieves all billing contacts of the organization",
      tags = {"billingcontacts"},
      description =
          "Returns all billing contacts of the organization. The organization is considered to be the one as user of which client is currently authorized",
      responses = {
        @ApiResponse(
            responseCode = "200",
            description = "Billing contacts list",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = BillingContactRepresentation.class)))
      })
  public Response getBillingContacts(
      @Context UriInfo uriInfo,
      @Parameter(description = DocDescription.ENDPOINT_VERSION)
          @DefaultValue("v1")
          @PathParam(value = "version")
          String version)
      throws Exception {
    AccountParameters params = new AccountParameters();
    params.setEndpointVersion(version);
    return getCollection(uriInfo, ab.getBillingContactCollection(), params);
  }

  @GET
  @Since(CommonParams.VERSION_1)
  @Path(CommonParams.PATH_ID)
  @Operation(
      summary = "Retrieves a single billing contact",
      tags = {"billingcontacts"},
      description = "Returns a single billing contact based on the provided object id",
      responses = {
        @ApiResponse(
            responseCode = "200",
            description = "A single billing contact",
            content =
                @Content(schema = @Schema(implementation = BillingContactRepresentation.class)))
      })
  public Response getBillingContact(
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
    return get(uriInfo, ab.getBillingContact(), params, true);
  }

  @POST
  @Since(CommonParams.VERSION_1)
  @Operation(
      summary = "Creates a billing contact",
      tags = {"billingcontacts"},
      description = "Creates a billing contact based on given request data",
      requestBody =
          @RequestBody(
              description = "JSON representing billing contact to be created",
              required = true,
              content =
                  @Content(
                      schema = @Schema(implementation = BillingContactRepresentation.class),
                      examples = {
                        @ExampleObject(
                            name = CommonConstants.EXAMPLE_REQUEST_BODY_DESCRIPTION,
                            value = AccountConstants.BILLING_CONTACT_EXAMPLE_BODY,
                            summary = CommonConstants.EXAMPLE_REQUEST_BODY_SUMMARY)
                      })),
      responses = {
        @ApiResponse(
            responseCode = "201",
            description = "Billing contact created successfully. " + CommonConstants.ID_INFO)
      })
  public Response createBillingContact(
      @Context UriInfo uriInfo,
      BillingContactRepresentation content,
      @Parameter(description = DocDescription.ENDPOINT_VERSION)
          @DefaultValue("v1")
          @PathParam(value = "version")
          String version)
      throws Exception {
    AccountParameters params = new AccountParameters();
    params.setEndpointVersion(version);
    return post(uriInfo, ab.postBillingContact(), content, params);
  }

  @PUT
  @Since(CommonParams.VERSION_1)
  @Path(CommonParams.PATH_ID)
  @Operation(
      summary = "Updates a single billing contact",
      tags = {"billingcontacts"},
      description =
          "Updates a single billing contact based on given id of the object and request data",
      requestBody =
          @RequestBody(
              description = "JSON representing billing contact to be updated",
              required = true,
              content =
                  @Content(
                      schema = @Schema(implementation = BillingContactRepresentation.class),
                      examples = {
                        @ExampleObject(
                            name = CommonConstants.EXAMPLE_REQUEST_BODY_DESCRIPTION,
                            value = AccountConstants.BILLING_CONTACT_EXAMPLE_BODY,
                            summary = CommonConstants.EXAMPLE_REQUEST_BODY_SUMMARY)
                      })),
      responses = {
        @ApiResponse(responseCode = "204", description = "Billing contact updated successfully")
      })
  public Response updateBillingContact(
      @Context UriInfo uriInfo,
      BillingContactRepresentation content,
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
    return put(uriInfo, ab.putBillingContact(), content, params);
  }

  @DELETE
  @Since(CommonParams.VERSION_1)
  @Path(CommonParams.PATH_ID)
  @Operation(
      summary = "Deletes a single billing contact",
      tags = {"billingcontacts"},
      description = "Deletes a single billing contact based on given id of the object",
      responses = {
        @ApiResponse(responseCode = "204", description = "Billing contact deleted successfully")
      })
  public Response deleteBillingContact(
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
    return delete(uriInfo, ab.deleteBillingContact(), params);
  }
}
