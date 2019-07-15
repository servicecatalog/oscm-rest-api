package org.oscm.rest.common.requestparameters;

import org.junit.jupiter.api.Test;
import org.oscm.rest.common.TestContants;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class SubscriptionParametersTest {

        @Test
        public void shouldCreate() {
             SubscriptionParameters parameters = new SubscriptionParameters();
             parameters.setLicKey(TestContants.LONG_VALUE);
             parameters.setUserId(TestContants.STRING_VALUE);

             parameters.validateParameters();
             parameters.validateETag();
             parameters.update();

             assertThat(parameters).extracting(SubscriptionParameters::getLicKey).isEqualTo(TestContants.LONG_VALUE);
             assertThat(parameters).extracting(SubscriptionParameters::getUserId).isEqualTo(TestContants.STRING_VALUE);
        }
}