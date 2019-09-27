package org.oscm.rest.common.requestparameters;

import org.junit.jupiter.api.Test;
import org.oscm.rest.common.TestContants;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class ServiceParametersTest {

        @Test
        public void shouldCreate() {
             ServiceParameters parameters = new ServiceParameters();
             parameters.setOrgKey(TestContants.LONG_VALUE);
             parameters.setOrgId(TestContants.STRING_VALUE);
             parameters.setEtag(TestContants.LONG_VALUE);

             parameters.validateParameters();
             parameters.validateETag();
             parameters.update();

             assertThat(parameters).extracting(ServiceParameters::getOrgKey).isEqualTo(TestContants.LONG_VALUE);
             assertThat(parameters).extracting(ServiceParameters::getOrgId).isEqualTo(TestContants.STRING_VALUE);
             assertThat(parameters).extracting(ServiceParameters::eTagToVersion).isEqualTo(TestContants.INTEGER_VALUE);
        }

}