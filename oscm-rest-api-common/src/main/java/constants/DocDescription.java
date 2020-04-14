/**
 * *****************************************************************************
 *
 * <p>Copyright FUJITSU LIMITED 2020
 *
 * <p>Creation Date: 25-03-2020
 *
 * <p>*****************************************************************************
 */
package constants;

public class DocDescription {

  public static final String ENDPOINT_VERSION = "Endpoint's version";
  public static final String ORGANIZATION_ID = "Id of the organization";
  public static final String USER_ID = "Id of the user";
  public static final String OBJECT_ID = "Unique id of the object";
  public static final String SUBSCRIPTION_ID = "Id of the subscription";
  public static final String SERVICE_ID = "Id of the service";
  public static final String MARKETPLACE_TYPE = "Type defining list of returned marketplaces";
  public static final String LICENSE_KEY = "Key of the usage license";
  public static final String ONBEHALFUSER_ID = "Id of the on behalf user";
  public static final String ORGANIZATION_KEY = "Key of the organization";
  public static final String SEARCH_PHRASE =
      "Optional search phrase for full text searching (no pagination).";
  public static final String LOCALE =
      "Locale identifier of the language for displaying the marketable services (default: en)";
  public static final String MARKETPLACE_ID = "id of the marketplace";
  public static final String LIMIT =
      "Mandatory for pagination. Maximum number of services per page included in response (=page size)";
  public static final String OFFSET =
      "Mandatory for pagination. Number of the first service in the whole services list that marks begin index of the returned page. The resulting service page contains a list of services starting from index offset to offset + limit -1.";
  public static final String FILTER =
      "Optional parameter in conjunction with LIMIT and OFFSET, to filter services with service tags matching the given string value.";
  public static final String PERFORMANCE_HINT = "Performance hint for paging";
  public static final String SORTING = "Sorting criteria for paging";
}
