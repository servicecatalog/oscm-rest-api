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
import org.oscm.rest.common.representation.AccountRepresentation;
import org.oscm.rest.common.representation.OrganizationRepresentation;
import org.oscm.rest.common.requestparameters.AccountParameters;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Since(CommonParams.VERSION_1)
@Path(CommonParams.PATH_VERSION + "/organizations")
@Stateless
public class OrganizationResource extends RestResource {

  @EJB
  @Setter(value = AccessLevel.PROTECTED)
  AccountBackend ab;

  @GET
  @Path(CommonParams.PATH_ID)
  @Operation(
      summary = "Get a single organization",
      tags = {"organization"},
      description = "Returns a single organization",
      responses = {
        @ApiResponse(
            responseCode = "200",
            description = "The organization",
            content = @Content(schema = @Schema(implementation = OrganizationRepresentation.class)))
      })
  public Response getOrganization(@Context UriInfo uriInfo, @BeanParam AccountParameters params)
      throws Exception {
    return get(uriInfo, ab.getOrganization(), params, true);
  }

  @POST
  @Operation(
      summary = "Create an organization",
      tags = {"organization"},
      description = "Creates an organization",
      requestBody =
          @RequestBody(
              description = "OrganizationRepresentation object to be created",
              required = true,
              content =
                  @Content(
                      schema = @Schema(implementation = OrganizationRepresentation.class),
                      examples = {
                        @ExampleObject(
                            name = CommonConstants.EXAMPLE_MINIMUM_BODY_NAME,
                            value = AccountConstants.ORGANIZATION_MINIMUM_BODY,
                            summary = CommonConstants.EXAMPLE_MINIMUM_BODY_SUMMARY),
                        @ExampleObject(
                            name = CommonConstants.EXAMPLE_MAXIMUM_BODY_NAME,
                            value = AccountConstants.ORGANIZATION_MAXIMUM_BODY,
                            summary = CommonConstants.EXAMPLE_MAXIMUM_BODY_SUMMARY)
                      })),
      responses = {
        @ApiResponse(responseCode = "201", description = "Organization successfully created")
      })
  public Response createOrganization(
      // FIXME Why is AccountRepresentation expected in body of creating organization method?
      @Context UriInfo uriInfo, AccountRepresentation content, @BeanParam AccountParameters params)
      throws Exception {
    return post(uriInfo, ab.postOrganization(), content, params);
  }
}
