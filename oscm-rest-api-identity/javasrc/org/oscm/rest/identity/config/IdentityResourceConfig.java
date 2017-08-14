/*******************************************************************************
 *                                                                              
 *  Copyright FUJITSU LIMITED 2016                                           
 *                                                                                                                                 
 *  Creation Date: May 2, 2016                                                      
 *                                                                              
 *******************************************************************************/

package org.oscm.rest.identity.config;

import java.util.Map;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import org.oscm.rest.common.GsonMessageProvider;
import org.oscm.rest.common.OSCMExceptionMapper;
import org.oscm.rest.common.SecurityFilter;
import org.oscm.rest.common.VersionFilter;
import org.oscm.rest.identity.LdapUserResource;
import org.oscm.rest.identity.OnBehalfUserResource;
import org.oscm.rest.identity.RolesResource;
import org.oscm.rest.identity.UserResource;

/**
 * Registers resources and providers of the user component to the application.
 * 
 * @author Weiser
 */
@ApplicationPath("")
public class IdentityResourceConfig extends ResourceConfig {

    private Map<String, Object> properties;

    public IdentityResourceConfig() {
        register(UserResource.class);
        register(RolesResource.class);
        register(OnBehalfUserResource.class);
        register(LdapUserResource.class);
        register(OSCMExceptionMapper.class);

        register(GsonMessageProvider.class);
        register(VersionFilter.class);
        register(SecurityFilter.class);
        register(RolesAllowedDynamicFeature.class);

    }

}
