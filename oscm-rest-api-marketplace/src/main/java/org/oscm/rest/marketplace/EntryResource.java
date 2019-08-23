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
import org.oscm.rest.marketplace.data.EntryRepresentation;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.BeanParam;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Since(CommonParams.VERSION_1)
@Path(CommonParams.PATH_VERSION + "/marketplaces" + CommonParams.PATH_ID + "/entries/{sKey}")
@Stateless
public class EntryResource extends RestResource {

  @EJB
  @Setter(value = AccessLevel.PROTECTED)
  EntryBackend eb;

  @PUT
  @Operation(summary = "Update a single marketplace entry",
          tags = {"marketplaces"},
          description = "Updates a single marketplace entry",
          requestBody = @RequestBody(
                  description = "EntryRepresentation object to be updated",
                  required = true,
                  content = @Content(
                          schema = @Schema(implementation = EntryRepresentation.class),
                          examples = {
                                  @ExampleObject(
                                          name = CommonConstants.EXAMPLE_MINIMUM_BODY_NAME + ". " +
                                                  MarketplaceConstants.ENTRY_RESOURCE_PUT_PRE_STEPS,
                                          value= MarketplaceConstants.ENTRY_MINIMUM_BODY,
                                          summary = CommonConstants.EXAMPLE_MINIMUM_BODY_SUMMARY),
                                  @ExampleObject(
                                          name = CommonConstants.EXAMPLE_MAXIMUM_BODY_NAME + ". " +
                                                  MarketplaceConstants.ENTRY_RESOURCE_PUT_PRE_STEPS,
                                          value= MarketplaceConstants.ENTRY_MAXIMUM_BODY,
                                          summary = CommonConstants.EXAMPLE_MAXIMUM_BODY_SUMMARY)
                          }
                  )),
          responses = {
                  @ApiResponse(responseCode = "204", description = "Marketplace entry updated successfully")
          })
  public Response updateCatalogEntry(
          @Context UriInfo uriInfo,
          EntryRepresentation content,
          @BeanParam MarketplaceParameters params)
          throws Exception {
    return put(uriInfo, eb.put(), content, params);
  }
}

