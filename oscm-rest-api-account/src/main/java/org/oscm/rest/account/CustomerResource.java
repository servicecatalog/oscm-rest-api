/**
 * *****************************************************************************
 *
 * <p>Copyright FUJITSU LIMITED 2020
 *
 * <p>Creation Date: April 23, 2020
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
import javax.ws.rs.DefaultValue;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import lombok.AccessLevel;
import lombok.Setter;
import org.oscm.rest.common.CommonParams;
import org.oscm.rest.common.RestResource;
import org.oscm.rest.common.Since;
import org.oscm.rest.common.representation.CreateCustomerOrganizationRepresentation;
import org.oscm.rest.common.representation.OrganizationRepresentation;
import org.oscm.rest.common.requestparameters.AccountParameters;

@Path(CommonParams.PATH_VERSION + "/customers")
@Stateless
public class CustomerResource extends RestResource {

  @EJB
  @Setter(value = AccessLevel.PROTECTED)
  AccountBackend ab;

  @POST
  @Since(CommonParams.VERSION_1)
  @Operation(
      summary = "Creates customer organization",
      tags = {"customers"},
      description =
          "Creates a customer organization along with its administrator based on given request data",
      requestBody =
          @RequestBody(
              description = "JSON representing customer organization to be created",
              required = true,
              content =
                  @Content(
                      mediaType = "application/json",
                      schema = @Schema(implementation = OrganizationRepresentation.class),
                      examples = {
                        @ExampleObject(
                            name =
                                "Request contains sample customer organization data and its administrator's user data which. Administrator user will be automatically created along with organization",
                            value = AccountConstants.CUSTOMER_EXAMPLE_REQUEST,
                            summary = CommonConstants.EXAMPLE_REQUEST_BODY_SUMMARY)
                      })),
      responses = {
        @ApiResponse(
            responseCode = "201",
            description = "Customer organization successfully created. " + CommonConstants.ID_INFO)
      })
  public Response createCustomer(
      @Context UriInfo uriInfo,
      CreateCustomerOrganizationRepresentation content,
      @Parameter(description = DocDescription.ENDPOINT_VERSION)
          @DefaultValue("v1")
          @PathParam(value = "version")
          String version)
      throws Exception {
    AccountParameters params = new AccountParameters();
    params.setEndpointVersion(version);
    return post(uriInfo, ab.postCustomer(), content, params);
  }
}
