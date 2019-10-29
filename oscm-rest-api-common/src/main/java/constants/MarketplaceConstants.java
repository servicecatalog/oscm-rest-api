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

  public static final String MARKETPLACE_MINIMUM_BODY =
      "{\n"
          + "\"marketplaceId\": \"test_marketplace\",\n"
          + "\"name\": \"test_name\",\n"
          + "\"owningOrganizationId\": \"959c9bf7\"\n"
          + "}";

  public static final String MARKETPLACE_MAXIMUM_BODY =
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
          + "\"etag\": 1\n"
          + "}\n";
}
