/**
 * *****************************************************************************
 *
 * <p>Copyright FUJITSU LIMITED 2016
 *
 * <p>Creation Date: May 2, 2016
 *
 * <p>*****************************************************************************
 */
package org.oscm.rest.identity.config;

import org.oscm.rest.common.GsonMessageProvider;
import org.oscm.rest.common.OSCMExceptionMapper;
import org.oscm.rest.common.ResourceConfiguration;
import org.oscm.rest.common.VersionFilter;
import org.oscm.rest.identity.LdapUserResource;
import org.oscm.rest.identity.OnBehalfUserResource;
import org.oscm.rest.identity.RolesResource;
import org.oscm.rest.identity.UserResource;

import java.util.HashSet;
import java.util.Set;

/**
 * Lists out resources and providers of the user component that will be registered to the
 * application.
 *
 * @author Weiser
 */
public class IdentityResourceConfig implements ResourceConfiguration {

  @Override
  public Set<Class<?>> getClassesToRegister() {
    final Set<Class<?>> classes = new HashSet<Class<?>>();

    classes.add(UserResource.class);
    classes.add(RolesResource.class);
    classes.add(OnBehalfUserResource.class);
    classes.add(LdapUserResource.class);
    classes.add(OSCMExceptionMapper.class);
    classes.add(GsonMessageProvider.class);
    classes.add(VersionFilter.class);

    return classes;
  }
}
