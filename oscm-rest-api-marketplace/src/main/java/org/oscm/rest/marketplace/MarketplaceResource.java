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
import constants.DocDescription;
import constants.MarketplaceConstants;
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
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import lombok.AccessLevel;
import lombok.Setter;
import org.oscm.rest.common.CommonParams;
import org.oscm.rest.common.MarketplaceListType;
import org.oscm.rest.common.RestResource;
import org.oscm.rest.common.Since;
import org.oscm.rest.common.errorhandling.RestErrorResponseFactory;
import org.oscm.rest.common.representation.MarketplaceRepresentation;
import org.oscm.rest.common.requestparameters.MarketplaceParameters;

@Path(CommonParams.PATH_VERSION + "/marketplaces")
@Stateless
public class MarketplaceResource extends RestResource {

  @EJB
  @Setter(value = AccessLevel.PROTECTED)
  MarketplaceBackend mb;

  @GET
  @Since(CommonParams.VERSION_1)
  @Produces(CommonParams.JSON)
  @Operation(
      summary = "Retrieves marketplaces",
      tags = {"marketplaces"},
      description =
          "Returns all marketplaces based on given type (by default OWNED - all marketplaces owned by the organization).",
      responses = {
        @ApiResponse(
            responseCode = "200",
            description = "Marketplace list",
            content =
                @Content(
                    mediaType = "application/json",
                    examples = {
                      @ExampleObject(MarketplaceConstants.MARKETPLACE_LIST_EXAMPLE_RESPONSE)
                    }))
      })
  public Response getMarketplaces(
      @Context UriInfo uriInfo,
      @Parameter(description = DocDescription.ENDPOINT_VERSION)
          @DefaultValue("v1")
          @PathParam(value = "version")
          String version,
      @Parameter(description = DocDescription.MARKETPLACE_TYPE) @QueryParam("listType")
          MarketplaceListType listType)
      throws Exception {
    try {
      MarketplaceParameters params = new MarketplaceParameters();
      params.setEndpointVersion(version);
      params.setListType(listType);
      return getCollection(uriInfo, mb.getCollection(), params);
    } catch (Exception e) {
      return RestErrorResponseFactory.getResponse(e);
    }
  }

  @GET
  @Since(CommonParams.VERSION_1)
  @Path(CommonParams.PATH_ID)
  @Produces(CommonParams.JSON)
  @Operation(
      summary = "Retrieves a single marketplace",
      tags = {"marketplaces"},
      description = "Returns a single marketplace based on provided object id",
      responses = {
        @ApiResponse(
            responseCode = "200",
            description = "A single marketplace",
            content =
                @Content(
                    mediaType = "application/json",
                    examples = {@ExampleObject(MarketplaceConstants.MARKETPLACE_EXAMPLE_RESPONSE)}))
      })
  public Response getMarketplace(
      @Context UriInfo uriInfo,
      @Parameter(description = DocDescription.ENDPOINT_VERSION)
          @DefaultValue("v1")
          @PathParam(value = "version")
          String version,
      @Parameter(description = DocDescription.OBJECT_ID) @PathParam(value = "id") String id)
      throws Exception {
    try {
      MarketplaceParameters params = new MarketplaceParameters();
      params.setEndpointVersion(version);
      params.setId(Long.valueOf(id));
      return get(uriInfo, mb.get(), params, true);
    } catch (Exception e) {
      return RestErrorResponseFactory.getResponse(e);
    }
  }

  @POST
  @Since(CommonParams.VERSION_1)
  @Operation(
      summary = "Creates a marketplace",
      tags = {"marketplaces"},
      description = "Creates a marketplace based on given request data",
      requestBody =
          @RequestBody(
              description = "JSON representing marketplace to be created",
              required = true,
              content =
                  @Content(
                      mediaType = "application/json",
                      schema = @Schema(implementation = MarketplaceRepresentation.class),
                      examples = {
                        @ExampleObject(
                            name = CommonConstants.EXAMPLE_REQUEST_BODY_DESCRIPTION,
                            value = MarketplaceConstants.MARKETPLACE_CREATE_EXAMPLE_RESPONSE,
                            summary = CommonConstants.EXAMPLE_REQUEST_BODY_DESCRIPTION)
                      })),
      responses = {
        @ApiResponse(
            responseCode = "201",
            description = "Marketplace created successfully" + CommonConstants.ID_INFO)
      })
  public Response createMarketplace(
      @Context UriInfo uriInfo,
      MarketplaceRepresentation content,
      @Parameter(description = DocDescription.ENDPOINT_VERSION)
          @DefaultValue("v1")
          @PathParam(value = "version")
          String version)
      throws Exception {
    try {
      MarketplaceParameters params = new MarketplaceParameters();
      params.setEndpointVersion(version);
      return post(uriInfo, mb.post(), content, params);
    } catch (Exception e) {
      return RestErrorResponseFactory.getResponse(e);
    }
  }

  @PUT
  @Since(CommonParams.VERSION_1)
  @Path(CommonParams.PATH_ID)
  @Operation(
      summary = "Updates a single marketplace",
      tags = {"marketplaces"},
      description = "Updates a single marketplace based on given request data",
      requestBody =
          @RequestBody(
              description = "JSON representing marketplace to be updated",
              required = true,
              content =
                  @Content(
                      mediaType = "application/json",
                      schema = @Schema(implementation = MarketplaceRepresentation.class),
                      examples = {
                        @ExampleObject(
                            name = CommonConstants.EXAMPLE_PUT_REQUEST_BODY_DESCRIPTION,
                            value = MarketplaceConstants.MARKETPLACE_UPDATE_EXAMPLE_RESPONSE,
                            summary = CommonConstants.EXAMPLE_REQUEST_BODY_SUMMARY)
                      })),
      responses = {
        @ApiResponse(responseCode = "204", description = "Marketplace updated successfully")
      })
  public Response updateMarketplace(
      @Context UriInfo uriInfo,
      MarketplaceRepresentation content,
      @Parameter(description = DocDescription.ENDPOINT_VERSION)
          @DefaultValue("v1")
          @PathParam(value = "version")
          String version,
      @Parameter(description = DocDescription.OBJECT_ID) @PathParam(value = "id") String id)
      throws Exception {
    try {
      MarketplaceParameters params = new MarketplaceParameters();
      params.setEndpointVersion(version);
      params.setId(Long.valueOf(id));
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
      @Context UriInfo uriInfo,
      @Parameter(description = DocDescription.ENDPOINT_VERSION)
          @DefaultValue("v1")
          @PathParam(value = "version")
          String version,
      @Parameter(description = DocDescription.OBJECT_ID) @PathParam(value = "id") String id)
      throws Exception {
    try {
      MarketplaceParameters params = new MarketplaceParameters();
      params.setEndpointVersion(version);
      params.setId(Long.valueOf(id));
      return delete(uriInfo, mb.delete(), params);
    } catch (Exception e) {
      return RestErrorResponseFactory.getResponse(e);
    }
  }
}
