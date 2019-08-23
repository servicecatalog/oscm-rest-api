/**
 * *****************************************************************************
 *
 * <p>Copyright FUJITSU LIMITED 2019
 *
 * <p>Creation Date: 10-04-2019
 *
 * <p>*****************************************************************************
 */
package org.oscm.rest.identity;

import constants.CommonConstants;
import constants.IdentityConstants;
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
import org.oscm.rest.identity.data.OnBehalfUserRepresentation;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.BeanParam;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Since(CommonParams.VERSION_1)
@Path(CommonParams.PATH_VERSION + "/onbehalfusers")
@Stateless
public class OnBehalfUserResource extends RestResource {

  @EJB
  @Setter(value = AccessLevel.PROTECTED)
  UserBackend ub;

  @POST
  @Operation(summary = "Create a single on behalf user",
          tags = {"onbehalfusers"},
          description = "Creates a single on behalf user",
          requestBody = @RequestBody(
                  description = "OnBehalfUserRepresentation object to be updated",
                  required = true,
                  content = @Content(
                          schema = @Schema(implementation = OnBehalfUserRepresentation.class),
                          examples = {
                                  @ExampleObject(
                                          name = CommonConstants.EXAMPLE_MINIMUM_BODY_NAME + ". " +
                                                  IdentityConstants.ON_BEHALF_USER_RESOURCE_POST_PRE_STEPS,
                                          value = IdentityConstants.ONBEHALFUSERS_MINIMUM_BODY,
                                          summary = CommonConstants.EXAMPLE_MINIMUM_BODY_SUMMARY),
                                  @ExampleObject(
                                          name = CommonConstants.EXAMPLE_MAXIMUM_BODY_NAME + ". " +
                                                  IdentityConstants.ON_BEHALF_USER_RESOURCE_POST_PRE_STEPS,
                                          value = IdentityConstants.ONBEHALFUSERS_MAXIMUM_BODY,
                                          summary = CommonConstants.EXAMPLE_MAXIMUM_BODY_SUMMARY)
                          })),
          responses = {
                  @ApiResponse(responseCode = "204", description = "On behalf user created successfully")
          })
  public Response createOnBehalfUser(
      @Context UriInfo uriInfo,
      OnBehalfUserRepresentation content,
      @BeanParam UserParameters params)
      throws Exception {
    return post(uriInfo, ub.postOnBehalfUser(), content, params);
  }

  @DELETE
  @Operation(summary = "Delete a single on behalf user",
          tags = {"onbehalfusers"},
          description = "Deletes a single on behalf user",
          responses = {
                  @ApiResponse(responseCode = "204", description = "On behalf user deleted successfully")
          })
  public Response deleteOnBehalfUser(@Context UriInfo uriInfo, @BeanParam UserParameters params)
      throws Exception {
    params.setUserIdRequired(false);
    return delete(uriInfo, ub.deleteOBehalfUser(), params);
  }
}
