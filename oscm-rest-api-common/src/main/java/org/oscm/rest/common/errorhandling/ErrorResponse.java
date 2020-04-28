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
import lombok.Setter;
import org.oscm.rest.common.CommonParams;

import javax.ws.rs.core.Response;

@Builder(builderMethodName = "provider")
@Getter
@Setter
public class ErrorResponse {

  private String errorMessage;
  private String errorDetails;

  public Response badRequest() {
    return Response.status(Response.Status.BAD_REQUEST).entity(this).build();
  }

  public Response badRequest(String details) {
    return Response.status(Response.Status.BAD_REQUEST)
        .entity(
            ErrorResponse.provider()
                .errorMessage(CommonParams.ERROR_JSON_FORMAT)
                .errorDetails(details)
                .build())
        .build();
  }

  public Response forbidden() {
    return Response.status(Response.Status.FORBIDDEN).entity(this).build();
  }

  public Response forbidden(String details) {
    return Response.status(Response.Status.FORBIDDEN)
        .entity(
            ErrorResponse.provider()
                .errorMessage(CommonParams.ERROR_NOT_AUTHORIZED)
                .errorDetails(details)
                .build())
        .build();
  }

  public Response notFound() {
    return Response.status(Response.Status.NOT_FOUND).entity(this).build();
  }

  public Response notFound(String details) {
    return Response.status(Response.Status.NOT_FOUND)
        .entity(
            ErrorResponse.provider()
                .errorMessage(CommonParams.ERROR_RESOURCE_NOT_FOUND)
                .errorDetails(details)
                .build())
        .build();
  }

  public Response conflict() {
    return Response.status(Response.Status.CONFLICT).entity(this).build();
  }

  public Response conflict(String details) {
    return Response.status(Response.Status.CONFLICT)
        .entity(
            ErrorResponse.provider()
                .errorMessage(CommonParams.ERROR_OUTDATED_VERSION)
                .errorDetails(details)
                .build())
        .build();
  }

  public Response internalServerError(String details) {
    return Response.serverError()
        .entity(
            ErrorResponse.provider()
                .errorMessage(CommonParams.ERROR_UNEXPECTED_EXCEPTION)
                .errorDetails(details)
                .build())
        .build();
  }
}
