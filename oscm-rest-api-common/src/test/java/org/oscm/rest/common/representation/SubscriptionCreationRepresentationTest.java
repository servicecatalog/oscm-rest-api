package org.oscm.rest.common.representation;

import org.junit.jupiter.api.Test;
import org.oscm.internal.vo.BaseVO;
import org.oscm.internal.vo.VOSubscription;

import static org.assertj.core.api.Assertions.assertThat;

public class SubscriptionCreationRepresentationTest {

    @Test
    public void shouldUpdateVOSubscription() {
        SubscriptionCreationRepresentation subscriptionCreationRepresentation = createRepresentation();
        subscriptionCreationRepresentation.setETag(100L);
        subscriptionCreationRepresentation.setId(100L);

        subscriptionCreationRepresentation.update();
        VOSubscription result = subscriptionCreationRepresentation.getVO();

        assertThat(result).isNotNull();
        assertThat(result).extracting(BaseVO::getKey).isEqualTo(result.getKey());
        assertThat(result).extracting(BaseVO::getVersion).isEqualTo(result.getVersion());
        assertThat(result)
                .extracting(VOSubscription::getPurchaseOrderNumber)
                .isEqualTo(subscriptionCreationRepresentation.getPurchaseOrderNumber());
        assertThat(result)
                .extracting(VOSubscription::getSubscriptionId)
                .isEqualTo(subscriptionCreationRepresentation.getSubscriptionId());
        assertThat(result)
                .extracting(VOSubscription::getUnitName)
                .isEqualTo(subscriptionCreationRepresentation.getUnitName());
    }

    @Test
    public void shouldUpdateVOSubscription_evenIfIdandETagIsNull() {
        SubscriptionCreationRepresentation subscriptionCreationRepresentation = createRepresentation();

        subscriptionCreationRepresentation.update();
        VOSubscription result = subscriptionCreationRepresentation.getVO();

        assertThat(result).isNotNull();
        assertThat(result).extracting(BaseVO::getKey).isEqualTo(result.getKey());
        assertThat(result).extracting(BaseVO::getVersion).isEqualTo(result.getVersion());
        assertThat(result)
                .extracting(VOSubscription::getPurchaseOrderNumber)
                .isEqualTo(subscriptionCreationRepresentation.getPurchaseOrderNumber());
        assertThat(result)
                .extracting(VOSubscription::getSubscriptionId)
                .isEqualTo(subscriptionCreationRepresentation.getSubscriptionId());
        assertThat(result)
                .extracting(VOSubscription::getUnitName)
                .isEqualTo(subscriptionCreationRepresentation.getUnitName());
    }

    @Test
    public void shouldConvertToSubscriptionCreationRepresentation() {
        VOSubscription voSubscription = createVO();

        SubscriptionCreationRepresentation representation = new SubscriptionCreationRepresentation(voSubscription);
        representation.update();

        assertThat(representation)
                .extracting(SubscriptionCreationRepresentation::getPurchaseOrderNumber)
                .isEqualTo(voSubscription.getPurchaseOrderNumber());
        assertThat(representation)
                .extracting(SubscriptionCreationRepresentation::getSubscriptionId)
                .isEqualTo(voSubscription.getSubscriptionId());
    }

    private VOSubscription createVO() {
        VOSubscription voSubscription = new VOSubscription();
        voSubscription.setPurchaseOrderNumber("Number10");
        voSubscription.setSubscriptionId("Sub100");
        return voSubscription;
    }

    private SubscriptionCreationRepresentation createRepresentation() {
        SubscriptionCreationRepresentation subscriptionCreationRepresentation = new SubscriptionCreationRepresentation();
        subscriptionCreationRepresentation.setPurchaseOrderNumber("100Purchase");
        subscriptionCreationRepresentation.setSubscriptionId("100ID");
        subscriptionCreationRepresentation.setUnitName("100Unit");
        return subscriptionCreationRepresentation;
    }

}