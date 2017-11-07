package org.oscm.rest.marketplace.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ws.rs.WebApplicationException;

import org.oscm.internal.vo.VOMarketplace;
import org.oscm.rest.common.Representation;

public class MarketplaceRepresentation extends Representation {

    private transient VOMarketplace vo;

    private String marketplaceId;
    private String name;
    private boolean open;
    private String tenantId;
    private boolean taggingEnabled = true;
    private boolean reviewEnabled = true;
    private boolean socialBookmarkEnabled = true;
    private boolean categoriesEnabled = true;
    private boolean restricted = false;
    private boolean hasPublicLandingPage;
    private String owningOrganizationName;
    private String owningOrganizationId;

    public MarketplaceRepresentation() {
        this(new VOMarketplace());
    }

    public MarketplaceRepresentation(VOMarketplace mp) {
        vo = mp;
    }

    @Override
    public void validateContent() throws WebApplicationException {

    }

    @Override
    public void update() {
        vo.setCategoriesEnabled(isCategoriesEnabled());
        vo.setHasPublicLandingPage(isHasPublicLandingPage());
        vo.setKey(convertIdToKey());
        vo.setMarketplaceId(getMarketplaceId());
        vo.setName(getName());
        vo.setOpen(isOpen());
        vo.setOwningOrganizationId(getOwningOrganizationId());
        vo.setOwningOrganizationName(getOwningOrganizationName());
        vo.setRestricted(isRestricted());
        vo.setReviewEnabled(isReviewEnabled());
        vo.setSocialBookmarkEnabled(isSocialBookmarkEnabled());
        vo.setTaggingEnabled(isTaggingEnabled());
        vo.setTenantId(getTenantId());
        vo.setVersion(convertETagToVersion());
    }

    @Override
    public void convert() {
        setCategoriesEnabled(vo.isCategoriesEnabled());
        setETag(Long.valueOf(vo.getVersion()));
        setHasPublicLandingPage(vo.isHasPublicLandingPage());
        setId(Long.valueOf(vo.getKey()));
        setMarketplaceId(vo.getMarketplaceId());
        setName(vo.getName());
        setOpen(vo.isOpen());
        setOwningOrganizationId(vo.getOwningOrganizationId());
        setOwningOrganizationName(vo.getOwningOrganizationName());
        setRestricted(vo.isRestricted());
        setReviewEnabled(vo.isReviewEnabled());
        setSocialBookmarkEnabled(vo.isSocialBookmarkEnabled());
        setTaggingEnabled(vo.isTaggingEnabled());
        setTenantId(vo.getTenantId());
    }

    public String getMarketplaceId() {
        return marketplaceId;
    }

    public void setMarketplaceId(String marketplaceId) {
        this.marketplaceId = marketplaceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public boolean isTaggingEnabled() {
        return taggingEnabled;
    }

    public void setTaggingEnabled(boolean taggingEnabled) {
        this.taggingEnabled = taggingEnabled;
    }

    public boolean isReviewEnabled() {
        return reviewEnabled;
    }

    public void setReviewEnabled(boolean reviewEnabled) {
        this.reviewEnabled = reviewEnabled;
    }

    public boolean isSocialBookmarkEnabled() {
        return socialBookmarkEnabled;
    }

    public void setSocialBookmarkEnabled(boolean socialBookmarkEnabled) {
        this.socialBookmarkEnabled = socialBookmarkEnabled;
    }

    public boolean isCategoriesEnabled() {
        return categoriesEnabled;
    }

    public void setCategoriesEnabled(boolean categoriesEnabled) {
        this.categoriesEnabled = categoriesEnabled;
    }

    public boolean isRestricted() {
        return restricted;
    }

    public void setRestricted(boolean restricted) {
        this.restricted = restricted;
    }

    public boolean isHasPublicLandingPage() {
        return hasPublicLandingPage;
    }

    public void setHasPublicLandingPage(boolean hasPublicLandingPage) {
        this.hasPublicLandingPage = hasPublicLandingPage;
    }

    public VOMarketplace getVO() {
        return vo;
    }

    public static Collection<MarketplaceRepresentation> toCollection(
            List<VOMarketplace> mps) {
        Collection<MarketplaceRepresentation> result = new ArrayList<MarketplaceRepresentation>();
        if (mps == null || mps.isEmpty()) {
            return result;
        }
        for (VOMarketplace mp : mps) {
            MarketplaceRepresentation mr = new MarketplaceRepresentation(mp);
            result.add(mr);
        }
        return result;
    }

    public String getOwningOrganizationName() {
        return owningOrganizationName;
    }

    public void setOwningOrganizationName(String owningOrganizationName) {
        this.owningOrganizationName = owningOrganizationName;
    }

    public String getOwningOrganizationId() {
        return owningOrganizationId;
    }

    public void setOwningOrganizationId(String owningOrganizationId) {
        this.owningOrganizationId = owningOrganizationId;
    }

    // FIXME move to super class
    protected long convertIdToKey() {
        if (getId() == null) {
            return 0L;
        }
        return getId().longValue();
    }

    // FIXME move to super class
    protected int convertETagToVersion() {
        if (getETag() == null) {
            return 0;
        }
        return getETag().intValue();
    }
}
