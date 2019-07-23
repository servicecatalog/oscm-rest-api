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

    public static final String ONBEHALFUSERS_MINIMUM_BODY = "{\n" +
            "\"organizationId\": \"959c9bf7\",\n" +
            "\"password\": \"somePasword\"\n" +
            "}";

    public static final String ONBEHALFUSERS_MAXIMUM_BODY = "{\n" +
            "\"organizationId\": \"959c9bf7\",\n" +
            "\"password\": \"somePasword\",\n" +
            "\"userId\": \"obUserId\",\n" +
            "\"etag\": 1\n" +
            "}";

    public static final String ROLE_EXAMPLE_BODY = "{\n" +
            "\"userRoles\": [\"TECHNOLOGY_MANAGER\", \"SERVICE_MANAGER\", \"MARKETPLACE_OWNER\", \"ORGANIZATION_ADMIN\"],\n" +
            "\"etag\": 0,\n" +
            "\"id\": 13010\n" +
            "}";
    public static final String USER_MINIMUM_BODY = "{\n" +
            "\"email\": \"test@email.com\",\n" +
            "\"locale\": \"ja\",\n" +
            "\"organizationId\": \"10000\",\n" +
            "\"userId\": \"fdgdfg\",\n" +
            "\"userRoles\": [\"MARKETPLACE_OWNER\", \"SERVICE_MANAGER\", \"TECHNOLOGY_MANAGER\", \"ORGANIZATION_ADMIN\"]\n" +
            "}";

    public static final String USER_MAXIMUM_BODY = "{\n" +
            "\"email\": \"rest@user.com\",\n" +
            "\"firstName\": \"John\",\n" +
            "\"lastName\": \"Rambo\",\n" +
            "\"address\": \"Some Address From Rest\",\n" +
            "\"phone\": \"123123123\",\n" +
            "\"locale\": \"ja\",\n" +
            "\"salutation\": \"MR\",\n" +
            "\"realmUserId\": \"jrambo\",\n" +
            "\"remoteLdapActive\": true,\n" +
            "\"organizationId\": \"10000\",\n" +
            "\"userId\": \"jbravo\",\n" +
            "\"status\": \"ACTIVE\",\n" +
            "\"organizationRoles\": [\"TECHNOLOGY_PROVIDER\", \"CUSTOMER\", \"SUPPLIER\", \"MARKETPLACE_OWNER\"],\n" +
            "\"userRoles\": [\"MARKETPLACE_OWNER\", \"SERVICE_MANAGER\", \"TECHNOLOGY_MANAGER\", \"ORGANIZATION_ADMIN\"],\n" +
            "\"etag\": 1\n" +
            "}";


    //FIXME: TMP values shall be removed after fixing problem with redundant id.
    public static final String TMP_USER_MIN_PUT_BODY = "{\n" +
            "\"email\": \"test@email.com\",\n" +
            "\"locale\": \"ja\",\n" +
            "\"organizationId\": \"10000\",\n" +
            "\"userId\": \"fdgdfg\",\n" +
            "\"userRoles\": [\"MARKETPLACE_OWNER\", \"SERVICE_MANAGER\", \"TECHNOLOGY_MANAGER\", \"ORGANIZATION_ADMIN\"]\n" +
            "\"id\": 13000\n" +
            "}";

    public static final String TMP_USER_MAX_PUT_BODY = "{\n" +
            "\"email\": \"rest@user.com\",\n" +
            "\"firstName\": \"John\",\n" +
            "\"lastName\": \"Rambo\",\n" +
            "\"address\": \"Address From REST TEST\",\n" +
            "\"phone\": \"123123123\",\n" +
            "\"locale\": \"ja\",\n" +
            "\"salutation\": \"MR\",\n" +
            "\"realmUserId\": \"jrambo\",\n" +
            "\"remoteLdapActive\": true,\n" +
            "\"organizationId\": \"10000\",\n" +
            "\"userId\": \"jbravo\",\n" +
            "\"status\": \"ACTIVE\",\n" +
            "\"organizationRoles\": [\"TECHNOLOGY_PROVIDER\", \"CUSTOMER\", \"SUPPLIER\", \"MARKETPLACE_OWNER\"],\n" +
            "\"userRoles\": [\"MARKETPLACE_OWNER\", \"SERVICE_MANAGER\", \"TECHNOLOGY_MANAGER\", \"ORGANIZATION_ADMIN\"],\n" +
            "\"etag\": 4,\n" +
            "\"id\": 13000\n" +
            "}";

    public static final String PUT_TMP_WARNING = "the \"id\" field at the bottom of PUT's body is required but it is not expected behaviour. It is redundant and will be removed after it is fixed in the code.";
}
