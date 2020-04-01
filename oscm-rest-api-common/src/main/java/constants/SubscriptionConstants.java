/**
 * *****************************************************************************
 *
 * <p>Copyright FUJITSU LIMITED 2019
 *
 * <p>Creation Date: Jul 22, 2019
 *
 * <p>*****************************************************************************
 */
package constants;

/**
 * Class for common shared constants
 *
 * @author crystalzord
 */
public class SubscriptionConstants {

  public static final String SUBSCRIPTION_CREATE_EXAMPLE_REQUEST =
      "{\n"
          + "\"billingContactKey\": 10000,\n"
          + "\"paymentInfoKey\": 10001,\n"
          + "\"users\": [],\n"
          + "\"udas\": [\n"
          + "{\n"
          + "\"udaDefinition\": {\n"
          + "\"udaId\": \"TENANT_ID\",\n"
          + "\"targetType\": \"CUSTOMER_SUBSCRIPTION\",\n"
          + "\"configurationType\": \"USER_OPTION_MANDATORY\",\n"
          + "\"id\": 10000,\n"
          + "\"etag\": 2\n"
          + "},\n"
          + "\"udaValue\": \"someudavalue\"\n"
          + "}\n"
          + "],\n"
          + "\"purchaseOrderNumber\": \"restPurchaseId\",\n"
          + "\"subscriptionId\": \"restSubscriptionId\",\n"
          + "\"unitKey\": 0,\n"
          + "\"unitName\": \"default\",\n"
          + "\"service\": {\n"
          + "\"id\": 10000,\n"
          + "\"etag\": 4,\n"
          + "\"parameters\": [\n"
          + "{\n"
          + "\"parameterDefinition\": {\n"
          + "\"parameterId\": \"STACK_NAME\"\n"
          + "},\n"
          + "\"value\": \"RESTStackName\"\n"
          + "}\n"
          + "]\n"
          + "}\n"
          + "}\n";

  public static final String LICENSE_MINIMUM_BODY =
      "{\n"
          + "\"user\": {\n"
          + "\"userId\": \"customer@adfs.com\",\n"
          + "\"id\": 10001\n"
          + "},\n"
          + "\"role\": {\n"
          + "\"roleId\": \"SAMPLE_ROLE\",\n"
          + "\"name\": \"RoleName\",\n"
          + "\"description\": \"RoleDescription\",\n"
          + "\"id\": 10000\n"
          + "}\n"
          + "}\n";

  public static final String LICENSE_MAXIMUM_BODY =
      "{\n"
          + "\"user\": {\n"
          + "\"remoteLdapActive\": false,\n"
          + "\"organizationId\": \"d4e7e9f4\",\n"
          + "\"userId\": \"customer@adfs.com\",\n"
          + "\"status\": \"ACTIVE\",\n"
          + "\"organizationRoles\": [\n"
          + "\"CUSTOMER\"\n"
          + "],\n"
          + "\"userRoles\": [\n"
          + "\"ORGANIZATION_ADMIN\"\n"
          + "],\n"
          + "\"etag\": 1,\n"
          + "\"id\": 10001\n"
          + "},\n"
          + "\"role\": {\n"
          + "\"roleId\": \"SAMPLE_ROLE\",\n"
          + "\"name\": \"\",\n"
          + "\"description\": \"\",\n"
          + "\"etag\": 0,\n"
          + "\"id\": 10000\n"
          + "}\n"
          + "}";
}
