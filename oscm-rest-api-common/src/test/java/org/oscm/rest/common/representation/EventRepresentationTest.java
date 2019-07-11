package org.oscm.rest.common.representation;

import org.junit.jupiter.api.Test;
import org.oscm.internal.vo.VOGatheredEvent;

import static org.assertj.core.api.Assertions.assertThat;

public class EventRepresentationTest {

    @Test
    public void shouldUpdateVOGatheredEvent() {
        EventRepresentation representation = createRepresentation();

        representation.update();
        VOGatheredEvent result = representation.getVO();

        assertThat(result).isNotNull();
        assertThat(result)
                .extracting(VOGatheredEvent::getActor)
                .isEqualTo(representation.getActor());
        assertThat(result)
                .extracting(VOGatheredEvent::getEventId)
                .isEqualTo(representation.getEventId());
        assertThat(result)
                .extracting(VOGatheredEvent::getMultiplier)
                .isEqualTo(representation.getMultiplier());
        assertThat(result)
                .extracting(VOGatheredEvent::getOccurrenceTime)
                .isEqualTo(representation.getOccurrenceTime());
        assertThat(result)
                .extracting(VOGatheredEvent::getUniqueId)
                .isEqualTo(representation.getUniqueId());
    }

    @Test
    public void shouldCreate() {
        EventRepresentation representation = createRepresentation();

        representation.validateContent();
        representation.convert();

        assertThat(representation).extracting(EventRepresentation::getActor).isEqualTo("Actor100");
        assertThat(representation).extracting(EventRepresentation::getEventId).isEqualTo("Event100");
        assertThat(representation).extracting(EventRepresentation::getMultiplier).isEqualTo(100L);
        assertThat(representation).extracting(EventRepresentation::getOccurrenceTime).isEqualTo(200L);
        assertThat(representation).extracting(EventRepresentation::getUniqueId).isEqualTo("Unique100");
        assertThat(representation).extracting(EventRepresentation::getSubscriptionKey).isEqualTo(123L);
        assertThat(representation).extracting(EventRepresentation::getInstanceId).isEqualTo("instanceId");
        assertThat(representation).extracting(EventRepresentation::getTechnicalServiceId).isEqualTo("tsId");


    }

    private EventRepresentation createRepresentation() {
        EventRepresentation representation = new EventRepresentation();
        representation.setActor("Actor100");
        representation.setEventId("Event100");
        representation.setMultiplier(100L);
        representation.setOccurrenceTime(200L);
        representation.setUniqueId("Unique100");
        representation.setSubscriptionKey(123L);
        representation.setInstanceId("instanceId");
        representation.setTechnicalServiceId("tsId");
        return representation;
    }

}