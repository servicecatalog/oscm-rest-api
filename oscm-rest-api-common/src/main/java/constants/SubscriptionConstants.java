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

    public static final String SUBSCRIPTION_MINIMUM_BODY = "{\n" +
            "\"billingContactKey\": 10002,\n" +
            "\"paymentInfoKey\": 10001,\n" +
            "\"users\": [],\n" +
            "\"udas\": [\n" +
            "{\n" +
            "\"udaDefinition\": {\n" +
            "\"udaId\": \"TENANT_ID\",\n" +
            "\"targetType\": \"CUSTOMER_SUBSCRIPTION\",\n" +
            "\"configurationType\": \"USER_OPTION_MANDATORY\",\n" +
            "\"id\": 10000,\n" +
            "\"etag\": 2\n" +
            "},\n" +
            "\"udaValue\": \"someudavalue\"\n" +
            "}\n" +
            "],\n" +
            "\"subscriptionId\": \"restSubscriptionId\",\n" +
            "\"service\": {\n" +
            "\"id\": 10000,\n" +
            "\"etag\": 4,\n" +
            "\"parameters\": [\n" +
            "{\n" +
            "\"parameterDefinition\": {\n" +
            "\"parameterId\": \"STACK_NAME\"\n" +
            "},\n" +
            "\"value\": \"RESTStackName\"\n" +
            "}\n" +
            "]\n" +
            "}\n" +
            "}\n";

    public static final String SUBSCRIPTION_MAXIMUM_BODY = "{\n" +
            "\"billingContactKey\": 10000,\n" +
            "\"paymentInfoKey\": 10001,\n" +
            "\"users\": [],\n" +
            "\"udas\": [\n" +
            "{\n" +
            "\"udaDefinition\": {\n" +
            "\"udaId\": \"TENANT_ID\",\n" +
            "\"targetType\": \"CUSTOMER_SUBSCRIPTION\",\n" +
            "\"configurationType\": \"USER_OPTION_MANDATORY\",\n" +
            "\"id\": 10000,\n" +
            "\"etag\": 2\n" +
            "},\n" +
            "\"udaValue\": \"someudavalue\"\n" +
            "}\n" +
            "],\n" +
            "\"purchaseOrderNumber\": \"restPurchaseId\",\n" +
            "\"subscriptionId\": \"restSubscriptionId\",\n" +
            "\"unitKey\": 0,\n" +
            "\"unitName\": \"default\",\n" +
            "\"service\": {\n" +
            "\"id\": 10000,\n" +
            "\"etag\": 4,\n" +
            "\"parameters\": [\n" +
            "{\n" +
            "\"parameterDefinition\": {\n" +
            "\"parameterId\": \"STACK_NAME\"\n" +
            "},\n" +
            "\"value\": \"RESTStackName\"\n" +
            "}\n" +
            "]\n" +
            "}\n" +
            "}\n";

    public static final String LICENSE_MINIMUM_BODY = "{\n" +
            "\"user\": {\n" +
            "\"userId\": \"customer@adfs.com\",\n" +
            "\"id\": 10001\n" +
            "},\n" +
            "\"role\": {\n" +
            "\"roleId\": \"SAMPLE_ROLE\",\n" +
            "\"name\": \"RoleName\",\n" +
            "\"description\": \"RoleDescription\",\n" +
            "\"id\": 10000\n" +
            "}\n" +
            "}\n";

    public static final String LICENSE_MAXIMUM_BODY = "{\n" +
            "\"user\": {\n" +
            "\"remoteLdapActive\": false,\n" +
            "\"organizationId\": \"d4e7e9f4\",\n" +
            "\"userId\": \"customer@adfs.com\",\n" +
            "\"status\": \"ACTIVE\",\n" +
            "\"organizationRoles\": [\n" +
            "\"CUSTOMER\"\n" +
            "],\n" +
            "\"userRoles\": [\n" +
            "\"ORGANIZATION_ADMIN\"\n" +
            "],\n" +
            "\"etag\": 1,\n" +
            "\"id\": 10001\n" +
            "},\n" +
            "\"role\": {\n" +
            "\"roleId\": \"SAMPLE_ROLE\",\n" +
            "\"name\": \"\",\n" +
            "\"description\": \"\",\n" +
            "\"etag\": 0,\n" +
            "\"id\": 10000\n" +
            "}\n" +
            "}";

    public static final String SUBSCRIPTION_POST_PRE_STEPS = "\nPre-steps: " +
            "\n - Create BillingContact for current user" +
            "\n - Verify if etag parameter that you are passing in the request body is the same with the version of that service in the database" +
            "\n - Ensure that there is no existing subscriptions for the service that you are referring to";

    public static final String SUBSCRIPTION_PUT_PRE_STEPS = "\nPre-steps: " +
            "\n - Go to ServiceInstance table in the bssapp database." +
            "\n - Set provisioningstatus to COMPLETED" +
            "\n - Copy instanceid value" +
            "\n - Paste copied instanceid value into productinstancefield in the subscription table" +
            "\n - Set subscription status to ACTIVE";

    public static final String USAGE_LICENSE_POST_PRE_STEPS = "\nPre-steps: " +
            "\n - Create a new Role Definition. For example (10000, 'SAMPLE ROLE', 0, 10000)";
}
