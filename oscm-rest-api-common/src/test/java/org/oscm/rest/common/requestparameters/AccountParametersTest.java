package org.oscm.rest.common.requestparameters;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class AccountParametersTest {

        @Test
        public void shouldCreate() {
             AccountParameters parameters = new AccountParameters();
             parameters.setOrgId("orgId");
             parameters.setMarketplaceId("mId");

             parameters.validateId();
             parameters.validateParameters();
             parameters.update();

             assertThat(parameters).extracting(AccountParameters::getOrgId).isEqualTo("orgId");
             assertThat(parameters).extracting(AccountParameters::getMarketplaceId).isEqualTo("mId");
        }

}