/**
 * *****************************************************************************
 *
 * <p>Copyright FUJITSU LIMITED 2019
 *
 * <p>Creation Date: June 19, 2019
 *
 * <p>*****************************************************************************
 */
package org.oscm.rest.config;

import org.oscm.rest.account.config.AccountResourceConfig;
import org.oscm.rest.event.config.EventResourceConfig;
import org.oscm.rest.identity.config.IdentityResourceConfig;
import org.oscm.rest.marketplace.config.MarketplaceResourceConfig;
import org.oscm.rest.operation.config.OperationResourceConfig;
import org.oscm.rest.service.config.ServiceResourceConfig;
import org.oscm.rest.subscription.config.SubscriptionResourceConfig;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("")
public class GlobalResourceConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        final Set<Class<?>> classesToRegister = new HashSet<Class<?>>();

        new AccountResourceConfig().getClassesToRegister().forEach(c -> classesToRegister.add(c));
        new EventResourceConfig().getClassesToRegister().forEach(c -> classesToRegister.add(c));
        new IdentityResourceConfig().getClassesToRegister().forEach(c -> classesToRegister.add(c));
        new MarketplaceResourceConfig().getClassesToRegister().forEach(c -> classesToRegister.add(c));
        new OperationResourceConfig().getClassesToRegister().forEach(c -> classesToRegister.add(c));
        new ServiceResourceConfig().getClassesToRegister().forEach(c -> classesToRegister.add(c));
        new SubscriptionResourceConfig().getClassesToRegister().forEach(c -> classesToRegister.add(c));

        return classesToRegister;
    }
}
