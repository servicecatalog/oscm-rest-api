package org.oscm.rest.subscription.data;

import org.junit.jupiter.api.Test;
import org.oscm.internal.vo.BaseVO;
import org.oscm.internal.vo.VOService;
import org.oscm.rest.common.representation.Representation;

import static org.assertj.core.api.Assertions.assertThat;

public class ServiceRepresentationTest {

    @Test
    public void shouldUpdateVOService() {
        ServiceRepresentation serviceRepresentation = createRepresentation();
        serviceRepresentation.setETag(100L);
        serviceRepresentation.setId(100L);

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
        voService.setServiceId("100ID");
        voService.setVersion(100);
        return voService;
    }

    private ServiceRepresentation createRepresentation() {
        ServiceRepresentation serviceRepresentation = new ServiceRepresentation();
        return serviceRepresentation;
    }

}