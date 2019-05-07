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
import org.oscm.rest.common.VersionFilter;
import org.oscm.rest.event.EventResource;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * Registers resources and providers of the event component to the application.
 *
 * @author Weiser
 */
@ApplicationPath("")
public class EventResourceConfig extends Application {

  @Override
  public Set<Class<?>> getClasses() {
    final Set<Class<?>> classes = new HashSet<Class<?>>();

    classes.add(EventResource.class);

    classes.add(GsonMessageProvider.class);

    classes.add(OSCMExceptionMapper.class);
    classes.add(VersionFilter.class);

    return classes;
  }
}
