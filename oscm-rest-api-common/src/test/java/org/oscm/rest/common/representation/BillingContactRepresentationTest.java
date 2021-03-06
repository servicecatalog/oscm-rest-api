/**
 * *****************************************************************************
 *
 * <p>Copyright FUJITSU LIMITED 2019
 *
 * <p>Creation Date: 10-04-2019
 *
 * <p>*****************************************************************************
 */
package org.oscm.rest.common.representation;

import org.junit.jupiter.api.Test;
import org.oscm.internal.vo.VOBillingContact;
import org.oscm.rest.common.TestContants;

import static org.assertj.core.api.Assertions.assertThat;

public class BillingContactRepresentationTest {

  @Test
  public void shouldUpdateVOBillingContact() {
    BillingContactRepresentation representation = createRepresentation();
    VOBillingContact billingContact = null;

    representation.update();
    billingContact = representation.getVO();

    assertThat(billingContact).isNotNull();
    assertThat(billingContact)
        .extracting(VOBillingContact::getAddress)
        .isEqualTo(representation.getAddress());
    assertThat(billingContact)
        .extracting(VOBillingContact::getCompanyName)
        .isEqualTo(representation.getCompanyName());
    assertThat(billingContact)
        .extracting(VOBillingContact::getId)
        .isEqualTo(representation.getContactId());
    assertThat(billingContact)
        .extracting(VOBillingContact::getEmail)
        .isEqualTo(representation.getEmail());
    assertThat(billingContact)
        .extracting(VOBillingContact::getKey)
        .isEqualTo(representation.getId().longValue());
    assertThat(billingContact)
        .extracting(VOBillingContact::isOrgAddressUsed)
        .isEqualTo(representation.isOrgAddressUsed());
    assertThat(billingContact)
        .extracting(VOBillingContact::getVersion)
        .isEqualTo(representation.getETag().intValue());
  }

  @Test
  public void shouldConvertToBillingContact() {
    VOBillingContact voBillingContact = createVO();

    BillingContactRepresentation contactRepresentation =
        new BillingContactRepresentation(voBillingContact);
    contactRepresentation.convert();

    assertThat(contactRepresentation)
        .extracting(BillingContactRepresentation::getAddress)
        .isEqualTo(voBillingContact.getAddress());
    assertThat(contactRepresentation)
        .extracting(BillingContactRepresentation::getCompanyName)
        .isEqualTo(voBillingContact.getCompanyName());
    assertThat(contactRepresentation)
        .extracting(BillingContactRepresentation::getContactId)
        .isEqualTo(voBillingContact.getId());
    assertThat(contactRepresentation)
        .extracting(BillingContactRepresentation::getEmail)
        .isEqualTo(voBillingContact.getEmail());
    assertThat(contactRepresentation)
        .extracting(BillingContactRepresentation::getId)
        .isEqualTo((long) voBillingContact.getKey());
    assertThat(contactRepresentation)
        .extracting(BillingContactRepresentation::isOrgAddressUsed)
        .isEqualTo(voBillingContact.isOrgAddressUsed());
    assertThat(contactRepresentation)
        .extracting(BillingContactRepresentation::getETag)
        .isEqualTo((long) voBillingContact.getVersion());
  }

  private BillingContactRepresentation createRepresentation() {
    BillingContactRepresentation contactRepresentation = new BillingContactRepresentation();
    contactRepresentation.setAddress(TestContants.STRING_VALUE);
    contactRepresentation.setCompanyName(TestContants.STRING_VALUE);
    contactRepresentation.setContactId(TestContants.STRING_VALUE);
    contactRepresentation.setEmail(TestContants.STRING_VALUE);
    contactRepresentation.setId(TestContants.LONG_VALUE);
    contactRepresentation.setOrgAddressUsed(true);
    contactRepresentation.setETag(TestContants.LONG_VALUE);
    return contactRepresentation;
  }

  private VOBillingContact createVO() {
    VOBillingContact billingContact = new VOBillingContact();
    billingContact.setAddress(TestContants.STRING_VALUE);
    billingContact.setCompanyName(TestContants.STRING_VALUE);
    billingContact.setId(TestContants.STRING_VALUE);
    billingContact.setEmail(TestContants.STRING_VALUE);
    billingContact.setKey(TestContants.INTEGER_VALUE);
    billingContact.setOrgAddressUsed(true);
    billingContact.setVersion(TestContants.INTEGER_VALUE);
    return billingContact;
  }
}
