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

import constants.AccountConstants;
import constants.CommonConstants;
import constants.DocDescription;
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
import org.oscm.rest.common.representation.AccountRepresentation;
import org.oscm.rest.common.representation.OrganizationRepresentation;
import org.oscm.rest.common.requestparameters.AccountParameters;

@Path(CommonParams.PATH_VERSION + "/organizations")
@Stateless
public class OrganizationResource extends RestResource {

  @EJB
  @Setter(value = AccessLevel.PROTECTED)
  AccountBackend ab;

  @GET
  @Since(CommonParams.VERSION_1)
  @Path(CommonParams.PATH_ORG_ID)
  @Operation(
      summary = "Retrieves organization information",
      tags = {"organizations"},
      description = "Returns information about organization based on specified organization id",
      responses = {
        @ApiResponse(
            responseCode = "200",
            description = "The organization",
            content = @Content(schema = @Schema(implementation = OrganizationRepresentation.class)))
      })
  public Response getOrganization(
      @Context UriInfo uriInfo,
      @Parameter(description = DocDescription.ENDPOINT_VERSION)
          @DefaultValue("v1")
          @PathParam(value = "version")
          String version,
      @Parameter(description = DocDescription.ORGANIZATION_ID) @PathParam(value = "orgId")
          String orgId)
      throws Exception {

    AccountParameters params = new AccountParameters();
    params.setEndpointVersion(version);
    params.setOrgId(orgId);
    return get(uriInfo, ab.getOrganization(), params, true);
  }

  @POST
  @Since(CommonParams.VERSION_1)
  @Operation(
      summary = "Creates organization",
      tags = {"organizations"},
      description =
          "Creates an organization along with its administrator based on given request data",
      requestBody =
          @RequestBody(
              description = "JSON representing organization to be created",
              required = true,
              content =
                  @Content(
                      schema = @Schema(implementation = OrganizationRepresentation.class),
                      examples = {
                        @ExampleObject(
                            name =
                                "Request contains specific organization roles, sample organization data and its administrator's user data which. Administrator user will be automatically created along with organization",
                            value = AccountConstants.ORGANIZATION_EXAMPLE_BODY,
                            summary = CommonConstants.EXAMPLE_REQUEST_BODY_SUMMARY)
                      })),
      responses = {
        @ApiResponse(
            responseCode = "201",
            description = "Organization successfully created. " + CommonConstants.ID_INFO)
      })
  public Response createOrganization(
      @Context UriInfo uriInfo,
      AccountRepresentation content,
      @Parameter(description = DocDescription.ENDPOINT_VERSION)
          @DefaultValue("v1")
          @PathParam(value = "version")
          String version)
      throws Exception {
    AccountParameters params = new AccountParameters();
    params.setEndpointVersion(version);
    return post(uriInfo, ab.postOrganization(), content, params);
  }
}
