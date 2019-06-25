/**
 * *****************************************************************************
 *
 * <p>Copyright FUJITSU LIMITED 2019
 *
 * <p>Creation Date: June 19, 2019
 *
 * <p>*****************************************************************************
 */
package org.oscm.rest.common;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

import java.util.Set;

/**
 * Provides interface for module enpoint/resource reigstration
 */
@OpenAPIDefinition(info =
@Info(
        title = "OSCM Rest API",
        version = "667.0",
        description = "Rest API description",
        license = @License(name = "Apache 2.0", url = "http://example.com"),
        contact = @Contact(url = "http://localhost.com", name = "Contact TEST ON INTERFACE", email = "email@adress.com")
)
)
public abstract class ResourceConfiguration {

    public abstract Set<Class<?>> getClassesToRegister();
}
