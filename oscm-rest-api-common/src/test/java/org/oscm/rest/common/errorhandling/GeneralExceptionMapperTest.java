/**
 * *****************************************************************************
 *
 * <p>Copyright FUJITSU LIMITED 2020
 *
 * <p>Creation Date: April 20, 2020
 *
 * <p>*****************************************************************************
 */
package org.oscm.rest.common.errorhandling;

import org.junit.jupiter.api.Test;
import org.oscm.rest.common.CommonParams;

import javax.ws.rs.core.Response;

import static org.assertj.core.api.Assertions.assertThat;

class GeneralExceptionMapperTest {

  @Test
  void testToResponse_returnsInternalError_whenAnyNotHandledException() {

    // given
    GeneralExceptionMapper mapper = new GeneralExceptionMapper();
    String excMsg = "ExceptionMessage";
    Exception exc = new Exception(excMsg);

    // when
    Response response = mapper.toResponse(exc);

    // then
    assertThat(response.getStatusInfo()).isEqualTo(Response.Status.INTERNAL_SERVER_ERROR);
    assertThat(response.getEntity()).isInstanceOf(ErrorResponse.class);
    assertThat(((ErrorResponse) response.getEntity()).getErrorMessage())
        .isEqualTo(CommonParams.ERROR_UNEXPECTED_EXCEPTION);
    assertThat(((ErrorResponse) response.getEntity()).getErrorDetails()).isEqualTo(excMsg);
  }
}
