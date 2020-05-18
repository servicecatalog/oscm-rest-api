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

  public static final String LICENSE_CREATE_EXAMPLE_REQUEST =
      "{\n"
          + "\"user\": {\n"
          + "\"remoteLdapActive\": false,\n"
          + "\"organizationId\": \"d4e7e9f4\",\n"
          + "\"userId\": \"customer\",\n"
          + "\"status\": \"ACTIVE\",\n"
          + "\"organizationRoles\": [\n"
          + "\"CUSTOMER\"\n"
          + "],\n"
          + "\"userRoles\": [\n"
          + "\"ORGANIZATION_ADMIN\"\n"
          + "],\n"
          + "\"etag\": 1,\n"
          + "\"id\": 10001\n"
          + "}\n"
          + "}";

  public static final String LICENSE_UPDATE_EXAMPLE_REQUEST =
      "{\n"
          + "\"user\": {\n"
          + "\"remoteLdapActive\": false,\n"
          + "\"organizationId\": \"d4e7e9f4\",\n"
          + "\"userId\": \"customer\",\n"
          + "\"status\": \"ACTIVE\",\n"
          + "\"organizationRoles\": [\n"
          + "\"TECHNOLOGY_PROVIDER\", \n"
          + "\"CUSTOMER\"\n"
          + "],\n"
          + "\"userRoles\": [\n"
          + "\"ORGANIZATION_ADMIN\",\n"
          + "\"SERVICE_MANAGER\" \n"
          + "],\n"
          + "\"etag\": 1,\n"
          + "\"id\": 10001\n"
          + "}\n"
          + "}";

  public static final String LICENSE_LIST_EXAMPLE_RESPONSE =
      "{\"items\":[" + LICENSE_CREATE_EXAMPLE_REQUEST + "]}";
}
