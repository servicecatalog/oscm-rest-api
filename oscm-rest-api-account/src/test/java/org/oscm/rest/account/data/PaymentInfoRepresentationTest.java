/**
 * *****************************************************************************
 *
 * <p>Copyright FUJITSU LIMITED 2019
 *
 * <p>Creation Date: 10-04-2019
 *
 * <p>*****************************************************************************
 */
package org.oscm.rest.account.data;

import org.junit.jupiter.api.Test;
import org.oscm.internal.vo.VOPaymentInfo;
import org.oscm.internal.vo.VOPaymentType;

import static org.assertj.core.api.Assertions.assertThat;

public class PaymentInfoRepresentationTest {

  @Test
  public void shouldUpdateVOPaymentInfo() {
    PaymentInfoRepresentation representation = createRepresentation();
    VOPaymentInfo paymentInfo = null;

    representation.update();
    paymentInfo = representation.getVO();

    assertThat(paymentInfo).isNotNull();
    assertThat(paymentInfo)
        .extracting(VOPaymentInfo::getAccountNumber)
        .isEqualTo(representation.getAccountNumber());
    assertThat(paymentInfo).extracting(VOPaymentInfo::getId).isEqualTo(representation.getInfoId());
    assertThat(paymentInfo).extracting(VOPaymentInfo::getKey).isEqualTo(representation.getId());
    assertThat(paymentInfo)
        .extracting(VOPaymentInfo::getPaymentType)
        .isEqualTo(representation.getVO().getPaymentType());
    assertThat(paymentInfo)
        .extracting(VOPaymentInfo::getProviderName)
        .isEqualTo(representation.getProviderName());
    assertThat(paymentInfo)
        .extracting(VOPaymentInfo::getVersion)
        .isEqualTo(representation.getETag().intValue());
  }

  @Test
  public void shouldConvertToPaymentRepresentation() {
    VOPaymentInfo voPaymentInfo = createVO();
    PaymentInfoRepresentation representation = new PaymentInfoRepresentation(voPaymentInfo);

    representation.convert();

    assertThat(representation)
        .extracting(PaymentInfoRepresentation::getAccountNumber)
        .isEqualTo(voPaymentInfo.getAccountNumber());
    assertThat(representation)
        .extracting(PaymentInfoRepresentation::getId)
        .isEqualTo(voPaymentInfo.getKey());
    assertThat(representation)
        .extracting(PaymentInfoRepresentation::getInfoId)
        .isEqualTo(voPaymentInfo.getId());
    assertThat(representation)
        .extracting(PaymentInfoRepresentation::getProviderName)
        .isEqualTo(voPaymentInfo.getProviderName());
    assertThat(representation)
        .extracting(PaymentInfoRepresentation::getETag)
        .isEqualTo((long) voPaymentInfo.getVersion());
  }

  private PaymentInfoRepresentation createRepresentation() {
    PaymentInfoRepresentation representation = new PaymentInfoRepresentation();
    representation.setAccountNumber("accountNr");
    representation.setId(123L);
    representation.setInfoId("infoId");
    representation.setProviderName("providerName");
    representation.setETag(456L);
    return representation;
  }

  private VOPaymentInfo createVO() {
    VOPaymentInfo paymentInfo = new VOPaymentInfo();
    paymentInfo.setAccountNumber("accountNr");
    paymentInfo.setId("id");
    paymentInfo.setPaymentType(new VOPaymentType());
    paymentInfo.setProviderName("providerName");
    paymentInfo.setVersion(456);
    return paymentInfo;
  }
}
