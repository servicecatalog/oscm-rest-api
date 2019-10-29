[![Build Status](https://travis-ci.org/servicecatalog/oscm-rest-api.svg?branch=master)](https://travis-ci.org/servicecatalog/oscm-rest-api)
[![codecov](https://codecov.io/gh/servicecatalog/oscm-rest-api/branch/master/graph/badge.svg)](https://codecov.io/gh/servicecatalog/oscm-rest-api)

# oscm-rest-api
This is a RESTful API for the Open Service Catalog Manager.
It is integrated with Swagger (an implementation of OpenApi standard). You can explore and use the API resources using Swagger-UI which is accessible via /oscm-rest-api context path on the oscm-core https port (default 8081).
All resources as well as Swagger UI are protected. When visiting the Swagger UI, your browser will ask for authorization. Enter the credentials for the platform administrator.

![Swagger UI endpoint categories view](https://raw.githubusercontent.com/servicecatalog/oscm-rest-api/master/oscm-rest-api-swagger/src/main/resources/swagger-ui-demo.png)

# Prerequisites
A Linux system with:

* Latest OSCM version installed and running (find the Quickstart instruction)
* Apache TomEE version >= 7.1.1 in the oscm-core container

# Building from source
To build the REST API:

1. Download the source code
2. Run `mvn clean install`
3. Copy the resulting war file into the oscm-core container.
     From docker host execute: `docker cp oscm-rest-api-uberwar/target/oscm-rest-api.war oscm-core:/opt/apache-tomee/webapps/`
     There is no need to restart the container afterwards. You can view the .war deployment logs in the newest `/logs/juli.log` file in the oscm-core container.
4. Open the Swagger UI in your client browser on https://FQDN:8081/oscm-rest-api (where FQDN refers to your OSCM fully qualified domain name as configured in your `/docker/var.env` file).
