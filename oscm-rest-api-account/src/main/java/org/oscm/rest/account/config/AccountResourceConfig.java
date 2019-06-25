/**
 * *****************************************************************************
 *
 * <p>Copyright FUJITSU LIMITED 2016
 *
 * <p>Creation Date: May 2, 2016
 *
 * <p>*****************************************************************************
 */
package org.oscm.rest.account.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.oscm.rest.account.BillingContactResource;
import org.oscm.rest.account.OrganizationResource;
import org.oscm.rest.account.PaymentInfoResource;
import org.oscm.rest.common.GsonMessageProvider;
import org.oscm.rest.common.OSCMExceptionMapper;
import org.oscm.rest.common.ResourceConfiguration;
import org.oscm.rest.common.VersionFilter;

import java.util.HashSet;
import java.util.Set;

/**
 * Lists out resources and providers of the account component that will be registered to the application.
 *
 * @author Weiser
 */
@OpenAPIDefinition(info =
@Info(
        title = "OSCM Rest API",
        version = "1.0",
        description = "Rest API description",
        license = @License(name = "Apache 2.0", url = "http://example.com"),
        contact = @Contact(url = "http://localhost.com", name = "Contact Name", email = "email@adress.com")
)
)
public class AccountResourceConfig implements ResourceConfiguration {

  @Override
  public Set<Class<?>> getClassesToRegister() {
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
