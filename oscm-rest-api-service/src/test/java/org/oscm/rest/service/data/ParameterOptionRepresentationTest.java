/**
 * *****************************************************************************
 *
 * <p>Copyright FUJITSU LIMITED 2019
 *
 * <p>Creation Date: 24-04-2019
 *
 * <p>*****************************************************************************
 */
package org.oscm.rest.service.data;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.oscm.internal.vo.BaseVO;
import org.oscm.internal.vo.VOParameterOption;
import org.oscm.rest.common.Representation;

class ParameterOptionRepresentationTest {

  @Test
  public void shouldUpdateVOParameterOption() {
    ParameterOptionRepresentation representation = new ParameterOptionRepresentation();
    representation.setId(100L);
    representation.setOptionDescription("Description");
    representation.setOptionId("100");
    representation.setParamDefId("100");
    representation.setETag(100L);

    representation.update();
    VOParameterOption result = representation.getVO();

    assertThat(result).isNotNull();
    assertThat(result).extracting(BaseVO::getKey).isEqualTo(representation.convertIdToKey());
    assertThat(result)
        .extracting(VOParameterOption::getOptionDescription)
        .isEqualTo(representation.getOptionDescription());
    assertThat(result)
        .extracting(VOParameterOption::getOptionId)
        .isEqualTo(representation.getOptionId());
    assertThat(result)
        .extracting(VOParameterOption::getParamDefId)
        .isEqualTo(representation.getParamDefId());
    assertThat(result)
        .extracting(BaseVO::getVersion)
        .isEqualTo(representation.convertETagToVersion());
  }

  @Test
  public void shouldUpdateVOParameterOption_evenIfIdAndETagIsNull() {
    ParameterOptionRepresentation representation = new ParameterOptionRepresentation();
    representation.setOptionDescription("Description");
    representation.setOptionId("100");
    representation.setParamDefId("100");

    representation.update();
    VOParameterOption result = representation.getVO();

    assertThat(result).isNotNull();
    assertThat(result).extracting(BaseVO::getKey).isEqualTo(representation.convertIdToKey());
    assertThat(result)
        .extracting(VOParameterOption::getOptionDescription)
        .isEqualTo(representation.getOptionDescription());
    assertThat(result)
        .extracting(VOParameterOption::getOptionId)
        .isEqualTo(representation.getOptionId());
    assertThat(result)
        .extracting(VOParameterOption::getParamDefId)
        .isEqualTo(representation.getParamDefId());
    assertThat(result)
        .extracting(BaseVO::getVersion)
        .isEqualTo(representation.convertETagToVersion());
  }

  @Test
  public void shouldConvertToParameterOptionRepresentation() {
    VOParameterOption voParameterOption = new VOParameterOption();
    voParameterOption.setKey(100L);
    voParameterOption.setOptionDescription("Description");
    voParameterOption.setOptionId("100");
    voParameterOption.setVersion(100);
    voParameterOption.setParamDefId("100");

    ParameterOptionRepresentation representation =
        new ParameterOptionRepresentation(voParameterOption);
    representation.convert();

    assertThat(representation)
        .extracting(Representation::getId)
        .isEqualTo(voParameterOption.getKey());
    assertThat(representation)
        .extracting(ParameterOptionRepresentation::getOptionDescription)
        .isEqualTo(voParameterOption.getOptionDescription());
    assertThat(representation)
        .extracting(ParameterOptionRepresentation::getOptionId)
        .isEqualTo(voParameterOption.getOptionId());
    assertThat(representation)
        .extracting(ParameterOptionRepresentation::getParamDefId)
        .isEqualTo(voParameterOption.getParamDefId());
    assertThat(representation)
        .extracting(ParameterOptionRepresentation::convertETagToVersion)
        .isEqualTo(voParameterOption.getVersion());
  }
}
