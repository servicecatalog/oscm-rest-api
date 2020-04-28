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

import lombok.Builder;
import lombok.Getter;
import org.oscm.rest.common.CommonParams;

import javax.ws.rs.core.Response;

@Builder(builderMethodName = "of")
@Getter
public class ErrorResponse {

  private String errorMessage;
  private String errorDetails;

  static class Provider {

    public static Response badRequest(Exception exc) {
      return Response.status(Response.Status.BAD_REQUEST)
          .entity(
              ErrorResponse.of()
                  .errorMessage(CommonParams.ERROR_JSON_FORMAT)
                  .errorDetails(exc.getMessage())
                  .build())
          .build();
    }

    public static Response forbidden(Exception exc) {
      return Response.status(Response.Status.FORBIDDEN)
          .entity(
              ErrorResponse.of()
                  .errorMessage(CommonParams.ERROR_NOT_AUTHORIZED)
                  .errorDetails(exc.getMessage())
                  .build())
          .build();
    }

    public static Response notFound(Exception exc) {
      return Response.status(Response.Status.NOT_FOUND)
          .entity(
              ErrorResponse.of()
                  .errorMessage(CommonParams.ERROR_RESOURCE_NOT_FOUND)
                  .errorDetails(exc.getMessage())
                  .build())
          .build();
    }

    public static Response conflict(Exception exc) {
      return Response.status(Response.Status.CONFLICT)
          .entity(
              ErrorResponse.of()
                  .errorMessage(CommonParams.ERROR_OUTDATED_VERSION)
                  .errorDetails(exc.getMessage())
                  .build())
          .build();
    }

    public static Response internalServerError(Exception exc) {
      return Response.serverError()
          .entity(
              ErrorResponse.of()
                  .errorMessage(CommonParams.ERROR_UNEXPECTED_EXCEPTION)
                  .errorDetails(exc.getMessage())
                  .build())
          .build();
    }
  }
}
