/**
 * *****************************************************************************
 *
 * <p>Copyright FUJITSU LIMITED 2019
 *
 * <p>Creation Date: Aug 1, 2019
 *
 * <p>*****************************************************************************
 */
package constants;

/** Class for account api module constants */
public class ServiceConstants {
  public static final String SERVICE_MAXIMUM_BODY =
      "{\n"
          + "\"description\": \"REST Description\",\n"
          + "\"name\": \"REST created service\",\n"
          + "\"serviceId\": \"REST serviceID\",\n"
          + "\"technicalId\": \"REST technicalID\",\n"
          + "\"status\": \"ACTIVE\",\n"
          + "\"accessType\": \"DIRECT\",\n"
          + "\"shortDescription\": \"REST short desc\",\n"
          + "\"offeringType\": \"BROKER\",\n"
          + "\"configuratorUrl\": \"http://some-random-url.com\",\n"
          + "\"billingIdentifier\": \"REST billing identifier\",\n"
          + "\"serviceType\": \"SUBSCRIPTION\",\n"
          + "\"parameters\": [],\n"
          + "\"imageDefined\": false,\n"
          + "\"technicalService\": {\n"
          + "            \"eventDefinitions\": [],\n"
          + "            \"technicalServiceId\": \"ESCM_Sample\",\n"
          + "            \"technicalServiceBuildId\": \"20171108\",\n"
          + "            \"accessType\": \"DIRECT\",\n"
          + "            \"technicalServiceDescription\": \"Simple sample OpenStack instance\",\n"
          + "            \"baseUrl\": \"\",\n"
          + "            \"provisioningUrl\": \"http://oscm-app:8880/oscm-app/webservices/oscm-app/oscm-app/org.oscm.app.v2_0.service.AsynchronousProvisioningProxy?wsdl\",\n"
          + "            \"loginPath\": \"/\",\n"
          + "            \"provisioningVersion\": \"1.0\",\n"
          + "            \"parameterDefinitions\": [\n"
          + "                {\n"
          + "                    \"parameterOptions\": [],\n"
          + "                    \"minValue\": 0,\n"
          + "                    \"mandatory\": false,\n"
          + "                    \"configurable\": true,\n"
          + "                    \"parameterType\": \"PLATFORM_PARAMETER\",\n"
          + "                    \"parameterId\": \"PERIOD\",\n"
          + "                    \"valueType\": \"DURATION\",\n"
          + "                    \"modificationType\": \"STANDARD\",\n"
          + "                    \"description\": \"Number of days after which the subscription will be deactivated.\",\n"
          + "                    \"etag\": 0,\n"
          + "                    \"id\": 1002\n"
          + "                },\n"
          + "                {\n"
          + "                    \"parameterOptions\": [],\n"
          + "                    \"defaultValue\": \"ess.openstack\",\n"
          + "                    \"mandatory\": true,\n"
          + "                    \"configurable\": false,\n"
          + "                    \"parameterType\": \"SERVICE_PARAMETER\",\n"
          + "                    \"parameterId\": \"APP_CONTROLLER_ID\",\n"
          + "                    \"valueType\": \"STRING\",\n"
          + "                    \"modificationType\": \"STANDARD\",\n"
          + "                    \"description\": \"ID of the controller implementation\",\n"
          + "                    \"etag\": 0,\n"
          + "                    \"id\": 10000\n"
          + "                },\n"
          + "                {\n"
          + "                    \"parameterOptions\": [],\n"
          + "                    \"mandatory\": true,\n"
          + "                    \"configurable\": true,\n"
          + "                    \"parameterType\": \"SERVICE_PARAMETER\",\n"
          + "                    \"parameterId\": \"STACK_NAME\",\n"
          + "                    \"valueType\": \"STRING\",\n"
          + "                    \"modificationType\": \"ONE_TIME\",\n"
          + "                    \"description\": \"OpenStack instance name\",\n"
          + "                    \"etag\": 0,\n"
          + "                    \"id\": 10001\n"
          + "                },\n"
          + "                {\n"
          + "                    \"parameterOptions\": [],\n"
          + "                    \"defaultValue\": \"([A-Za-z][A-Za-z0-9_-]*){1,30}\",\n"
          + "                    \"mandatory\": true,\n"
          + "                    \"configurable\": true,\n"
          + "                    \"parameterType\": \"SERVICE_PARAMETER\",\n"
          + "                    \"parameterId\": \"STACK_NAME_PATTERN\",\n"
          + "                    \"valueType\": \"STRING\",\n"
          + "                    \"modificationType\": \"ONE_TIME\",\n"
          + "                    \"description\": \"Validation pattern for the stack name (regex)\",\n"
          + "                    \"etag\": 0,\n"
          + "                    \"id\": 10002\n"
          + "                },\n"
          + "                {\n"
          + "                    \"parameterOptions\": [\n"
          + "                        {\n"
          + "                            \"optionId\": \"Sample.yaml\",\n"
          + "                            \"optionDescription\": \"Sample.yaml\",\n"
          + "                            \"paramDefId\": \"TEMPLATE_NAME\",\n"
          + "                            \"etag\": 0,\n"
          + "                            \"id\": 10000\n"
          + "                        }\n"
          + "                    ],\n"
          + "                    \"defaultValue\": \"Sample.yaml\",\n"
          + "                    \"mandatory\": true,\n"
          + "                    \"configurable\": true,\n"
          + "                    \"parameterType\": \"SERVICE_PARAMETER\",\n"
          + "                    \"parameterId\": \"TEMPLATE_NAME\",\n"
          + "                    \"valueType\": \"ENUMERATION\",\n"
          + "                    \"modificationType\": \"ONE_TIME\",\n"
          + "                    \"description\": \"Template files for resource orchestration\",\n"
          + "                    \"etag\": 1,\n"
          + "                    \"id\": 10003\n"
          + "                },\n"
          + "                {\n"
          + "                    \"parameterOptions\": [],\n"
          + "                    \"defaultValue\": \"External (floating) network address: {floating_ip}\",\n"
          + "                    \"mandatory\": true,\n"
          + "                    \"configurable\": true,\n"
          + "                    \"parameterType\": \"SERVICE_PARAMETER\",\n"
          + "                    \"parameterId\": \"ACCESS_INFO_PATTERN\",\n"
          + "                    \"valueType\": \"STRING\",\n"
          + "                    \"modificationType\": \"ONE_TIME\",\n"
          + "                    \"description\": \"Pattern for access information\",\n"
          + "                    \"etag\": 0,\n"
          + "                    \"id\": 10004\n"
          + "                },\n"
          + "                {\n"
          + "                    \"parameterOptions\": [],\n"
          + "                    \"mandatory\": true,\n"
          + "                    \"configurable\": true,\n"
          + "                    \"parameterType\": \"SERVICE_PARAMETER\",\n"
          + "                    \"parameterId\": \"TP_image\",\n"
          + "                    \"valueType\": \"STRING\",\n"
          + "                    \"modificationType\": \"ONE_TIME\",\n"
          + "                    \"description\": \"Image ID\",\n"
          + "                    \"etag\": 0,\n"
          + "                    \"id\": 10005\n"
          + "                },\n"
          + "                {\n"
          + "                    \"parameterOptions\": [],\n"
          + "                    \"defaultValue\": \"m1.tiny\",\n"
          + "                    \"mandatory\": true,\n"
          + "                    \"configurable\": true,\n"
          + "                    \"parameterType\": \"SERVICE_PARAMETER\",\n"
          + "                    \"parameterId\": \"TP_flavor\",\n"
          + "                    \"valueType\": \"STRING\",\n"
          + "                    \"modificationType\": \"ONE_TIME\",\n"
          + "                    \"description\": \"Instance type (flavor)\",\n"
          + "                    \"etag\": 0,\n"
          + "                    \"id\": 10006\n"
          + "                },\n"
          + "                {\n"
          + "                    \"parameterOptions\": [],\n"
          + "                    \"defaultValue\": \"floating\",\n"
          + "                    \"mandatory\": true,\n"
          + "                    \"configurable\": true,\n"
          + "                    \"parameterType\": \"SERVICE_PARAMETER\",\n"
          + "                    \"parameterId\": \"TP_external_network\",\n"
          + "                    \"valueType\": \"STRING\",\n"
          + "                    \"modificationType\": \"STANDARD\",\n"
          + "                    \"description\": \"Public Network Name\",\n"
          + "                    \"etag\": 0,\n"
          + "                    \"id\": 10007\n"
          + "                },\n"
          + "                {\n"
          + "                    \"parameterOptions\": [],\n"
          + "                    \"mandatory\": false,\n"
          + "                    \"configurable\": true,\n"
          + "                    \"parameterType\": \"SERVICE_PARAMETER\",\n"
          + "                    \"parameterId\": \"MAIL_FOR_COMPLETION\",\n"
          + "                    \"valueType\": \"STRING\",\n"
          + "                    \"modificationType\": \"ONE_TIME\",\n"
          + "                    \"description\": \"The address to which emails are to be sent that describe manual steps required to complete an operation. If you specify this parameter, the service controller interrupts the processing of each operation before its completion and waits for a notification about the execution of a manual action. Omit this parameter if you do not want to interrupt the processing.\",\n"
          + "                    \"etag\": 0,\n"
          + "                    \"id\": 10008\n"
          + "                },\n"
          + "                {\n"
          + "                    \"parameterOptions\": [],\n"
          + "                    \"defaultValue\": \"0\",\n"
          + "                    \"mandatory\": true,\n"
          + "                    \"configurable\": false,\n"
          + "                    \"parameterType\": \"SERVICE_PARAMETER\",\n"
          + "                    \"parameterId\": \"VMS_NUMBER\",\n"
          + "                    \"valueType\": \"LONG\",\n"
          + "                    \"modificationType\": \"STANDARD\",\n"
          + "                    \"description\": \"The number of VMs of IaaS subscriptions\",\n"
          + "                    \"etag\": 0,\n"
          + "                    \"id\": 10009\n"
          + "                }\n"
          + "            ],\n"
          + "            \"roleDefinitions\": [],\n"
          + "            \"tags\": [\n"
          + "                \"escm_sample_inst\"\n"
          + "            ],\n"
          + "            \"license\": \"\",\n"
          + "            \"accessInfo\": \"ESCM Sample Instance\",\n"
          + "            \"billingIdentifier\": \"NATIVE_BILLING\",\n"
          + "            \"technicalServiceOperations\": [\n"
          + "                {\n"
          + "                    \"operationId\": \"START_VIRTUAL_SYSTEM\",\n"
          + "                    \"operationName\": \"Start\",\n"
          + "                    \"operationDescription\": \"Start all servers\",\n"
          + "                    \"operationParameters\": [],\n"
          + "                    \"etag\": 0,\n"
          + "                    \"id\": 10000\n"
          + "                },\n"
          + "                {\n"
          + "                    \"operationId\": \"STOP_VIRTUAL_SYSTEM\",\n"
          + "                    \"operationName\": \"Stop\",\n"
          + "                    \"operationDescription\": \"Stop all servers\",\n"
          + "                    \"operationParameters\": [],\n"
          + "                    \"etag\": 0,\n"
          + "                    \"id\": 10001\n"
          + "                },\n"
          + "                {\n"
          + "                    \"operationId\": \"RESUME_VIRTUAL_SYSTEM\",\n"
          + "                    \"operationName\": \"Resume\",\n"
          + "                    \"operationDescription\": \"Resume the instance\",\n"
          + "                    \"operationParameters\": [],\n"
          + "                    \"etag\": 0,\n"
          + "                    \"id\": 10002\n"
          + "                },\n"
          + "                {\n"
          + "                    \"operationId\": \"SUSPEND_VIRTUAL_SYSTEM\",\n"
          + "                    \"operationName\": \"Suspend\",\n"
          + "                    \"operationDescription\": \"Suspend the instance\",\n"
          + "                    \"operationParameters\": [],\n"
          + "                    \"etag\": 0,\n"
          + "                    \"id\": 10003\n"
          + "                }\n"
          + "            ],\n"
          + "            \"externalBilling\": false,\n"
          + "            \"etag\": 1,\n"
          + "            \"id\": 10000\n"
          + "        }\n"
          + "}\n";

