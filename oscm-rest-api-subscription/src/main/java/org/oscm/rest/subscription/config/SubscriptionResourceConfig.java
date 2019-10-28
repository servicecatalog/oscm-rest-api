/**
 * *****************************************************************************
 *
 * <p>Copyright FUJITSU LIMITED 2016
 *
 * <p>Creation Date: May 2, 2016
 *
 * <p>*****************************************************************************
 */
package org.oscm.rest.subscription.config;

import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import org.oscm.rest.common.GsonMessageProvider;
import org.oscm.rest.common.OSCMExceptionMapper;
import org.oscm.rest.common.ResourceConfiguration;
import org.oscm.rest.common.VersionFilter;
import org.oscm.rest.subscription.SubscriptionResource;
import org.oscm.rest.subscription.UsageLicenseResource;

/**
 * Lists out resources and providers of the event component that will be registered to the
 * application.
 *
 * @author Weiser
 */
@ApplicationPath("")
public class SubscriptionResourceConfig implements ResourceConfiguration {

  @Override
  public Set<Class<?>> getClassesToRegister() {
    final Set<Class<?>> classes = new HashSet<Class<?>>();

    classes.add(SubscriptionResource.class);
    classes.add(OSCMExceptionMapper.class);
    classes.add(GsonMessageProvider.class);
    classes.add(VersionFilter.class);
    classes.add(UsageLicenseResource.class);

    return classes;
  }
}
