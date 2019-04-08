package org.oscm.rest.event;

import org.oscm.internal.intf.EventService;
import org.oscm.rest.common.RestBackend;
import org.oscm.rest.event.data.EventRepresentation;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class EventBackend {

  @EJB EventService es;

  public RestBackend.Post<EventRepresentation, EventParameters> post() {
    return new RestBackend.Post<EventRepresentation, EventParameters>() {

      @Override
      public Object post(EventRepresentation content, EventParameters params) throws Exception {
        if (content.isSubcriptionKeySet()) {
          es.recordEventForSubscription(content.getSubscriptionKey(), content.getVO());
        } else {
          es.recordEventForInstance(
              content.getTechnicalServiceId(), content.getInstanceId(), content.getVO());
        }
        return null;
      }
    };
  }
}
