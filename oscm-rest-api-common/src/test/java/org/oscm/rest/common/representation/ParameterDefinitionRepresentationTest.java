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
import org.oscm.internal.types.enumtypes.ParameterModificationType;
import org.oscm.internal.types.enumtypes.ParameterType;
import org.oscm.internal.types.enumtypes.ParameterValueType;
import org.oscm.internal.vo.BaseVO;
import org.oscm.internal.vo.VOParameterDefinition;
import org.oscm.internal.vo.VOParameterOption;
import org.oscm.rest.common.TestContants;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ParameterDefinitionRepresentationTest {

  @Test
  public void shouldUpdateVOParameterDefinition() {
    ParameterDefinitionRepresentation representation = new ParameterDefinitionRepresentation();
    representation.setConfigurable(true);
    representation.setDefaultValue(TestContants.STRING_VALUE);
    representation.setDescription(TestContants.STRING_VALUE);
    representation.setId(TestContants.LONG_VALUE);
    representation.setMandatory(true);
    representation.setMaxValue(TestContants.LONG_VALUE);
    representation.setMinValue(TestContants.LONG_VALUE);
    representation.setModificationType(ParameterModificationType.ONE_TIME);
    representation.setParameterId(TestContants.STRING_NUM_VALUE);
    List<ParameterOptionRepresentation> list = new ArrayList<>();
    ParameterOptionRepresentation parameterDefinitionRepresentation =
        new ParameterOptionRepresentation();
    parameterDefinitionRepresentation.setOptionId(TestContants.STRING_NUM_VALUE);
    list.add(parameterDefinitionRepresentation);
    representation.setParameterOptions(list);
    representation.setParameterType(ParameterType.PLATFORM_PARAMETER);
    representation.setValueType(ParameterValueType.STRING);
    representation.setETag(TestContants.LONG_VALUE);

    representation.update();
    VOParameterDefinition result = representation.getVO();

    assertThat(result).isNotNull();
    assertThat(result)
        .extracting(VOParameterDefinition::isConfigurable)
        .isEqualTo(representation.isConfigurable());
    assertThat(result)
        .extracting(VOParameterDefinition::getDefaultValue)
        .isEqualTo(representation.getDefaultValue());
    assertThat(result)
        .extracting(VOParameterDefinition::getDescription)
        .isEqualTo(representation.getDescription());
    assertThat(result).extracting(BaseVO::getKey).isEqualTo(representation.convertIdToKey());
    assertThat(result)
        .extracting(VOParameterDefinition::isMandatory)
        .isEqualTo(representation.isMandatory());
    assertThat(result)
        .extracting(VOParameterDefinition::getMaxValue)
        .isEqualTo(representation.getMaxValue());
    assertThat(result)
        .extracting(VOParameterDefinition::getMinValue)
        .isEqualTo(representation.getMinValue());
    assertThat(result)
        .extracting(VOParameterDefinition::getModificationType)
        .isEqualTo(representation.getModificationType());
    assertThat(result)
        .extracting(VOParameterDefinition::getParameterId)
        .isEqualTo(representation.getParameterId());
    assertThat(
            ((ParameterOptionRepresentation) representation.getParameterOptions().toArray()[0])
                .getOptionId())
        .isEqualTo(parameterDefinitionRepresentation.getOptionId());
    assertThat(result)
        .extracting(VOParameterDefinition::getParameterType)
        .isEqualTo(representation.getParameterType());
    assertThat(result)
        .extracting(VOParameterDefinition::getValueType)
        .isEqualTo(representation.getValueType());
    assertThat(result)
        .extracting(VOParameterDefinition::getVersion)
        .isEqualTo(representation.convertETagToVersion());
  }

  @Test
  public void shouldUpdateVOParameterDefinition_evenIfIdAndETagIsNull() {
    ParameterDefinitionRepresentation representation = new ParameterDefinitionRepresentation();
    representation.setConfigurable(true);
    representation.setDefaultValue(TestContants.STRING_VALUE);
    representation.setDescription(TestContants.STRING_VALUE);
    representation.setMandatory(true);
    representation.setMaxValue(TestContants.LONG_VALUE);
    representation.setMinValue(TestContants.LONG_VALUE);
    representation.setModificationType(ParameterModificationType.ONE_TIME);
    representation.setParameterId(TestContants.STRING_NUM_VALUE);
    List<ParameterOptionRepresentation> list = new ArrayList<>();
    ParameterOptionRepresentation parameterDefinitionRepresentation =
        new ParameterOptionRepresentation();
    parameterDefinitionRepresentation.setOptionId(TestContants.STRING_NUM_VALUE);
    list.add(parameterDefinitionRepresentation);
    representation.setParameterOptions(list);
    representation.setParameterType(ParameterType.PLATFORM_PARAMETER);
    representation.setValueType(ParameterValueType.STRING);

    representation.update();
    VOParameterDefinition result = representation.getVO();

    assertThat(result).isNotNull();
    assertThat(result)
        .extracting(VOParameterDefinition::isConfigurable)
        .isEqualTo(representation.isConfigurable());
    assertThat(result)
        .extracting(VOParameterDefinition::getDefaultValue)
        .isEqualTo(representation.getDefaultValue());
    assertThat(result)
        .extracting(VOParameterDefinition::getDescription)
        .isEqualTo(representation.getDescription());
    assertThat(result).extracting(BaseVO::getKey).isEqualTo(representation.convertIdToKey());
    assertThat(result)
        .extracting(VOParameterDefinition::isMandatory)
        .isEqualTo(representation.isMandatory());
    assertThat(result)
        .extracting(VOParameterDefinition::getMaxValue)
        .isEqualTo(representation.getMaxValue());
    assertThat(result)
        .extracting(VOParameterDefinition::getMinValue)
        .isEqualTo(representation.getMinValue());
    assertThat(result)
        .extracting(VOParameterDefinition::getModificationType)
        .isEqualTo(representation.getModificationType());
    assertThat(result)
        .extracting(VOParameterDefinition::getParameterId)
        .isEqualTo(representation.getParameterId());
    assertThat(
            ((ParameterOptionRepresentation) representation.getParameterOptions().toArray()[0])
                .getOptionId())
        .isEqualTo(parameterDefinitionRepresentation.getOptionId());
    assertThat(result)
        .extracting(VOParameterDefinition::getParameterType)
        .isEqualTo(representation.getParameterType());
    assertThat(result)
        .extracting(VOParameterDefinition::getValueType)
        .isEqualTo(representation.getValueType());
    assertThat(result)
        .extracting(VOParameterDefinition::getVersion)
        .isEqualTo(representation.convertETagToVersion());
  }

  @Test
  public void shouldConvertToParameterDefinitionRepresentation() {
    VOParameterDefinition voParameterDefinition = createVO();

    ParameterDefinitionRepresentation representation =
        new ParameterDefinitionRepresentation(voParameterDefinition);
    representation.convert();

    assertThat(representation)
        .extracting(ParameterDefinitionRepresentation::isConfigurable)
        .isEqualTo(voParameterDefinition.isConfigurable());
    assertThat(representation)
        .extracting(ParameterDefinitionRepresentation::getDefaultValue)
        .isEqualTo(voParameterDefinition.getDefaultValue());
    assertThat(representation)
        .extracting(ParameterDefinitionRepresentation::getDescription)
        .isEqualTo(voParameterDefinition.getDescription());
    assertThat(representation)
        .extracting(Representation::getId)
        .isEqualTo(voParameterDefinition.getKey());
    assertThat(representation)
        .extracting(ParameterDefinitionRepresentation::isMandatory)
        .isEqualTo(voParameterDefinition.isMandatory());
    assertThat(representation)
        .extracting(ParameterDefinitionRepresentation::getMaxValue)
        .isEqualTo(voParameterDefinition.getMaxValue());
    assertThat(representation)
        .extracting(ParameterDefinitionRepresentation::getMinValue)
        .isEqualTo(voParameterDefinition.getMinValue());
    assertThat(representation)
        .extracting(ParameterDefinitionRepresentation::getModificationType)
        .isEqualTo(voParameterDefinition.getModificationType());
    assertThat(representation)
        .extracting(ParameterDefinitionRepresentation::getParameterId)
        .isEqualTo(voParameterDefinition.getParameterId());
    assertThat(representation)
        .extracting(ParameterDefinitionRepresentation::getParameterType)
        .isEqualTo(voParameterDefinition.getParameterType());
    assertThat(representation)
        .extracting(ParameterDefinitionRepresentation::convertETagToVersion)
        .isEqualTo(voParameterDefinition.getVersion());
    assertThat(representation)
        .extracting(ParameterDefinitionRepresentation::getValueType)
        .isEqualTo(voParameterDefinition.getValueType());
  }

  private VOParameterDefinition createVO() {
    VOParameterDefinition voParameterDefinition = new VOParameterDefinition();
    voParameterDefinition.setConfigurable(true);
    voParameterDefinition.setDefaultValue(TestContants.STRING_VALUE);
    voParameterDefinition.setDescription(TestContants.STRING_VALUE);
    voParameterDefinition.setKey(TestContants.LONG_VALUE);
    voParameterDefinition.setMandatory(true);
    voParameterDefinition.setMaxValue(TestContants.LONG_VALUE);
    voParameterDefinition.setMinValue(TestContants.LONG_VALUE);
    voParameterDefinition.setModificationType(ParameterModificationType.ONE_TIME);
    voParameterDefinition.setParameterId(TestContants.STRING_NUM_VALUE);
    List<VOParameterOption> list = new ArrayList<>();
    VOParameterOption voParameterOption = new VOParameterOption();
    voParameterOption.setOptionId(TestContants.STRING_NUM_VALUE);
    list.add(voParameterOption);
    voParameterDefinition.setParameterOptions(list);
    voParameterDefinition.setParameterType(ParameterType.PLATFORM_PARAMETER);
    voParameterDefinition.setVersion(TestContants.INTEGER_VALUE);
    voParameterDefinition.setValueType(ParameterValueType.STRING);
    return voParameterDefinition;
  }
}
