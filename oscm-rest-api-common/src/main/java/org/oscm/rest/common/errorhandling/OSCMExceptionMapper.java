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
import org.oscm.rest.common.CommonParams;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

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
        response =
            Response.status(Response.Status.NOT_FOUND)
                .entity(
                    ErrorResponse.of()
                        .errorMessage(CommonParams.ERROR_RESOURCE_NOT_FOUND)
                        .errorDetails(e.getMessage())
                        .build())
                .build();
        break;
      case "ConcurrentModificationException":
        response =
            Response.status(Response.Status.CONFLICT)
                .entity(
                    ErrorResponse.of()
                        .errorMessage(CommonParams.ERROR_OUTDATED_VERSION)
                        .errorDetails(e.getMessage())
                        .build())
                .build();
        break;
      case "ValidationException":
        response =
            Response.status(Response.Status.BAD_REQUEST)
                .entity(
                    ErrorResponse.of()
                        .errorMessage(CommonParams.ERROR_JSON_FORMAT)
                        .errorDetails(e.getMessage())
                        .build())
                .build();
        break;
      case "OperationNotPermittedException":
        response =
            Response.status(Response.Status.FORBIDDEN)
                .entity(
                    ErrorResponse.of()
                        .errorMessage(CommonParams.ERROR_NOT_AUTHORIZED)
                        .errorDetails(e.getMessage())
                        .build())
                .build();
        break;
      default:
        response =
            Response.serverError()
                .entity(
                    ErrorResponse.of()
                        .errorMessage(CommonParams.ERROR_UNEXPECTED_EXCEPTION)
                        .errorDetails(e.getMessage())
                        .build())
                .build();
        break;
    }

    return response;
  }
}
