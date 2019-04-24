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

import org.junit.jupiter.api.Test;
import org.oscm.internal.types.enumtypes.ParameterModificationType;
import org.oscm.internal.types.enumtypes.ParameterType;
import org.oscm.internal.types.enumtypes.ParameterValueType;
import org.oscm.internal.vo.BaseVO;
import org.oscm.internal.vo.VOParameterDefinition;
import org.oscm.internal.vo.VOParameterOption;
import org.oscm.rest.common.Representation;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ParameterDefinitionRepresentationTest {

    @Test
    public void shouldUpdateVOParameterDefinition() {
        ParameterDefinitionRepresentation representation = new ParameterDefinitionRepresentation();
        representation.setConfigurable(true);
        representation.setDefaultValue("DefaultValue");
        representation.setDescription("Description");
        representation.setId(100L);
        representation.setMandatory(true);
        representation.setMaxValue(100L);
        representation.setMinValue(0L);
        representation.setModificationType(ParameterModificationType.ONE_TIME);
        representation.setParameterId("100");
        List<ParameterOptionRepresentation> list = new ArrayList<>();
        ParameterOptionRepresentation parameterDefinitionRepresentation
                = new ParameterOptionRepresentation();
        parameterDefinitionRepresentation.setOptionId("123");
        list.add(parameterDefinitionRepresentation);
        representation.setParameterOptions(list);
        representation.setParameterType(ParameterType.PLATFORM_PARAMETER);
        representation.setValueType(ParameterValueType.STRING);
        representation.setETag(100L);

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
        assertThat(result)
                .extracting(BaseVO::getKey)
                .isEqualTo(representation.convertIdToKey());
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
                ((ParameterOptionRepresentation)
                        representation.getParameterOptions().toArray()[0])
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
        representation.setDefaultValue("DefaultValue");
        representation.setDescription("Description");
        representation.setMandatory(true);
        representation.setMaxValue(100L);
        representation.setMinValue(0L);
        representation.setModificationType(ParameterModificationType.ONE_TIME);
        representation.setParameterId("100");
        List<ParameterOptionRepresentation> list = new ArrayList<>();
        ParameterOptionRepresentation parameterDefinitionRepresentation
                = new ParameterOptionRepresentation();
        parameterDefinitionRepresentation.setOptionId("123");
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
        assertThat(result)
                .extracting(BaseVO::getKey)
                .isEqualTo(representation.convertIdToKey());
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
                ((ParameterOptionRepresentation)
                        representation.getParameterOptions().toArray()[0])
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
        VOParameterDefinition voParameterDefinition = new VOParameterDefinition();
        voParameterDefinition.setConfigurable(true);
        voParameterDefinition.setDefaultValue("DefaultValue");
        voParameterDefinition.setDescription("Description");
        voParameterDefinition.setKey(100L);
        voParameterDefinition.setMandatory(true);
        voParameterDefinition.setMaxValue(100L);
        voParameterDefinition.setMinValue(0L);
        voParameterDefinition.setModificationType(ParameterModificationType.ONE_TIME);
        voParameterDefinition.setParameterId("100");
        List<VOParameterOption> list = new ArrayList<>();
        VOParameterOption voParameterOption
                = new VOParameterOption();
        voParameterOption.setOptionId("123");
        list.add(voParameterOption);
        voParameterDefinition.setParameterOptions(list);
        voParameterDefinition.setParameterType(ParameterType.PLATFORM_PARAMETER);
        voParameterDefinition.setVersion(100);
        voParameterDefinition.setValueType(ParameterValueType.STRING);

        ParameterDefinitionRepresentation representation
                = new ParameterDefinitionRepresentation(voParameterDefinition);
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
        assertThat(
                ((ParameterOptionRepresentation)representation.getParameterOptions().toArray()[0])
                .getOptionId())
                .isEqualTo(voParameterOption.getOptionId());
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

}
