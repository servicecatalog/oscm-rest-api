package org.oscm.rest.common.requestparameters;

import org.junit.jupiter.api.Test;
import org.oscm.rest.common.TestContants;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class EventParametersTest {

        @Test
        public void shouldCreate() {
                EventParameters parameters = new EventParameters();
                parameters.setMarketplaceId(TestContants.STRING_VALUE);
                parameters.setPattern(TestContants.STRING_VALUE);
                parameters.setUserId(TestContants.STRING_VALUE);

                parameters.validateParameters();
                parameters.update();

                assertThat(parameters).extracting(EventParameters::getMarketplaceId).isEqualTo(TestContants.STRING_VALUE);
                assertThat(parameters).extracting(EventParameters::getPattern).isEqualTo(TestContants.STRING_VALUE);
                assertThat(parameters).extracting(EventParameters::getUserId).isEqualTo(TestContants.STRING_VALUE);
        }
}