  public static final String SERVICE_MINIMUM_BODY =
      "{\n"
          + "\"serviceId\": \"REST serviceID\",\n"
          + "\"technicalService\": {\n"
          + "            \"parameterDefinitions\": [\n"
          + "                {\n"
          + "                    \"parameterOptions\": [],\n"
          + "                    \"minValue\": 0,\n"
          + "                    \"mandatory\": false,\n"
          + "                    \"configurable\": true,\n"
          + "                    \"parameterType\": \"PLATFORM_PARAMETER\",\n"
          + "                    \"parameterId\": \"PERIOD\",\n"
          + "                    \"valueType\": \"DURATION\",\n"
          + "                    \"modificationType\": \"STANDARD\",\n"
          + "                    \"description\": \"Number of days after which the subscription will be deactivated.\",\n"
          + "                    \"etag\": 0,\n"
          + "                    \"id\": 1002\n"
          + "                },\n"
          + "                {\n"
          + "                    \"parameterOptions\": [],\n"
          + "                    \"defaultValue\": \"ess.openstack\",\n"
          + "                    \"mandatory\": true,\n"
          + "                    \"configurable\": false,\n"
          + "                    \"parameterType\": \"SERVICE_PARAMETER\",\n"
          + "                    \"parameterId\": \"APP_CONTROLLER_ID\",\n"
          + "                    \"valueType\": \"STRING\",\n"
          + "                    \"modificationType\": \"STANDARD\",\n"
          + "                    \"description\": \"ID of the controller implementation\",\n"
          + "                    \"etag\": 0,\n"
          + "                    \"id\": 10000\n"
          + "                },\n"
          + "                {\n"
          + "                    \"parameterOptions\": [],\n"
          + "                    \"mandatory\": true,\n"
          + "                    \"configurable\": true,\n"
          + "                    \"parameterType\": \"SERVICE_PARAMETER\",\n"
          + "                    \"parameterId\": \"STACK_NAME\",\n"
          + "                    \"valueType\": \"STRING\",\n"
          + "                    \"modificationType\": \"ONE_TIME\",\n"
          + "                    \"description\": \"OpenStack instance name\",\n"
          + "                    \"etag\": 0,\n"
          + "                    \"id\": 10001\n"
          + "                },\n"
          + "                {\n"
          + "                    \"parameterOptions\": [],\n"
          + "                    \"defaultValue\": \"([A-Za-z][A-Za-z0-9_-]*){1,30}\",\n"
          + "                    \"mandatory\": true,\n"
          + "                    \"configurable\": true,\n"
          + "                    \"parameterType\": \"SERVICE_PARAMETER\",\n"
          + "                    \"parameterId\": \"STACK_NAME_PATTERN\",\n"
          + "                    \"valueType\": \"STRING\",\n"
          + "                    \"modificationType\": \"ONE_TIME\",\n"
          + "                    \"description\": \"Validation pattern for the stack name (regex)\",\n"
          + "                    \"etag\": 0,\n"
          + "                    \"id\": 10002\n"
          + "                },\n"
          + "                {\n"
          + "                    \"parameterOptions\": [\n"
          + "                        {\n"
          + "                            \"optionId\": \"Sample.yaml\",\n"
          + "                            \"optionDescription\": \"Sample.yaml\",\n"
          + "                            \"paramDefId\": \"TEMPLATE_NAME\",\n"
          + "                            \"etag\": 0,\n"
          + "                            \"id\": 10000\n"
          + "                        }\n"
          + "                    ],\n"
          + "                    \"defaultValue\": \"Sample.yaml\",\n"
          + "                    \"mandatory\": true,\n"
          + "                    \"configurable\": true,\n"
          + "                    \"parameterType\": \"SERVICE_PARAMETER\",\n"
          + "                    \"parameterId\": \"TEMPLATE_NAME\",\n"
          + "                    \"valueType\": \"ENUMERATION\",\n"
          + "                    \"modificationType\": \"ONE_TIME\",\n"
          + "                    \"description\": \"Template files for resource orchestration\",\n"
          + "                    \"etag\": 1,\n"
          + "                    \"id\": 10003\n"
          + "                },\n"
          + "                {\n"
          + "                    \"parameterOptions\": [],\n"
          + "                    \"defaultValue\": \"External (floating) network address: {floating_ip}\",\n"
          + "                    \"mandatory\": true,\n"
          + "                    \"configurable\": true,\n"
          + "                    \"parameterType\": \"SERVICE_PARAMETER\",\n"
          + "                    \"parameterId\": \"ACCESS_INFO_PATTERN\",\n"
          + "                    \"valueType\": \"STRING\",\n"
          + "                    \"modificationType\": \"ONE_TIME\",\n"
          + "                    \"description\": \"Pattern for access information\",\n"
          + "                    \"etag\": 0,\n"
          + "                    \"id\": 10004\n"
          + "                },\n"
          + "                {\n"
          + "                    \"parameterOptions\": [],\n"
          + "                    \"mandatory\": true,\n"
          + "                    \"configurable\": true,\n"
          + "                    \"parameterType\": \"SERVICE_PARAMETER\",\n"
          + "                    \"parameterId\": \"TP_image\",\n"
          + "                    \"valueType\": \"STRING\",\n"
          + "                    \"modificationType\": \"ONE_TIME\",\n"
          + "                    \"description\": \"Image ID\",\n"
          + "                    \"etag\": 0,\n"
          + "                    \"id\": 10005\n"
          + "                },\n"
          + "                {\n"
          + "                    \"parameterOptions\": [],\n"
          + "                    \"defaultValue\": \"m1.tiny\",\n"
          + "                    \"mandatory\": true,\n"
          + "                    \"configurable\": true,\n"
          + "                    \"parameterType\": \"SERVICE_PARAMETER\",\n"
          + "                    \"parameterId\": \"TP_flavor\",\n"
          + "                    \"valueType\": \"STRING\",\n"
          + "                    \"modificationType\": \"ONE_TIME\",\n"
          + "                    \"description\": \"Instance type (flavor)\",\n"
          + "                    \"etag\": 0,\n"
          + "                    \"id\": 10006\n"
          + "                },\n"
          + "                {\n"
          + "                    \"parameterOptions\": [],\n"
          + "                    \"defaultValue\": \"floating\",\n"
          + "                    \"mandatory\": true,\n"
          + "                    \"configurable\": true,\n"
          + "                    \"parameterType\": \"SERVICE_PARAMETER\",\n"
          + "                    \"parameterId\": \"TP_external_network\",\n"
          + "                    \"valueType\": \"STRING\",\n"
          + "                    \"modificationType\": \"STANDARD\",\n"
          + "                    \"description\": \"Public Network Name\",\n"
          + "                    \"etag\": 0,\n"
          + "                    \"id\": 10007\n"
          + "                },\n"
          + "                {\n"
          + "                    \"parameterOptions\": [],\n"
          + "                    \"mandatory\": false,\n"
          + "                    \"configurable\": true,\n"
          + "                    \"parameterType\": \"SERVICE_PARAMETER\",\n"
          + "                    \"parameterId\": \"MAIL_FOR_COMPLETION\",\n"
          + "                    \"valueType\": \"STRING\",\n"
          + "                    \"modificationType\": \"ONE_TIME\",\n"
          + "                    \"description\": \"The address to which emails are to be sent that describe manual steps required to complete an operation. If you specify this parameter, the service controller interrupts the processing of each operation before its completion and waits for a notification about the execution of a manual action. Omit this parameter if you do not want to interrupt the processing.\",\n"
          + "                    \"etag\": 0,\n"
          + "                    \"id\": 10008\n"
          + "                },\n"
          + "                {\n"
          + "                    \"parameterOptions\": [],\n"
          + "                    \"defaultValue\": \"0\",\n"
          + "                    \"mandatory\": true,\n"
          + "                    \"configurable\": false,\n"
          + "                    \"parameterType\": \"SERVICE_PARAMETER\",\n"
          + "                    \"parameterId\": \"VMS_NUMBER\",\n"
          + "                    \"valueType\": \"LONG\",\n"
          + "                    \"modificationType\": \"STANDARD\",\n"
          + "                    \"description\": \"The number of VMs of IaaS subscriptions\",\n"
          + "                    \"etag\": 0,\n"
          + "                    \"id\": 10009\n"
          + "                }\n"
          + "            ],\n"
          + "            \"technicalServiceOperations\": [\n"
          + "                {\n"
          + "                    \"operationId\": \"START_VIRTUAL_SYSTEM\",\n"
          + "                    \"operationName\": \"Start\",\n"
          + "                    \"operationDescription\": \"Start all servers\",\n"
          + "                    \"operationParameters\": [],\n"
          + "                    \"etag\": 0,\n"
          + "                    \"id\": 10000\n"
          + "                },\n"
          + "                {\n"
          + "                    \"operationId\": \"STOP_VIRTUAL_SYSTEM\",\n"
          + "                    \"operationName\": \"Stop\",\n"
          + "                    \"operationDescription\": \"Stop all servers\",\n"
          + "                    \"operationParameters\": [],\n"
          + "                    \"etag\": 0,\n"
          + "                    \"id\": 10001\n"
          + "                },\n"
          + "                {\n"
          + "                    \"operationId\": \"RESUME_VIRTUAL_SYSTEM\",\n"
          + "                    \"operationName\": \"Resume\",\n"
          + "                    \"operationDescription\": \"Resume the instance\",\n"
          + "                    \"operationParameters\": [],\n"
          + "                    \"etag\": 0,\n"
          + "                    \"id\": 10002\n"
          + "                },\n"
          + "                {\n"
          + "                    \"operationId\": \"SUSPEND_VIRTUAL_SYSTEM\",\n"
          + "                    \"operationName\": \"Suspend\",\n"
          + "                    \"operationDescription\": \"Suspend the instance\",\n"
          + "                    \"operationParameters\": [],\n"
          + "                    \"etag\": 0,\n"
          + "                    \"id\": 10003\n"
          + "                }\n"
          + "            ],\n"
          + "            \"etag\": 1,\n"
          + "            \"id\": 10000\n"
          + "        }\n"
          + "}\n";

