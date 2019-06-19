package org.oscm.rest.subscription.config;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.oscm.rest.common.GsonMessageProvider;
import org.oscm.rest.common.OSCMExceptionMapper;
import org.oscm.rest.common.VersionFilter;
import org.oscm.rest.subscription.SubscriptionResource;
import org.oscm.rest.subscription.UsageLicenseResource;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class SubscriptionResourceConfigTest {

    @Test
    @Disabled("Not implemented")
    public void shouldGetClasses() {
        SubscriptionResourceConfig resourceConfig = new SubscriptionResourceConfig();

//        Set<Class<?>> classes = resourceConfig.getClasses();
//
//        assertThat(classes.size()).isEqualTo(5);
//        assertThat(classes.contains(SubscriptionResource.class)).isEqualTo(true);
//        assertThat(classes.contains(OSCMExceptionMapper.class)).isEqualTo(true);
//        assertThat(classes.contains(GsonMessageProvider.class)).isEqualTo(true);
//        assertThat(classes.contains(VersionFilter.class)).isEqualTo(true);
//        assertThat(classes.contains(UsageLicenseResource.class)).isEqualTo(true);
    }

}