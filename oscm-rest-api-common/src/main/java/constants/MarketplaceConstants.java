/**
 * *****************************************************************************
 *
 * <p>Copyright FUJITSU LIMITED 2019
 *
 * <p>Creation Date: Aug 05, 2019
 *
 * <p>*****************************************************************************
 */
package constants;

/**
 * Class for marketplace api module constants
 *
 * @author crystalzord
 */
public class MarketplaceConstants {

  public static final String ENTRY_MINIMUM_BODY = "{}";

  public static final String ENTRY_MAXIMUM_BODY =
      "{\n"
          + "\"anonymousVisible\": true,\n"
          + "\"visibleInCatalog\": true,\n"
          + "\"categories\": [\n"
          + "{\n"
          + "\"categoryId\": \"sampleCategory2\",\n"
          + "\"marketplaceId\": \"959c9bf7\",\n"
          + "\"id\": 10000,\n"
          + "\"etag\": 1\n"
          + "}\n"
          + "],\n"
          + "\"etag\": 1\n"
          + "}\n";

  public static final String MARKETPLACE_EXAMPLE_RESPONSE =
      "{\n"
          + "\"marketplaceId\": \"restmarketplace\",\n"
          + "\"name\": \"marketplaceREST\",\n"
          + "\"open\": true,\n"
          + "\"taggingEnabled\": false,\n"
          + "\"reviewEnabled\": false,\n"
          + "\"socialBookmarkEnabled\": false,\n"
          + "\"categoriesEnabled\": false,\n"
          + "\"restricted\": true,\n"
          + "\"hasPublicLandingPage\": false,\n"
          + "\"owningOrganizationName\": \"supplier_org\",\n"
          + "\"owningOrganizationId\": \"959c9bf7\",\n"
          + "\"etag\": 1,\n"
          + "\"id\": 10001"
          + "}\n";

  public static final String MARKETPLACE_LIST_EXAMPLE_RESPONSE =
      "{\"items\":[" + MARKETPLACE_EXAMPLE_RESPONSE + "]}";

  public static final String MARKETPLACE_CREATE_EXAMPLE_RESPONSE =
      "{\n"
          + "\"marketplaceId\": \"restmarketplace\",\n"
          + "\"name\": \"marketplaceREST\",\n"
          + "\"open\": true,\n"
          + "\"taggingEnabled\": true,\n"
          + "\"reviewEnabled\": true,\n"
          + "\"categoriesEnabled\": true,\n"
          + "\"owningOrganizationId\": \"959c9bf7\"\n"
          + "}\n";

  public static final String MARKETPLACE_UPDATE_EXAMPLE_RESPONSE =
      "{\n"
          + "\"name\": \"marketplaceREST\",\n"
          + "\"open\": true,\n"
          + "\"taggingEnabled\": true,\n"
          + "\"reviewEnabled\": true,\n"
          + "\"categoriesEnabled\": true,\n"
          + "\"owningOrganizationId\": \"959c9bf7\",\n"
          + "\"etag\": 1\n"
          + "}\n";
}
