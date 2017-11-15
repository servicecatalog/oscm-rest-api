package org.oscm.rest.marketplace.data;

import java.util.List;

import javax.ws.rs.WebApplicationException;

import org.oscm.internal.vo.VOCatalogEntry;
import org.oscm.rest.common.Representation;

public class EntryRepresentation extends Representation {

    private transient VOCatalogEntry vo;

    private boolean anonymousVisible;
    private List<CategoryRepresentation> categories;
    private boolean visibleInCatalog = true;

    public EntryRepresentation() {
        this(new VOCatalogEntry());
    }

    public EntryRepresentation(VOCatalogEntry ce) {
        vo = ce;
    }

    @Override
    public void validateContent() throws WebApplicationException {

    }

    @Override
    public void update() {
        vo.setAnonymousVisible(isAnonymousVisible());
        vo.setCategories(CategoryRepresentation.update(getCategories()));
        vo.setKey(convertIdToKey());
        vo.setVersion(convertETagToVersion());
        vo.setVisibleInCatalog(isVisibleInCatalog());
    }

    @Override
    public void convert() {
        setAnonymousVisible(vo.isAnonymousVisible());
        setCategories(CategoryRepresentation.convert(vo.getCategories()));
        setETag(Long.valueOf(vo.getVersion()));
        setId(Long.valueOf(vo.getKey()));
        setVisibleInCatalog(vo.isVisibleInCatalog());
    }

    public boolean isAnonymousVisible() {
        return anonymousVisible;
    }

    public void setAnonymousVisible(boolean anonymousVisible) {
        this.anonymousVisible = anonymousVisible;
    }

    public List<CategoryRepresentation> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryRepresentation> categories) {
        this.categories = categories;
    }

    public boolean isVisibleInCatalog() {
        return visibleInCatalog;
    }

    public void setVisibleInCatalog(boolean visibleInCatalog) {
        this.visibleInCatalog = visibleInCatalog;
    }

    public VOCatalogEntry getVO() {
        return vo;
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
