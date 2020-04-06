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

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.oscm.rest.common.CommonParams;
import org.oscm.rest.common.RestResource;
import org.oscm.rest.common.Since;
import org.oscm.rest.common.representation.OrganizationRepresentation;
import org.oscm.rest.common.requestparameters.ServiceParameters;

import constants.CommonConstants;
import constants.DocDescription;
import constants.ServiceConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AccessLevel;
import lombok.Setter;

@Path(CommonParams.PATH_VERSION + "/technicalservices" + CommonParams.PATH_ID + "/suppliers")
@Stateless
public class TSSupplierResource extends RestResource {

  @EJB
  @Setter(value = AccessLevel.PROTECTED)
  TSSupplierBackend sb;

  @GET
  @Since(CommonParams.VERSION_1)
  @Produces(CommonParams.JSON)
  @Operation(
      summary = "Retrieves suppliers for technical service",
      tags = {"technicalservices"},
      description = "Returns all suppliers for technical service",
      responses = {
        @ApiResponse(
            responseCode = "200",
            description = "Suppliers list",
            content =
                @Content(
                    mediaType = "application/json",
                    examples = {@ExampleObject(ServiceConstants.SUPPLIER_LIST_EXAMPLE_RESPONSE)},
                    schema = @Schema(implementation = OrganizationRepresentation.class)))
      })
  public Response getSuppliers(
      @Context UriInfo uriInfo,
      @Parameter(description = DocDescription.ENDPOINT_VERSION)
          @DefaultValue("v1")
          @PathParam(value = "version")
          String version,
      @Parameter(description = DocDescription.OBJECT_ID) @PathParam(value = "id") String id)
      throws Exception {
    ServiceParameters params = new ServiceParameters();
    params.setEndpointVersion(version);
    params.setId(Long.valueOf(id));
    return getCollection(uriInfo, sb.getCollection(), params);
  }

  @POST
  @Since(CommonParams.VERSION_1)
  @Operation(
      summary = "Adds supplier to the technical service",
      tags = {"technicalservices"},
      description = "Adds supplier to the specific technical service",
      requestBody =
          @RequestBody(
              description = "JSON representing organization (supplier) object to be added",
              required = true,
              content =
                  @Content(
                      mediaType = "application/json",
                      schema = @Schema(implementation = OrganizationRepresentation.class),
                      examples = {
                        @ExampleObject(
                            name = CommonConstants.EXAMPLE_REQUEST_BODY_DESCRIPTION,
                            value = ServiceConstants.SUPPLIER_ADD_EXAMPLE_REQUEST,
                            summary = CommonConstants.EXAMPLE_REQUEST_BODY_SUMMARY)
                      })),
      responses = {
        @ApiResponse(
            responseCode = "201",
            description =
                "Technical service supplier created successfully. " + CommonConstants.ID_INFO)
      })
  public Response addSupplier(
      @Context UriInfo uriInfo,
      OrganizationRepresentation content,
      @Parameter(description = DocDescription.ENDPOINT_VERSION)
          @DefaultValue("v1")
          @PathParam(value = "version")
          String version,
      @Parameter(description = DocDescription.OBJECT_ID) @PathParam(value = "id") String id)
      throws Exception {
    ServiceParameters params = new ServiceParameters();
    params.setEndpointVersion(version);
    params.setId(Long.valueOf(id));
    return post(uriInfo, sb.post(), content, params);
  }

  @DELETE
  @Since(CommonParams.VERSION_1)
  @Path(CommonParams.PATH_ORG_ID)
  @Operation(
      summary = "Removes supplier from authorized list to offer a marketable service",
      tags = {"technicalservices"},
      description =
          "Removes the supplier with the given ID from the list of suppliers who"
              + "are authorized to offer marketable services based on the specified technical service and id of the organization",
      responses = {
        @ApiResponse(
            responseCode = "204",
            description = "Technical service supplier removed successfully")
      })
  public Response removeSupplier(
      @Context UriInfo uriInfo,
      @Parameter(description = DocDescription.ENDPOINT_VERSION)
          @DefaultValue("v1")
          @PathParam(value = "version")
          String version,
      @Parameter(description = DocDescription.OBJECT_ID) @PathParam(value = "id") String id,
      @Parameter(description = DocDescription.ORGANIZATION_ID) @PathParam(value = "orgId")
          String orgId)
      throws Exception {
    ServiceParameters params = new ServiceParameters();
    params.setEndpointVersion(version);
    params.setId(Long.valueOf(id));
    params.setOrgId(orgId);
    return delete(uriInfo, sb.delete(), params);
  }
}
