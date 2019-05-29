package org.oscm.rest.marketplace.config;

import org.junit.jupiter.api.Test;
import org.oscm.rest.common.GsonMessageProvider;
import org.oscm.rest.common.OSCMExceptionMapper;
import org.oscm.rest.common.VersionFilter;
import org.oscm.rest.marketplace.EntryResource;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class MarketplaceResourceConfigTest {

    @Test
    public void shouldGetClasses() {
        MarketplaceResourceConfig resourceConfig = new MarketplaceResourceConfig();

        Set<Class<?>> classes = resourceConfig.getClasses();

        assertThat(classes.size()).isEqualTo(5);
        assertThat(classes.contains(EntryResource.class)).isEqualTo(true);
        assertThat(classes.contains(OSCMExceptionMapper.class)).isEqualTo(true);
        assertThat(classes.contains(GsonMessageProvider.class)).isEqualTo(true);
        assertThat(classes.contains(VersionFilter.class)).isEqualTo(true);
    }

}