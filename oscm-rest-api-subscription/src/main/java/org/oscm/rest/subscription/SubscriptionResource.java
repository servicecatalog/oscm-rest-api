/**
 * *****************************************************************************
 *
 * <p>Copyright FUJITSU LIMITED 2019
 *
 * <p>Creation Date: 10-04-2019
 *
 * <p>*****************************************************************************
 */
package org.oscm.rest.subscription;

import constants.CommonConstants;
import constants.DocDescription;
import constants.SubscriptionConstants;
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
import org.oscm.rest.common.representation.SubscriptionCreationRepresentation;
import org.oscm.rest.common.representation.SubscriptionUpdateRepresentation;
import org.oscm.rest.common.requestparameters.SubscriptionParameters;

@Path(CommonParams.PATH_VERSION + "/subscriptions")
@Stateless
public class SubscriptionResource extends RestResource {

  @EJB
  @Setter(value = AccessLevel.PROTECTED)
  SubscriptionBackend sb;

  @GET
  @Since(CommonParams.VERSION_1)
  @Produces(CommonParams.JSON)
  @Operation(
      summary = "Retrieves subscriptions for the specific user or for whole organization",
      tags = {"subscriptions"},
      description =
          "If a user id is provided, the subscription list for the user will be returned, "
              + "otherwise the entire subscription list for organization will be returned",
      responses = {
        @ApiResponse(
            responseCode = "200",
            description = "Subscriptions list",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = SubscriptionCreationRepresentation.class)))
      })
  public Response getSubscriptions(
      @Context UriInfo uriInfo,
      @Parameter(description = DocDescription.ENDPOINT_VERSION)
          @DefaultValue("v1")
          @PathParam(value = "version")
          String version,
      @Parameter(description = DocDescription.USER_ID) @QueryParam(value = "userId") String userId)
      throws Exception {
    final SubscriptionParameters params = new SubscriptionParameters();
    params.setEndpointVersion(version);
    params.setUserId(userId);
    return getCollection(uriInfo, sb.getCollection(), params);
  }

  @GET
  @Since(CommonParams.VERSION_1)
  @Path(CommonParams.PATH_ID)
  @Produces(CommonParams.JSON)
  @Operation(
      summary = "Retrieves a single subscription and its details",
      tags = {"subscriptions"},
      description = "Returns a single subscription and its details based on the provided id",
      responses = {
        @ApiResponse(
            responseCode = "200",
            description = "A single subscription",
            content =
                @Content(
                    schema = @Schema(implementation = SubscriptionCreationRepresentation.class)))
      })
  public Response getSubscription(
      @Context UriInfo uriInfo,
      @Parameter(description = DocDescription.ENDPOINT_VERSION)
          @DefaultValue("v1")
          @PathParam(value = "version")
          String version,
      @Parameter(description = DocDescription.SUBSCRIPTION_ID) @PathParam(value = "id") String id)
      throws Exception {
    final SubscriptionParameters params = new SubscriptionParameters();
    params.setEndpointVersion(version);
    params.setId(Long.valueOf(id));
    return get(uriInfo, sb.get(), params, true);
  }

  @POST
  @Since(CommonParams.VERSION_1)
  @Operation(
      summary = "Create a subscription",
      tags = {"subscriptions"},
      description = "Creates a subscription in current service based on given request data",
      requestBody =
          @RequestBody(
              description =
                  "JSON representing subscription to be created. It must contains reference to marketable service (serviceKey) for which subscription is going to be created. "
                      + "If service is not free of charge JSON must also contains references to payment information (paymentInfoId) and billing contact (billingContactId).",
              required = true,
              content =
                  @Content(
                      mediaType = "application/json",
                      schema = @Schema(implementation = SubscriptionCreationRepresentation.class),
                      examples = {
                        @ExampleObject(
                            name = CommonConstants.EXAMPLE_REQUEST_BODY_DESCRIPTION,
                            value = SubscriptionConstants.SUBSCRIPTION_CREATE_EXAMPLE_REQUEST,
                            summary = CommonConstants.EXAMPLE_REQUEST_BODY_SUMMARY)
                      })),
      responses = {
        @ApiResponse(
            responseCode = "201",
            description = "Subscription created successfully" + CommonConstants.ID_INFO)
      })
  public Response createSubscription(
      @Context UriInfo uriInfo,
      SubscriptionCreationRepresentation content,
      @Parameter(description = DocDescription.ENDPOINT_VERSION)
          @DefaultValue("v1")
          @PathParam(value = "version")
          String version)
      throws Exception {
    final SubscriptionParameters params = new SubscriptionParameters();
    params.setEndpointVersion(version);
    return post(uriInfo, sb.post(), content, params);
  }

  @PUT
  @Since(CommonParams.VERSION_1)
  @Path(CommonParams.PATH_ID)
  @Operation(
      summary = "Update a single subscription",
      tags = {"subscriptions"},
      description = "Updates a subscription based on given subscription id and request data",
      requestBody =
          @RequestBody(
              description = "JSON representing subscription to be updated",
              required = true,
              content =
                  @Content(
                      mediaType = "application/json",
                      schema = @Schema(implementation = SubscriptionCreationRepresentation.class),
                      examples = {
                        @ExampleObject(
                            name = CommonConstants.EXAMPLE_REQUEST_BODY_DESCRIPTION,
                            value = SubscriptionConstants.SUBSCRIPTION_UPDATE_EXAMPLE_REQUEST,
                            summary = CommonConstants.EXAMPLE_REQUEST_BODY_SUMMARY)
                      })),
      responses = {
        @ApiResponse(responseCode = "204", description = "Subscription updated successfully")
      })
  public Response updateSubscription(
      @Context UriInfo uriInfo,
      SubscriptionUpdateRepresentation content,
      @Parameter(description = DocDescription.ENDPOINT_VERSION)
          @DefaultValue("v1")
          @PathParam(value = "version")
          String version,
      @Parameter(description = DocDescription.SUBSCRIPTION_ID) @PathParam(value = "id") String id)
      throws Exception {
    final SubscriptionParameters params = new SubscriptionParameters();
    params.setEndpointVersion(version);
    params.setId(Long.valueOf(id));
    content.setId(params.getId());
    return put(uriInfo, sb.put(), content, params);
  }

  @DELETE
  @Since(CommonParams.VERSION_1)
  @Path(CommonParams.PATH_ID)
  @Operation(
      summary = "Delete a single subscription",
      tags = {"subscriptions"},
      description = "Delete a single subscription based on given subscription id",
      responses = {
        @ApiResponse(responseCode = "204", description = "Subscription deleted successfully")
      })
  public Response deleteSubscription(
      @Context UriInfo uriInfo,
      @Parameter(description = DocDescription.ENDPOINT_VERSION)
          @DefaultValue("v1")
          @PathParam(value = "version")
          String version,
      @Parameter(description = DocDescription.SUBSCRIPTION_ID) @PathParam(value = "id") String id)
      throws Exception {
    final SubscriptionParameters params = new SubscriptionParameters();
    params.setEndpointVersion(version);
    params.setId(Long.valueOf(id));
    return delete(uriInfo, sb.delete(), params);
  }
}
