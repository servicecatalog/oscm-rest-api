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
import org.oscm.internal.vo.BaseVO;
import org.oscm.internal.vo.VOParameter;
import org.oscm.internal.vo.VOParameterDefinition;
import org.oscm.rest.common.representation.ParameterDefinitionRepresentation;
import org.oscm.rest.common.representation.ParameterRepresentation;
import org.oscm.rest.common.representation.Representation;

import static org.assertj.core.api.Assertions.assertThat;

public class ParameterRepresentationTest {

  @Test
  public void shouldUpdateVOParameter() {
    ParameterRepresentation representation = createRepresentation();
    representation.setId(100L);
    representation.setETag(100L);

    representation.update();
    VOParameter result = representation.getVO();

    assertThat(result).isNotNull();
    assertThat(result)
        .extracting(VOParameter::isConfigurable)
        .isEqualTo(representation.isConfigurable());
    assertThat(result).extracting(BaseVO::getKey).isEqualTo(representation.convertIdToKey());
    assertThat(result)
        .extracting(VOParameter::getParameterDefinition)
        .isEqualTo(representation.getParameterDefinition().getVO());
    assertThat(result).extracting(VOParameter::getValue).isEqualTo(representation.getValue());
    assertThat(result)
        .extracting(VOParameter::getVersion)
        .isEqualTo(representation.convertETagToVersion());
  }

  @Test
  public void shouldUpdateVOParameter_evenIfIdAndETagIsNull() {
    ParameterRepresentation representation = createRepresentation();

    representation.update();
    VOParameter result = representation.getVO();

    assertThat(result).isNotNull();
    assertThat(result)
        .extracting(VOParameter::isConfigurable)
        .isEqualTo(representation.isConfigurable());
    assertThat(result).extracting(BaseVO::getKey).isEqualTo(representation.convertIdToKey());
    assertThat(result)
        .extracting(VOParameter::getParameterDefinition)
        .isEqualTo(representation.getParameterDefinition().getVO());
    assertThat(result).extracting(VOParameter::getValue).isEqualTo(representation.getValue());
    assertThat(result)
        .extracting(VOParameter::getVersion)
        .isEqualTo(representation.convertETagToVersion());
  }

  @Test
  public void shouldConvertToParameterRepresentation() {
    VOParameter voParameter = createVO();

    ParameterRepresentation representation = new ParameterRepresentation(voParameter);
    representation.convert();

    assertThat(representation)
        .extracting(ParameterRepresentation::isConfigurable)
        .isEqualTo(voParameter.isConfigurable());
    assertThat(representation).extracting(Representation::getId).isEqualTo(voParameter.getKey());
    assertThat(representation.getParameterDefinition().getParameterId())
        .isEqualTo(voParameter.getParameterDefinition().getParameterId());
    assertThat(representation)
        .extracting(ParameterRepresentation::convertETagToVersion)
        .isEqualTo(voParameter.getVersion());
    assertThat(representation)
        .extracting(ParameterRepresentation::getValue)
        .isEqualTo(voParameter.getValue());
  }

  private ParameterRepresentation createRepresentation() {
    ParameterRepresentation representation = new ParameterRepresentation();
    representation.setConfigurable(true);
    ParameterDefinitionRepresentation parameterDefinitionRepresentation =
        new ParameterDefinitionRepresentation();
    representation.setParameterDefinition(parameterDefinitionRepresentation);
    representation.setValue("Value");
    return representation;
  }

  private VOParameter createVO() {
    VOParameter voParameter = new VOParameter();
    voParameter.setConfigurable(true);
    voParameter.setKey(100L);
    VOParameterDefinition voParameterDefinition = new VOParameterDefinition();
    voParameterDefinition.setParameterId("123");
    voParameter.setParameterDefinition(voParameterDefinition);
    voParameter.setVersion(100);
    voParameter.setValue("Value");
    return voParameter;
  }
}
