package org.oscm.rest.subscription.data;

import org.junit.jupiter.api.Test;
import org.oscm.internal.types.enumtypes.SubscriptionStatus;
import org.oscm.internal.vo.BaseVO;
import org.oscm.internal.vo.VOSubscription;
import org.oscm.rest.common.Representation;

import static org.assertj.core.api.Assertions.assertThat;

class SubscriptionRepresentationTest {

    @Test
    public void shouldUpdateVOSubscription() {
        SubscriptionRepresentation subscriptionRepresentation = createRepresentation();
        subscriptionRepresentation.setId(100L);
        subscriptionRepresentation.setETag(100L);

        subscriptionRepresentation.update();
        VOSubscription result = subscriptionRepresentation.getVO();

        assertThat(result).isNotNull();
        assertThat(result)
                .extracting(VOSubscription::getActivationDate)
                .isEqualTo(subscriptionRepresentation.getActivationDate());
        assertThat(result)
                .extracting(VOSubscription::getCreationDate)
                .isEqualTo(subscriptionRepresentation.getCreationDate());
        assertThat(result)
                .extracting(VOSubscription::getDeactivationDate)
                .isEqualTo(subscriptionRepresentation.getDeactivationDate());
        assertThat(result)
                .extracting(BaseVO::getKey)
                .isEqualTo(subscriptionRepresentation.convertIdToKey());
        assertThat(result)
                .extracting(VOSubscription::getStatus)
                .isEqualTo(subscriptionRepresentation.getStatus());
        assertThat(result)
                .extracting(VOSubscription::getSubscriptionId)
                .isEqualTo(subscriptionRepresentation.getSubscriptionId());
        assertThat(result)
                .extracting(VOSubscription::getUnitName)
                .isEqualTo(subscriptionRepresentation.getUnitName());
        assertThat(result)
                .extracting(VOSubscription::getUnitKey)
                .isEqualTo(subscriptionRepresentation.getUnitKey());
        assertThat(result)
                .extracting(BaseVO::getVersion)
                .isEqualTo(subscriptionRepresentation.convertETagToVersion());
    }

    @Test
    public void shouldUpdateVOSubscription_evenIfIdAndETagIsNull() {
        SubscriptionRepresentation subscriptionRepresentation = createRepresentation();

        subscriptionRepresentation.update();
        VOSubscription result = subscriptionRepresentation.getVO();

        assertThat(result).isNotNull();
        assertThat(result)
                .extracting(VOSubscription::getActivationDate)
                .isEqualTo(subscriptionRepresentation.getActivationDate());
        assertThat(result)
                .extracting(VOSubscription::getCreationDate)
                .isEqualTo(subscriptionRepresentation.getCreationDate());
        assertThat(result)
                .extracting(VOSubscription::getDeactivationDate)
                .isEqualTo(subscriptionRepresentation.getDeactivationDate());
        assertThat(result)
                .extracting(BaseVO::getKey)
                .isEqualTo(subscriptionRepresentation.convertIdToKey());
        assertThat(result)
                .extracting(VOSubscription::getStatus)
                .isEqualTo(subscriptionRepresentation.getStatus());
        assertThat(result)
                .extracting(VOSubscription::getSubscriptionId)
                .isEqualTo(subscriptionRepresentation.getSubscriptionId());
        assertThat(result)
                .extracting(VOSubscription::getUnitName)
                .isEqualTo(subscriptionRepresentation.getUnitName());
        assertThat(result)
                .extracting(VOSubscription::getUnitKey)
                .isEqualTo(subscriptionRepresentation.getUnitKey());
        assertThat(result)
                .extracting(BaseVO::getVersion)
                .isEqualTo(subscriptionRepresentation.convertETagToVersion());
    }

    @Test
    public void shouldConvertToSubscriptionRepresentation() {
        VOSubscription voSubscription = createVO();

        SubscriptionRepresentation representation = new SubscriptionRepresentation(voSubscription);
        representation.convert();

        assertThat(representation)
                .extracting(SubscriptionRepresentation::getActivationDate)
                .isEqualTo(voSubscription.getActivationDate());
        assertThat(representation)
                .extracting(SubscriptionRepresentation::getCreationDate)
                .isEqualTo(voSubscription.getCreationDate());
        assertThat(representation)
                .extracting(SubscriptionRepresentation::getDeactivationDate)
                .isEqualTo(voSubscription.getDeactivationDate());
        assertThat(representation)
                .extracting(Representation::convertIdToKey)
                .isEqualTo(voSubscription.getKey());
        assertThat(representation)
                .extracting(Representation::convertETagToVersion)
                .isEqualTo(voSubscription.getVersion());
        assertThat(representation)
                .extracting(SubscriptionRepresentation::getStatus)
                .isEqualTo(voSubscription.getStatus());
        assertThat(representation)
                .extracting(SubscriptionRepresentation::getSubscriptionId)
                .isEqualTo(voSubscription.getSubscriptionId());
        assertThat(representation)
                .extracting(SubscriptionRepresentation::getUnitName)
                .isEqualTo(voSubscription.getUnitName());
        assertThat(representation)
                .extracting(SubscriptionRepresentation::getUnitKey)
                .isEqualTo(voSubscription.getUnitKey());
    }

    private VOSubscription createVO() {
        VOSubscription voSubscription = new VOSubscription();
        voSubscription.setVersion(100);
        voSubscription.setKey(100L);
        voSubscription.setActivationDate(100L);
        voSubscription.setCreationDate(200L);
        voSubscription.setDeactivationDate(300L);
        voSubscription.setStatus(SubscriptionStatus.ACTIVE);
        voSubscription.setSubscriptionId("100Sub");
        voSubscription.setUnitName("Unit100");
        voSubscription.setUnitKey(100L);

        return voSubscription;
    }

    private SubscriptionRepresentation createRepresentation() {
        SubscriptionRepresentation subscriptionRepresentation = new SubscriptionRepresentation();
        subscriptionRepresentation.setActivationDate(100L);
        subscriptionRepresentation.setCreationDate(200L);
        subscriptionRepresentation.setDeactivationDate(300L);
        subscriptionRepresentation.setStatus(SubscriptionStatus.ACTIVE);
        subscriptionRepresentation.setSubscriptionId("SubID");
        subscriptionRepresentation.setUnitName("Unit100");
        subscriptionRepresentation.setUnitKey(100L);

        return subscriptionRepresentation;
    }

}