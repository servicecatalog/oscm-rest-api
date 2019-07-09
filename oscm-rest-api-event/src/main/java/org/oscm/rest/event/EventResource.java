/**
 * *****************************************************************************
 *
 * <p>Copyright FUJITSU LIMITED 2019
 *
 * <p>Creation Date: 10-04-2019
 *
 * <p>*****************************************************************************
 */
package org.oscm.rest.event;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AccessLevel;
import lombok.Setter;
import org.oscm.rest.common.CommonParams;
import org.oscm.rest.common.RestResource;
import org.oscm.rest.common.Since;
import org.oscm.rest.event.data.EventRepresentation;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.BeanParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path(CommonParams.PATH_VERSION + "/events")
@Stateless
@Produces(MediaType.APPLICATION_JSON)
@Since(CommonParams.VERSION_1)
public class EventResource extends RestResource {

  @EJB
  @Setter(value = AccessLevel.PROTECTED)
  EventBackend eb;

  @POST
  @Operation(summary = "Get a single event.",
          tags = {"event"},
          description = "Returns a single event.",
          responses = {
                  @ApiResponse(responseCode = "200", description = "A single event", content = @Content(
                          schema = @Schema(implementation = EventRepresentation.class)
                  ))
          })
  public Response recordEvent(
      @Context UriInfo uriInfo,
      @RequestBody(description = "EventRepresentation object to be created.", required = true,
              content = @Content(
                      schema = @Schema(implementation = EventRepresentation.class))) EventRepresentation content,
      @BeanParam EventParameters params)
      throws Exception {
    return post(uriInfo, eb.post(), content, params);
  }
}
