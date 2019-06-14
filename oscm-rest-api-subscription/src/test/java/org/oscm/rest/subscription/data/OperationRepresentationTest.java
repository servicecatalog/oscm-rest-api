package org.oscm.rest.subscription.data;

import org.junit.jupiter.api.Test;
import org.oscm.internal.vo.BaseVO;
import org.oscm.internal.vo.VOServiceOperationParameter;
import org.oscm.internal.vo.VOTechnicalServiceOperation;
import org.oscm.rest.common.Representation;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class OperationRepresentationTest {

    @Test
    public void shouldUpdateVOTechnicalServiceOperation() {
        OperationRepresentation representation = createRepresentation();
        representation.setId(100L);
        representation.setETag(100L);

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
        representation.setOperationDescription("Description");
        representation.setOperationId("100");
        representation.setOperationName("Name");
        List<OperationParameterRepresentation> list = new ArrayList<>();
        OperationParameterRepresentation operationParameterRepresentation =
                new OperationParameterRepresentation();
        operationParameterRepresentation.setParameterName("abc123");
        list.add(operationParameterRepresentation);
        representation.setOperationParameters(list);
        return representation;
    }

    private VOTechnicalServiceOperation createVO() {
        VOTechnicalServiceOperation voTechnicalServiceOperation = new VOTechnicalServiceOperation();
        voTechnicalServiceOperation.setKey(100L);
        voTechnicalServiceOperation.setOperationDescription("Description");
        voTechnicalServiceOperation.setOperationId("100");
        voTechnicalServiceOperation.setOperationName("Name");
        List<VOServiceOperationParameter> list = new ArrayList<>();
        VOServiceOperationParameter voServiceOperationParameter = new VOServiceOperationParameter();
        voServiceOperationParameter.setParameterName("abc123");
        list.add(voServiceOperationParameter);
        voTechnicalServiceOperation.setOperationParameters(list);
        voServiceOperationParameter.setVersion(100);
        return voTechnicalServiceOperation;
    }
}