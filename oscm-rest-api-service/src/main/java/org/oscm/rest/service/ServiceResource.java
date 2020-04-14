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
import constants.DocDescription;
import constants.ServiceConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.util.Optional;
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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import lombok.AccessLevel;
import lombok.Setter;
import org.oscm.internal.types.enumtypes.PerformanceHint;
import org.oscm.internal.types.enumtypes.Sorting;
import org.oscm.internal.vo.ListCriteria;
import org.oscm.rest.common.CommonParams;
import org.oscm.rest.common.RestResource;
import org.oscm.rest.common.Since;
import org.oscm.rest.common.representation.ServiceDetailsRepresentation;
import org.oscm.rest.common.representation.ServiceRepresentation;
import org.oscm.rest.common.representation.StatusRepresentation;
import org.oscm.rest.common.requestparameters.ServiceParameters;

@Path(CommonParams.PATH_VERSION + "/services")
@Stateless
public class ServiceResource extends RestResource {

  @EJB
  @Setter(value = AccessLevel.PROTECTED)
  ServiceBackend sb;

  @GET
  @Since(CommonParams.VERSION_1)
  @Produces(CommonParams.JSON)
  @Operation(
      summary = "Retrieves all services",
      tags = {"services"},
      description =
          "Returns services available for current user if no query parameter is specified. "
              + "Use the searchPhrase query parameter to search for a list of services by name. "
              + "Paging can not be performed when searching via phrase. Specify offset and limit to use paging",
      responses = {
        @ApiResponse(
            responseCode = "200",
            description = "Services list",
            content =
                @Content(
                    mediaType = "application/json",
                    examples = {@ExampleObject(ServiceConstants.SERVICE_LIST_EXAMPLE_RESPONSE)},
                    schema = @Schema(implementation = ServiceRepresentation.class)))
      })
  public Response getServices(
      @Context UriInfo uriInfo,
      @Parameter(description = DocDescription.ENDPOINT_VERSION)
          @DefaultValue("v1")
          @PathParam(value = "version")
          String version,
      @Parameter(description = DocDescription.LOCALE, required = false)
          @QueryParam(value = "locale")
          String locale,
      @Parameter(description = DocDescription.MARKETPLACE_ID, required = false)
          @QueryParam(value = "marketplaceId")
          String marketplaceId,
      @Parameter(description = DocDescription.SEARCH_PHRASE, required = false)
          @QueryParam(value = "searchPhrase")
          String searchPhrase,
      @Parameter(description = DocDescription.LIMIT, required = false) @QueryParam(value = "limit")
          String limit,
      @Parameter(description = DocDescription.OFFSET, required = false)
          @QueryParam(value = "offset")
          String offset,
      @Parameter(description = DocDescription.FILTER, required = false)
          @QueryParam(value = "filter")
          String filter,
      @Parameter(description = DocDescription.SORTING) @QueryParam("sorting") Sorting sorting)
      throws Exception {

    ServiceParameters params = new ServiceParameters();
    params.setEndpointVersion(version);
    params.setSearchPhrase(searchPhrase);
    params.setLanguage(locale);
    params.setMarketPlaceId(marketplaceId);
    params.setPerformanceHint(PerformanceHint.ALL_FIELDS);
    params.setListCriteria(createListCriteria(offset, limit, filter, sorting));
    return getCollection(uriInfo, sb.getCollection(), params);
  }

  private ListCriteria createListCriteria(
      String offset, String limit, String filter, Sorting sorting) {
    ListCriteria c = new ListCriteria();
    if (Optional.ofNullable(limit).isPresent() && Optional.ofNullable(offset).isPresent()) {
      c.setFilter(filter);
      c.setLimit(Integer.parseInt(limit));
      c.setOffset(Integer.parseInt(offset));
      c.setSorting(getSorting(sorting));
    }
    return c;
  }

  private Sorting getSorting(Sorting sorting) {
    return Optional.ofNullable(sorting).orElse(Sorting.NAME_ASCENDING);
  }

  @GET
  @Since(CommonParams.VERSION_1)
  @Path(CommonParams.PATH_ID)
  @Produces(CommonParams.JSON)
  @Operation(
      summary = "Get a single service",
      tags = {"services"},
      description = "Returns a single service",
      responses = {
        @ApiResponse(
            responseCode = "200",
            description = "A single service",
            content =
                @Content(
                    mediaType = "application/json",
                    examples = {@ExampleObject(ServiceConstants.SERVICE_EXAMPLE_RESPONSE)},
                    schema = @Schema(implementation = ServiceRepresentation.class)))
      })
  public Response getService(
      @Context UriInfo uriInfo,
      @Parameter(description = DocDescription.ENDPOINT_VERSION)
          @DefaultValue("v1")
          @PathParam(value = "version")
          String version,
      @Parameter(description = DocDescription.SERVICE_ID) @PathParam(value = "id") String id)
      throws Exception {
    ServiceParameters params = new ServiceParameters();
    params.setEndpointVersion(version);
    params.setId(Long.valueOf(id));
    return get(uriInfo, sb.get(), params, true);
  }

