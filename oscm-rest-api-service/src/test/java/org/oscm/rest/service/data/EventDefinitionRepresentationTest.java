/**
 * *****************************************************************************
 *
 * <p>Copyright FUJITSU LIMITED 2019
 *
 * <p>Creation Date: 24-04-2019
 *
 * <p>*****************************************************************************
 */
package org.oscm.rest.service.data;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.oscm.internal.types.enumtypes.EventType;
import org.oscm.internal.vo.BaseVO;
import org.oscm.internal.vo.VOEventDefinition;
import org.oscm.rest.common.Representation;

public class EventDefinitionRepresentationTest {

  @Test
  public void shouldUpdateVOEventDefinition() {
    EventDefinitionRepresentation eventDefinitionRepresentation = createRepresentation();
    eventDefinitionRepresentation.setETag(100L);
    eventDefinitionRepresentation.setId(100L);

    eventDefinitionRepresentation.update();
    VOEventDefinition result = eventDefinitionRepresentation.getVO();

    assertThat(result).isNotNull();
    assertThat(result)
        .extracting(VOEventDefinition::getEventDescription)
        .isEqualTo(result.getEventDescription());
    assertThat(result).extracting(VOEventDefinition::getEventId).isEqualTo(result.getEventId());
    assertThat(result).extracting(VOEventDefinition::getEventType).isEqualTo(result.getEventType());
    assertThat(result).extracting(BaseVO::getKey).isEqualTo(result.getKey());
    assertThat(result).extracting(BaseVO::getVersion).isEqualTo(result.getVersion());
  }

  @Test
  public void shouldUpdateVOEventDefinition_evenIfIdAndETagIsNull() {
    EventDefinitionRepresentation eventDefinitionRepresentation = createRepresentation();

    eventDefinitionRepresentation.update();
    VOEventDefinition result = eventDefinitionRepresentation.getVO();

    assertThat(result).isNotNull();
    assertThat(result)
        .extracting(VOEventDefinition::getEventDescription)
        .isEqualTo(result.getEventDescription());
    assertThat(result).extracting(VOEventDefinition::getEventId).isEqualTo(result.getEventId());
    assertThat(result).extracting(VOEventDefinition::getEventType).isEqualTo(result.getEventType());
    assertThat(result).extracting(BaseVO::getKey).isEqualTo(result.getKey());
    assertThat(result).extracting(BaseVO::getVersion).isEqualTo(result.getVersion());
  }

  @Test
  public void shouldConvertToEventDefinition() {
    VOEventDefinition voEventDefinition = new VOEventDefinition();
    voEventDefinition.setEventDescription("description");
    voEventDefinition.setEventId("100");
    voEventDefinition.setEventType(EventType.PLATFORM_EVENT);
    voEventDefinition.setVersion(100);

    EventDefinitionRepresentation representation =
        new EventDefinitionRepresentation(voEventDefinition);
    representation.convert();

    assertThat(representation)
        .extracting(EventDefinitionRepresentation::getEventDescription)
        .isEqualTo(voEventDefinition.getEventDescription());
    assertThat(representation)
        .extracting(EventDefinitionRepresentation::getEventId)
        .isEqualTo(voEventDefinition.getEventId());
    assertThat(representation)
        .extracting(EventDefinitionRepresentation::getEventType)
        .isEqualTo(voEventDefinition.getEventType());
    assertThat(representation)
        .extracting(Representation::getId)
        .isEqualTo(voEventDefinition.getKey());
    assertThat(representation)
        .extracting(Representation::getETag)
        .isEqualTo((long) voEventDefinition.getVersion());
  }

  private EventDefinitionRepresentation createRepresentation() {
    EventDefinitionRepresentation eventDefinitionRepresentation =
        new EventDefinitionRepresentation();
    eventDefinitionRepresentation.setEventDescription("description");
    eventDefinitionRepresentation.setEventId("100");
    eventDefinitionRepresentation.setEventType(EventType.PLATFORM_EVENT);
    eventDefinitionRepresentation.setVersion(100);
    return eventDefinitionRepresentation;
  }
}
