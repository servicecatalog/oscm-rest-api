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
import org.oscm.internal.vo.VOServiceOperationParameter;
import org.oscm.internal.vo.VOTechnicalServiceOperation;
import org.oscm.rest.common.TestContants;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class OperationRepresentationTest {

  @Test
  public void shouldUpdateVOTechnicalServiceOperation() {
    OperationRepresentation representation = createRepresentation();
    representation.setId(TestContants.LONG_VALUE);
    representation.setETag(TestContants.LONG_VALUE);

    representation.update();
    VOTechnicalServiceOperation result = representation.getVO();

    assertThat(result).isNotNull();
    assertThat(result).extracting(BaseVO::getKey).isEqualTo(representation.convertIdToKey());
    assertThat(result)
        .extracting(VOTechnicalServiceOperation::getOperationDescription)
        .isEqualTo(representation.getOperationDescription());
    assertThat(result)
        .extracting(VOTechnicalServiceOperation::getOperationId)
        .isEqualTo(representation.getOperationId());
    assertThat(result)
        .extracting(VOTechnicalServiceOperation::getOperationName)
        .isEqualTo(representation.getOperationName());
    assertThat(
            ((OperationParameterRepresentation)
                    representation.getOperationParameters().toArray()[0])
                .getParameterName())
        .isEqualTo(
            ((VOServiceOperationParameter) result.getOperationParameters().toArray()[0])
                .getParameterName());
    assertThat(result)
        .extracting(BaseVO::getVersion)
        .isEqualTo(representation.convertETagToVersion());
  }

  @Test
  public void shouldUpdateVOTechnicalServiceOperation_evenIfIdAndETagIsNull() {
    OperationRepresentation representation = createRepresentation();

    representation.update();
    VOTechnicalServiceOperation result = representation.getVO();

    assertThat(result).isNotNull();
    assertThat(result).extracting(BaseVO::getKey).isEqualTo(representation.convertIdToKey());
    assertThat(result)
        .extracting(VOTechnicalServiceOperation::getOperationDescription)
        .isEqualTo(representation.getOperationDescription());
    assertThat(result)
        .extracting(VOTechnicalServiceOperation::getOperationId)
        .isEqualTo(representation.getOperationId());
    assertThat(result)
        .extracting(VOTechnicalServiceOperation::getOperationName)
        .isEqualTo(representation.getOperationName());
    assertThat(
            ((OperationParameterRepresentation)
                    representation.getOperationParameters().toArray()[0])
                .getParameterName())
        .isEqualTo(
            ((VOServiceOperationParameter) result.getOperationParameters().toArray()[0])
                .getParameterName());
    assertThat(result)
        .extracting(BaseVO::getVersion)
        .isEqualTo(representation.convertETagToVersion());
  }

  @Test
  public void shouldConvertToOperationRepresentation() {
    VOTechnicalServiceOperation voTechnicalServiceOperation = createVO();

    OperationRepresentation representation =
        new OperationRepresentation(voTechnicalServiceOperation);
    representation.convert();

    assertThat(representation)
        .extracting(Representation::getId)
        .isEqualTo(voTechnicalServiceOperation.getKey());
    assertThat(representation)
        .extracting(OperationRepresentation::getOperationDescription)
        .isEqualTo(voTechnicalServiceOperation.getOperationDescription());
    assertThat(representation)
        .extracting(OperationRepresentation::getOperationId)
        .isEqualTo(voTechnicalServiceOperation.getOperationId());
    assertThat(representation)
        .extracting(OperationRepresentation::getOperationName)
        .isEqualTo(voTechnicalServiceOperation.getOperationName());
    assertThat(representation)
        .extracting(OperationRepresentation::convertETagToVersion)
        .isEqualTo(voTechnicalServiceOperation.getVersion());
  }

  private OperationRepresentation createRepresentation() {
    OperationRepresentation representation = new OperationRepresentation();
    representation.setOperationDescription(TestContants.STRING_VALUE);
    representation.setOperationId(TestContants.STRING_NUM_VALUE);
    representation.setOperationName(TestContants.STRING_VALUE);
    List<OperationParameterRepresentation> list = new ArrayList<>();
    OperationParameterRepresentation operationParameterRepresentation =
        new OperationParameterRepresentation();
    operationParameterRepresentation.setParameterName(TestContants.STRING_VALUE);
    list.add(operationParameterRepresentation);
    representation.setOperationParameters(list);
    return representation;
  }

  private VOTechnicalServiceOperation createVO() {
    VOTechnicalServiceOperation voTechnicalServiceOperation = new VOTechnicalServiceOperation();
    voTechnicalServiceOperation.setKey(TestContants.LONG_VALUE);
    voTechnicalServiceOperation.setOperationDescription(TestContants.STRING_VALUE);
    voTechnicalServiceOperation.setOperationId(TestContants.STRING_NUM_VALUE);
    voTechnicalServiceOperation.setOperationName(TestContants.STRING_VALUE);
    List<VOServiceOperationParameter> list = new ArrayList<>();
    VOServiceOperationParameter voServiceOperationParameter = new VOServiceOperationParameter();
    voServiceOperationParameter.setParameterName(TestContants.STRING_VALUE);
    list.add(voServiceOperationParameter);
    voTechnicalServiceOperation.setOperationParameters(list);
    voServiceOperationParameter.setVersion(TestContants.INTEGER_VALUE);
    return voTechnicalServiceOperation;
  }
}