  public static final String COMPATIBLE_SERVICE_MINIMUM_BODY =
      "{\n"
          + "\"items\": [\n"
          + "{\n"
          + "\"etag\": 1,\n"
          + "\"id\": 11022\n"
          + "        }\n"
          + "],\n"
          + "\"etag\": 7\n"
          + "}\n";

  public static final String COMPATIBLE_SERVICE_MAXIMUM_BODY =
      "{\n"
          + "\"items\": [\n"
          + "{\n"
          + "\"parameters\": [],\n"
          + "\"imageDefined\": false,\n"
          + "\"description\": \"\",\n"
          + "\"name\": \"TSForRest\",\n"
          + "\"serviceId\": \"NotUsedMarketableService\",\n"
          + "\"technicalId\": \"TSForRest\",\n"
          + "\"status\": \"ACTIVE\",\n"
          + "\"accessType\": \"EXTERNAL\",\n"
          + "\"shortDescription\": \"\",\n"
          + "\"offeringType\": \"DIRECT\",\n"
          + "\"configuratorUrl\": \"\",\n"
          + "\"billingIdentifier\": \"NATIVE_BILLING\",\n"
          + "\"serviceType\": \"TEMPLATE\",\n"
          + "\"etag\": 1,\n"
          + "\"id\": 12000\n"
          + "}\n"
          + "],\n"
          + "\"etag\": 7\n"
          + "}\n";

