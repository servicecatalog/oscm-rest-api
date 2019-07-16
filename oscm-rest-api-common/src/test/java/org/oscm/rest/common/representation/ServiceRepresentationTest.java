package org.oscm.rest.common.representation;

import org.junit.jupiter.api.Test;
import org.oscm.internal.vo.BaseVO;
import org.oscm.internal.vo.VOService;
import org.oscm.rest.common.TestContants;

import static org.assertj.core.api.Assertions.assertThat;

public class ServiceRepresentationTest {

    @Test
    public void shouldUpdateVOService() {
        ServiceRepresentation serviceRepresentation = createRepresentation();
        serviceRepresentation.setETag(TestContants.LONG_VALUE);
        serviceRepresentation.setId(TestContants.LONG_VALUE);

        serviceRepresentation.update();
        VOService result = serviceRepresentation.getVO();

        assertThat(result).isNotNull();
        assertThat(result).extracting(BaseVO::getKey).isEqualTo(result.getKey());
        assertThat(result).extracting(BaseVO::getVersion).isEqualTo(result.getVersion());
    }

    @Test
    public void shouldUpdateVOService_evenIfIdAndETagIsNull() {
        ServiceRepresentation serviceRepresentation = createRepresentation();

        serviceRepresentation.update();
        VOService result = serviceRepresentation.getVO();

        assertThat(result).isNotNull();
        assertThat(result).extracting(BaseVO::getKey).isEqualTo(result.getKey());
        assertThat(result).extracting(BaseVO::getVersion).isEqualTo(result.getVersion());
    }

    @Test
    public void shouldConvertToServiceRepresentation() {
        VOService voService = createVO();

        ServiceRepresentation representation = new ServiceRepresentation(voService);
        representation.convert();

        assertThat(representation)
                .extracting(ServiceRepresentation::getServiceId)
                .isEqualTo(voService.getServiceId());
        assertThat(representation)
                .extracting(Representation::getETag)
                .isEqualTo((long) voService.getVersion());
    }

    private VOService createVO() {
        VOService voService = new VOService();
        voService.setServiceId(TestContants.STRING_VALUE);
        voService.setVersion(TestContants.INTEGER_VALUE);
        return voService;
    }

    private ServiceRepresentation createRepresentation() {
        ServiceRepresentation serviceRepresentation = new ServiceRepresentation();
        return serviceRepresentation;
    }
}