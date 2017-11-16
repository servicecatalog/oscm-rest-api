/*******************************************************************************
 *                                                                              
 *  Copyright FUJITSU LIMITED 2016                                           
 *                                                                                                                                 
 *  Creation Date: May 2, 2016                                                      
 *                                                                              
 *******************************************************************************/

package org.oscm.rest.subscription.config;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.oscm.rest.common.GsonMessageProvider;
import org.oscm.rest.common.OSCMExceptionMapper;
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
public class SubscriptionResourceConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        final Set<Class<?>> classes = new HashSet<Class<?>>();

        classes.add(SubscriptionResource.class);
        classes.add(OSCMExceptionMapper.class);
        classes.add(GsonMessageProvider.class);
        classes.add(VersionFilter.class);
        classes.add(UsageLicenseResource.class);

        return classes;
    }

}
