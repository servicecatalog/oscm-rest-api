/**
 * *****************************************************************************
 *
 * <p>Copyright FUJITSU LIMITED 2020
 *
 * <p>Creation Date: April 16, 2020
 *
 * <p>*****************************************************************************
 */
package org.oscm.rest.common.errorhandling;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import lombok.extern.slf4j.Slf4j;
import org.apache.openejb.OpenEJBException;

/**
 * Exception handler triggered in case of EJB container exception is thrown (check @{@link
 * OpenEJBException})
 */
@Provider
@Slf4j
public class ContainerExceptionMapper implements ExceptionMapper<OpenEJBException> {

  @Override
  public Response toResponse(OpenEJBException e) {
    Response response;
    log.info("Handling exception: " + e.getClass().getName());
    String exceptionName = e.getClass().getSimpleName();

    switch (exceptionName) {
      case "InvalidateReferenceException":
        response = ErrorResponse.provider().build().forbidden(e.getMessage());
        break;
      default:
        response = ErrorResponse.provider().build().internalServerError(e.getMessage());
        break;
    }

    return response;
  }
}
