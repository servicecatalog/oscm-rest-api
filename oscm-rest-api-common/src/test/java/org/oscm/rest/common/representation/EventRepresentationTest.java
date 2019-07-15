package org.oscm.rest.common.representation;

import org.junit.jupiter.api.Test;
import org.oscm.internal.vo.VOGatheredEvent;
import org.oscm.rest.common.TestContants;

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

        assertThat(representation).extracting(EventRepresentation::getActor).isEqualTo(TestContants.STRING_VALUE);
        assertThat(representation).extracting(EventRepresentation::getEventId).isEqualTo(TestContants.STRING_VALUE);
        assertThat(representation).extracting(EventRepresentation::getMultiplier).isEqualTo(TestContants.LONG_VALUE);
        assertThat(representation).extracting(EventRepresentation::getOccurrenceTime).isEqualTo(TestContants.LONG_VALUE);
        assertThat(representation).extracting(EventRepresentation::getUniqueId).isEqualTo(TestContants.STRING_VALUE);
        assertThat(representation).extracting(EventRepresentation::getSubscriptionKey).isEqualTo(TestContants.LONG_VALUE);
        assertThat(representation).extracting(EventRepresentation::getInstanceId).isEqualTo(TestContants.STRING_VALUE);
        assertThat(representation).extracting(EventRepresentation::getTechnicalServiceId).isEqualTo(TestContants.STRING_VALUE);
    }

    private EventRepresentation createRepresentation() {
        EventRepresentation representation = new EventRepresentation();
        representation.setActor(TestContants.STRING_VALUE);
        representation.setEventId(TestContants.STRING_VALUE);
        representation.setMultiplier(TestContants.LONG_VALUE);
        representation.setOccurrenceTime(TestContants.LONG_VALUE);
        representation.setUniqueId(TestContants.STRING_VALUE);
        representation.setSubscriptionKey(TestContants.LONG_VALUE);
        representation.setInstanceId(TestContants.STRING_VALUE);
        representation.setTechnicalServiceId(TestContants.STRING_VALUE);
        return representation;
    }
}