package org.oscm.rest.common.representation;

import org.junit.jupiter.api.Test;
import org.oscm.internal.vo.BaseVO;
import org.oscm.internal.vo.VOCategory;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CategoryRepresentationTest {

    @Test
    public void shouldUpdateVOCategory() {
        CategoryRepresentation representation = createRepresentation();
        representation.setId(100L);
        representation.setETag(100L);

        representation.update();
        VOCategory result = representation.getVO();

        assertThat(result).isNotNull();
        assertThat(result).extracting(BaseVO::getKey).isEqualTo(representation.convertIdToKey());
        assertThat(result)
                .extracting(VOCategory::getCategoryId)
                .isEqualTo(representation.getCategoryId());
        assertThat(result)
                .extracting(BaseVO::getVersion)
                .isEqualTo(representation.convertETagToVersion());
    }

    @Test
    public void shouldUpdateVOCategory_evenIfIdAndEtagIsNull() {
        CategoryRepresentation representation = createRepresentation();

        representation.update();
        VOCategory result = representation.getVO();

        assertThat(result).isNotNull();
        assertThat(result).extracting(BaseVO::getKey).isEqualTo(representation.convertIdToKey());
        assertThat(result)
                .extracting(VOCategory::getCategoryId)
                .isEqualTo(representation.getCategoryId());
        assertThat(result)
                .extracting(BaseVO::getVersion)
                .isEqualTo(representation.convertETagToVersion());
    }

    @Test
    public void shouldConvertToCategoryRepresentation() {
        VOCategory voCategory = createVO();

        CategoryRepresentation representation = new CategoryRepresentation(voCategory);
        representation.convert();

        assertThat(representation)
                .extracting(CategoryRepresentation::getCategoryId)
                .isEqualTo(voCategory.getCategoryId());
        assertThat(representation)
                .extracting(CategoryRepresentation::convertIdToKey)
                .isEqualTo(voCategory.getKey());
        assertThat(representation)
                .extracting(CategoryRepresentation::convertETagToVersion)
                .isEqualTo(voCategory.getVersion());
    }

    @Test
    public void shouldUpdateAndReturnListOfVoCategory() {
        List<CategoryRepresentation> representationList = new ArrayList<>();
        CategoryRepresentation categoryRepresentation = new CategoryRepresentation();
        categoryRepresentation.setCategoryId("Category100");
        representationList.add(categoryRepresentation);

        List<VOCategory> result = CategoryRepresentation.update(representationList);

        assertThat(result.size()).isEqualTo(1);
        assertThat(((VOCategory) result.toArray()[0]).getCategoryId())
                .isEqualTo(((CategoryRepresentation) representationList.toArray()[0]).getCategoryId());
    }

    @Test
    public void shouldConvertAndReturnListOfCategoryRepresentation() {
        List<VOCategory> voList = new ArrayList<>();
        VOCategory voCategory = new VOCategory();
        voCategory.setCategoryId("Category200");
        voList.add(voCategory);

        List<CategoryRepresentation> result = CategoryRepresentation.convert(voList);

        assertThat(result.size()).isEqualTo(1);
        assertThat(((CategoryRepresentation) result.toArray()[0]).getCategoryId())
                .isEqualTo(((VOCategory) voList.toArray()[0]).getCategoryId());
    }

    private CategoryRepresentation createRepresentation() {
        CategoryRepresentation representation = new CategoryRepresentation();
        representation.setCategoryId("Category100");
        return representation;
    }

    private VOCategory createVO() {
        VOCategory voCategory = new VOCategory();
        voCategory.setCategoryId("Category100");
        voCategory.setKey(100L);
        voCategory.setVersion(100);
        return voCategory;
    }
}