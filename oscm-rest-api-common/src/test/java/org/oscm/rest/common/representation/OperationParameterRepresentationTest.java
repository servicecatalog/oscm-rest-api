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
import org.oscm.internal.types.enumtypes.OperationParameterType;
import org.oscm.internal.vo.BaseVO;
import org.oscm.internal.vo.VOServiceOperationParameter;

import static org.assertj.core.api.Assertions.assertThat;

public class OperationParameterRepresentationTest {

  @Test
  public void shouldUpdateVOServiceOperationParameter() {
    OperationParameterRepresentation representation = createRepresentation();
    representation.setId(100L);
    representation.setETag(100L);

    representation.update();
    VOServiceOperationParameter result = representation.getVO();

    assertThat(result).isNotNull();
    assertThat(result).extracting(BaseVO::getKey).isEqualTo(representation.convertIdToKey());
    assertThat(result)
        .extracting(VOServiceOperationParameter::isMandatory)
        .isEqualTo(representation.isMandatory());
    assertThat(result)
        .extracting(VOServiceOperationParameter::getParameterId)
        .isEqualTo(representation.getParameterId());
    assertThat(result)
        .extracting(VOServiceOperationParameter::getParameterName)
        .isEqualTo(representation.getParameterName());
    assertThat(result)
        .extracting(VOServiceOperationParameter::getParameterValue)
        .isEqualTo(representation.getParameterValue());
    assertThat(result)
        .extracting(VOServiceOperationParameter::getType)
        .isEqualTo(representation.getType());
    assertThat(result)
        .extracting(BaseVO::getVersion)
        .isEqualTo(representation.convertETagToVersion());
  }

  @Test
  public void shouldUpdateVOServiceOperationParameter_evenIfIdAndETagIsNull() {
    OperationParameterRepresentation representation = createRepresentation();

    representation.update();
    VOServiceOperationParameter result = representation.getVO();

    assertThat(result).isNotNull();
    assertThat(result).extracting(BaseVO::getKey).isEqualTo(representation.convertIdToKey());
    assertThat(result)
        .extracting(VOServiceOperationParameter::isMandatory)
        .isEqualTo(representation.isMandatory());
    assertThat(result)
        .extracting(VOServiceOperationParameter::getParameterId)
        .isEqualTo(representation.getParameterId());
    assertThat(result)
        .extracting(VOServiceOperationParameter::getParameterName)
        .isEqualTo(representation.getParameterName());
    assertThat(result)
        .extracting(VOServiceOperationParameter::getParameterValue)
        .isEqualTo(representation.getParameterValue());
    assertThat(result)
        .extracting(VOServiceOperationParameter::getType)
        .isEqualTo(representation.getType());
    assertThat(result)
        .extracting(BaseVO::getVersion)
        .isEqualTo(representation.convertETagToVersion());
  }

  @Test
  public void shouldConvertToOperationParameterRepresentation() {
    VOServiceOperationParameter voServiceOperationParameter = new VOServiceOperationParameter();
    voServiceOperationParameter.setKey(100L);
    voServiceOperationParameter.setParameterId("100");
    voServiceOperationParameter.setParameterName("ParameterName");
    voServiceOperationParameter.setParameterValue("Value");
    voServiceOperationParameter.setVersion(100);
    voServiceOperationParameter.setType(OperationParameterType.INPUT_STRING);

    OperationParameterRepresentation representation =
        new OperationParameterRepresentation(voServiceOperationParameter);
    representation.convert();

    assertThat(representation)
        .extracting(Representation::getId)
        .isEqualTo(voServiceOperationParameter.getKey());
    assertThat(representation)
        .extracting(OperationParameterRepresentation::isMandatory)
        .isEqualTo(voServiceOperationParameter.isMandatory());
    assertThat(representation)
        .extracting(OperationParameterRepresentation::getParameterId)
        .isEqualTo(voServiceOperationParameter.getParameterId());
    assertThat(representation)
        .extracting(OperationParameterRepresentation::getParameterName)
        .isEqualTo(voServiceOperationParameter.getParameterName());
    assertThat(representation)
        .extracting(OperationParameterRepresentation::getParameterValue)
        .isEqualTo(voServiceOperationParameter.getParameterValue());
    assertThat(representation)
        .extracting(OperationParameterRepresentation::convertETagToVersion)
        .isEqualTo(voServiceOperationParameter.getVersion());
    assertThat(representation)
        .extracting(OperationParameterRepresentation::getType)
        .isEqualTo(voServiceOperationParameter.getType());
  }

  private OperationParameterRepresentation createRepresentation() {
    OperationParameterRepresentation representation = new OperationParameterRepresentation();
    representation.setMandatory(true);
    representation.setParameterId("100");
    representation.setParameterName("ParameterName");
    representation.setParameterValue("Value");
    representation.setType(OperationParameterType.INPUT_STRING);
    return representation;
  }
}
