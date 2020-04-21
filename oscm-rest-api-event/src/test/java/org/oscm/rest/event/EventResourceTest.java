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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.oscm.internal.types.exception.*;
import org.oscm.rest.common.SampleTestDataUtility;
import org.oscm.rest.common.representation.EventRepresentation;
import org.oscm.rest.common.requestparameters.EventParameters;

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
}
