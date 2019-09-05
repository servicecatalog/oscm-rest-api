/**
 * *****************************************************************************
 *
 * <p>Copyright FUJITSU LIMITED 2019
 *
 * <p>Creation Date: 04-09-2019
 *
 * <p>*****************************************************************************
 */
package org.oscm.rest.common.errorhandling;

import java.util.HashMap;
import java.util.Map;
import javax.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import org.oscm.internal.types.exception.*;

/** This class created error Response object, depending on passed exception instance */
@Slf4j
public class RestErrorResponseFactory {
  private static final String NO_RESPONSE_DEFINITON_FOUND_ERROR_MESSAGE =
      "There is no error handling configured for %s. Generic response will be returned!";

  private static Map<Class, Response.Status> errorResponseStatusMap;

  static {
    errorResponseStatusMap = new HashMap<>();
    errorResponseStatusMap.put(SaaSSystemException.class, Response.Status.INTERNAL_SERVER_ERROR);
    errorResponseStatusMap.put(DuplicateEventException.class, Response.Status.CONFLICT);
    errorResponseStatusMap.put(OrganizationAuthoritiesException.class, Response.Status.FORBIDDEN);
    errorResponseStatusMap.put(ObjectNotFoundException.class, Response.Status.NOT_FOUND);
    errorResponseStatusMap.put(ValidationException.class, Response.Status.BAD_REQUEST);
    errorResponseStatusMap.put(IllegalArgumentException.class, Response.Status.BAD_REQUEST);
  }

  /**
   * Creates proper error Response for REST, depeding on passed exception instance
   *
   * @param e caught exception instance
   * @return Error Response object for REST API
   */
  public static Response getResponse(Exception e) {
    if (!errorResponseStatusMap.containsKey(e.getClass())) {
      log.warn(String.format(NO_RESPONSE_DEFINITON_FOUND_ERROR_MESSAGE, e.getClass().getName()));
      return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e).build();
    } else {
      return Response.status(errorResponseStatusMap.get(e.getClass())).entity(e).build();
    }
  }
}
