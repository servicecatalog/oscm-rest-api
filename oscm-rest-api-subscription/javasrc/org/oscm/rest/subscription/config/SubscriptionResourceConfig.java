/*******************************************************************************
 *                                                                              
 *  Copyright FUJITSU LIMITED 2016                                           
 *                                                                                                                                 
 *  Creation Date: May 2, 2016                                                      
 *                                                                              
 *******************************************************************************/

package org.oscm.rest.subscription.config;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import org.oscm.rest.common.GsonMessageProvider;
import org.oscm.rest.common.OSCMExceptionMapper;
import org.oscm.rest.common.SecurityFilter;
import org.oscm.rest.common.VersionFilter;
import org.oscm.rest.subscription.SubscriptionResource;
import org.oscm.rest.subscription.UsageLicenseResource;

/**
 * Registers resources and providers of the operation component to the
 * application.
 * 
 * @author Weiser
 */
@ApplicationPath("")
public class SubscriptionResourceConfig extends ResourceConfig {

    public SubscriptionResourceConfig() {
        register(SubscriptionResource.class);
        register(UsageLicenseResource.class);
        register(OSCMExceptionMapper.class);

        register(GsonMessageProvider.class);
        register(VersionFilter.class);
        register(SecurityFilter.class);
        register(RolesAllowedDynamicFeature.class);
    }

}
