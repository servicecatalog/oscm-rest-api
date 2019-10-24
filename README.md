[![Build Status](https://travis-ci.org/servicecatalog/oscm-rest-api.svg?branch=master)](https://travis-ci.org/servicecatalog/oscm-rest-api)
[![codecov](https://codecov.io/gh/servicecatalog/oscm-rest-api/branch/master/graph/badge.svg)](https://codecov.io/gh/servicecatalog/oscm-rest-api)

<p align="center"><h1><img height="52" src="https://avatars0.githubusercontent.com/u/14330878" alt="Open Service Catalog Manager"/>&nbsp;Open Service Catalog Manager</h1></p>

# oscm-rest-api
REST API for the Open Service Catalog Manager.
It is integrated with Swagger (an implementation of OpenApi standard). You can explore and use the API resources using Swagger-UI which is accessible via /oscm-rest-api context path on the oscm-core https port (default 8081).
Currently all the resources as well as Swagger UI are protected. To visit the Swagger UI, when the browser will ask for authorization, please enter the default credentials for administrator.

# Prerequisites
A Linux system with:

* OSCM installed and running
* Apache TomEE version >= 7.1.1 in the oscm-core container (Update commited to oscm-dockerbuild repository at the master branch on 18.10.2019).

# Building from source
To build the REST API:

* 1. Download the source code
* 2. Run `mvn clean install`
* 3. Copy the oscm-rest-api-uberwar/target/oscm-rest-api.war file to the oscm-core container and put it into the /opt/apache-tomee/webapps/ directory. There is no need to restart the container afterwards. You can view the .war deployment logs in the newest /logs/juli*log file in the oscm-core container.
* 4. The Swagger UI should be available on the /oscm-rest-api context path.
