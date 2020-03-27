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
}
