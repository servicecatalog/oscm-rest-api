/**
 * *****************************************************************************
 *
 * <p>Copyright FUJITSU LIMITED 2019
 *
 * <p>Creation Date: 24-04-2019
 *
 * <p>*****************************************************************************
 */
package org.oscm.rest.common.representation;

import org.junit.jupiter.api.Test;
import org.oscm.internal.vo.BaseVO;
import org.oscm.internal.vo.VOParameterOption;
import org.oscm.rest.common.representation.ParameterOptionRepresentation;
import org.oscm.rest.common.representation.Representation;

import static org.assertj.core.api.Assertions.assertThat;

public class ParameterOptionRepresentationTest {

  @Test
  public void shouldUpdateVOParameterOption() {
    ParameterOptionRepresentation representation = createRepresentation();
    representation.setId(100L);
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
    ParameterOptionRepresentation representation = createRepresentation();

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
    VOParameterOption voParameterOption = createVO();

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

  private ParameterOptionRepresentation createRepresentation() {
    ParameterOptionRepresentation representation = new ParameterOptionRepresentation();
    representation.setOptionDescription("Description");
    representation.setOptionId("100");
    representation.setParamDefId("100");
    return representation;
  }

  private VOParameterOption createVO() {
    VOParameterOption voParameterOption = new VOParameterOption();
    voParameterOption.setKey(100L);
    voParameterOption.setOptionDescription("Description");
    voParameterOption.setOptionId("100");
    voParameterOption.setVersion(100);
    voParameterOption.setParamDefId("100");
    return voParameterOption;
  }
}
