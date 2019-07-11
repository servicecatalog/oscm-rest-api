package org.oscm.rest.common.representation;

import org.junit.jupiter.api.Test;
import org.oscm.rest.common.ServiceStatus;

import static org.assertj.core.api.Assertions.assertThat;

public class StatusRepresentationTest {

  @Test
  public void shouldCreate() {
    StatusRepresentation representation = new StatusRepresentation();
    representation.setStatus(ServiceStatus.ACTIVE);
    representation.setReason("REASON");

    representation.validateContent();
    representation.update();
    representation.convert();

    assertThat(representation).extracting(StatusRepresentation::getReason).isEqualTo("REASON");
    assertThat(representation)
        .extracting(StatusRepresentation::getStatus)
        .isEqualTo(ServiceStatus.ACTIVE);
  }
}
