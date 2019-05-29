package org.oscm.rest.subscription.data;

import org.junit.jupiter.api.Test;
import org.oscm.internal.vo.BaseVO;
import org.oscm.internal.vo.VOParameterDefinition;
import org.oscm.rest.common.Representation;

import static org.assertj.core.api.Assertions.assertThat;

public class ParameterDefinitionRepresentationTest {

    @Test
    public void shouldUpdateVOParameterDefinition() {
        ParameterDefinitionRepresentation parameterDefinitionRepresentation = createRepresentation();
        parameterDefinitionRepresentation.setETag(100L);
        parameterDefinitionRepresentation.setId(100L);

        parameterDefinitionRepresentation.update();
        VOParameterDefinition result = parameterDefinitionRepresentation.getVO();

        assertThat(result).isNotNull();
        assertThat(result).extracting(BaseVO::getKey).isEqualTo(result.getKey());
        assertThat(result).extracting(BaseVO::getVersion).isEqualTo(result.getVersion());
    }

    @Test
    public void shouldUpdateVOBillingContact_evenIfIdandETagIsNull() {
        ParameterDefinitionRepresentation parameterDefinitionRepresentation = createRepresentation();

        parameterDefinitionRepresentation.update();
        VOParameterDefinition result = parameterDefinitionRepresentation.getVO();

        assertThat(result).isNotNull();
        assertThat(result).extracting(BaseVO::getKey).isEqualTo(result.getKey());
        assertThat(result).extracting(BaseVO::getVersion).isEqualTo(result.getVersion());
    }

    @Test
    public void shouldConvertToBillingContact() {
        VOParameterDefinition voParameterDefinition = createVO();

        ParameterDefinitionRepresentation representation = new ParameterDefinitionRepresentation(voParameterDefinition);
        representation.convert();

        assertThat(representation)
                .extracting(Representation::getId)
                .isEqualTo(voParameterDefinition.getKey());
        assertThat(representation)
                .extracting(Representation::getETag)
                .isEqualTo((long) voParameterDefinition.getVersion());
    }

    private VOParameterDefinition createVO() {
        VOParameterDefinition voParameterDefinition = new VOParameterDefinition();
        voParameterDefinition.setKey(100L);
        voParameterDefinition.setVersion(100);
        return voParameterDefinition;
    }

    private ParameterDefinitionRepresentation createRepresentation() {
        ParameterDefinitionRepresentation parameterDefinitionRepresentation = new ParameterDefinitionRepresentation();
        return parameterDefinitionRepresentation;
    }
}