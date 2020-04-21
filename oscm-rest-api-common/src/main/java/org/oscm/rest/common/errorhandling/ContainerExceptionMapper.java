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

import lombok.extern.slf4j.Slf4j;
import org.apache.openejb.OpenEJBException;
import org.oscm.rest.common.CommonParams;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

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
