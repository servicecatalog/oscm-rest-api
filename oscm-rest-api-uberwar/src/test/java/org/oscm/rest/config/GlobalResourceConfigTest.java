package org.oscm.rest.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.oscm.rest.account.config.AccountResourceConfig;
import org.oscm.rest.event.config.EventResourceConfig;
import org.oscm.rest.identity.config.IdentityResourceConfig;
import org.oscm.rest.marketplace.config.MarketplaceResourceConfig;
import org.oscm.rest.operation.config.OperationResourceConfig;
import org.oscm.rest.service.config.ServiceResourceConfig;
import org.oscm.rest.subscription.config.SubscriptionResourceConfig;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class GlobalResourceConfigTest {

        private GlobalResourceConfig resourceConfig;

        @BeforeEach
        private void setUp() {
                resourceConfig = new GlobalResourceConfig();
        }

        @Test
        public void shouldRegisterClassesFromSubmodules() {
           assertThat(resourceConfig.getClasses()).containsAll(new AccountResourceConfig().getClassesToRegister());
           assertThat(resourceConfig.getClasses()).containsAll(new EventResourceConfig().getClassesToRegister());
           assertThat(resourceConfig.getClasses()).containsAll(new IdentityResourceConfig().getClassesToRegister());
           assertThat(resourceConfig.getClasses()).containsAll(new MarketplaceResourceConfig().getClassesToRegister());
           assertThat(resourceConfig.getClasses()).containsAll(new OperationResourceConfig().getClassesToRegister());
           assertThat(resourceConfig.getClasses()).containsAll(new ServiceResourceConfig().getClassesToRegister());
           assertThat(resourceConfig.getClasses()).containsAll(new SubscriptionResourceConfig().getClassesToRegister());
        }

}