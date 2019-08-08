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

import com.google.common.base.Strings;
import constants.AccountConstants;
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
import org.oscm.internal.intf.ServiceProvisioningService;
import org.oscm.internal.vo.VOTechnicalService;
import org.oscm.rest.common.CommonParams;
import org.oscm.rest.common.RestResource;
import org.oscm.rest.common.Since;
import org.oscm.rest.common.requestparameters.ServiceParameters;
import org.oscm.rest.common.representation.TechnicalServiceRepresentation;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;
import java.util.Collections;

@Since(CommonParams.VERSION_1)
@Path(CommonParams.PATH_VERSION + "/technicalservices")
@Stateless
public class TechnicalServiceResource extends RestResource {

  @EJB
  @Setter(value = AccessLevel.PROTECTED)
  TechnicalServiceBackend tsb;

  @EJB ServiceProvisioningService sps;

  @GET
  @Operation(summary = "Get all available technical services",
          tags = {"services"},
          description = "Returns all available technical services",
          responses = {
                  @ApiResponse(responseCode = "200",
                          description = "Technical services list",
                          content = @Content(
                                  mediaType = "application/json",
                                  schema = @Schema(implementation = TechnicalServiceRepresentation.class)
                          ))
          })
  public Response getTechnicalServices(
      @Context UriInfo uriInfo, @BeanParam ServiceParameters params) throws Exception {
    return getCollection(uriInfo, tsb.getCollection(), params);
  }

  public Response exportTechnicalService(
          @Context UriInfo uriInfo, @BeanParam ServiceParameters params) throws Exception {
    // FIXME: Implement this endpoint properly. Use get() from interface
    // FIXME: Document this endpoint using Swagger
    // key needed
    VOTechnicalService ts = new VOTechnicalService();
    ts.setKey(params.getId().longValue());
    byte[] export = sps.exportTechnicalServices(Collections.singletonList(ts));
    return Response.ok(export, MediaType.APPLICATION_XML_TYPE).build();
  }

  @POST
  @Operation(summary = "Create a technical service",
          tags = {"services"},
          description = "Creates a technical service",
          requestBody = @RequestBody(
                  description = "TechnicalServiceRepresentation object to be created",
                  required = true,
                  content = @Content(
                          schema = @Schema(implementation = TechnicalServiceRepresentation.class),
                          examples = {
                                  @ExampleObject(
                                          name = CommonConstants.EXAMPLE_MINIMUM_BODY_NAME,
                                          value= ServiceConstants.TECHNICAL_SERVICE_MINIMUM_BODY,
                                          summary = CommonConstants.EXAMPLE_MINIMUM_BODY_SUMMARY),
                                  @ExampleObject(
                                          name = CommonConstants.EXAMPLE_MAXIMUM_BODY_NAME,
                                          value= ServiceConstants.TECHNICAL_SERVICE_MAXIMUM_BODY,
                                          summary = CommonConstants.EXAMPLE_MAXIMUM_BODY_SUMMARY)
                          }
                  )),
          responses = {
                  @ApiResponse(responseCode = "201", description = "Technical service created successfully")
          })
  public Response createTechnicalService(
      @Context UriInfo uriInfo,
      TechnicalServiceRepresentation content,
      @BeanParam ServiceParameters params)
      throws Exception {
    return post(uriInfo, tsb.post(), content, params);
  }

  @PUT
  public Response importTechnicalServices(
          @Context UriInfo uriInfo, byte[] input, @BeanParam ServiceParameters params)
          throws Exception {
    // FIXME: Implement this endpoint properly. Use put() from interface
    // FIXME: This endpoint should accept TSRepresentation instead of byte array (just like every
    // FIXME: Document this endpoint using Swagger
    // other  PUT endpoint)
    String msg = sps.importTechnicalServices(input);
    if (Strings.isNullOrEmpty(msg)) {
      return Response.noContent().build();
    }
    return Response.status(Status.BAD_REQUEST).entity(msg).build();
  }

  @DELETE
  @Path(CommonParams.PATH_ID)
  @Operation(summary = "Delete a single technical service",
          tags = {"services"},
          description = "Deletes a single technical service",
          responses = {
                  @ApiResponse(responseCode = "204", description = "Technical service deleted successfully")
          })
  public Response deleteTechnicalService(
      @Context UriInfo uriInfo, @BeanParam ServiceParameters params) throws Exception {
    return delete(uriInfo, tsb.delete(), params);
  }
}
