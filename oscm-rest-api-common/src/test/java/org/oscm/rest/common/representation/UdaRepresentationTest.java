package org.oscm.rest.common.representation;

import org.junit.jupiter.api.Test;
import org.oscm.internal.vo.BaseVO;
import org.oscm.internal.vo.VOUda;
import org.oscm.internal.vo.VOUdaDefinition;
import org.oscm.rest.common.TestContants;

import static org.assertj.core.api.Assertions.assertThat;

public class UdaRepresentationTest {

    @Test
    public void shouldUpdateVOUda() {
        UdaRepresentation udaRepresentation = createRepresentation();
    udaRepresentation.setId(TestContants.LONG_VALUE);
        udaRepresentation.setETag(TestContants.LONG_VALUE);

        udaRepresentation.update();
        VOUda result = udaRepresentation.getVO();

        assertThat(result).isNotNull();
        assertThat(result)
                .extracting(BaseVO::getKey)
                .isEqualTo(udaRepresentation.convertIdToKey());
        assertThat(result)
                .extracting(BaseVO::getVersion)
                .isEqualTo(udaRepresentation.convertETagToVersion());
        assertThat(result)
                .extracting(VOUda::getTargetObjectKey)
                .isEqualTo(udaRepresentation.getTargetObjectKey());
        assertThat(result)
                .extracting(VOUda::getUdaValue)
                .isEqualTo(udaRepresentation.getUdaValue());
        assertThat(result.getUdaDefinition().getDefaultValue())
                .isEqualTo(udaRepresentation.getUdaDefinition().getDefaultValue());
    }

    @Test
    public void shouldUpdateVOUda_evenIfIdAndETagIsNull() {
        UdaRepresentation udaRepresentation = createRepresentation();

        udaRepresentation.update();
        VOUda result = udaRepresentation.getVO();

        assertThat(result).isNotNull();
        assertThat(result)
                .extracting(BaseVO::getKey)
                .isEqualTo(udaRepresentation.convertIdToKey());
        assertThat(result)
                .extracting(BaseVO::getVersion)
                .isEqualTo(udaRepresentation.convertETagToVersion());
        assertThat(result)
                .extracting(VOUda::getTargetObjectKey)
                .isEqualTo(udaRepresentation.getTargetObjectKey());
        assertThat(result)
                .extracting(VOUda::getUdaValue)
                .isEqualTo(udaRepresentation.getUdaValue());
        assertThat(result.getUdaDefinition().getDefaultValue())
                .isEqualTo(udaRepresentation.getUdaDefinition().getDefaultValue());
    }

    @Test
    public void shouldConvertToUdaRepresentation() {
        VOUda voUda = createVO();

        UdaRepresentation representation = new UdaRepresentation(voUda);
        representation.convert();

        assertThat(representation)
                .extracting(UdaRepresentation::getTargetObjectKey)
                .isEqualTo(voUda.getTargetObjectKey());
        assertThat(representation)
                .extracting(UdaRepresentation::getUdaValue)
                .isEqualTo(voUda.getUdaValue());
        assertThat(representation)
                .extracting(Representation::convertIdToKey)
                .isEqualTo(voUda.getKey());
        assertThat(representation)
                .extracting(Representation::convertETagToVersion)
                .isEqualTo(voUda.getVersion());
        assertThat(representation.getUdaDefinition().getDefaultValue())
                .isEqualTo(voUda.getUdaDefinition().getDefaultValue());
    }

    private VOUda createVO() {
        VOUda voUda = new VOUda();
        voUda.setTargetObjectKey(TestContants.LONG_VALUE);
        voUda.setUdaValue(TestContants.STRING_VALUE);
        VOUdaDefinition voUdaDefinition = new VOUdaDefinition();
        voUdaDefinition.setKey(TestContants.LONG_VALUE);
        voUdaDefinition.setDefaultValue(TestContants.STRING_VALUE);
        voUda.setUdaDefinition(voUdaDefinition);
        voUda.setKey(TestContants.INTEGER_VALUE);
        voUda.setVersion(TestContants.INTEGER_VALUE);

        return voUda;
    }

    private UdaRepresentation createRepresentation() {
        UdaRepresentation udaRepresentation = new UdaRepresentation();
        udaRepresentation.setTargetObjectKey(TestContants.LONG_VALUE);
        udaRepresentation.setUdaValue(TestContants.STRING_VALUE);
        UdaDefinitionRepresentation udaDefinitionRepresentation = new UdaDefinitionRepresentation();
        udaDefinitionRepresentation.setId(TestContants.LONG_VALUE);
        udaDefinitionRepresentation.setDefaultValue(TestContants.STRING_VALUE);
        udaRepresentation.setUdaDefinition(udaDefinitionRepresentation);

        return udaRepresentation;
    }
}