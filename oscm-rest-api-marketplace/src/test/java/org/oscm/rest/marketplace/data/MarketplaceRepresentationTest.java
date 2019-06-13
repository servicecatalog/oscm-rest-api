package org.oscm.rest.marketplace.data;

import org.junit.jupiter.api.Test;
import org.oscm.internal.vo.BaseVO;
import org.oscm.internal.vo.VOMarketplace;

import static org.assertj.core.api.Assertions.assertThat;

class MarketplaceRepresentationTest {

    @Test
    public void shouldUpdateVOMarketplace() {
        MarketplaceRepresentation representation = createRepresentation();
        representation.setId(100L);
        representation.setETag(100L);

        representation.update();
        VOMarketplace result = representation.getVO();

        assertThat(result).isNotNull();
        assertThat(result).extracting(BaseVO::getKey).isEqualTo(representation.convertIdToKey());
        assertThat(result)
                .extracting(BaseVO::getVersion)
                .isEqualTo(representation.convertETagToVersion());
        assertThat(result)
                .extracting(VOMarketplace::isCategoriesEnabled)
                .isEqualTo(representation.isCategoriesEnabled());
        assertThat(result)
                .extracting(VOMarketplace::isHasPublicLandingPage)
                .isEqualTo(representation.isHasPublicLandingPage());
        assertThat(result)
                .extracting(VOMarketplace::getMarketplaceId)
                .isEqualTo(representation.getMarketplaceId());
        assertThat(result)
                .extracting(VOMarketplace::isOpen)
                .isEqualTo(representation.isOpen());
        assertThat(result)
                .extracting(VOMarketplace::getOwningOrganizationId)
                .isEqualTo(representation.getOwningOrganizationId());
        assertThat(result)
                .extracting(VOMarketplace::getOwningOrganizationName)
                .isEqualTo(representation.getOwningOrganizationName());
        assertThat(result)
                .extracting(VOMarketplace::isRestricted)
                .isEqualTo(representation.isRestricted());
        assertThat(result)
                .extracting(VOMarketplace::isReviewEnabled)
                .isEqualTo(representation.isReviewEnabled());
        assertThat(result)
                .extracting(VOMarketplace::isTaggingEnabled)
                .isEqualTo(representation.isTaggingEnabled());
        assertThat(result)
                .extracting(VOMarketplace::getTenantId)
                .isEqualTo(representation.getTenantId());
    }

    @Test
    public void shouldUpdateVOMarketplace_evenIfIDAndEtagIsNull() {
        MarketplaceRepresentation representation = createRepresentation();

        representation.update();
        VOMarketplace result = representation.getVO();

        assertThat(result).isNotNull();
        assertThat(result).extracting(BaseVO::getKey).isEqualTo(representation.convertIdToKey());
        assertThat(result)
                .extracting(BaseVO::getVersion)
                .isEqualTo(representation.convertETagToVersion());
        assertThat(result)
                .extracting(VOMarketplace::isCategoriesEnabled)
                .isEqualTo(representation.isCategoriesEnabled());
        assertThat(result)
                .extracting(VOMarketplace::isHasPublicLandingPage)
                .isEqualTo(representation.isHasPublicLandingPage());
        assertThat(result)
                .extracting(VOMarketplace::getMarketplaceId)
                .isEqualTo(representation.getMarketplaceId());
        assertThat(result)
                .extracting(VOMarketplace::isOpen)
                .isEqualTo(representation.isOpen());
        assertThat(result)
                .extracting(VOMarketplace::getOwningOrganizationId)
                .isEqualTo(representation.getOwningOrganizationId());
        assertThat(result)
                .extracting(VOMarketplace::getOwningOrganizationName)
                .isEqualTo(representation.getOwningOrganizationName());
        assertThat(result)
                .extracting(VOMarketplace::isRestricted)
                .isEqualTo(representation.isRestricted());
        assertThat(result)
                .extracting(VOMarketplace::isReviewEnabled)
                .isEqualTo(representation.isReviewEnabled());
        assertThat(result)
                .extracting(VOMarketplace::isTaggingEnabled)
                .isEqualTo(representation.isTaggingEnabled());
        assertThat(result)
                .extracting(VOMarketplace::getTenantId)
                .isEqualTo(representation.getTenantId());
    }

