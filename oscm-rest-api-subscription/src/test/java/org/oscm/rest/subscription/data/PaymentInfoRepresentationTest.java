package org.oscm.rest.subscription.data;

import org.junit.jupiter.api.Test;
import org.oscm.internal.vo.BaseVO;
import org.oscm.internal.vo.VOPaymentInfo;
import org.oscm.internal.vo.VOPaymentType;
import org.oscm.rest.common.representation.PaymentInfoRepresentation;
import org.oscm.rest.common.representation.Representation;

import static org.assertj.core.api.Assertions.assertThat;

public class PaymentInfoRepresentationTest {

    @Test
    public void shouldUpdateVOPaymentInfo() {
        PaymentInfoRepresentation representation = createRepresentation();
        representation.setId(100L);
        representation.setETag(100L);

        representation.update();
        VOPaymentInfo result = representation.getVO();

        assertThat(result).isNotNull();
        assertThat(result).extracting(BaseVO::getKey).isEqualTo(representation.convertIdToKey());
        assertThat(result)
                .extracting(BaseVO::getVersion)
                .isEqualTo(representation.convertETagToVersion());
    }

    @Test
    public void shouldUpdateVOPaymentInfo_evenIfIdAndETagIsNull() {
        PaymentInfoRepresentation representation = createRepresentation();

        representation.update();
        VOPaymentInfo result = representation.getVO();

        assertThat(result).isNotNull();
        assertThat(result).extracting(BaseVO::getKey).isEqualTo(representation.convertIdToKey());
        assertThat(result)
                .extracting(BaseVO::getVersion)
                .isEqualTo(representation.convertETagToVersion());
    }

    @Test
    public void shouldConvertToPaymentInfoRepresentation() {
        VOPaymentInfo voPaymentInfo = createVO();

        PaymentInfoRepresentation representation = new PaymentInfoRepresentation(voPaymentInfo);
        representation.convert();

        assertThat(representation)
                .extracting(Representation::convertETagToVersion)
                .isEqualTo(voPaymentInfo.getVersion());
        assertThat(representation)
                .extracting(Representation::convertIdToKey)
                .isEqualTo(voPaymentInfo.getKey());
    }

    private VOPaymentInfo createVO() {
        VOPaymentInfo voPaymentInfo = new VOPaymentInfo();
        voPaymentInfo.setKey(100L);
        voPaymentInfo.setVersion(100);
        voPaymentInfo.setPaymentType(new VOPaymentType());

        return voPaymentInfo;
    }

    private PaymentInfoRepresentation createRepresentation() {
        PaymentInfoRepresentation paymentInfoRepresentation = new PaymentInfoRepresentation();

        return paymentInfoRepresentation;
    }
}