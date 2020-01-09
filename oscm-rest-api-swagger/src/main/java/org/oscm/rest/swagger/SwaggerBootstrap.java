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
            title = "OSCM REST API Documentation",
            version = "1.0",
            description = "NOTICE: Remember to use URL-encoded values for path variables!"
        ),
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
