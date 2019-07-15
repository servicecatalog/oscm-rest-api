package org.oscm.rest.common.representation;

import org.junit.jupiter.api.Test;
import org.oscm.internal.types.enumtypes.SubscriptionStatus;
import org.oscm.internal.vo.BaseVO;
import org.oscm.internal.vo.VOSubscription;
import org.oscm.rest.common.TestContants;

import static org.assertj.core.api.Assertions.assertThat;

public class SubscriptionRepresentationTest {

    @Test
    public void shouldUpdateVOSubscription() {
        SubscriptionRepresentation subscriptionRepresentation = createRepresentation();
        subscriptionRepresentation.setId(TestContants.LONG_VALUE);
        subscriptionRepresentation.setETag(TestContants.LONG_VALUE);

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
        voSubscription.setVersion(TestContants.INTEGER_VALUE);
        voSubscription.setKey(TestContants.LONG_VALUE);
        voSubscription.setActivationDate(TestContants.LONG_VALUE);
        voSubscription.setCreationDate(TestContants.LONG_VALUE);
        voSubscription.setDeactivationDate(TestContants.LONG_VALUE);
        voSubscription.setStatus(SubscriptionStatus.ACTIVE);
        voSubscription.setSubscriptionId(TestContants.STRING_VALUE);
        voSubscription.setUnitName(TestContants.STRING_VALUE);
        voSubscription.setUnitKey(TestContants.LONG_VALUE);

        return voSubscription;
    }

    private SubscriptionRepresentation createRepresentation() {
        SubscriptionRepresentation subscriptionRepresentation = new SubscriptionRepresentation();
        subscriptionRepresentation.setActivationDate(TestContants.LONG_VALUE);
        subscriptionRepresentation.setCreationDate(TestContants.LONG_VALUE);
        subscriptionRepresentation.setDeactivationDate(TestContants.LONG_VALUE);
        subscriptionRepresentation.setStatus(SubscriptionStatus.ACTIVE);
        subscriptionRepresentation.setSubscriptionId(TestContants.STRING_VALUE);
        subscriptionRepresentation.setUnitName(TestContants.STRING_VALUE);
        subscriptionRepresentation.setUnitKey(TestContants.LONG_VALUE);

        return subscriptionRepresentation;
    }
}