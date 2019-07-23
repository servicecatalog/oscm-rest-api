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

    public static final String SETTING_POST_BODY = "{\n" +
            "\"informationId\": \"MP_ERROR_REDIRECT_HTTPS\",\n" +
            "\"contextId\": \"global\",\n" +
            "\"value\": \"https://redirect.com\"\n" +
            "}";

    public static final String SETTING_PUT_BODY = "{\n" +
            "\"informationId\": \"MP_ERROR_REDIRECT_HTTPS\",\n" +
            "\"contextId\": \"global\",\n" +
            "\"value\": \"https://redirect.com\",\n" +
            "\"etag\": 0,\n" +
            "\"id\": 15\n" +
            "}";
}
