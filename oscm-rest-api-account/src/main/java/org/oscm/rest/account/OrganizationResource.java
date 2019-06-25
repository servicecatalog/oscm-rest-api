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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.oscm.rest.account.data.AccountRepresentation;
import org.oscm.rest.account.data.BillingContactRepresentation;
import org.oscm.rest.account.data.OrganizationRepresentation;
import org.oscm.rest.common.CommonParams;
import org.oscm.rest.common.RestResource;
import org.oscm.rest.common.Since;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path(CommonParams.PATH_VERSION + "/organizations")
@Produces(MediaType.APPLICATION_JSON)
@Since(CommonParams.VERSION_1)
@Stateless
public class OrganizationResource extends RestResource {

  @EJB AccountBackend ab;

  @POST
  @Operation(summary = "Create an organization.",
          tags = {"organization"},
          description = "Creates an organization.",
          responses = {
                  @ApiResponse(responseCode = "201", description = "Organization successfully created.")
          })
  public Response createOrganization(//FIXME Why is AccountRepresentation expected in body of creating orgnization method?
      @Context UriInfo uriInfo,
      @RequestBody(description = "OrganizationRepresentation object that needs to be created.", required = true,
              content = @Content(
                      schema = @Schema(implementation = OrganizationRepresentation.class))) AccountRepresentation content, @BeanParam AccountParameters params)
      throws Exception {
    return post(uriInfo, ab.postOrganization(), content, params);
  }

  @GET
  @Path(CommonParams.PATH_ID)
  @Operation(summary = "Get the organization.",
          tags = {"organization"},
          description = "Returns the organization.",
          responses = {
                  @ApiResponse(responseCode = "200", description = "The organization", content = @Content(
                          schema = @Schema(implementation = OrganizationRepresentation.class)
                  ))
          })
  public Response getOrganization(@Context UriInfo uriInfo,
                                  @Parameter(description = "Parameters including the ID of the organization.", required = true) @BeanParam AccountParameters params)
      throws Exception {
    return get(uriInfo, ab.getOrganization(), params, true);
  }
}
