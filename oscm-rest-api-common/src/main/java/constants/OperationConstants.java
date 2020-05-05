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
public class OperationConstants {

  public static final String SETTING_UPDATE_EXAMPLE_REQUEST =
      "{\n" + "\"contextId\": \"global\",\n" + "\"value\": \"INFO\",\n" + "\"etag\": 0\n" + "}";

  public static final String SETTING_EXAMPLE_RESPONSE =
      "{\n"
          + "  \"informationId\": \"LOG_LEVEL\",\n"
          + "  \"contextId\": \"global\",\n"
          + "  \"value\": \"INFO\",\n"
          + "  \"etag\": 0,\n"
          + "  \"id\": 9\n"
          + "}";

  public static final String SETTING_LIST_EXAMPLE_RESPONSE =
      "{\"items\":[" + SETTING_EXAMPLE_RESPONSE + "]}";
}
