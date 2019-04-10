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
import org.oscm.internal.intf.EventService;
import org.oscm.rest.common.RestBackend;
import org.oscm.rest.event.data.EventRepresentation;

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
