/**
 * *****************************************************************************
 *
 * <p>Copyright FUJITSU LIMITED 2020
 *
 * <p>Creation Date: 21-04-2020
 *
 * <p>*****************************************************************************
 */
package org.oscm.rest.subscription.config;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;
import org.junit.jupiter.api.Test;
import org.oscm.rest.common.GsonMessageProvider;
import org.oscm.rest.common.VersionFilter;
import org.oscm.rest.common.errorhandling.OSCMExceptionMapper;
import org.oscm.rest.subscription.SubscriptionResource;
import org.oscm.rest.subscription.UsageLicenseResource;

public class SubscriptionResourceConfigTest {

  @Test
  public void shouldGetClasses() {
    SubscriptionResourceConfig resourceConfig = new SubscriptionResourceConfig();

    Set<Class<?>> classes = resourceConfig.getClassesToRegister();

    assertThat(classes.size()).isEqualTo(5);
    assertThat(classes.contains(SubscriptionResource.class)).isEqualTo(true);
    assertThat(classes.contains(OSCMExceptionMapper.class)).isEqualTo(true);
    assertThat(classes.contains(GsonMessageProvider.class)).isEqualTo(true);
    assertThat(classes.contains(VersionFilter.class)).isEqualTo(true);
    assertThat(classes.contains(UsageLicenseResource.class)).isEqualTo(true);
  }
}
