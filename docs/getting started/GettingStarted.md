# RESTful API Sample for OSCM

## Requirements
-	OSCM v18.1 running in internal mode with sample data (installation with “-e SAMPLE_DATA=TRUE”)
-	Postman

## Create a Service via Rest
-	Use in Postmen authorization “BasicAuth”
-	Login with user key “10000” and password supplier (default values)
-	Create your service in JSON. As an example, which is working with sample data, see the file service example in the json folder.
-	Use POST method of endpoint: https://host:8081/oscm-rest-api/v1/services to post the message body.

**Get Data to Create a Service.**

To get the all necessary information to create a message body, call the endpoint: https://host: 8081/oscm-rest-api/v1/technicalservices. This endpoint provides a list of all available technical services. Use the provided information to get information like “id” or “technicalServiceId”. Furthermore, this endpoint provides the parameter definitions for the technical service. Here you can find an “id” for every parameter you want to define. With this information, you can create your JSON, to create a Service. 


## Create a Subscription via Rest
-	Use in Postmen authorization “BasicAuth”
-	Login with user key “10000” and password supplier (default values)
-	If you want to create a subscription without assigned user, create your JSON like shown in the file subscription example. If you want to create a subscription with assigned user, create your JSON like shown in the file subscription example with user in the json folder. 
-	Use POST method of endpoint: https:/host:8081/oscm-rest-api/v1/subscriptions to post the message body. 

**Get Data to Create a Subscription.**

To get all necessary information to create a message body, call the endpoint https://host:8081/oscm-rest-api/v1/services. This endpoint provides a list of available services. Search for the available service you want to subscribe to and call the endpoint again with the id of the service, you want to subscribe to: https://host:8081/oscm-rest-api/v1/services/{id}. You will get now a detail few of the service with all available parameter definitions. Use this information to create your JSON to create a subscription without an assigned user. If you want to assign also a user to the subscription, call the endpoint: https://host:8081/oscm-rest-api/v1/users. This endpoint will list all available users. 
 
