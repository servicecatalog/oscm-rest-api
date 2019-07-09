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
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AccessLevel;
import lombok.Setter;
import org.oscm.rest.account.data.AccountRepresentation;
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

  @EJB
  @Setter(value = AccessLevel.PROTECTED)
  AccountBackend ab;

  @GET
  @Path(CommonParams.PATH_ID)
  @Operation(summary = "Get a single organization.",
          tags = {"organization"},
          description = "Returns a single organization.",
          responses = {
                  @ApiResponse(responseCode = "200", description = "The organization", content = @Content(
                          schema = @Schema(implementation = OrganizationRepresentation.class)
                  ))
          })
  public Response getOrganization(@Context UriInfo uriInfo,
                                  @BeanParam AccountParameters params)
          throws Exception {
    return get(uriInfo, ab.getOrganization(), params, true);
  }

  @POST
  @Operation(summary = "Create an organization.",
          tags = {"organization"},
          description = "Creates an organization.",
          requestBody = @RequestBody(
                  description = "OrganizationRepresentation object to be created.",
                  required = true,
                  content = @Content(
                  schema = @Schema(implementation = OrganizationRepresentation.class))),
          responses = {
                  @ApiResponse(responseCode = "201", description = "Organization successfully created.")
          })
  public Response createOrganization(
          //FIXME Why is AccountRepresentation expected in body of creating organization method?
          @Context UriInfo uriInfo,
          AccountRepresentation content,
          @BeanParam AccountParameters params)
      throws Exception {
    return post(uriInfo, ab.postOrganization(), content, params);
  }
}
