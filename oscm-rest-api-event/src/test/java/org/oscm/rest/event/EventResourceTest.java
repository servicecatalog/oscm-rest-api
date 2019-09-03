/**
 * *****************************************************************************
 *
 * <p>Copyright FUJITSU LIMITED 2019
 *
 * <p>Creation Date: 03-09-2019
 *
 * <p>*****************************************************************************
 */
package org.oscm.rest.event;

import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.oscm.internal.types.exception.*;
import org.oscm.rest.common.SampleTestDataUtility;
import org.oscm.rest.common.representation.EventRepresentation;
import org.oscm.rest.common.requestparameters.EventParameters;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.lang.IllegalArgumentException;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EventResourceTest {

  @Mock private EventBackend eventBackend;

  @InjectMocks @Spy private EventResource eventResource;

  private Response response;
  private EventRepresentation eventRepresentation;
  private UriInfo uriInfo;
  private EventParameters eventParameters;

  @BeforeEach
  public void setUp() {
    eventRepresentation = SampleTestDataUtility.createEventRepresentation(true);
    uriInfo = SampleTestDataUtility.createUriInfo();
    eventParameters = SampleTestDataUtility.createEventParameters();
  }

  @AfterEach
  public void cleanUp() {
    response = null;
  }

  @Test
  @SneakyThrows
  public void shouldRecordEvent() {
    when(eventBackend.post()).thenReturn((content, params) -> true);

    try {
      response = eventResource.recordEvent(uriInfo, eventRepresentation, eventParameters);
    } catch (Exception e) {
      fail(e);
    }

    assertThat(response).isNotNull();
    assertThat(response)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.CREATED.getStatusCode());
    assertThat(response).extracting(Response::hasEntity).isEqualTo(true);
  }

  @ParameterizedTest
  @MethodSource("provideThrowablesAsArguments")
  @SneakyThrows
  public void shouldReturnResponseWithError_whenExceptionIsThrown(
      Exception exception, Response.Status status) {
    doThrow(exception).when(eventBackend).post();

    Response response = eventResource.recordEvent(uriInfo, eventRepresentation, eventParameters);

    assertThat(response.getStatus()).isEqualTo(status.getStatusCode());
  }

  public static Stream<Arguments> provideThrowablesAsArguments() {
    return Stream.of(
        Arguments.of(new DuplicateEventException(), Response.Status.CONFLICT),
        Arguments.of(new OrganizationAuthoritiesException(), Response.Status.FORBIDDEN),
        Arguments.of(new ObjectNotFoundException(), Response.Status.NOT_FOUND),
        Arguments.of(new ValidationException(), Response.Status.BAD_REQUEST),
        Arguments.of(new IllegalArgumentException(), Response.Status.BAD_REQUEST),
        Arguments.of(new SaaSSystemException(), Response.Status.INTERNAL_SERVER_ERROR),
        Arguments.of(new WebApplicationException(), Response.Status.INTERNAL_SERVER_ERROR));
  }
}
