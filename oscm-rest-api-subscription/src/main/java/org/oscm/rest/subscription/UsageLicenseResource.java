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

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.oscm.rest.common.CommonParams;
import org.oscm.rest.common.RestResource;
import org.oscm.rest.common.Since;
import org.oscm.rest.common.representation.UsageLicenseRepresentation;
import org.oscm.rest.common.requestparameters.SubscriptionParameters;

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
import lombok.AccessLevel;
import lombok.Setter;

@Path(CommonParams.PATH_VERSION + "/subscriptions" + CommonParams.PATH_ID + "/usagelicenses")
@Stateless
public class UsageLicenseResource extends RestResource {

  @EJB
  @Setter(value = AccessLevel.PROTECTED)
  UsageLicenseBackend ulb;

  @GET
  @Since(CommonParams.VERSION_1)
  @Produces(CommonParams.JSON)
  @Operation(
      summary = "Retrieves all usage licenses for the subscription",
      tags = {"usagelicenses"},
      description = "Retrieves all usage licenses for the given subscription id",
      responses = {
        @ApiResponse(
            responseCode = "200",
            description = "All usage licenses for the subscription",
            content =
                @Content(
                    mediaType = "application/json",
                    examples = {
                      @ExampleObject(SubscriptionConstants.LICENSE_LIST_EXAMPLE_RESPONSE)
                    }))
      })
  public Response getLicenses(
      @Context UriInfo uriInfo,
      @Parameter(description = DocDescription.ENDPOINT_VERSION)
          @DefaultValue("v1")
          @PathParam(value = "version")
          String version,
      @Parameter(description = DocDescription.SUBSCRIPTION_ID) @PathParam(value = "id") String id)
      throws Exception {
    SubscriptionParameters params = new SubscriptionParameters();
    params.setEndpointVersion(version);
    params.setId(Long.valueOf(id));
    return getCollection(uriInfo, ulb.getCollection(), params);
  }

  @POST
  @Since(CommonParams.VERSION_1)
  @Operation(
      summary = "Create a license for the subscription",
      tags = {"usagelicenses"},
      description = "Creates a license for the given subscription id",
      requestBody =
          @RequestBody(
              description = "JSON representing usage license to be created",
              required = true,
              content =
                  @Content(
                      mediaType = "application/json",
                      schema = @Schema(implementation = UsageLicenseRepresentation.class),
                      examples = {
                        @ExampleObject(
                            name = CommonConstants.EXAMPLE_REQUEST_BODY_DESCRIPTION,
                            value = SubscriptionConstants.LICENSE_CREATE_EXAMPLE_REQUEST,
                            summary = CommonConstants.EXAMPLE_REQUEST_BODY_SUMMARY),
                      })),
      responses = {
        @ApiResponse(
            responseCode = "201",
            description = "Usage license created successfully" + CommonConstants.ID_INFO)
      })
  public Response createLicense(
      @Context UriInfo uriInfo,
      UsageLicenseRepresentation content,
      @Parameter(description = DocDescription.ENDPOINT_VERSION)
          @DefaultValue("v1")
          @PathParam(value = "version")
          String version,
      @Parameter(description = DocDescription.SUBSCRIPTION_ID) @PathParam(value = "id") String id)
      throws Exception {
    SubscriptionParameters params = new SubscriptionParameters();
    params.setEndpointVersion(version);
    params.setId(Long.valueOf(id));
    return post(uriInfo, ulb.post(), content, params);
  }

  @PUT
  @Since(CommonParams.VERSION_1)
  @Path("/{licKey}")
  @Operation(
      summary = "Updates a single usage license for a subscription",
      tags = {"usagelicenses"},
      description = "Updates a single usage license for a subscirption by a given licenseKey",
      requestBody =
          @RequestBody(
              description = "JSON representing a usage license to be updated",
              required = true,
              content =
                  @Content(
                      schema = @Schema(implementation = UsageLicenseRepresentation.class),
                      examples = {
                        @ExampleObject(
                            name = CommonConstants.EXAMPLE_PUT_REQUEST_BODY_DESCRIPTION,
                            value = SubscriptionConstants.LICENSE_UPDATE_EXAMPLE_REQUEST,
                            summary = CommonConstants.EXAMPLE_REQUEST_BODY_SUMMARY),
                      })),
      responses = {
        @ApiResponse(responseCode = "204", description = "Usage license updated successfully")
      })
  public Response updateLicense(
      @Context UriInfo uriInfo,
      UsageLicenseRepresentation content,
      @Parameter(description = DocDescription.ENDPOINT_VERSION)
          @DefaultValue("v1")
          @PathParam(value = "version")
          String version,
      @Parameter(description = DocDescription.SUBSCRIPTION_ID) @PathParam(value = "id") String id,
      @Parameter(description = DocDescription.LICENSE_KEY) @PathParam(value = "licKey")
          String licKey)
      throws Exception {
    SubscriptionParameters params = new SubscriptionParameters();
    params.setEndpointVersion(version);
    params.setId(Long.valueOf(id));
    params.setLicKey(Long.valueOf(licKey));
    return put(uriInfo, ulb.put(), content, params);
  }

  @DELETE
  @Since(CommonParams.VERSION_1)
  @Path("/{licKey}")
  @Operation(
      summary = "Deletes a single usage license for a subscirption",
      tags = {"usagelicenses"},
      description = "Deletes a single usage license for a subscirption by given licenskey",
      responses = {
        @ApiResponse(responseCode = "204", description = "Usage license deleted successfully")
      })
  public Response deleteLicense(
      @Context UriInfo uriInfo,
      UsageLicenseRepresentation content,
      @Parameter(description = DocDescription.ENDPOINT_VERSION)
          @DefaultValue("v1")
          @PathParam(value = "version")
          String version,
      @Parameter(description = DocDescription.SUBSCRIPTION_ID) @PathParam(value = "id") String id,
      @Parameter(description = DocDescription.LICENSE_KEY) @PathParam(value = "licKey")
          String licKey)
      throws Exception {
    SubscriptionParameters params = new SubscriptionParameters();
    params.setEndpointVersion(version);
    params.setId(Long.valueOf(id));
    params.setLicKey(Long.valueOf(licKey));
    return delete(uriInfo, ulb.delete(), params);
  }
}
