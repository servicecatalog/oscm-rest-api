package org.oscm.rest.common.requestparameters;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class EventParametersTest {

        @Test
        public void shouldCreate() {
                EventParameters parameters = new EventParameters();
                parameters.setMarketplaceId("mId");
                parameters.setPattern("pattern");
                parameters.setUserId("userId");

                parameters.validateParameters();
                parameters.validateId();
                parameters.update();

                assertThat(parameters).extracting(EventParameters::getMarketplaceId).isEqualTo("mId");
                assertThat(parameters).extracting(EventParameters::getPattern).isEqualTo("pattern");
                assertThat(parameters).extracting(EventParameters::getUserId).isEqualTo("userId");
        }
}