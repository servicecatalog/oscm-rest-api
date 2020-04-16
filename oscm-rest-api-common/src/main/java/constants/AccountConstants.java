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

  public static final String BILLING_CONTACT_EXAMPLE_BODY =
      "{\n"
          + "\"email\": \"sample.email@escm.com\",\n"
          + "\"companyName\": \"Sample Company Name\",\n"
          + "\"address\": \"Sample addresss\",\n"
          + "\"orgAddressUsed\": true,"
          + "\"contactId\": \"Sample contact ID.\"\n"
          + "}";

  public static final String ORGANIZATION_EXAMPLE_BODY =
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

  public static final String PAYMENT_INFO_EXAMPLE_RESPONSE =
      "\"{\"paymentType\":{\"name\":\"Invoice\",\"paymentTypeId\":\"INVOICE\",\"collectionType\":\"ORGANIZATION\",\"etag\":0,\"id\":3},\"infoId\":\"some info id\",\"etag\":0,\"id\":10000}";

  public static final String PAYMENT_INFO_LIST_EXAMPLE_RESPONSE =
      "{\"items\":[" + PAYMENT_INFO_EXAMPLE_RESPONSE + "]}";

  public static final String PAYMENT_INFO_UPDATE_EXAMPLE_REQUEST =
      "{\"paymentType\":{\"paymentTypeId\":\"INVOICE\"},\"infoId\":\"some info id\",\"etag\":0}";
}
