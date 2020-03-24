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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.oscm.internal.vo.BaseVO;
import org.oscm.internal.vo.VOSubscription;
import org.oscm.internal.vo.VOUda;
import org.oscm.internal.vo.VOUsageLicense;
import org.oscm.rest.common.TestContants;

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

  @Test
  public void shouldUpdateVOUsageLicense() {

    // given
    ArrayList<UsageLicenseRepresentation> ulrList = new ArrayList<UsageLicenseRepresentation>();
    SubscriptionCreationRepresentation scr = createRepresentation();
    UsageLicenseRepresentation ulr = new UsageLicenseRepresentation();
    UserRepresentation ur = new UserRepresentation();

    ur.setUserId("supplier");
    ur.setOrganizationId("959c9bf7");
    ulr.setUser(ur);
    ulrList.add(ulr);
    scr.setUsers(ulrList);

    // when
    List<VOUsageLicense> result = scr.getUsageLicenses();

    // then
    assertEquals("supplier", result.get(0).getUser().getUserId());
    assertEquals("959c9bf7", result.get(0).getUser().getOrganizationId());
  }

  @Test
  public void shouldUpdateVOUdaRepresentation() {

    // given
    ArrayList<UdaRepresentation> urList = new ArrayList<UdaRepresentation>();
    SubscriptionCreationRepresentation scr = createRepresentation();
    UdaRepresentation ur = new UdaRepresentation();
    UdaDefinitionRepresentation udr = new UdaDefinitionRepresentation();

    udr.setDefaultValue("test");
    udr.setUdaId("123");
    ur.setUdaDefinition(udr);
    ur.setUdaValue("testvalue");
    urList.add(ur);
    scr.setUdas(urList);

    // when
    List<VOUda> result = scr.getUdas();

    // then
    assertEquals("testvalue", result.get(0).getUdaValue());
    assertEquals("test", result.get(0).getUdaDefinition().getDefaultValue());
    assertEquals("123", result.get(0).getUdaDefinition().getUdaId());
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
