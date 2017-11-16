/*******************************************************************************
 *                                                                              
 *  Copyright FUJITSU LIMITED 2016                                           
 *                                                                                                                                 
 *  Creation Date: May 2, 2016                                                      
 *                                                                              
 *******************************************************************************/

package org.oscm.rest.account.config;

import java.util.Map;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
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
public class AccountResourceConfig extends ResourceConfig {

    private Map<String, Object> properties;

    public AccountResourceConfig() {
        register(OrganizationResource.class);
        register(BillingContactResource.class);
        register(PaymentInfoResource.class);
        register(OSCMExceptionMapper.class);

        register(GsonMessageProvider.class);
        register(VersionFilter.class);

        register(RolesAllowedDynamicFeature.class);
    }

}
