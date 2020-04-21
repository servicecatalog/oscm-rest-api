/**
 * *****************************************************************************
 *
 * <p>Copyright FUJITSU LIMITED 2016
 *
 * <p>Creation Date: May 2, 2016
 *
 * <p>*****************************************************************************
 */
package org.oscm.rest.operation.config;

import java.util.HashSet;
import java.util.Set;
import org.oscm.rest.common.GsonMessageProvider;
import org.oscm.rest.common.ResourceConfiguration;
import org.oscm.rest.common.VersionFilter;
import org.oscm.rest.common.errorhandling.OSCMExceptionMapper;
import org.oscm.rest.operation.SettingsResource;

/**
 * Lists out resources and providers of the operation component that will be registered to the
 * application.
 *
 * @author Weiser
 */
public class OperationResourceConfig implements ResourceConfiguration {

  @Override
  public Set<Class<?>> getClassesToRegister() {
    final Set<Class<?>> classes = new HashSet<Class<?>>();

    classes.add(SettingsResource.class);
    classes.add(OSCMExceptionMapper.class);
    classes.add(GsonMessageProvider.class);
    classes.add(VersionFilter.class);

    return classes;
  }
}
