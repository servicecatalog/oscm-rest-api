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
 * Class for event api module constants
 *
 * @author crystalzord
 */

public class EventConstants {

    public final static String EVENT_MAXIMUM_SUBSCRIPTION_EVENT_BODY = "{\n" +
            "\"occurrenceTime\": 2019060612463227027,\n" +
            "\"actor\": \"Rest event actor\",\n" +
            "\"eventId\": \"USER_LOGIN_TO_SERVICE\",\n" +
            "\"uniqueId\": \"REST UQ id\",\n" +
            "\"technicalServiceId\": \"Rest TS ID\",\n" +
            "\"instanceId\": \"Rest I ID\",\n" +
            "\"subscriptionKey\": 10001\n" +
            "}\n";


    public final static String EVENT_MAXIMUM_INSTANCE_EVENT_BODY = "{\n" +
            "\"occurrenceTime\": 2019060612463227027,\n" +
            "\"actor\": \"Rest event actor2\",\n" +
            "\"eventId\": \"USER_LOGIN_TO_SERVICE\",\n" +
            "\"uniqueId\": \"REST UQ id3\",\n" +
            "\"technicalServiceId\": \"ESCM_Sample\",\n" +
            "\"instanceId\": \"restprodinstance\"\n" +
            "}\n";

    public final static String INSTANCE_EVENT_ADDITIONAL_INFO = "Make sure that there is a subscription that is bound to technical service instance that you're referencing";
    public static final String SUBSCRIPTION_EVENT_SUMMARY = "for subscription event";
    public static final String INSTANCE_EVENT_SUMMARY = "for instance event";

}
