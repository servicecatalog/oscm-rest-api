[![Build Status](https://travis-ci.org/servicecatalog/oscm-rest-api.svg?branch=master)](https://travis-ci.org/servicecatalog/oscm-rest-api)
[![codecov](https://codecov.io/gh/servicecatalog/oscm-rest-api/branch/master/graph/badge.svg)](https://codecov.io/gh/servicecatalog/oscm-rest-api)

# oscm-rest-api
This is a RESTful API for the Open Service Catalog Manager.

This API is integrated with Swagger - an implementation of [OpenAPI specification](https://www.openapis.org/). You can explore and use the OSCM API resources via the Swagger UI, which is deployed in the [oscm-core](https://hub.docker.com/r/servicecatalog/oscm-core/) container. A description for installing OSCM on docker can be found [here](https://github.com/servicecatalog/oscm-dockerbuild#setup).

You can access the Swagger UI with ```<OSCM BASE URL>/oscm-rest-api```, by default on the oscm-core HTTPS port 8081. All resources as well as Swagger UI are protected. When visiting the Swagger UI, your browser will ask for authorization. Enter the user key ```1000``` and password for the OSCM platform administrator.

![IMAGE Swagger UI](/swaggerui.png)

# Prerequisites
A Linux operating system, with
* Mininum specification as described [here](https://github.com/servicecatalog/oscm-dockerbuild#prerequisites)
* Latest OSCM version installed and running (find the [Quickstart instructions](https://github.com/servicecatalog/oscm-dockerbuild#setup))
* Apache TomEE version >= 7.1.1, which is already included since OSCM v18.1

# Building from source
To build the REST API:

1. Download the source code
2. Run `mvn clean install`
3. Copy the resulting war file into the oscm-core container.
     From docker host execute: `docker cp oscm-rest-api-uberwar/target/oscm-rest-api.war oscm-core:/opt/apache-tomee/webapps/`
     There is no need to restart the container afterwards. You can view the .war deployment logs in the newest `/logs/juli.log` file in the oscm-core container.
4. Open the Swagger UI in your client browser on https://FQDN:8081/oscm-rest-api (where FQDN refers to your OSCM fully qualified domain name as configured in your `/docker/var.env` file).

