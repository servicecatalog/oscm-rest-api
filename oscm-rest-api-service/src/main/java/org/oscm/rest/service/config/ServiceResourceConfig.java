/**
 * *****************************************************************************
 *
 * <p>Copyright FUJITSU LIMITED 2016
 *
 * <p>Creation Date: May 2, 2016
 *
 * <p>*****************************************************************************
 */
package org.oscm.rest.service.config;

import java.util.HashSet;
import java.util.Set;
import org.oscm.rest.common.GsonMessageProvider;
import org.oscm.rest.common.ResourceConfiguration;
import org.oscm.rest.common.VersionFilter;
import org.oscm.rest.common.errorhandling.OSCMExceptionMapper;
import org.oscm.rest.service.*;

/**
 * Lists out resources and providers of the service component that will be registered to the
 * application.
 *
 * @author Weiser
 */
public class ServiceResourceConfig implements ResourceConfiguration {

  @Override
  public Set<Class<?>> getClassesToRegister() {
    final Set<Class<?>> classes = new HashSet<Class<?>>();

    classes.add(CompatibleServiceResource.class);
    classes.add(PriceModelResource.class);
    classes.add(ServiceImageResource.class);
    classes.add(ServiceResource.class);
    classes.add(TechnicalServiceResource.class);
    classes.add(TSSupplierResource.class);
    classes.add(OSCMExceptionMapper.class);
    classes.add(GsonMessageProvider.class);
    classes.add(VersionFilter.class);

    return classes;
  }
}
