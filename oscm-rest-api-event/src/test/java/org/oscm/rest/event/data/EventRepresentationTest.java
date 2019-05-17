package org.oscm.rest.event.data;

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

    private EventRepresentation createRepresentation() {
        EventRepresentation representation = new EventRepresentation();
        representation.setActor("Actor100");
        representation.setEventId("Event100");
        representation.setMultiplier(100L);
        representation.setOccurrenceTime(200L);
        representation.setUniqueId("Unique100");
        return representation;
    }

}