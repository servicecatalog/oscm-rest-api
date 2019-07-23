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

import constants.CommonConstants;
import constants.EventConstants;
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
import org.oscm.rest.event.data.EventRepresentation;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Since(CommonParams.VERSION_1)
@Path(CommonParams.PATH_VERSION + "/events")
@Produces(MediaType.APPLICATION_JSON)
@Stateless
public class EventResource extends RestResource {

  @EJB
  @Setter(value = AccessLevel.PROTECTED)
  EventBackend eb;

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Operation(summary = "Create a single event.",
          tags = {"event"},
          description = "Creates a single event.",
          requestBody = @RequestBody(
                  description = "EventRepresentation object to be created.",
                  required = true,
                  content = @Content(
                          schema = @Schema(implementation = EventRepresentation.class),
                  examples = {
                          @ExampleObject(
                                  name = CommonConstants.EXAMPLE_MAXIMUM_BODY_NAME,
                                  value = EventConstants.EVENT_MAXIMUM_SUBSCRIPTION_EVENT_BODY,
                                  summary = CommonConstants.EXAMPLE_MAXIMUM_BODY_SUMMARY + " "
                                          + EventConstants.SUBSCRIPTION_EVENT_SUMMARY),
                          @ExampleObject(
                                  name = CommonConstants.EXAMPLE_MAXIMUM_BODY_NAME + ". " +
                                          EventConstants.INSTANCE_EVENT_ADDITIONAL_INFO,
                                  value = EventConstants.EVENT_MAXIMUM_INSTANCE_EVENT_BODY,
                                  summary = CommonConstants.EXAMPLE_MAXIMUM_BODY_SUMMARY + " " +
                                          EventConstants.INSTANCE_EVENT_SUMMARY)
                  })),
          responses = {
                  @ApiResponse(responseCode = "200", description = "A single event", content = @Content(
                          schema = @Schema(implementation = EventRepresentation.class)
                  ))
          })
  public Response recordEvent(@Context UriInfo uriInfo, EventRepresentation content,
                              @BeanParam EventParameters params) throws Exception {
    return post(uriInfo, eb.post(), content, params);
  }
}
