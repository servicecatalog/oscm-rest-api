package org.oscm.rest.common.representation;

import org.junit.jupiter.api.Test;
import org.oscm.rest.common.ServiceStatus;
import org.oscm.rest.common.TestContants;

import static org.assertj.core.api.Assertions.assertThat;

public class StatusRepresentationTest {

  @Test
  public void shouldCreate() {
    StatusRepresentation representation = new StatusRepresentation();
    representation.setStatus(ServiceStatus.ACTIVE);
    representation.setReason(TestContants.STRING_VALUE);

    representation.validateContent();
    representation.update();
    representation.convert();

    assertThat(representation).extracting(StatusRepresentation::getReason).isEqualTo(TestContants.STRING_VALUE);
    assertThat(representation)
        .extracting(StatusRepresentation::getStatus)
        .isEqualTo(ServiceStatus.ACTIVE);
  }
}
