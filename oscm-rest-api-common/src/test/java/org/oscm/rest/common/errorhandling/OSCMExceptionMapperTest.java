package org.oscm.rest.common.errorhandling;

import org.junit.jupiter.api.Test;
import org.oscm.internal.types.exception.*;
import org.oscm.rest.common.CommonParams;

import javax.ws.rs.core.Response;

import static org.assertj.core.api.Assertions.assertThat;

class OSCMExceptionMapperTest {

  @Test
  void testToResponse_returnsInternalError_whenNotHandledSaasException() {

    // given
    OSCMExceptionMapper mapper = new OSCMExceptionMapper();
    String excMsg = "ExceptionMessage";

    SaaSApplicationException exc = new SaaSApplicationException(excMsg);
    String expectedMsg = "EXCEPTIONID " + exc.getId() + ": " + excMsg;

    // when
    Response response = mapper.toResponse(exc);

    // then
    assertThat(response.getStatusInfo()).isEqualTo(Response.Status.INTERNAL_SERVER_ERROR);
    assertThat(response.getEntity()).isInstanceOf(ErrorResponse.class);
    assertThat(((ErrorResponse) response.getEntity()).getErrorMessage())
        .isEqualTo(CommonParams.ERROR_UNEXPECTED_EXCEPTION);
    assertThat(((ErrorResponse) response.getEntity()).getErrorDetails()).isEqualTo(expectedMsg);
  }

  @Test
  void testToResponse_returnsNotFound_whenObjectNotFoundException() {

    // given
    OSCMExceptionMapper mapper = new OSCMExceptionMapper();
    String excMsg = "ExceptionMessage";
    ObjectNotFoundException exc = new ObjectNotFoundException(excMsg);
    String expectedMsg = "EXCEPTIONID " + exc.getId() + ": " + excMsg;

    // when
    Response response = mapper.toResponse(exc);

    // then
    assertThat(response.getStatusInfo()).isEqualTo(Response.Status.NOT_FOUND);
    assertThat(response.getEntity()).isInstanceOf(ErrorResponse.class);
    assertThat(((ErrorResponse) response.getEntity()).getErrorMessage())
        .isEqualTo(CommonParams.ERROR_RESOURCE_NOT_FOUND);
    assertThat(((ErrorResponse) response.getEntity()).getErrorDetails()).isEqualTo(expectedMsg);
  }

  @Test
  void testToResponse_returnsNotFound_whenConcurrentModificationException() {

    // given
    OSCMExceptionMapper mapper = new OSCMExceptionMapper();
    String excMsg = "ExceptionMessage";
    ConcurrentModificationException exc = new ConcurrentModificationException(excMsg);
    String expectedMsg = "EXCEPTIONID " + exc.getId() + ": " + excMsg;

    // when
    Response response = mapper.toResponse(exc);

    // then
    assertThat(response.getStatusInfo()).isEqualTo(Response.Status.CONFLICT);
    assertThat(response.getEntity()).isInstanceOf(ErrorResponse.class);
    assertThat(((ErrorResponse) response.getEntity()).getErrorMessage())
        .isEqualTo(CommonParams.ERROR_OUTDATED_VERSION);
    assertThat(((ErrorResponse) response.getEntity()).getErrorDetails()).isEqualTo(expectedMsg);
  }

  @Test
  void testToResponse_returnsNotFound_whenValidationException() {

    // given
    OSCMExceptionMapper mapper = new OSCMExceptionMapper();
    String excMsg = "ExceptionMessage";
    ValidationException exc = new ValidationException(excMsg);
    String expectedMsg = "EXCEPTIONID " + exc.getId() + ": " + excMsg;

    // when
    Response response = mapper.toResponse(exc);

    // then
    assertThat(response.getStatusInfo()).isEqualTo(Response.Status.BAD_REQUEST);
    assertThat(response.getEntity()).isInstanceOf(ErrorResponse.class);
    assertThat(((ErrorResponse) response.getEntity()).getErrorMessage())
        .isEqualTo(CommonParams.ERROR_JSON_FORMAT);
    assertThat(((ErrorResponse) response.getEntity()).getErrorDetails()).isEqualTo(expectedMsg);
  }

  @Test
  void testToResponse_returnsNotFound_whenOperationNotPermittedException() {

    // given
    OSCMExceptionMapper mapper = new OSCMExceptionMapper();
    String excMsg = "ExceptionMessage";
    OperationNotPermittedException exc = new OperationNotPermittedException(excMsg);
    String expectedMsg = "EXCEPTIONID " + exc.getId() + ": " + excMsg;

    // when
    Response response = mapper.toResponse(exc);

    // then
    assertThat(response.getStatusInfo()).isEqualTo(Response.Status.FORBIDDEN);
    assertThat(response.getEntity()).isInstanceOf(ErrorResponse.class);
    assertThat(((ErrorResponse) response.getEntity()).getErrorMessage())
        .isEqualTo(CommonParams.ERROR_NOT_AUTHORIZED);
    assertThat(((ErrorResponse) response.getEntity()).getErrorDetails()).isEqualTo(expectedMsg);
  }
}
