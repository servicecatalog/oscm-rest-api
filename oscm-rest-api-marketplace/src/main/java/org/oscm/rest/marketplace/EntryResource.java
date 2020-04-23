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

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.oscm.rest.common.CommonParams;
import org.oscm.rest.common.RestResource;
import org.oscm.rest.common.Since;
import org.oscm.rest.common.representation.EntryRepresentation;
import org.oscm.rest.common.requestparameters.MarketplaceParameters;

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
import lombok.AccessLevel;
import lombok.Setter;

@Path(CommonParams.PATH_VERSION + "/marketplaces" + CommonParams.PATH_ID + "/entries/{sKey}")
@Stateless
public class EntryResource extends RestResource {

  @EJB
  @Setter(value = AccessLevel.PROTECTED)
  EntryBackend eb;

  @PUT
  @Since(CommonParams.VERSION_1)
  @Operation(
      summary = "Publish service to marketplace",
      tags = {"marketplaces"},
      description = "Publishes a single service to a marketplace",
      requestBody =
          @RequestBody(
              description = "EntryRepresentation object to be updated",
              required = true,
              content =
                  @Content(
                      mediaType = "application/json",
                      schema = @Schema(implementation = EntryRepresentation.class),
                      examples = {
                        @ExampleObject(
                            name = CommonConstants.EXAMPLE_PUT_REQUEST_BODY_DESCRIPTION,
                            value = MarketplaceConstants.ENTRY_PUBLISH_EXAMPLE_REQUEST,
                            summary = CommonConstants.EXAMPLE_REQUEST_BODY_SUMMARY),
                      })),
      responses = {
        @ApiResponse(responseCode = "204", description = "Service published successfully")
      })
  public Response updateCatalogEntry(
      @Context UriInfo uriInfo,
      @Parameter(description = DocDescription.ENDPOINT_VERSION)
          @DefaultValue("v1")
          @PathParam(value = "version")
          String version,
      @Parameter(description = DocDescription.SERVICE_KEY) @PathParam(value = "sKey") String sKey,
      @Parameter(description = DocDescription.MARKETPLACE_ID) @PathParam(value = "id") String id,
      EntryRepresentation content)
      throws Exception {
    MarketplaceParameters params = new MarketplaceParameters();
    params.setEndpointVersion(version);
    params.setServiceKey(Long.valueOf(sKey));
    params.setId(Long.valueOf(id));
    return put(uriInfo, eb.put(), content, params);
  }
}
