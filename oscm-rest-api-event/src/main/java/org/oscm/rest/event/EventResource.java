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
import lombok.AccessLevel;
import lombok.Setter;
import org.oscm.rest.common.CommonParams;
import org.oscm.rest.common.RestResource;
import org.oscm.rest.common.Since;
import org.oscm.rest.common.errorhandling.RestErrorResponseFactory;
import org.oscm.rest.common.representation.EventRepresentation;
import org.oscm.rest.common.requestparameters.EventParameters;

@Path(CommonParams.PATH_VERSION + "/events")
@Stateless
public class EventResource extends RestResource {

  @EJB
  @Setter(value = AccessLevel.PROTECTED)
  EventBackend eb;

  @Since(CommonParams.VERSION_1)
  @POST
  @Produces(MediaType.APPLICATION_JSON)
  public Response recordEvent(
      @Context UriInfo uriInfo, EventRepresentation content, @BeanParam EventParameters params) {
    try {
      return post(uriInfo, eb.post(), content, params);
    } catch (Exception e) {
      return RestErrorResponseFactory.getResponse(e);
    }
  }
}
