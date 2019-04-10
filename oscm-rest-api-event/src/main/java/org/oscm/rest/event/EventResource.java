package org.oscm.rest.event;

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
public class EventResource extends RestResource {

  @EJB EventBackend eb;

  @Since(CommonParams.VERSION_1)
  @POST
  @Produces(MediaType.APPLICATION_JSON)
  public Response recordEvent(
      @Context UriInfo uriInfo, EventRepresentation content, @BeanParam EventParameters params)
      throws Exception {
    return post(uriInfo, eb.post(), content, params);
  }
}