# RESTful API Sample for OSCM

This is a short sample for getting started with the OSCM RESTful API.

## Requirements
-	OSCM v18.1 running in internal mode with sample data (installation with “-e SAMPLE_DATA=TRUE”)
-	Postman

## Create a Service via API
-	Use in Postman authorization “BasicAuth”
-	Login with user key and password (default: 10000 and supplier)
-	Create your service in JSON. [Here](https://github.com/servicecatalog/oscm-rest-api/blob/add/gettingStarted/docs/json/Service%20example) is a service example.
-	Use POST method of endpoint https://host:8081/oscm-rest-api/v1/services to post the message body.

**Get Data to Create a Service**

Call the endpoint https://host:8081/oscm-rest-api/v1/technicalservices. This provides a list of all available technical services. Use the technical service list to get values such as "id" or "TechnicalServiceId". This collection also contains the parameter definitions for each technical service. Here you will find an “id” and other values for each parameter. With this information, you can build your JSON to create a service.


## Create a Subscription via API
-	Use in Postman authorization “BasicAuth”
-	Login with user key and password (default: 10000 and supplier)
-	To subscribe without an assgined user, build your JSON as shown [here](https://github.com/servicecatalog/oscm-rest-api/blob/add/gettingStarted/docs/json/Subscription%20example). 
-	To subscirbe with an assigned user, build your JSON as shown [here](https://github.com/servicecatalog/oscm-rest-api/blob/add/gettingStarted/docs/json/Subscription%20with%20user). 
-	Use POST method of endpoint https:/host:8081/oscm-rest-api/v1/subscriptions to post the message body. 

**Get Data to Create a Subscription**

Call the endpoint https://host:8081/oscm-rest-api/v1/services. This offers a list of all existing services. Find the service you want to subscribe to and call it again with the service id https://host:8081/oscm-rest-api/v1/services/{id}. The result contains the details of the service. Use this information to build your JSON and create a subscription without an assigned user. If you also want to assign a user to the subscription, use the endpoint https://host:8081/oscm-rest-api/v1/users. All available users are provided here.




 
