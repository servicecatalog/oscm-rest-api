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

import static javax.ws.rs.core.MediaType.APPLICATION_XML;

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
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import lombok.AccessLevel;
import lombok.Setter;
import org.oscm.rest.common.CommonParams;
import org.oscm.rest.common.RestResource;
import org.oscm.rest.common.Since;
import org.oscm.rest.common.representation.TechnicalServiceImportRepresentation;
import org.oscm.rest.common.representation.TechnicalServiceRepresentation;
import org.oscm.rest.common.representation.TechnicalServiceXMLRepresentation;
import org.oscm.rest.common.requestparameters.ServiceParameters;

@Path(CommonParams.PATH_VERSION + "/technicalservices")
@Stateless
public class TechnicalServiceResource extends RestResource {

  @EJB
  @Setter(value = AccessLevel.PROTECTED)
  TechnicalServiceBackend tsb;

  @GET
  @Since(CommonParams.VERSION_1)
  @Path(CommonParams.PATH_ID)
  @Produces({CommonParams.JSON, APPLICATION_XML})
  @Operation(
      summary = "Retrieves a technical service with given id",
      tags = {"technicalservices"},
      description = "Returns technical services based on given id",
      responses = {
        @ApiResponse(
            responseCode = "200",
            description = "Technical service",
            content =
                @Content(
                    mediaType = "application/json",
                    examples = {
                      @ExampleObject(ServiceConstants.TECHNICAL_SERVICE_EXAMPLE_RESPONSE)
                    },
                    schema = @Schema(implementation = TechnicalServiceRepresentation.class)))
      })
  public Response getTechnicalService(
      @Context UriInfo uriInfo,
      @Context HttpHeaders headers,
      @Parameter(description = DocDescription.ENDPOINT_VERSION)
          @DefaultValue("v1")
          @PathParam(value = "version")
          String version,
      @Parameter(description = DocDescription.TECHNICAL_SERVICE_ID) @PathParam(value = "id")
          long id)
      throws Exception {

    ServiceParameters params = new ServiceParameters();
    params.setEndpointVersion(version);
    params.setId(Long.valueOf(id));
    return get(uriInfo, tsb.get(), params, true);
  }

  @GET
  @Since(CommonParams.VERSION_1)
  @Produces({CommonParams.JSON})
  @Operation(
      summary = "Retrieves all available technical services",
      tags = {"technicalservices"},
      description = "Returns all available technical services",
      responses = {
        @ApiResponse(
            responseCode = "200",
            description = "Technical services list",
            content =
                @Content(
                    mediaType = "application/json",
                    examples = {
                      @ExampleObject(ServiceConstants.TECHNICAL_SERVICE_LIST_EXAMPLE_RESPONSE)
                    },
                    schema = @Schema(implementation = TechnicalServiceRepresentation.class)))
      })
  public Response getTechnicalServices(
      @Context UriInfo uriInfo,
      @Context HttpHeaders headers,
      @Parameter(description = DocDescription.ENDPOINT_VERSION)
          @DefaultValue("v1")
          @PathParam(value = "version")
          String version)
      throws Exception {
    ServiceParameters params = new ServiceParameters();
    params.setEndpointVersion(version);
    return getCollection(uriInfo, tsb.getCollection(), params);
  }

  @GET
  @Path("/xml")
  @Since(CommonParams.VERSION_1)
  @Produces({CommonParams.JSON})
  @Operation(
      summary = "Retrieves all available technical services as XML",
      tags = {"technicalservices"},
      description = "Returns all available technical services as XML",
      responses = {
        @ApiResponse(
            responseCode = "200",
            description = "Technical services list",
            content =
                @Content(
                    mediaType = "application/json",
                    examples = {
                      @ExampleObject(ServiceConstants.TECHNICAL_SERVICE_LIST_XML_EXAMPLE_RESPONSE)
                    },
                    schema = @Schema(implementation = TechnicalServiceXMLRepresentation.class)))
      })
  public Response getTechnicalServicesAsXML(
      @Context UriInfo uriInfo,
      @Context HttpHeaders headers,
      @Parameter(description = DocDescription.ENDPOINT_VERSION)
          @DefaultValue("v1")
          @PathParam(value = "version")
          String version)
      throws Exception {
    ServiceParameters params = new ServiceParameters();
    params.setEndpointVersion(version);
    return getCollection(uriInfo, tsb.getXMLCollection(), params);
  }

