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

import static org.assertj.core.api.Assertions.assertThat;

import javax.ws.rs.core.Response;
import org.apache.openejb.ApplicationException;
import org.apache.openejb.InvalidateReferenceException;
import org.junit.jupiter.api.Test;
import org.oscm.rest.common.CommonParams;

class ContainerExceptionMapperTest {

  @Test
  void testToResponse_returnsInternalError_whenGeneralApplicationException() {

    // given
    ContainerExceptionMapper mapper = new ContainerExceptionMapper();
    String excMsg = "ExceptionMessage";
    ApplicationException exc = new ApplicationException(excMsg);

    // when
    Response response = mapper.toResponse(exc);

    // then
    assertThat(response.getStatusInfo()).isEqualTo(Response.Status.INTERNAL_SERVER_ERROR);
    assertThat(response.getEntity()).isInstanceOf(ErrorResponse.class);
    assertThat(((ErrorResponse) response.getEntity()).getErrorMessage())
        .isEqualTo(CommonParams.ERROR_UNEXPECTED_EXCEPTION);
    assertThat(((ErrorResponse) response.getEntity()).getErrorDetails()).isEqualTo(excMsg);
  }

  @Test
  void testToResponse_returnsForbidden_whenInvalidateReferenceException() {

    // given
    ContainerExceptionMapper mapper = new ContainerExceptionMapper();
    String excMsg = "ExceptionMessage";
    InvalidateReferenceException exc = new InvalidateReferenceException(excMsg);

    // when
    Response response = mapper.toResponse(exc);

    // then
    assertThat(response.getStatusInfo()).isEqualTo(Response.Status.FORBIDDEN);
    assertThat(response.getEntity()).isInstanceOf(ErrorResponse.class);
    assertThat(((ErrorResponse) response.getEntity()).getErrorMessage())
        .isEqualTo(CommonParams.ERROR_NOT_AUTHORIZED);
    assertThat(((ErrorResponse) response.getEntity()).getErrorDetails()).isEqualTo(excMsg);
  }
}
