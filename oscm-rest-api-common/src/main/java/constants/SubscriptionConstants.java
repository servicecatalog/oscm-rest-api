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
          + "  \"serviceKey\": 10000,\n"
          + "  \"subscriptionId\": \"RestSubscription\",\n"
          + "  \"purchaseOrderNumber\": \"ref123456\",\n"
          + "  \"unitName\": \"default\",\n"
          + "  \"billingContactId\": \"Home\",\n"
          + "  \"paymentInfoId\": \"Invoice\",\n"
          + "  \"parameters\": {\n"
          + "     \"PARAM1_ID\":\"param1 value\",\n"
          + "     \"PARAM2_ID\": \"param2 value\"\n"
          + "   },\n"
          + "  \"udas\": {\n"
          + "     \"UDA1_ID\":\"uda1 value\",\n"
          + "     \"UDA2_ID\": \"uda2 value\"\n"
          + "   }\n"
          + "}";

  public static final String SUBSCRIPTION_UPDATE_EXAMPLE_REQUEST =
      "{\n"
          + "  \"subscriptionId\": \"RestSubscription\",\n"
          + "  \"purchaseOrderNumber\": \"ref123456\",\n"
          + "  \"billingContactId\": \"Home\",\n"
          + "  \"paymentInfoId\": \"Invoice\",\n"
          + "  \"parameters\": {\n"
          + "     \"PARAM1_ID\":\"param1 value\",\n"
          + "     \"PARAM2_ID\": \"param2 value\"\n"
          + "   },\n"
          + "  \"udas\": {\n"
          + "     \"UDA1_ID\":\"uda1 value\",\n"
          + "     \"UDA2_ID\": \"uda2 value\"\n"
          + "   }\n"
          + "}";

  public static final String LICENSE_CREATE_EXAMPLE_REQUEST = "{\n \"userId\": \"supplier\"}";

  public static final String LICENSE_LIST_EXAMPLE_RESPONSE =
      "{\n"
          + "  \"items\": [\n"
          + "    {\n"
          + "      \"user\": {\n"
          + "        \"remoteLdapActive\": false,\n"
          + "        \"organizationId\": \"a2acb920\",\n"
          + "        \"userId\": \"supplier\",\n"
          + "        \"status\": \"ACTIVE\",\n"
          + "        \"organizationRoles\": [\n"
          + "          \"TECHNOLOGY_PROVIDER\",\n"
          + "          \"CUSTOMER\",\n"
          + "          \"SUPPLIER\"\n"
          + "        ],\n"
          + "        \"userRoles\": [\n"
          + "          \"TECHNOLOGY_MANAGER\",\n"
          + "          \"ORGANIZATION_ADMIN\",\n"
          + "          \"SERVICE_MANAGER\"\n"
          + "        ],\n"
          + "        \"etag\": 1,\n"
          + "        \"id\": 10000\n"
          + "      }\n"
          + "    },\n"
          + "    {\n"
          + "      \"user\": {\n"
          + "        \"remoteLdapActive\": false,\n"
          + "        \"organizationId\": \"a2acb920\",\n"
          + "        \"userId\": \"supplier_user1\",\n"
          + "        \"status\": \"ACTIVE\",\n"
          + "        \"organizationRoles\": [\n"
          + "          \"TECHNOLOGY_PROVIDER\",\n"
          + "          \"CUSTOMER\",\n"
          + "          \"SUPPLIER\"\n"
          + "        ],\n"
          + "        \"userRoles\": [\n"
          + "          \"SUBSCRIPTION_MANAGER\",\n"
          + "          \"SERVICE_MANAGER\"\n"
          + "        ],\n"
          + "        \"etag\": 1,\n"
          + "        \"id\": 10002\n"
          + "      }\n"
          + "    }\n"
          + "  ]\n"
          + "}\n";
}
