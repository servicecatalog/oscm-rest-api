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
import org.oscm.rest.common.CommonParams;
import org.oscm.rest.common.RestBackend;
import org.oscm.rest.common.requestparameters.EventParameters;
import org.oscm.rest.common.representation.EventRepresentation;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class EventBackend {

  @EJB EventService es;

  public RestBackend.Post<EventRepresentation, EventParameters> post() {
    return (content, params) -> {
      if (content.isSubcriptionKeySet()) {
        es.recordEventForSubscription(content.getSubscriptionKey(), content.getVO());
      } else {
        es.recordEventForInstance(
            content.getTechnicalServiceId(), content.getInstanceId(), content.getVO());
      }
      return CommonParams.DUMMY_ID;
    };
  }
}
