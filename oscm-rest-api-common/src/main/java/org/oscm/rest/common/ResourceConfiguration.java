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
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.servers.ServerVariable;

import java.util.Set;

/**
 * Provides interface for module endpoint/resource registration
 */
@OpenAPIDefinition(
        info = @Info(
                title = "OSCM Rest API Sample Title",
                version = "1.0",
                description = "Rest API Sample Description",
                license = @License(
                        name = "Apache 2.0",
                        url = "https://www.apache.org/licenses/LICENSE-2.0"),
                contact = @Contact(
                        url = "https://example.com",
                        name = "Sample Contact Details",
                        email = "example@email.com")
        ),
        servers = {
                @Server(
                        description = "Enter your own OSCM REST API instance",
                        url = "{server}",
                        variables = {
                                @ServerVariable(
                                        name = "server",
                                        description = "IP address and port",
                                        defaultValue = "protocol://ip:port"
                                )
                        }
                )
        }
)

public abstract class ResourceConfiguration {

    public abstract Set<Class<?>> getClassesToRegister();
}