  public static final String SERVICE_PRICE_MODEL_MINIMUM_BODY =
      "{\n"
          + "\"period\": \"MONTH\",\n"
          + "\"currencyISOCode\": \"EUR\",\n"
          + "\"etag\": 3,\n"
          + "\"id\": 10000\n"
          + "}\n";

  public static final String SERVICE_PRICE_MODEL_MAXIMUM_BODY =
      "{\n"
          + "    \"description\": \"\",\n"
          + "    \"period\": \"MONTH\",\n"
          + "    \"pricePerPeriod\": 0,\n"
          + "    \"pricePerUserAssignment\": 0,\n"
          + "    \"currencyISOCode\": \"EUR\",\n"
          + "    \"oneTimeFee\": 150,\n"
          + "    \"type\": \"PRO_RATA\",\n"
          + "    \"etag\": 2,\n"
          + "    \"id\": 12001\n"
          + "}\n";

  public static final String CUSTOMER_PRICE_MODEL_MINIMUM_BODY = "{ }";

  public static final String CUSTOMER_PRICE_MODEL_MAXIMUM_BODY =
      "{\n"
          + "   \"description\": \"\",\n"
          + "    \"period\": \"MONTH\",\n"
          + "    \"pricePerPeriod\": 0,\n"
          + "    \"pricePerUserAssignment\": 0,\n"
          + "    \"currencyISOCode\": \"EUR\",\n"
          + "    \"oneTimeFee\": 251,\n"
          + "    \"type\": \"PRO_RATA\",\n"
          + "    \"etag\": 3,\n"
          + "    \"id\": 12001\n"
          + "}\n";
  public static final String TECHNICAL_SERVICE_MINIMUM_BODY =
      "{\n"
          + "\"technicalServiceId\": \"newTSRESTID\",\n"
          + "\"accessType\": \"EXTERNAL\",\n"
          + "\"baseUrl\": \"http://some-random-url-from-rest-api.com\"\n"
          + "}\n";

  public static final String TECHNICAL_SERVICE_MAXIMUM_BODY =
      ""
          + "{\n"
          + "\"technicalServiceId\": \"newTSRESTID\",\n"
          + "\"technicalServiceBuildId\": \"tsBuildIdRest\",\n"
          + "\"accessType\": \"EXTERNAL\",\n"
          + "\"technicalServiceDescription\": \"TSREST Desc\",\n"
          + "\"baseUrl\": \"http://some-random-url-from-rest-api.com\",\n"
          + "\"provisioningUrl\": \"http://prov-url-from-rest-api.com\",\n"
          + "\"loginPath\": \"/login\",\n"
          + "\"provisioningVersion\": \"1.0\",\n"
          + "\"license\": \"REST License\",\n"
          + "\"accessInfo\": \"REST Access Info\",\n"
          + "\"billingIdentifier\": \"billing id\",\n"
          + "\"externalBilling\": false\n"
          + "}\n";
  public static final String TS_SUPPLIER_MINIMUM_BODY =
      "{\n" + "\"organizationId\": \"dummyorgid\"\n" + "}\n";;
  public static final String TS_SUPPLIER_MAXIMUM_BODY =
      "{\n" + "\"organizationId\": \"dummyorgid\"\n" + "}\n";
}
