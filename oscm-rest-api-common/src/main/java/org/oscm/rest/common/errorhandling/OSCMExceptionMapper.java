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
        response = ErrorResponse.provider().build().notFound(e.getMessage());
        break;
      case "ConcurrentModificationException":
        response = ErrorResponse.provider().build().conflict(e.getMessage());
        break;
      case "ValidationException":
      case "NonUniqueBusinessKeyException":
        response = ErrorResponse.provider().build().badRequest(e.getMessage());
        break;
      case "OrganizationAuthorityException":
        String message = e.getMessage();
        if (message.contains("Creation of organization failed")) {
          response = ErrorResponse.provider().build().badRequest(e.getMessage());
        } else {
          response = ErrorResponse.provider().build().forbidden(e.getMessage());
        }
        break;
      case "OperationNotPermittedException":
        response = ErrorResponse.provider().build().forbidden(e.getMessage());
        break;
      default:
        response = ErrorResponse.provider().build().internalServerError(e.getMessage());
        break;
    }

    return response;
  }
}
