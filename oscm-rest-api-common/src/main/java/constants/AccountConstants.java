/**
 * *****************************************************************************
 *
 * <p>Copyright FUJITSU LIMITED 2019
 *
 * <p>Creation Date: Jul 15, 2019
 *
 * <p>*****************************************************************************
 */
package constants;

/**
 * Class for account api module constants
 *
 * @author crystalzord
 */
public class AccountConstants {

  public static final String BILLING_CONTACT_EXAMPLE_RESPONSE =
      "{\n"
          + "  \"email\": \"sample.email@escm.com\",\n"
          + "  \"companyName\": \"Sample Company Name\",\n"
          + "  \"address\": \"Sample addresssssssssss\",\n"
          + "  \"orgAddressUsed\": true,\n"
          + "  \"contactId\": \"Sample contact ID.\",\n"
          + "  \"etag\": 3,\n"
          + "  \"id\": 10000\n"
          + "}";

  public static final String BILLING_CONTACT_LIST_EXAMPLE_RESPONSE =
      "{\"items\":[" + BILLING_CONTACT_EXAMPLE_RESPONSE + "]}";

  public static final String BILLING_CONTACT_CREATE_EXAMPLE_REQUEST =
      "{\n"
          + "\"email\": \"sample.email@escm.com\",\n"
          + "\"companyName\": \"Sample Company Name\",\n"
          + "\"address\": \"Sample addresss\",\n"
          + "\"orgAddressUsed\": true,"
          + "\"contactId\": \"Sample contact ID.\"\n"
          + "}";

  public static final String BILLING_CONTACT_UPDATE_EXAMPLE_REQUEST =
      "{\n"
          + "\"email\": \"sample.email@escm.com\",\n"
          + "\"companyName\": \"Sample Company Name\",\n"
          + "\"address\": \"Sample addresss\",\n"
          + "\"orgAddressUsed\": true,"
          + "\"contactId\": \"Sample contact ID.\",\n"
          + "\"etag\": 1 \n"
          + "}";

  public static final String ORGANIZATION_EXAMPLE_RESPONSE =
      "{\n"
          + "  \"organizationId\": \"ba5774cd\",\n"
          + "  \"address\": \"adr12345\",\n"
          + "  \"email\": \"org@test.com\",\n"
          + "  \"locale\": \"en\",\n"
          + "  \"name\": \"SupplierOrg\",\n"
          + "  \"phone\": \"1234567\",\n"
          + "  \"url\": \"http://onet.pl\",\n"
          + "  \"description\": \"\",\n"
          + "  \"domicileCountry\": \"ES\",\n"
          + "  \"etag\": 1,\n"
          + "  \"id\": 10000\n"
          + "}";

  public static final String ORGANIZATION_EXAMPLE_REQUEST =
      "{\n"
          + "\"organizationRoles\": [\"TECHNOLOGY_PROVIDER\"],\n"
          + "\"organization\": {\n"
          + "\"address\": \"sample address2\",\n"
          + "\"email\": \"secondsupplier@escm.org\",\n"
          + "\"locale\": \"en\",\n"
          + "\"name\": \"restorg\",\n"
          + "\"phone\": \"987654321\",\n"
          + "\"url\": \"http://example.com/org\",\n"
          + "\"description\": \"\",\n"
          + "\"domicileCountry\": \"PL\"\n"
          + "},\n"
          + "\"user\": {\n"
          + "\"email\": \"mail@orgidd.com\",\n"
          + "\"firstName\": \"fname\",\n"
          + "\"lastName\": \"lname\",\n"
          + "\"address\": \"addr\",\n"
          + "\"phone\": \"111111111\",\n"
          + "\"locale\": \"en\",\n"
          + "\"salutation\": \"MR\",\n"
          + "\"userId\": \"user\"\n"
          + "}\n"
          + "}\n";

  public static final String ORGANIZATION_EXAMPLE_PUT_BODY =
      "{\n"
          + "\"organization\": { \n"
          + "\"etag\": \"1\", \n"
          + "\"id\": \"10000\", \n"
          + "\"organizationId\": \"959c9bf7\", \n"
          + "\"address\": \"sample address2\",\n"
          + "\"email\": \"secondsupplier@escm.org\",\n"
          + "\"locale\": \"en\",\n"
          + "\"name\": \"restorg\",\n"
          + "\"phone\": \"987654321\",\n"
          + "\"url\": \"http://example.com/org\",\n"
          + "\"description\": \"\",\n"
          + "\"domicileCountry\": \"PL\"\n"
          + "}\n"
          + "}\n";

  public static final String CUSTOMER_EXAMPLE_REQUEST =
      "{\n"
          + "\"marketPlaceId\": \"b3e46tzq\",\n"
          + "\"organization\": {\n"
          + "\"name\": \"restorg\",\n"
          + "\"email\": \"secondsupplier@escm.org\",\n"
          + "\"locale\": \"en\",\n"
          + "\"address\": \"sample address2\",\n"
          + "\"phone\": \"987654321\",\n"
          + "\"url\": \"http://example.com/org\",\n"
          + "\"description\": \"some description\",\n"
          + "\"domicileCountry\": \"PL\"\n"
          + "},\n"
          + "\"user\": {\n"
          + "\"userId\": \"user\",\n"
          + "\"email\": \"mail@orgidd.com\",\n"
          + "\"locale\": \"en\",\n"
          + "\"salutation\": \"MR\",\n"
          + "\"firstName\": \"fname\",\n"
          + "\"lastName\": \"lname\",\n"
          + "\"address\": \"addr\",\n"
          + "\"phone\": \"111111111\"\n"
          + "}\n"
          + "}\n";

  public static final String PAYMENT_INFO_EXAMPLE_RESPONSE =
      "\"{\"paymentType\":{\"name\":\"Invoice\",\"paymentTypeId\":\"INVOICE\",\"collectionType\":\"ORGANIZATION\",\"etag\":0,\"id\":3},\"infoId\":\"some info id\",\"etag\":0,\"id\":10000}";

  public static final String PAYMENT_INFO_LIST_EXAMPLE_RESPONSE =
      "{\"items\":[" + PAYMENT_INFO_EXAMPLE_RESPONSE + "]}";

  public static final String PAYMENT_INFO_UPDATE_EXAMPLE_REQUEST =
      "{\"paymentType\":{\"paymentTypeId\":\"INVOICE\"},\"infoId\":\"some info id\",\"etag\":0}";
}
