/**
 * *****************************************************************************
 *
 * <p>Copyright FUJITSU LIMITED 2020
 *
 * <p>Creation Date: 24-03-2020
 *
 * <p>*****************************************************************************
 */
package org.oscm.rest.common.representation;

import org.junit.jupiter.api.Test;
import org.oscm.internal.vo.BaseVO;
import org.oscm.internal.vo.VOSubscription;
import org.oscm.rest.common.TestContants;

import static org.assertj.core.api.Assertions.assertThat;

public class SubscriptionCreationRepresentationTest {

  @Test
  public void shouldUpdateVOSubscription() {
    SubscriptionCreationRepresentation subscriptionCreationRepresentation = createRepresentation();
    subscriptionCreationRepresentation.setETag(TestContants.LONG_VALUE);
    subscriptionCreationRepresentation.setId(TestContants.LONG_VALUE);

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

    SubscriptionCreationRepresentation representation =
        new SubscriptionCreationRepresentation(voSubscription);
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
    voSubscription.setPurchaseOrderNumber(TestContants.STRING_VALUE);
    voSubscription.setSubscriptionId(TestContants.STRING_VALUE);
    return voSubscription;
  }

  private SubscriptionCreationRepresentation createRepresentation() {
    SubscriptionCreationRepresentation subscriptionCreationRepresentation =
        new SubscriptionCreationRepresentation();
    subscriptionCreationRepresentation.setPurchaseOrderNumber(TestContants.STRING_VALUE);
    subscriptionCreationRepresentation.setSubscriptionId(TestContants.STRING_VALUE);
    subscriptionCreationRepresentation.setUnitName(TestContants.STRING_VALUE);
    return subscriptionCreationRepresentation;
  }
}
