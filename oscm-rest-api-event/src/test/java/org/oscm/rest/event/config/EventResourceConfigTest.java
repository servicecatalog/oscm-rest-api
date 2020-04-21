/**
 * *****************************************************************************
 *
 * <p>Copyright FUJITSU LIMITED 2020
 *
 * <p>Creation Date: 21-04-2020
 *
 * <p>*****************************************************************************
 */
package org.oscm.rest.event.config;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;
import org.junit.jupiter.api.Test;
import org.oscm.rest.common.GsonMessageProvider;
import org.oscm.rest.common.VersionFilter;
import org.oscm.rest.common.errorhandling.OSCMExceptionMapper;
import org.oscm.rest.event.EventResource;

public class EventResourceConfigTest {

  @Test
  public void shouldGetClasses() {
    EventResourceConfig resourceConfig = new EventResourceConfig();

    Set<Class<?>> classes = resourceConfig.getClassesToRegister();

    assertThat(classes.size()).isEqualTo(4);
    assertThat(classes.contains(EventResource.class)).isEqualTo(true);
    assertThat(classes.contains(GsonMessageProvider.class)).isEqualTo(true);
    assertThat(classes.contains(OSCMExceptionMapper.class)).isEqualTo(true);
    assertThat(classes.contains(VersionFilter.class)).isEqualTo(true);
  }
}
