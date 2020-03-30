/**
 * *****************************************************************************
 *
 * <p>Copyright FUJITSU LIMITED 2019
 *
 * <p>Creation Date: Jul 23, 2019
 *
 * <p>*****************************************************************************
 */
package constants;

/**
 * Class for identity api module constants
 *
 * @author crystalzord
 */
public class IdentityConstants {

  public static final String ONBEHALFUSERS_MINIMUM_BODY =
      "{\n" + "\"organizationId\": \"959c9bf7\",\n" + "\"password\": \"somePasword\"\n" + "}";

  public static final String ONBEHALFUSERS_MAXIMUM_BODY =
      "{\n"
          + "\"organizationId\": \"959c9bf7\",\n"
          + "\"password\": \"somePasword\",\n"
          + "\"userId\": \"obUserId\",\n"
          + "\"etag\": 1\n"
          + "}";

  public static final String USER_ROLE_EXAMPLE_RESPONSE =
      "{\"userRoles\": [\"PLATFORM_OPERATOR\",\"MARKETPLACE_OWNER\",\"ORGANIZATION_ADMIN\"],\"etag\": 0,\"id\": 1000}";

  public static final String USER_ROLE_UPDATE_EXAMPLE_REQUEST =
      "{\"userRoles\": [\"PLATFORM_OPERATOR\",\"MARKETPLACE_OWNER\",\"ORGANIZATION_ADMIN\"]}";

  public static final String USER_EXAMPLE_RESPONSE =
      "{\n"
          + "  \"email\": \"rest@user.com\",\n"
          + "  \"firstName\": \"John\",\n"
          + "  \"lastName\": \"Rest\",\n"
          + "  \"locale\": \"en\",\n"
          + "  \"realmUserId\": \"administrator\",\n"
          + "  \"remoteLdapActive\": false,\n"
          + "  \"organizationId\": \"PLATFORM_OPERATOR\",\n"
          + "  \"userId\": \"administrator\",\n"
          + "  \"status\": \"ACTIVE\",\n"
          + "  \"organizationRoles\": [\n"
          + "    \"CUSTOMER\",\n"
          + "    \"PLATFORM_OPERATOR\",\n"
          + "    \"MARKETPLACE_OWNER\"\n"
          + "  ],\n"
          + "  \"userRoles\": [\n"
          + "    \"PLATFORM_OPERATOR\",\n"
          + "    \"ORGANIZATION_ADMIN\"\n"
          + "  ],\n"
          + "  \"etag\": 0,\n"
          + "  \"id\": 1000\n"
          + "}";

  public static final String USER_LIST_EXAMPLE_RESPONSE =
      "{\"items\":[" + USER_EXAMPLE_RESPONSE + "]}";

  public static final String USER_CREATE_EXAMPLE_BODY =
      "{\n"
          + "  \"salutation\": \"MR\",\n"
          + "  \"email\": \"rest@user.com\",\n"
          + "  \"firstName\": \"John\",\n"
          + "  \"lastName\": \"Rest\",\n"
          + "  \"address\": \"Some Address From Rest\",\n"
          + "  \"phone\": \"123123123\",\n"
          + "  \"locale\": \"en\",\n"
          + "  \"organizationId\": \"c5c0a827\",\n"
          + "  \"userId\": \"jrest\",\n"
          + "  \"userRoles\": [\n"
          + "    \"MARKETPLACE_OWNER\",\n"
          + "    \"ORGANIZATION_ADMIN\"\n"
          + "  ]\n"
          + "}";

  public static final String USER_UPDATE_EXAMPLE_REQUEST =
      "{\n"
          + "\"email\": \"rest@user.com\",\n"
          + "\"salutation\": \"MR\",\n"
          + "\"firstName\": \"John\",\n"
          + "\"lastName\": \"Rest\",\n"
          + "\"address\": \"Address From REST TEST\",\n"
          + "\"phone\": \"123123123\",\n"
          + "\"locale\": \"en\",\n"
          + "\"organizationId\": \"c5c0a827\",\n"
          + "\"userId\": \"jrest\",\n"
          + "\"userRoles\": [\"MARKETPLACE_OWNER\", \"SERVICE_MANAGER\", \"TECHNOLOGY_MANAGER\", \"ORGANIZATION_ADMIN\"],\n"
          + "\"etag\": 1\n"
          + "}";
}
