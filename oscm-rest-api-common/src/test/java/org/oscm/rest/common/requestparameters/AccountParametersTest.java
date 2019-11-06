package org.oscm.rest.common.requestparameters;

import org.junit.jupiter.api.Test;
import org.oscm.rest.common.TestContants;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class AccountParametersTest {

        @Test
        public void shouldCreate() {
             IdentifiableAccountParameters parameters = new IdentifiableAccountParameters();
             parameters.setOrgId(TestContants.STRING_VALUE);
             parameters.setMarketplaceId(TestContants.STRING_VALUE);

             parameters.validateId();
             parameters.validateParameters();
             parameters.update();

             assertThat(parameters).extracting(IdentifiableAccountParameters::getOrgId).isEqualTo(TestContants.STRING_VALUE);
             assertThat(parameters).extracting(IdentifiableAccountParameters::getMarketplaceId).isEqualTo(TestContants.STRING_VALUE);
        }

}