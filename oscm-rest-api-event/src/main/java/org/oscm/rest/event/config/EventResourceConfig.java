/**
 * *****************************************************************************
 *
 * <p>Copyright FUJITSU LIMITED 2016
 *
 * <p>Creation Date: May 2, 2016
 *
 * <p>*****************************************************************************
 */
package org.oscm.rest.event.config;

import org.oscm.rest.common.GsonMessageProvider;
import org.oscm.rest.common.OSCMExceptionMapper;
import org.oscm.rest.common.ResourceConfiguration;
import org.oscm.rest.common.VersionFilter;
import org.oscm.rest.event.EventResource;

import java.util.HashSet;
import java.util.Set;

/**
 * Lists out resources and providers of the event component that will be registered to the application.
 *
 * @author Weiser
 */
public class EventResourceConfig extends ResourceConfiguration {

  @Override
  public Set<Class<?>> getClassesToRegister() {
    final Set<Class<?>> classes = new HashSet<Class<?>>();

    classes.add(EventResource.class);
    classes.add(GsonMessageProvider.class);
    classes.add(OSCMExceptionMapper.class);
    classes.add(VersionFilter.class);

    return classes;
  }
}
