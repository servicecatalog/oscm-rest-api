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

import org.oscm.internal.intf.EventService;
import org.oscm.internal.types.exception.*;
import org.oscm.rest.common.RestBackend;
import org.oscm.rest.common.representation.EventRepresentation;
import org.oscm.rest.common.requestparameters.EventParameters;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.core.Response;
import java.lang.IllegalArgumentException;

@Stateless
public class EventBackend {

  @EJB EventService es;
  private Response restResponse;

  public RestBackend.Post<EventRepresentation, EventParameters> post()
      throws DuplicateEventException, OrganizationAuthoritiesException, ObjectNotFoundException,
          ValidationException, IllegalArgumentException, SaaSSystemException {
    return (content, params) -> {
      if (content.isSubcriptionKeySet()) {
        es.recordEventForSubscription(content.getSubscriptionKey(), content.getVO());
      } else {
        es.recordEventForInstance(
            content.getTechnicalServiceId(), content.getInstanceId(), content.getVO());
      }
      return Response.noContent().build();
    };
  }
}
