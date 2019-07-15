/**
 * *****************************************************************************
 *
 * <p>Copyright FUJITSU LIMITED 2019
 *
 * <p>Creation Date: July 1, 2019
 *
 * <p>*****************************************************************************
 */
package org.oscm.rest.event;

import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.oscm.internal.intf.EventService;
import org.oscm.rest.common.SampleTestDataUtility;
import org.oscm.rest.common.representation.EventRepresentation;
import org.oscm.rest.common.requestparameters.EventParameters;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class EventBackendTest {

  @Mock private EventService service;
  @InjectMocks private EventBackend backend;
  private EventResource resource;

  private UriInfo uriInfo;
  private EventParameters parameters;

  @BeforeEach
  public void setUp() {
    resource = new EventResource();
    resource.setEb(backend);
    uriInfo = SampleTestDataUtility.createUriInfo();
    parameters = SampleTestDataUtility.createEventParameters();
  }

  @ParameterizedTest
  @MethodSource("provideRepresentationObjectsAsArugments")
  @SneakyThrows
  public void shouldRecordEvent_givenInstanceOrSubscription(EventRepresentation representation) {
    Response response = resource.recordEvent(uriInfo, representation, parameters);

    assertThat(response).isNotNull();
    assertThat(response)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.CREATED.getStatusCode());
  }

  public static Stream<Arguments> provideRepresentationObjectsAsArugments() {
    return Stream.of(
        Arguments.of(SampleTestDataUtility.createEventRepresentation(true)),
        Arguments.of(SampleTestDataUtility.createEventRepresentation(false)));
  }
}
