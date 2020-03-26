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
 * Class for common shared constants
 *
 * @author crystalzord
 */
public class CommonConstants {

  public static final String EXAMPLE_MINIMUM_BODY_NAME = "Only required parameters";
  public static final String EXAMPLE_MINIMUM_BODY_SUMMARY = "Minimum body example";

  public static final String EXAMPLE_MAXIMUM_BODY_NAME = "All possible parameters";
  public static final String EXAMPLE_MAXIMUM_BODY_SUMMARY = "Maximum body example";

  public static final String EXAMPLE_REQUEST_BODY_DESCRIPTION =
      "Request contains object specific data";

  public static final String EXAMPLE_REQUEST_BODY_SUMMARY = "RequestBody example";

  private static final String PUT_REQUEST_ADDITIONAL_INFO =
      "Field \"etag\" is required. It is a version number and must be greater that existing one.";

  public static final String EXAMPLE_PUT_REQUEST_BODY_DESCRIPTION =
      EXAMPLE_REQUEST_BODY_DESCRIPTION + ". " + PUT_REQUEST_ADDITIONAL_INFO;

  public static final String ID_INFO = "ID is present in the response body.";
}
