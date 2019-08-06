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

    public static final String ENTRY_MAXIMUM_BODY = "{\n" +
            "\t\"anonymousVisible\": true,\n" +
            "\t\"visibleInCatalog\": true,\n" +
            "\t\"categories\": [\n" +
            "\t\t{\n" +
            "\t\t\t\"categoryId\": \"sampleCategory2\",\n" +
            "\t\t\t\"marketplaceId\": \"959c9bf7\",\n" +
            "\t\t\t\"id\": 10000,\n" +
            "\t\t\t\"etag\": 1\n" +
            "\t\t}\n" +
            "\t],\n" +
            "\t\"etag\": 1\n" +
            "}\n";

    public static final String MARKETPLACE_MINIMUM_BODY = "{\n" +
            "\"marketplaceId\": \"test_marketplace\",\n" +
            "\"name\": \"test_name\",\n" +
            "\"owningOrganizationId\": \"959c9bf7\"\n" +
            "}";

    public static final String MARKETPLACE_MAXIMUM_BODY = "{\n" +
            "\"marketplaceId\": \"restmarketplace\",\n" +
            "\"name\": \"marketplaceREST\",\n" +
            "\"open\": true,\n" +
            "\"taggingEnabled\": false,\n" +
            "\"reviewEnabled\": false,\n" +
            "\"socialBookmarkEnabled\": false,\n" +
            "\"categoriesEnabled\": false,\n" +
            "\"restricted\": true,\n" +
            "\"hasPublicLandingPage\": false,\n" +
            "\"owningOrganizationName\": \"supplier_org\",\n" +
            "\"owningOrganizationId\": \"959c9bf7\",\n" +
            "\"etag\": 1\n" +
            "}\n";
}
