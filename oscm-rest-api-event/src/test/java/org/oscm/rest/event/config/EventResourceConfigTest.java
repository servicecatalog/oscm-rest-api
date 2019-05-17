package org.oscm.rest.event.config;

import org.junit.jupiter.api.Test;
import org.oscm.rest.common.GsonMessageProvider;
import org.oscm.rest.common.OSCMExceptionMapper;
import org.oscm.rest.common.VersionFilter;
import org.oscm.rest.event.EventResource;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class EventResourceConfigTest {

    @Test
    public void shouldGetClasses() {
        EventResourceConfig resourceConfig = new EventResourceConfig();

        Set<Class<?>> classes = resourceConfig.getClasses();

        assertThat(classes.size()).isEqualTo(4);
        assertThat(classes.contains(EventResource.class)).isEqualTo(true);
        assertThat(classes.contains(GsonMessageProvider.class)).isEqualTo(true);
        assertThat(classes.contains(OSCMExceptionMapper.class)).isEqualTo(true);
        assertThat(classes.contains(VersionFilter.class)).isEqualTo(true);
    }
}