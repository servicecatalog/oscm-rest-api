package org.oscm.rest.common.representation;

import org.junit.jupiter.api.Test;
import org.oscm.internal.types.enumtypes.UdaConfigurationType;
import org.oscm.internal.vo.BaseVO;
import org.oscm.internal.vo.VOUdaDefinition;

import static org.assertj.core.api.Assertions.assertThat;

public class UdaDefinitionRepresentationTest {

    @Test
    public void shouldUpdateVOUdaDefinition() {
        UdaDefinitionRepresentation udaDefinitionRepresentation = createRepresentation();
        udaDefinitionRepresentation.setId(100L);
        udaDefinitionRepresentation.setETag(100L);

        udaDefinitionRepresentation.update();
        VOUdaDefinition result = udaDefinitionRepresentation.getVO();

        assertThat(result).isNotNull();
        assertThat(result)
                .extracting(VOUdaDefinition::getConfigurationType)
                .isEqualTo(udaDefinitionRepresentation.getConfigurationType());
        assertThat(result)
                .extracting(VOUdaDefinition::getDefaultValue)
                .isEqualTo(udaDefinitionRepresentation.getDefaultValue());
        assertThat(result)
                .extracting(BaseVO::getKey)
                .isEqualTo(udaDefinitionRepresentation.convertIdToKey());
        assertThat(result)
                .extracting(BaseVO::getVersion)
                .isEqualTo(udaDefinitionRepresentation.convertETagToVersion());
        assertThat(result)
                .extracting(VOUdaDefinition::getTargetType)
                .isEqualTo(udaDefinitionRepresentation.getTargetType());
        assertThat(result)
                .extracting(VOUdaDefinition::getUdaId)
                .isEqualTo(udaDefinitionRepresentation.getUdaId());
    }

    @Test
    public void shouldUpdateVOUdaDefinition_evenIfIdAndETagIsNull() {
        UdaDefinitionRepresentation udaDefinitionRepresentation = createRepresentation();

        udaDefinitionRepresentation.update();
        VOUdaDefinition result = udaDefinitionRepresentation.getVO();

        assertThat(result).isNotNull();
        assertThat(result)
                .extracting(VOUdaDefinition::getConfigurationType)
                .isEqualTo(udaDefinitionRepresentation.getConfigurationType());
        assertThat(result)
                .extracting(VOUdaDefinition::getDefaultValue)
                .isEqualTo(udaDefinitionRepresentation.getDefaultValue());
        assertThat(result)
                .extracting(BaseVO::getKey)
                .isEqualTo(udaDefinitionRepresentation.convertIdToKey());
        assertThat(result)
                .extracting(BaseVO::getVersion)
                .isEqualTo(udaDefinitionRepresentation.convertETagToVersion());
        assertThat(result)
                .extracting(VOUdaDefinition::getTargetType)
                .isEqualTo(udaDefinitionRepresentation.getTargetType());
        assertThat(result)
                .extracting(VOUdaDefinition::getUdaId)
                .isEqualTo(udaDefinitionRepresentation.getUdaId());
    }

    @Test
    public void shouldConvertToUdaDefinitionRepresentation() {
        VOUdaDefinition voUdaDefinition = createVO();

        UdaDefinitionRepresentation representation = new UdaDefinitionRepresentation(voUdaDefinition);
        representation.convert();

        assertThat(representation)
                .extracting(UdaDefinitionRepresentation::getConfigurationType)
                .isEqualTo(voUdaDefinition.getConfigurationType());
        assertThat(representation)
                .extracting(UdaDefinitionRepresentation::getDefaultValue)
                .isEqualTo(voUdaDefinition.getDefaultValue());
        assertThat(representation)
                .extracting(UdaDefinitionRepresentation::getTargetType)
                .isEqualTo(voUdaDefinition.getTargetType());
        assertThat(representation)
                .extracting(UdaDefinitionRepresentation::getUdaId)
                .isEqualTo(voUdaDefinition.getUdaId());
        assertThat(representation)
                .extracting(Representation::convertETagToVersion)
                .isEqualTo(voUdaDefinition.getVersion());
        assertThat(representation)
                .extracting(Representation::convertIdToKey)
                .isEqualTo(voUdaDefinition.getKey());
    }

    private VOUdaDefinition createVO() {
        VOUdaDefinition voUdaDefinition = new VOUdaDefinition();
        voUdaDefinition.setConfigurationType(UdaConfigurationType.SUPPLIER);
        voUdaDefinition.setDefaultValue("Default");
        voUdaDefinition.setTargetType("Target100");
        voUdaDefinition.setUdaId("Uda100");
        voUdaDefinition.setVersion(100);
        voUdaDefinition.setKey(100L);

        return voUdaDefinition;
    }

    private UdaDefinitionRepresentation createRepresentation() {
        UdaDefinitionRepresentation udaDefinitionRepresentation = new UdaDefinitionRepresentation();
        udaDefinitionRepresentation.setConfigurationType(UdaConfigurationType.SUPPLIER);
        udaDefinitionRepresentation.setDefaultValue("Default");
        udaDefinitionRepresentation.setTargetType("Target100");
        udaDefinitionRepresentation.setUdaId("Uda100");

        return udaDefinitionRepresentation;
    }

}