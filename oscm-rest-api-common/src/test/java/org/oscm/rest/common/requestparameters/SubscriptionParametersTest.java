package org.oscm.rest.common.requestparameters;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class SubscriptionParametersTest {

        @Test
        public void shouldCreate() {
             SubscriptionParameters parameters = new SubscriptionParameters();
             parameters.setLicKey(123L);
             parameters.setUserId("userId");

             parameters.validateParameters();
             parameters.validateETag();
             parameters.update();

             assertThat(parameters).extracting(SubscriptionParameters::getLicKey).isEqualTo(123L);
             assertThat(parameters).extracting(SubscriptionParameters::getUserId).isEqualTo("userId");
        }
}