/**
 * *****************************************************************************
 *
 * <p>Copyright FUJITSU LIMITED 2019
 *
 * <p>Creation Date: 10-04-2019
 *
 * <p>*****************************************************************************
 */
package org.oscm.rest.marketplace;

import constants.CommonConstants;
import constants.MarketplaceConstants;
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
import org.oscm.rest.common.errorhandling.RestErrorResponseFactory;
import org.oscm.rest.common.representation.MarketplaceRepresentation;
import org.oscm.rest.common.requestparameters.MarketplaceParameters;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("/marketplaces")
@Stateless
public class MarketplaceResource extends RestResource {

  @EJB
  @Setter(value = AccessLevel.PROTECTED)
  MarketplaceBackend mb;

  @GET
  @Since(CommonParams.VERSION_1)
  @Operation(
      summary = "Get all marketplaces",
      tags = {"marketplaces"},
      description = "Returns all marketplaces",
      responses = {
        @ApiResponse(
            responseCode = "200",
            description = "Marketplace list",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = MarketplaceRepresentation.class)))
      })
  public Response getMarketplaces(@Context UriInfo uriInfo, @BeanParam MarketplaceParameters params)
      throws Exception {
    try {
      return getCollection(uriInfo, mb.getCollection(), params);
    } catch (Exception e) {
      return RestErrorResponseFactory.getResponse(e);
    }
  }

  @GET
  @Since(CommonParams.VERSION_1)
  @Path(CommonParams.PATH_ID)
  @Operation(
      summary = "Get a single marketplace",
      tags = {"marketplaces"},
      description = "Returns a single marketplace",
      responses = {
        @ApiResponse(
            responseCode = "200",
            description = "A single marketplace",
            content = @Content(schema = @Schema(implementation = MarketplaceRepresentation.class)))
      })
  public Response getMarketplace(@Context UriInfo uriInfo, @BeanParam MarketplaceParameters params)
      throws Exception {
    try {
      return get(uriInfo, mb.get(), params, true);
    } catch (Exception e) {
      return RestErrorResponseFactory.getResponse(e);
    }
  }

  @POST
  @Since(CommonParams.VERSION_1)
  @Operation(
      summary = "Create a marketplace",
      tags = {"marketplaces"},
      description = "Creates a marketplace",
      requestBody =
          @RequestBody(
              description = "MarketplaceRepresentation object to be created",
              required = true,
              content =
                  @Content(
                      schema = @Schema(implementation = MarketplaceRepresentation.class),
                      examples = {
                        @ExampleObject(
                            name = CommonConstants.EXAMPLE_MINIMUM_BODY_NAME,
                            value = MarketplaceConstants.MARKETPLACE_MINIMUM_BODY,
                            summary = CommonConstants.EXAMPLE_MINIMUM_BODY_SUMMARY),
                        @ExampleObject(
                            name = CommonConstants.EXAMPLE_MAXIMUM_BODY_NAME,
                            value = MarketplaceConstants.MARKETPLACE_MAXIMUM_BODY,
                            summary = CommonConstants.EXAMPLE_MAXIMUM_BODY_SUMMARY)
                      })),
      responses = {
        @ApiResponse(responseCode = "201", description = "Marketplace created successfully")
      })
  public Response createMarketplace(
      @Context UriInfo uriInfo,
      MarketplaceRepresentation content,
      @BeanParam MarketplaceParameters params)
      throws Exception {
    try {
      return post(uriInfo, mb.post(), content, params);
    } catch (Exception e) {
      return RestErrorResponseFactory.getResponse(e);
    }
  }

  @PUT
  @Since(CommonParams.VERSION_1)
  @Path(CommonParams.PATH_ID)
  @Operation(
          summary = "Update a single marketplace",
          tags = {"marketplaces"},
          description = "Updates a single marketplace",
          requestBody =
          @RequestBody(
                  description = "MarketplaceRepresentation object to be updated",
                  required = true,
                  content =
                  @Content(
                          schema = @Schema(implementation = MarketplaceRepresentation.class),
                          examples = {
                                  @ExampleObject(
                                          name = CommonConstants.EXAMPLE_MINIMUM_BODY_NAME,
                                          value = MarketplaceConstants.MARKETPLACE_MINIMUM_BODY,
                                          summary = CommonConstants.EXAMPLE_MINIMUM_BODY_SUMMARY),
                                  @ExampleObject(
                                          name = CommonConstants.EXAMPLE_MAXIMUM_BODY_NAME,
                                          value = MarketplaceConstants.MARKETPLACE_MAXIMUM_BODY,
                                          summary = CommonConstants.EXAMPLE_MAXIMUM_BODY_SUMMARY)
                          })),
          responses = {
                  @ApiResponse(responseCode = "204", description = "Billing contact updated successfully")
          })
  public Response updateMarketplace(
          @Context UriInfo uriInfo,
          MarketplaceRepresentation content,
          @BeanParam MarketplaceParameters params) {
    try {
      return put(uriInfo, mb.put(), content, params);
    } catch (Exception e) {
      return RestErrorResponseFactory.getResponse(e);
    }
  }

  @DELETE
  @Since(CommonParams.VERSION_1)
  @Path(CommonParams.PATH_ID)
  @Operation(
      summary = "Delete a single marketplace",
      tags = {"marketplaces"},
      description = "Deletes a single marketplace",
      responses = {
        @ApiResponse(responseCode = "204", description = "Marketplace deleted successfully")
      })
  public Response deleteMarketplace(
      @Context UriInfo uriInfo, @BeanParam MarketplaceParameters params) {
    try {
      return delete(uriInfo, mb.delete(), params);
    } catch (Exception e) {
      return RestErrorResponseFactory.getResponse(e);
    }
  }
}