  @GET
  @Path("/xml/" + CommonParams.PATH_ID)
  @Since(CommonParams.VERSION_1)
  @Produces({CommonParams.JSON, APPLICATION_XML})
  @Operation(
      summary = "Retrieves a technical service with given id as XML",
      tags = {"technicalservices"},
      description = "Returns technical services based on given id",
      responses = {
        @ApiResponse(
            responseCode = "200",
            description = "Technical service",
            content = {
              @Content(
                  mediaType = "application/json",
                  examples = {
                    @ExampleObject(ServiceConstants.TECHNICAL_SERVICE_LIST_XML_EXAMPLE_RESPONSE)
                  },
                  schema = @Schema(implementation = TechnicalServiceXMLRepresentation.class))
            })
      })
  public Response getTechnicalServiceAsXML(
      @Context UriInfo uriInfo,
      @Context HttpHeaders headers,
      @Parameter(description = DocDescription.ENDPOINT_VERSION)
          @DefaultValue("v1")
          @PathParam(value = "version")
          String version,
      @Parameter(description = DocDescription.TECHNICAL_SERVICE_ID) @PathParam(value = "id")
          long id)
      throws Exception {
    ServiceParameters params = new ServiceParameters();
    params.setEndpointVersion(version);
    params.setId(Long.valueOf(id));
    return get(uriInfo, tsb.getXML(), params, true);
  }

  @POST
  @Since(CommonParams.VERSION_1)
  @Operation(
      summary = "Creates a technical service",
      tags = {"technicalservices"},
      description = "Creates a technical service based on given request data",
      requestBody =
          @RequestBody(
              description = "JSON representing technical service object to be created",
              required = true,
              content =
                  @Content(
                      mediaType = "application/json",
                      schema = @Schema(implementation = TechnicalServiceRepresentation.class),
                      examples = {
                        @ExampleObject(
                            name = CommonConstants.EXAMPLE_REQUEST_BODY_DESCRIPTION,
                            value = ServiceConstants.TECHNICAL_SERVICE_CREATE_EXAMPLE_BODY,
                            summary = CommonConstants.EXAMPLE_REQUEST_BODY_SUMMARY)
                      })),
      responses = {
        @ApiResponse(
            responseCode = "201",
            description = "Technical service created successfully" + CommonConstants.ID_INFO)
      })
  public Response createTechnicalService(
      @Context UriInfo uriInfo,
      @Parameter(description = DocDescription.ENDPOINT_VERSION)
          @DefaultValue("v1")
          @PathParam(value = "version")
          String version,
      TechnicalServiceRepresentation content)
      throws Exception {
    ServiceParameters params = new ServiceParameters();
    params.setEndpointVersion(version);
    return post(uriInfo, tsb.post(), content, params);
  }

  @DELETE
  @Since(CommonParams.VERSION_1)
  @Path(CommonParams.PATH_ID)
  @Operation(
      summary = "Delete a single technical service",
      tags = {"technicalservices"},
      description = "Deletes a single technical service based on given id of the object",
      responses = {
        @ApiResponse(responseCode = "204", description = "Technical service deleted successfully")
      })
  public Response deleteTechnicalService(
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
    return delete(uriInfo, tsb.delete(), params);
  }

  @PUT
  @Path("/import")
  @Since(CommonParams.VERSION_1)
  @Operation(
      summary = "Imports a technical service",
      tags = {"technicalservices"},
      description = "Imports a technical service based on given xml content",
      requestBody =
          @RequestBody(
              description = "JSON representing technical service object to be imported",
              required = true,
              content =
                  @Content(
                      mediaType = "application/json",
                      schema = @Schema(implementation = TechnicalServiceImportRepresentation.class),
                      examples = {
                        @ExampleObject(
                            name = CommonConstants.EXAMPLE_REQUEST_BODY_DESCRIPTION,
                            value = ServiceConstants.TECHNICAL_SERVICE_IMPORT_EXAMPLE_BODY,
                            summary = CommonConstants.EXAMPLE_REQUEST_BODY_SUMMARY)
                      })),
      responses = {
        @ApiResponse(responseCode = "204", description = "Technical service imported successfully")
      })
  public Response importTechnicalService(
      @Context UriInfo uriInfo,
      @Parameter(description = DocDescription.ENDPOINT_VERSION)
          @DefaultValue("v1")
          @PathParam(value = "version")
          String version,
      TechnicalServiceImportRepresentation content)
      throws Exception {
    ServiceParameters params = new ServiceParameters();
    params.setEndpointVersion(version);
    params.setId(Long.valueOf(0L));
    return put(uriInfo, tsb.importFromXml(), content, params);
  }
}
