/*******************************************************************************
 *                                                                              
 *  Copyright FUJITSU LIMITED 2016                                           
 *                                                                                                                                 
 *  Creation Date: May 2, 2016                                                      
 *                                                                              
 *******************************************************************************/

package org.oscm.rest.account.config;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.oscm.rest.account.BillingContactResource;
import org.oscm.rest.account.OrganizationResource;
import org.oscm.rest.account.PaymentInfoResource;
import org.oscm.rest.common.GsonMessageProvider;
import org.oscm.rest.common.OSCMExceptionMapper;
import org.oscm.rest.common.VersionFilter;

/**
 * Registers resources and providers of the account component to the
 * application.
 * 
 * @author Weiser
 */
@ApplicationPath("")
public class AccountResourceConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        final Set<Class<?>> classes = new HashSet<Class<?>>();

        classes.add(OrganizationResource.class);
        classes.add(BillingContactResource.class);
        classes.add(PaymentInfoResource.class);
        classes.add(OSCMExceptionMapper.class);
        classes.add(GsonMessageProvider.class);
        classes.add(VersionFilter.class);

        return classes;
    }

}