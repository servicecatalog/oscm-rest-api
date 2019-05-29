package org.oscm.rest.subscription.data;

import org.junit.jupiter.api.Test;
import org.oscm.internal.vo.BaseVO;
import org.oscm.internal.vo.VOUda;
import org.oscm.internal.vo.VOUdaDefinition;
import org.oscm.rest.common.Representation;

import static org.assertj.core.api.Assertions.assertThat;

class UdaRepresentationTest {

    @Test
    public void shouldUpdateVOUda() {
        UdaRepresentation udaRepresentation = createRepresentation();
        udaRepresentation.setId(100L);
        udaRepresentation.setETag(100L);

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
        voUda.setTargetObjectKey(200L);
        voUda.setUdaValue("Uda100");
        VOUdaDefinition voUdaDefinition = new VOUdaDefinition();
        voUdaDefinition.setKey(100L);
        voUdaDefinition.setDefaultValue("Default");
        voUda.setUdaDefinition(voUdaDefinition);
        voUda.setKey(100);
        voUda.setVersion(100);

        return voUda;
    }

    private UdaRepresentation createRepresentation() {
        UdaRepresentation udaRepresentation = new UdaRepresentation();
        udaRepresentation.setTargetObjectKey(200L);
        udaRepresentation.setUdaValue("Uda100");
        UdaDefinitionRepresentation udaDefinitionRepresentation = new UdaDefinitionRepresentation();
        udaDefinitionRepresentation.setId(100L);
        udaDefinitionRepresentation.setDefaultValue("Default");
        udaRepresentation.setUdaDefinition(udaDefinitionRepresentation);

        return udaRepresentation;
    }

}