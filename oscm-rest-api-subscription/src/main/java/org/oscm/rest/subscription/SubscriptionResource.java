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
import constants.SubscriptionConstants;
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
import org.oscm.rest.subscription.data.SubscriptionCreationRepresentation;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Since(CommonParams.VERSION_1)
@Path(CommonParams.PATH_VERSION + "/subscriptions")
@Stateless
public class SubscriptionResource extends RestResource {

  @EJB
  @Setter(value = AccessLevel.PROTECTED)
  SubscriptionBackend sb;

  @GET
  @Operation(summary = "Get all subscriptions to the service for a user",
          tags = {"subscriptions"},
          description = "Returns all subscriptions to the service for a user",
          responses = {
                  @ApiResponse(responseCode = "200",
                          description = "Subscriptions list",
                          content = @Content(
                                  mediaType = "application/json",
                                  schema = @Schema(implementation = SubscriptionCreationRepresentation.class)
                          ))
          })
  public Response getSubscriptions(
      @Context UriInfo uriInfo, @BeanParam SubscriptionParameters params) throws Exception {
    return getCollection(uriInfo, sb.getCollection(), params);
  }

  @GET
  @Path(CommonParams.PATH_ID)
  @Operation(summary = "Get a single subscription to the service for a user",
          tags = {"subscriptions"},
          description = "Returns a single subscription to the service for a user",
          responses = {
                  @ApiResponse(responseCode = "200", description = "A single subscription", content = @Content(
                          schema = @Schema(implementation = SubscriptionCreationRepresentation.class)
                  ))
          })
  public Response getSubscription(
          @Context UriInfo uriInfo, @BeanParam SubscriptionParameters params) throws Exception {
    return get(uriInfo, sb.get(), params, true);
  }

  @POST
  @Operation(summary = "Create a subscription",
          tags = {"subscriptions"},
          description = "Creates a subscription",
          requestBody = @RequestBody(
                  description = "SubscriptionCreationRepresentation object to be created",
                  required = true,
                  content = @Content(
                          schema = @Schema(implementation = SubscriptionCreationRepresentation.class),
                          examples = {
                                  @ExampleObject(
                                          name = CommonConstants.EXAMPLE_MINIMUM_BODY_NAME
                                                  + SubscriptionConstants.SUBSCRIPTION_POST_PRE_STEPS,
                                          value= SubscriptionConstants.SUBSCRIPTION_MINIMUM_BODY,
                                          summary = CommonConstants.EXAMPLE_MINIMUM_BODY_SUMMARY),
                                  @ExampleObject(
                                          name = CommonConstants.EXAMPLE_MAXIMUM_BODY_NAME
                                                  + SubscriptionConstants.SUBSCRIPTION_POST_PRE_STEPS,
                                          value= SubscriptionConstants.SUBSCRIPTION_MAXIMUM_BODY,
                                          summary = CommonConstants.EXAMPLE_MAXIMUM_BODY_SUMMARY)
                          }
                  )),
          responses = {
                  @ApiResponse(responseCode = "201", description = "Subscription created successfully")
          })
  public Response createSubscription(
      @Context UriInfo uriInfo,
      SubscriptionCreationRepresentation content,
      @BeanParam SubscriptionParameters params)
      throws Exception {
    return post(uriInfo, sb.post(), content, params);
  }

  @PUT
  @Path(CommonParams.PATH_ID)
  @Operation(summary = "Update a single subscription",
          tags = {"subscriptions"},
          description = "Updates a single subscription",
          requestBody = @RequestBody(
                  description = "SubscriptionCreationRepresentation object to be updated",
                  required = true,
                  content = @Content(
                          schema = @Schema(implementation = SubscriptionCreationRepresentation.class),
                          examples = {
                                  @ExampleObject(
                                          name = CommonConstants.EXAMPLE_MINIMUM_BODY_NAME + ". " +
                                                  SubscriptionConstants.SUBSCRIPTION_PUT_PRE_STEPS,
                                          value= SubscriptionConstants.SUBSCRIPTION_MINIMUM_BODY,
                                          summary = CommonConstants.EXAMPLE_MINIMUM_BODY_SUMMARY),
                                  @ExampleObject(
                                          name = CommonConstants.EXAMPLE_MAXIMUM_BODY_NAME + ". " +
                                                  SubscriptionConstants.SUBSCRIPTION_PUT_PRE_STEPS,
                                          value= SubscriptionConstants.SUBSCRIPTION_MAXIMUM_BODY,
                                          summary = CommonConstants.EXAMPLE_MAXIMUM_BODY_SUMMARY)
                          }
                  )),
          responses = {
                  @ApiResponse(responseCode = "204", description = "Subscription updated successfully")
          })
  public Response updateSubscription(
      @Context UriInfo uriInfo,
      SubscriptionCreationRepresentation content,
      @BeanParam SubscriptionParameters params)
      throws Exception {
    return put(uriInfo, sb.put(), content, params);
  }

  @DELETE
  @Path(CommonParams.PATH_ID)
  @Operation(summary = "Delete a single subscription",
          tags = {"subscriptions"},
          description = "Deletes a single subscription",
          responses = {
                  @ApiResponse(responseCode = "204", description = "Subscription deleted successfully")
          })
  public Response deleteSubscription(
      @Context UriInfo uriInfo, @BeanParam SubscriptionParameters params) throws Exception {
    return delete(uriInfo, sb.delete(), params);
  }
}
