package org.oscm.rest.subscription.data;

import org.junit.jupiter.api.Test;
import org.oscm.internal.vo.BaseVO;
import org.oscm.internal.vo.VOBillingContact;
import org.oscm.rest.common.Representation;

import static org.assertj.core.api.Assertions.assertThat;

class BillingContactRepresentationTest {

    @Test
    public void shouldUpdateVOBillingContact() {
        BillingContactRepresentation billingContactRepresentation = createRepresentation();
        billingContactRepresentation.setETag(100L);
        billingContactRepresentation.setId(100L);

        billingContactRepresentation.update();
        VOBillingContact result = billingContactRepresentation.getVO();

        assertThat(result).isNotNull();
        assertThat(result).extracting(BaseVO::getKey).isEqualTo(billingContactRepresentation.convertIdToKey());
        assertThat(result).extracting(BaseVO::getVersion).isEqualTo(billingContactRepresentation.convertETagToVersion());
    }

    @Test
    public void shouldUpdateVOBillingContact_evenIfIdAndETagIsNull() {
        BillingContactRepresentation billingContactRepresentation = createRepresentation();

        billingContactRepresentation.update();
        VOBillingContact result = billingContactRepresentation.getVO();

        assertThat(result).isNotNull();
        assertThat(result).extracting(BaseVO::getKey).isEqualTo(billingContactRepresentation.convertIdToKey());
        assertThat(result).extracting(BaseVO::getVersion).isEqualTo(billingContactRepresentation.getVersion());
    }

    @Test
    public void shouldConvertToBillingContact() {
        VOBillingContact voBillingContact = createVO();

        BillingContactRepresentation representation = new BillingContactRepresentation(voBillingContact);
        representation.convert();

        assertThat(representation)
                .extracting(Representation::getId)
                .isEqualTo(voBillingContact.getKey());
        assertThat(representation)
                .extracting(Representation::getETag)
                .isEqualTo((long) voBillingContact.getVersion());
    }

    private VOBillingContact createVO() {
        VOBillingContact voBillingContact = new VOBillingContact();
        voBillingContact.setId("100");
        voBillingContact.setVersion(100);
        return voBillingContact;
    }

    private BillingContactRepresentation createRepresentation() {
        BillingContactRepresentation billingContactRepresentation = new BillingContactRepresentation();
        return billingContactRepresentation;
    }

}