package org.oscm.rest.common.requestparameters;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class ServiceParametersTest {

        @Test
        public void shouldCreate() {
             ServiceParameters parameters = new ServiceParameters();
             parameters.setOrgKey(123L);
             parameters.setOrgId("orgId");
             parameters.setEtag(123L);

             parameters.validateParameters();
             parameters.validateETag();
             parameters.update();

             assertThat(parameters).extracting(ServiceParameters::getOrgKey).isEqualTo(123L);
             assertThat(parameters).extracting(ServiceParameters::getOrgId).isEqualTo("orgId");
             assertThat(parameters).extracting(ServiceParameters::eTagToVersion).isEqualTo(123);
        }

}