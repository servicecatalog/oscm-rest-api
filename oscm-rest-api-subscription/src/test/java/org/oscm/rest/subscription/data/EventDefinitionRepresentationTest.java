package org.oscm.rest.subscription.data;

import org.junit.jupiter.api.Test;
import org.oscm.internal.types.enumtypes.EventType;
import org.oscm.internal.vo.BaseVO;
import org.oscm.internal.vo.VOEventDefinition;
import org.oscm.rest.common.Representation;

import static org.assertj.core.api.Assertions.assertThat;

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
                .isEqualTo(eventDefinitionRepresentation.getEventDescription());
        assertThat(result).extracting(VOEventDefinition::getEventId).isEqualTo(eventDefinitionRepresentation.getEventId());
        assertThat(result).extracting(VOEventDefinition::getEventType).isEqualTo(eventDefinitionRepresentation.getEventType());
        assertThat(result).extracting(BaseVO::getKey).isEqualTo(eventDefinitionRepresentation.convertIdToKey());
        assertThat(result).extracting(BaseVO::getVersion).isEqualTo(eventDefinitionRepresentation.convertETagToVersion());
    }

    @Test
    public void shouldUpdateVOEventDefinition_evenIfIdAndETagIsNull() {
        EventDefinitionRepresentation eventDefinitionRepresentation = createRepresentation();

        eventDefinitionRepresentation.update();
        VOEventDefinition result = eventDefinitionRepresentation.getVO();

        assertThat(result).isNotNull();
        assertThat(result)
                .extracting(VOEventDefinition::getEventDescription)
                .isEqualTo(eventDefinitionRepresentation.getEventDescription());
        assertThat(result).extracting(VOEventDefinition::getEventId).isEqualTo(eventDefinitionRepresentation.getEventId());
        assertThat(result).extracting(VOEventDefinition::getEventType).isEqualTo(eventDefinitionRepresentation.getEventType());
        assertThat(result).extracting(BaseVO::getKey).isEqualTo(eventDefinitionRepresentation.convertIdToKey());
        assertThat(result).extracting(BaseVO::getVersion).isEqualTo(eventDefinitionRepresentation.convertETagToVersion());
    }

    @Test
    public void shouldConvertToEventDefinition() {
        VOEventDefinition voEventDefinition = createVO();

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

    private VOEventDefinition createVO() {
        VOEventDefinition voEventDefinition = new VOEventDefinition();
        voEventDefinition.setEventDescription("description");
        voEventDefinition.setEventId("100");
        voEventDefinition.setEventType(EventType.PLATFORM_EVENT);
        voEventDefinition.setVersion(100);
        return voEventDefinition;
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