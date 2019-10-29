/**
 * *****************************************************************************
 *
 * <p>Copyright FUJITSU LIMITED 2019
 *
 * <p>Creation Date: Oct 3, 2019
 *
 * <p>*****************************************************************************
 */
package org.oscm.rest.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.servers.ServerVariable;

@OpenAPIDefinition(
    info =
        @Info(
            title = "OSCM REST API",
            version = "1.0",
            description = "This is the REST API for OSCM",
            license =
                @License(name = "Apache 2.0", url = "https://www.apache.org/licenses/LICENSE-2.0"),
            contact =
                @Contact(
                    url = "https://example.com",
                    name = "Sample Contact Details",
                    email = "example@email.com")),
    servers = {
      @Server(
          description = "OSCM instance",
          url = "{server}",
          variables = {
            @ServerVariable(
                name = "server",
                description = "Base REST API URL",
                defaultValue = "https://oscm-ip:8081/oscm-rest-api")
          })
    })
public class SwaggerBootstrap {}
