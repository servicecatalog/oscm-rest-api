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
import org.oscm.rest.common.CommonParams;
import org.oscm.rest.common.RestResource;
import org.oscm.rest.common.Since;
import org.oscm.rest.service.data.OrganizationRepresentation;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Since(CommonParams.VERSION_1)
@Path(CommonParams.PATH_VERSION + "/technicalservices" + CommonParams.PATH_ID + "/suppliers")
@Stateless
public class TSSupplierResource extends RestResource {

  @EJB
  @Setter(value = AccessLevel.PROTECTED)
  TSSupplierBackend sb;

  @GET
  @Operation(summary = "Get all suppliers for technical service",
          tags = {"services"},
          description = "Returns all suppliers for technical service",
          responses = {
                  @ApiResponse(responseCode = "200",
                          description = "Suppliers list",
                          content = @Content(
                                  mediaType = "application/json",
                                  schema = @Schema(implementation = OrganizationRepresentation.class)
                          ))
          })
  public Response getSuppliers(@Context UriInfo uriInfo, @BeanParam ServiceParameters params)
      throws Exception {
    return getCollection(uriInfo, sb.getCollection(), params);
  }

  @POST
  @Operation(summary = "Create supplier for the technical service",
          tags = {"services"},
          description = "Creates supplier for the technical service",
          requestBody = @RequestBody(
                  description = "OrganizationRepresentation (supplier) object to be created",
                  required = true,
                  content = @Content(
                          schema = @Schema(implementation = OrganizationRepresentation.class),
                          examples = {
                                  @ExampleObject(
                                          name = CommonConstants.EXAMPLE_MINIMUM_BODY_NAME,
                                          value= ServiceConstants.TS_SUPPLIER_MINIMUM_BODY,
                                          summary = CommonConstants.EXAMPLE_MINIMUM_BODY_SUMMARY),
                                  @ExampleObject(
                                          name = CommonConstants.EXAMPLE_MAXIMUM_BODY_NAME,
                                          value= ServiceConstants.TS_SUPPLIER_MAXIMUM_BODY,
                                          summary = CommonConstants.EXAMPLE_MAXIMUM_BODY_SUMMARY)
                          }
                  )),
          responses = {
                  @ApiResponse(responseCode = "201", description = "Technical service supplier created successfully")
          })
  public Response addSupplier(
      @Context UriInfo uriInfo,
      OrganizationRepresentation content,
      @BeanParam ServiceParameters params)
      throws Exception {
    return post(uriInfo, sb.post(), content, params);
  }

  @DELETE
  @Path("/{orgId}")
  @Operation(summary = "Delete a single technical service supplier",
          tags = {"services"},
          description = "Deletes a single technical service supplier",
          responses = {
                  @ApiResponse(responseCode = "204", description = "Technical service supplier deleted successfully")
          })
  public Response removeSupplier(@Context UriInfo uriInfo, @BeanParam ServiceParameters params)
      throws Exception {
    return delete(uriInfo, sb.delete(), params);
  }
}
