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

import constants.AccountConstants;
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
import org.oscm.rest.subscription.data.UsageLicenseRepresentation;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Since(CommonParams.VERSION_1)
@Path(CommonParams.PATH_VERSION + "/subscriptions" + CommonParams.PATH_ID + "/usagelicenses")
@Stateless
public class UsageLicenseResource extends RestResource {

  @EJB
  @Setter(value = AccessLevel.PROTECTED)
  UsageLicenseBackend ulb;

  @GET
  @Operation(summary = "Get all usage licenses for the subscription",
          tags = {"usagelicenses"},
          description = "Returns all usage licenses for the subscription",
          responses = {
                  @ApiResponse(responseCode = "200",
                          description = "Usage licenses list",
                          content = @Content(
                                  mediaType = "application/json",
                                  schema = @Schema(implementation = UsageLicenseRepresentation.class)
                          ))
          })
  public Response getLicenses(@Context UriInfo uriInfo, @BeanParam SubscriptionParameters params)
      throws Exception {
    return getCollection(uriInfo, ulb.getCollection(), params);
  }

  @POST
  @Operation(summary = "Create a license for the subscription",
          tags = {"usagelicenses"},
          description = "Creates a license for the subscription",
          requestBody = @RequestBody(
                  description = "UsageLicenseRepresentation object to be created",
                  required = true,
                  content = @Content(
                          schema = @Schema(implementation = UsageLicenseRepresentation.class),
                          examples = {
                                  @ExampleObject(
                                          name = CommonConstants.EXAMPLE_MINIMUM_BODY_NAME + ". "
                                                  + SubscriptionConstants.USAGE_LICENSE_POST_PRE_STEPS,
                                          value= SubscriptionConstants.LICENSE_MINIMUM_BODY,
                                          summary = CommonConstants.EXAMPLE_MINIMUM_BODY_SUMMARY),
                                  @ExampleObject(
                                          name = CommonConstants.EXAMPLE_MAXIMUM_BODY_NAME + ". "
                                                  + SubscriptionConstants.USAGE_LICENSE_POST_PRE_STEPS,
                                          value= SubscriptionConstants.LICENSE_MAXIMUM_BODY,
                                          summary = CommonConstants.EXAMPLE_MAXIMUM_BODY_SUMMARY)
                          }
                  )),
          responses = {
                  @ApiResponse(responseCode = "201", description = "Usage license created successfully")
          })
  public Response createLicense(
      @Context UriInfo uriInfo,
      UsageLicenseRepresentation content,
      @BeanParam SubscriptionParameters params)
      throws Exception {
    return post(uriInfo, ulb.post(), content, params);
  }

  @PUT
  @Path("/{licKey}")
  @Operation(summary = "Update a single usage license",
          tags = {"usagelicenses"},
          description = "Updates a single usage license",
          requestBody = @RequestBody(
                  description = "UsageLicenseRepresentation object to be updated",
                  required = true,
                  content = @Content(
                          schema = @Schema(implementation = UsageLicenseRepresentation.class),
                          examples = {
                                  @ExampleObject(
                                          name = CommonConstants.EXAMPLE_MINIMUM_BODY_NAME,
                                          value= SubscriptionConstants.LICENSE_MINIMUM_BODY,
                                          summary = CommonConstants.EXAMPLE_MINIMUM_BODY_SUMMARY),
                                  @ExampleObject(
                                          name = CommonConstants.EXAMPLE_MAXIMUM_BODY_NAME,
                                          value= SubscriptionConstants.LICENSE_MAXIMUM_BODY,
                                          summary = CommonConstants.EXAMPLE_MAXIMUM_BODY_SUMMARY)
                          }
                  )),
          responses = {
                  @ApiResponse(responseCode = "204", description = "Usage license updated successfully")
          })
  public Response updateLicense(
      @Context UriInfo uriInfo,
      UsageLicenseRepresentation content,
      @BeanParam SubscriptionParameters params)
      throws Exception {
    return put(uriInfo, ulb.put(), content, params);
  }

  @DELETE
  @Path("/{licKey}")
  @Operation(summary = "Delete a single usage license",
          tags = {"usagelicenses"},
          description = "Deletes a single usage license",
          responses = {
                  @ApiResponse(responseCode = "204", description = "Usage license deleted successfully")
          })
  public Response deleteLicense(@Context UriInfo uriInfo, @BeanParam SubscriptionParameters params)
      throws Exception {
    return delete(uriInfo, ulb.delete(), params);
  }
}
