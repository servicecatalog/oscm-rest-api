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

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import lombok.extern.slf4j.Slf4j;
import org.oscm.internal.types.exception.SaaSApplicationException;

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
    String exceptionMessage = e.getMessage();

    switch (exceptionName) {
      case "ObjectNotFoundException":
        response = ErrorResponse.provider().build().notFound(exceptionMessage);
        break;
      case "ConcurrentModificationException":
        response = ErrorResponse.provider().build().conflict(exceptionMessage);
        break;
      case "ValidationException":
      case "UserRoleAssignmentException":
      case "NonUniqueBusinessKeyException":
      case "MarketplaceValidationException":
        response = ErrorResponse.provider().build().badRequest(exceptionMessage);
        break;
      case "OrganizationAuthorityException":
        if (exceptionMessage.contains("Creation of organization failed")) {
          response = ErrorResponse.provider().build().badRequest(exceptionMessage);
        } else {
          response = ErrorResponse.provider().build().forbidden(exceptionMessage);
        }
        break;
      case "OperationNotPermittedException":
        response = ErrorResponse.provider().build().forbidden(exceptionMessage);
        break;
      default:
        response = ErrorResponse.provider().build().internalServerError(exceptionMessage);
        break;
    }

    return response;
  }
}
