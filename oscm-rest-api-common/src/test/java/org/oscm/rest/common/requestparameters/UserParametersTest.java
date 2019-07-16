package org.oscm.rest.common.requestparameters;

import org.junit.jupiter.api.Test;
import org.oscm.rest.common.TestContants;

import static org.assertj.core.api.Assertions.assertThat;

public class UserParametersTest {

        @Test
        public void shouldCreate() {
             UserParameters parameters = new UserParameters();
             parameters.setUserId(TestContants.STRING_VALUE);
             parameters.setMarketplaceId(TestContants.STRING_VALUE);
             parameters.setPattern(TestContants.STRING_VALUE);
             parameters.setUserIdRequired(true);

             parameters.update();
             parameters.validateParameters();
             parameters.validateId();

             assertThat(parameters).extracting(UserParameters::getUserId).isEqualTo(TestContants.STRING_VALUE);
             assertThat(parameters).extracting(UserParameters::getMarketplaceId).isEqualTo(TestContants.STRING_VALUE);
             assertThat(parameters).extracting(UserParameters::getPattern).isEqualTo(TestContants.STRING_VALUE);
             assertThat(parameters).extracting(UserParameters::getUserIdRequired).isEqualTo(true);
        }

}