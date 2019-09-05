/**
 * *****************************************************************************
 *
 * <p>Copyright FUJITSU LIMITED 2019
 *
 * <p>Creation Date: 04-09-2019
 *
 * <p>*****************************************************************************
 */
package org.oscm.rest.common.errorhandling;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.oscm.internal.types.exception.*;

public class RestErrorResponseFactoryTest {

  @ParameterizedTest
  @MethodSource("provideThrowablesAsArguments")
  public void shouldReturnErrorResponse_givenException(Exception exception) {
    Response response = RestErrorResponseFactory.getResponse(exception);

    assertThat(response.getEntity()).isEqualTo(exception);
  }

  public static Stream<Arguments> provideThrowablesAsArguments() {
    return Stream.of(
        Arguments.of(new DuplicateEventException()),
        Arguments.of(new OrganizationAuthoritiesException()),
        Arguments.of(new ObjectNotFoundException()),
        Arguments.of(new ValidationException()),
        Arguments.of(new IllegalArgumentException()),
        Arguments.of(new SaaSSystemException()),
        Arguments.of(new WebApplicationException()));
  }
}
