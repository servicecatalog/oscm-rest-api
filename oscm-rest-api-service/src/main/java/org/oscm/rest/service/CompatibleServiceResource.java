/**
 * *****************************************************************************
 *
 * <p>Copyright FUJITSU LIMITED 2019
 *
 * <p>Creation Date: 10-04-2019
 *
 * <p>*****************************************************************************
 */
package org.oscm.rest.service;

import constants.CommonConstants;
import constants.ServiceConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AccessLevel;
import lombok.Setter;
import org.oscm.rest.common.CommonParams;
import org.oscm.rest.common.RepresentationCollection;
import org.oscm.rest.common.RestResource;
import org.oscm.rest.common.Since;
import org.oscm.rest.service.data.ServiceRepresentation;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Since(CommonParams.VERSION_1)
@Path(CommonParams.PATH_VERSION + "/services" + CommonParams.PATH_ID + "/compatibleservices")
@Stateless
public class CompatibleServiceResource extends RestResource {

  @EJB
  @Setter(value = AccessLevel.PROTECTED)
  ServiceBackend sb;


  @GET
  @Operation(summary = "Get all services compatible to selected service",
          tags = {"services"},
          description = "Returns all services compatible to selected service",
          responses = {
                  @ApiResponse(responseCode = "200",
                          description = "Services list",
                          content = @Content(
                                  mediaType = "application/json",
                                  schema = @Schema(implementation = ServiceRepresentation.class)
                          ))
          })
  public Response getCompatibleServices(
      @Context UriInfo uriInfo, @BeanParam ServiceParameters params) throws Exception {
    return getCollection(uriInfo, sb.getCompatibles(), params);
  }

  @PUT
  @Operation(summary = "Update service's compatiblity relation",
          tags = {"services"},
          description = "Updates service's compatiblity relation",
          requestBody = @RequestBody(
                  description = "ServiceRepresentation object to be updated",
                  required = true,
                  content = @Content(
                          schema = @Schema(implementation = ServiceRepresentation.class),
                          examples = {
                                  @ExampleObject(
                                          name = CommonConstants.EXAMPLE_MINIMUM_BODY_NAME,
                                          value= ServiceConstants.COMPATIBLE_SERVICE_MINIMUM_BODY,
                                          summary = CommonConstants.EXAMPLE_MINIMUM_BODY_SUMMARY),
                                  @ExampleObject(
                                          name = CommonConstants.EXAMPLE_MAXIMUM_BODY_NAME,
                                          value= ServiceConstants.COMPATIBLE_SERVICE_MAXIMUM_BODY,
                                          summary = CommonConstants.EXAMPLE_MAXIMUM_BODY_SUMMARY)
                          })),
          responses = {
                  @ApiResponse(responseCode = "204", description = "Billing contact updated successfully")
          })
  public Response setCompatibleServices(
      @Context UriInfo uriInfo,
      RepresentationCollection<ServiceRepresentation> content,
      @BeanParam ServiceParameters params)
      throws Exception {

    params.setEtag(content.getETag());
    return put(uriInfo, sb.putCompatibles(), content, params);
  }
}