    @Test
    public void shouldConvertToMarketplaceRepresentation() {
        VOMarketplace voMarketplace = createVO();

        MarketplaceRepresentation representation = new MarketplaceRepresentation(voMarketplace);
        representation.convert();

        assertThat(representation)
                .extracting(MarketplaceRepresentation::convertIdToKey)
                .isEqualTo(voMarketplace.getKey());
        assertThat(representation)
                .extracting(MarketplaceRepresentation::convertETagToVersion)
                .isEqualTo(voMarketplace.getVersion());
        assertThat(representation)
                .extracting(MarketplaceRepresentation::isCategoriesEnabled)
                .isEqualTo(voMarketplace.isCategoriesEnabled());
        assertThat(representation)
                .extracting(MarketplaceRepresentation::isHasPublicLandingPage)
                .isEqualTo(voMarketplace.isHasPublicLandingPage());
        assertThat(representation)
                .extracting(MarketplaceRepresentation::getMarketplaceId)
                .isEqualTo(voMarketplace.getMarketplaceId());
        assertThat(representation)
                .extracting(MarketplaceRepresentation::getName)
                .isEqualTo(voMarketplace.getName());
        assertThat(representation)
                .extracting(MarketplaceRepresentation::isOpen)
                .isEqualTo(voMarketplace.isOpen());
        assertThat(representation)
                .extracting(MarketplaceRepresentation::getOwningOrganizationId)
                .isEqualTo(voMarketplace.getOwningOrganizationId());
        assertThat(representation)
                .extracting(MarketplaceRepresentation::getOwningOrganizationName)
                .isEqualTo(voMarketplace.getOwningOrganizationName());
        assertThat(representation)
                .extracting(MarketplaceRepresentation::isRestricted)
                .isEqualTo(voMarketplace.isRestricted());
        assertThat(representation)
                .extracting(MarketplaceRepresentation::isReviewEnabled)
                .isEqualTo(voMarketplace.isReviewEnabled());
        assertThat(representation)
                .extracting(MarketplaceRepresentation::isTaggingEnabled)
                .isEqualTo(voMarketplace.isTaggingEnabled());
        assertThat(representation)
                .extracting(MarketplaceRepresentation::getTenantId)
                .isEqualTo(voMarketplace.getTenantId());
    }

    private MarketplaceRepresentation createRepresentation() {
        MarketplaceRepresentation representation = new MarketplaceRepresentation();
        representation.setCategoriesEnabled(true);
        representation.setHasPublicLandingPage(true);
        representation.setMarketplaceId("Marketplace100");
        representation.setName("Name");
        representation.setOpen(true);
        representation.setOwningOrganizationId("OwnerId100");
        representation.setOwningOrganizationName("OwnerName100");
        representation.setRestricted(true);
        representation.setReviewEnabled(true);
        representation.setSocialBookmarkEnabled(true);
        representation.setTaggingEnabled(true);
        representation.setTenantId("Tenant100");
        return representation;
    }

    private VOMarketplace createVO() {
        VOMarketplace voMarketplace = new VOMarketplace();
        voMarketplace.setKey(100L);
        voMarketplace.setVersion(100);
        voMarketplace.setCategoriesEnabled(true);
        voMarketplace.setHasPublicLandingPage(true);
        voMarketplace.setMarketplaceId("Marketplace100");
        voMarketplace.setName("Name");
        voMarketplace.setOpen(true);
        voMarketplace.setOwningOrganizationId("OwnerId100");
        voMarketplace.setOwningOrganizationName("OwnerName100");
        voMarketplace.setRestricted(true);
        voMarketplace.setReviewEnabled(true);
        voMarketplace.setTaggingEnabled(true);
        voMarketplace.setTenantId("Tenant100");
        return voMarketplace;
    }

}