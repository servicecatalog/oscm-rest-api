package org.oscm.rest.common.representation;

import org.junit.jupiter.api.Test;
import org.oscm.internal.vo.BaseVO;
import org.oscm.internal.vo.VOCatalogEntry;
import org.oscm.internal.vo.VOCategory;
import org.oscm.rest.common.TestContants;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class EntryRepresentationTest {

    @Test
    public void shouldUpdateVOCatalogEntry() {
        EntryRepresentation representation = createRepresentation();
        representation.setId(TestContants.LONG_VALUE);
        representation.setETag(TestContants.LONG_VALUE);

        representation.update();
        VOCatalogEntry result = representation.getVO();

        assertThat(result).isNotNull();
        assertThat(result).extracting(BaseVO::getKey).isEqualTo(representation.convertIdToKey());
        assertThat(result)
                .extracting(VOCatalogEntry::isAnonymousVisible)
                .isEqualTo(representation.isAnonymousVisible());
        assertThat(result)
                .extracting(VOCatalogEntry::isVisibleInCatalog)
                .isEqualTo(representation.isVisibleInCatalog());
        assertThat(result)
                .extracting(BaseVO::getVersion)
                .isEqualTo(representation.convertETagToVersion());
        assertThat(((VOCategory) result.getCategories().toArray()[0]).getCategoryId())
                .isEqualTo(((CategoryRepresentation) representation.getCategories().toArray()[0]).getCategoryId());
    }

    @Test
    public void shouldUpdateVOCatalogEntry_evenIfIdAndETagNull() {
        EntryRepresentation representation = createRepresentation();

        representation.update();
        VOCatalogEntry result = representation.getVO();

        assertThat(result).isNotNull();
        assertThat(result).extracting(BaseVO::getKey).isEqualTo(representation.convertIdToKey());
        assertThat(result)
                .extracting(VOCatalogEntry::isAnonymousVisible)
                .isEqualTo(representation.isAnonymousVisible());
        assertThat(result)
                .extracting(VOCatalogEntry::isVisibleInCatalog)
                .isEqualTo(representation.isVisibleInCatalog());
        assertThat(result)
                .extracting(BaseVO::getVersion)
                .isEqualTo(representation.convertETagToVersion());
        assertThat(((VOCategory) result.getCategories().toArray()[0]).getCategoryId())
                .isEqualTo(((CategoryRepresentation) representation.getCategories().toArray()[0]).getCategoryId());
    }

    @Test
    public void shouldCOnvertToVoCatalogEntry() {
        VOCatalogEntry voCatalogEntry = createVO();

        EntryRepresentation representation = new EntryRepresentation(voCatalogEntry);
        representation.convert();

        assertThat(representation)
                .extracting(EntryRepresentation::isAnonymousVisible)
                .isEqualTo(voCatalogEntry.isAnonymousVisible());
        assertThat(representation)
                .extracting(EntryRepresentation::isVisibleInCatalog)
                .isEqualTo(voCatalogEntry.isVisibleInCatalog());
        assertThat(((CategoryRepresentation) representation.getCategories().toArray()[0]).getCategoryId())
                .isEqualTo(((VOCategory) voCatalogEntry.getCategories().toArray()[0]).getCategoryId());
        assertThat(representation)
                .extracting(EntryRepresentation::convertIdToKey)
                .isEqualTo(voCatalogEntry.getKey());
        assertThat(representation)
                .extracting(EntryRepresentation::convertETagToVersion)
                .isEqualTo(voCatalogEntry.getVersion());
    }

    private EntryRepresentation createRepresentation() {
        EntryRepresentation representation = new EntryRepresentation();
        representation.setAnonymousVisible(true);
        representation.setVisibleInCatalog(true);
        List<CategoryRepresentation> list = new ArrayList<>();
        CategoryRepresentation categoryRepresentation = new CategoryRepresentation();
        categoryRepresentation.setCategoryId(TestContants.STRING_VALUE);
        list.add(categoryRepresentation);
        representation.setCategories(list);
        return representation;
    }

    private VOCatalogEntry createVO() {
        VOCatalogEntry voCatalogEntry = new VOCatalogEntry();
        voCatalogEntry.setAnonymousVisible(true);
        voCatalogEntry.setVisibleInCatalog(true);
        voCatalogEntry.setKey(TestContants.LONG_VALUE);
        voCatalogEntry.setVersion(TestContants.INTEGER_VALUE);
        List<VOCategory> list = new ArrayList<>();
        VOCategory voCategory = new VOCategory();
        voCategory.setCategoryId(TestContants.STRING_VALUE);
        list.add(voCategory);
        voCatalogEntry.setCategories(list);
        return voCatalogEntry;
    }
}