  @POST
  @Since(CommonParams.VERSION_1)
  @Operation(
      summary = "Creates a service",
      tags = {"services"},
      description = "Creates a service based on given request data",
      requestBody =
          @RequestBody(
              description = "JSON representing service object to be created",
              required = true,
              content =
                  @Content(
                      mediaType = "application/json",
                      schema = @Schema(implementation = ServiceRepresentation.class),
                      examples = {
                        @ExampleObject(
                            name = CommonConstants.EXAMPLE_REQUEST_BODY_DESCRIPTION,
                            value = ServiceConstants.SERVICE_CREATE_EXAMPLE_REQUEST,
                            summary = CommonConstants.EXAMPLE_REQUEST_BODY_SUMMARY)
                      })),
      responses = {
        @ApiResponse(
            responseCode = "201",
            description = "Service created successfully" + CommonConstants.ID_INFO)
      })
  public Response createService(
      @Context UriInfo uriInfo,
      ServiceDetailsRepresentation content,
      @Parameter(description = DocDescription.ENDPOINT_VERSION)
          @DefaultValue("v1")
          @PathParam(value = "version")
          String version)
      throws Exception {
    ServiceParameters params = new ServiceParameters();
    params.setEndpointVersion(version);
    return post(uriInfo, sb.post(), content, params);
  }

  @PUT
  @Since(CommonParams.VERSION_1)
  @Path(CommonParams.PATH_ID)
  @Operation(
      summary = "Updates a single service",
      tags = {"services"},
      description = "Updates a single service based on given request data",
      requestBody =
          @RequestBody(
              description = "JSON representing service object to be updated",
              required = true,
              content =
                  @Content(
                      mediaType = "application/json",
                      schema = @Schema(implementation = ServiceRepresentation.class),
                      examples = {
                        @ExampleObject(
                            name = CommonConstants.EXAMPLE_PUT_REQUEST_BODY_DESCRIPTION,
                            value = ServiceConstants.SERVICE_UPDATE_EXAMPLE_REQUEST,
                            summary = CommonConstants.EXAMPLE_REQUEST_BODY_SUMMARY)
                      })),
      responses = {
        @ApiResponse(responseCode = "204", description = "Service updated successfully")
      })
  public Response updateService(
      @Context UriInfo uriInfo,
      ServiceDetailsRepresentation content,
      @Parameter(description = DocDescription.ENDPOINT_VERSION)
          @DefaultValue("v1")
          @PathParam(value = "version")
          String version,
      @Parameter(description = DocDescription.SERVICE_ID) @PathParam(value = "id") String id)
      throws Exception {
    ServiceParameters params = new ServiceParameters();
    params.setEndpointVersion(version);
    params.setId(Long.valueOf(id));
    return put(uriInfo, sb.put(), content, params);
  }

  @PUT
  @Since(CommonParams.VERSION_1)
  @Path(CommonParams.PATH_ID + "/status")
  @Operation(
      summary = "Updates service status",
      tags = {"services"},
      description = "Updates service status based on given request data",
      requestBody =
          @RequestBody(
              description = "JSON representing status object for given service to be set",
              required = true,
              content =
                  @Content(
                      mediaType = "application/json",
                      schema = @Schema(implementation = ServiceRepresentation.class),
                      examples = {
                        @ExampleObject(
                            name = CommonConstants.EXAMPLE_REQUEST_BODY_DESCRIPTION,
                            value = ServiceConstants.SERVICE_STATUS_UPDATE_EXAMPLE_REQUEST,
                            summary = CommonConstants.EXAMPLE_REQUEST_BODY_SUMMARY)
                      })),
      responses = {
        @ApiResponse(responseCode = "204", description = "Service status updated successfully")
      })
  public Response setServiceState(
      @Context UriInfo uriInfo,
      StatusRepresentation content,
      @Parameter(description = DocDescription.ENDPOINT_VERSION)
          @DefaultValue("v1")
          @PathParam(value = "version")
          String version,
      @Parameter(description = DocDescription.SERVICE_ID) @PathParam(value = "id") String id)
      throws Exception {
    ServiceParameters params = new ServiceParameters();
    params.setEndpointVersion(version);
    params.setId(Long.valueOf(id));
    return put(uriInfo, sb.putStatus(), content, params);
  }

  @DELETE
  @Since(CommonParams.VERSION_1)
  @Path(CommonParams.PATH_ID)
  @Operation(
      summary = "Deletes a single service",
      tags = {"services"},
      description = "Deletes a single service based on given service id",
      responses = {
        @ApiResponse(responseCode = "204", description = "Service deleted successfully")
      })
  public Response deleteService(
      @Context UriInfo uriInfo,
      @Parameter(description = DocDescription.ENDPOINT_VERSION)
          @DefaultValue("v1")
          @PathParam(value = "version")
          String version,
      @Parameter(description = DocDescription.SERVICE_ID) @PathParam(value = "id") String id)
      throws Exception {
    ServiceParameters params = new ServiceParameters();
    params.setEndpointVersion(version);
    params.setId(Long.valueOf(id));
    return delete(uriInfo, sb.delete(), params);
  }
}
