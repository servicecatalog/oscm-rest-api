/**
 * *****************************************************************************
 *
 * <p>Copyright FUJITSU LIMITED 2019
 *
 * <p>Creation Date: 18-04-2019
 *
 * <p>*****************************************************************************
 */
package org.oscm.rest.service.config;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;
import org.junit.jupiter.api.Test;
import org.oscm.rest.common.GsonMessageProvider;
import org.oscm.rest.common.OSCMExceptionMapper;
import org.oscm.rest.common.VersionFilter;
import org.oscm.rest.service.*;

class ServiceResourceConfigTest {

  @Test
  public void shouldGetClasses() {
    ServiceResourceConfig resourceConfig = new ServiceResourceConfig();

    Set<Class<?>> classes = resourceConfig.getClasses();

<<<<<<< HEAD
        assertThat(classes.size()).isEqualTo(8);
        assertThat(classes.contains(CompatibleServiceResource.class)).isEqualTo(true);
        assertThat(classes.contains(PriceModelResource.class)).isEqualTo(true);
        assertThat(classes.contains(ServiceImageResource.class)).isEqualTo(true);
        assertThat(classes.contains(TechnicalServiceResource.class)).isEqualTo(true);
        assertThat(classes.contains(TSSupplierResource.class)).isEqualTo(true);
        assertThat(classes.contains(OSCMExceptionMapper.class)).isEqualTo(true);
        assertThat(classes.contains(GsonMessageProvider.class)).isEqualTo(true);
        assertThat(classes.contains(VersionFilter.class)).isEqualTo(true);
    }
}
=======
    assertThat(classes.size()).isEqualTo(4);
    assertThat(classes.contains(CompatibleServiceResource.class)).isEqualTo(true);
    assertThat(classes.contains(PriceModelResource.class)).isEqualTo(true);
    assertThat(classes.contains(ServiceImageResource.class)).isEqualTo(true);
    assertThat(classes.contains(TechnicalServiceResource.class)).isEqualTo(true);
    assertThat(classes.contains(TSSupplierResource.class)).isEqualTo(true);
    assertThat(classes.contains(OSCMExceptionMapper.class)).isEqualTo(true);
    assertThat(classes.contains(GsonMessageProvider.class)).isEqualTo(true);
    assertThat(classes.contains(VersionFilter.class)).isEqualTo(true);
  }
}
>>>>>>> 8499d363d4cc705f5b3169bfa26dfb677594d3a7
