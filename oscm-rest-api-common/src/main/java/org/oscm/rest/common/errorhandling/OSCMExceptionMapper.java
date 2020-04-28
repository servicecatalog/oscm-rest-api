/**
 * *****************************************************************************
 *
 * <p>Copyright FUJITSU LIMITED 2017
 *
 * <p>Creation Date: August 26, 2016
 *
 * <p>*****************************************************************************
 */
package org.oscm.rest.common.errorhandling;

import lombok.extern.slf4j.Slf4j;
import org.oscm.internal.types.exception.SaaSApplicationException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Exception handler triggered in case of OSCM defined exception is thrown (check @{@link
 * SaaSApplicationException})
 */
@Slf4j
@Provider
public class OSCMExceptionMapper implements ExceptionMapper<SaaSApplicationException> {

  @Override
  public Response toResponse(SaaSApplicationException e) {

    Response response;
    log.info("Handling exception: " + e.getClass().getName());
    String exceptionName = e.getClass().getSimpleName();

    switch (exceptionName) {
      case "ObjectNotFoundException":
        response = ErrorResponse.Provider.notFound(e);
        break;
      case "ConcurrentModificationException":
        response = ErrorResponse.Provider.conflict(e);
        break;
      case "ValidationException":
      case "NonUniqueBusinessKeyException":
        response = ErrorResponse.Provider.badRequest(e);
        break;
      case "OrganizationAuthorityException":
        String message = e.getMessage();
        if (message.contains("Creation of organization failed")) {
          response = ErrorResponse.Provider.badRequest(e);
        } else {
          response = ErrorResponse.Provider.forbidden(e);
        }
        break;
      case "OperationNotPermittedException":
        response = ErrorResponse.Provider.forbidden(e);
        break;
      default:
        response = ErrorResponse.Provider.internalServerError(e);
        break;
    }

    return response;
  }
}
