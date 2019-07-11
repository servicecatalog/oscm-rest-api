package org.oscm.rest.common.requestparameters;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserParametersTest {

        @Test
        public void shouldCreate() {
             UserParameters parameters = new UserParameters();
             parameters.setUserId("userId");
             parameters.setMarketplaceId("mId");
             parameters.setPattern("pattern");
             parameters.setUserIdRequired(true);

             parameters.update();
             parameters.validateParameters();
             parameters.validateId();

             assertThat(parameters).extracting(UserParameters::getUserId).isEqualTo("userId");
             assertThat(parameters).extracting(UserParameters::getMarketplaceId).isEqualTo("mId");
             assertThat(parameters).extracting(UserParameters::getPattern).isEqualTo("pattern");
             assertThat(parameters).extracting(UserParameters::getUserIdRequired).isEqualTo(true);
        }

}