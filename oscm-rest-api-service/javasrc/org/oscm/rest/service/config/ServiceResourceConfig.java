/*******************************************************************************
 *                                                                              
 *  Copyright FUJITSU LIMITED 2016                                           
 *                                                                                                                                 
 *  Creation Date: May 2, 2016                                                      
 *                                                                              
 *******************************************************************************/

package org.oscm.rest.service.config;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import org.oscm.rest.common.GsonMessageProvider;
import org.oscm.rest.common.OSCMExceptionMapper;
import org.oscm.rest.common.VersionFilter;
import org.oscm.rest.service.CompatibleServiceResource;
import org.oscm.rest.service.PriceModelResource;
import org.oscm.rest.service.ServiceImageResource;
import org.oscm.rest.service.TSSupplierResource;
import org.oscm.rest.service.TechnicalServiceResource;

/**
 * Registers resources and providers of the operation component to the
 * application.
 * 
 * @author Weiser
 */
@ApplicationPath("")
public class ServiceResourceConfig extends ResourceConfig {

    public ServiceResourceConfig() {
        register(CompatibleServiceResource.class);
        register(PriceModelResource.class);
        register(ServiceImageResource.class);
        register(TechnicalServiceResource.class);
        register(TSSupplierResource.class);
        register(OSCMExceptionMapper.class);

        register(GsonMessageProvider.class);
        register(VersionFilter.class);

        register(RolesAllowedDynamicFeature.class);
    }

}
