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

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.oscm.internal.types.exception.*;
import org.oscm.rest.common.CommonParams;

import javax.ws.rs.core.Response;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class OSCMExceptionMapperTest {

  @ParameterizedTest
  @MethodSource("provideArguments")
  public void testToResponse_shouldReturnProperErrorResponse_whenExceptionIsThrown(
      SaaSApplicationException exception, Response.Status status, String errorMsg) {

    // given
    OSCMExceptionMapper mapper = new OSCMExceptionMapper();

    // when
    Response response = mapper.toResponse(exception);

    // then
    assertThat(response.getStatusInfo()).isEqualTo(status);
    assertThat(response.getEntity()).isInstanceOf(ErrorResponse.class);
    assertThat(((ErrorResponse) response.getEntity()).getErrorMessage()).isEqualTo(errorMsg);
    assertThat(((ErrorResponse) response.getEntity()).getErrorDetails())
        .isEqualTo(exception.getMessage());
  }

  public static Stream<Arguments> provideArguments() {

    String excMsg = "ExceptionMessage";

    return Stream.of(
        Arguments.of(
            new ObjectNotFoundException(excMsg),
            Response.Status.NOT_FOUND,
            CommonParams.ERROR_RESOURCE_NOT_FOUND),
        Arguments.of(
            new ConcurrentModificationException(excMsg),
            Response.Status.CONFLICT,
            CommonParams.ERROR_OUTDATED_VERSION),
        Arguments.of(
            new ValidationException(excMsg),
            Response.Status.BAD_REQUEST,
            CommonParams.ERROR_JSON_FORMAT),
        Arguments.of(
            new OperationNotPermittedException(excMsg),
            Response.Status.FORBIDDEN,
            CommonParams.ERROR_NOT_AUTHORIZED),
        Arguments.of(
            new SaaSApplicationException(excMsg),
            Response.Status.INTERNAL_SERVER_ERROR,
            CommonParams.ERROR_UNEXPECTED_EXCEPTION));
  }
}
