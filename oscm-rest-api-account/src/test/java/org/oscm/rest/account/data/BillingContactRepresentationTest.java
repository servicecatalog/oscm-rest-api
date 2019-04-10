package org.oscm.rest.account.data;

import org.junit.jupiter.api.Test;
import org.oscm.internal.vo.VOBillingContact;

import static org.assertj.core.api.Assertions.assertThat;

public class BillingContactRepresentationTest {

        @Test
        public void shouldUpdateVOBillingContact() {
                BillingContactRepresentation representation = createBillingContactRepresentation();
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
                VOBillingContact voBillingContact = createVOBillingContact();

                BillingContactRepresentation contactRepresentation =
                        new BillingContactRepresentation(voBillingContact);
                contactRepresentation.convert();

                assertThat(contactRepresentation)
                        .extracting(BillingContactRepresentation::getAddress)
                        .isEqualTo(voBillingContact.getAddress());
                assertThat(contactRepresentation)
                        .extracting(
                                BillingContactRepresentation::getCompanyName)
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
                        .extracting(
                                BillingContactRepresentation::isOrgAddressUsed)
                        .isEqualTo(voBillingContact.isOrgAddressUsed());
                assertThat(contactRepresentation)
                        .extracting(BillingContactRepresentation::getETag)
                        .isEqualTo((long) voBillingContact.getVersion());
        }

        private BillingContactRepresentation createBillingContactRepresentation() {
                BillingContactRepresentation contactRepresentation = new BillingContactRepresentation();
                contactRepresentation.setAddress("address");
                contactRepresentation.setCompanyName("companyName");
                contactRepresentation.setContactId("contactId");
                contactRepresentation.setEmail("email");
                contactRepresentation.setId(123L);
                contactRepresentation.setOrgAddressUsed(true);
                contactRepresentation.setETag(456L);
                return contactRepresentation;
        }

        private VOBillingContact createVOBillingContact() {
                VOBillingContact billingContact = new VOBillingContact();
                billingContact.setAddress("address");
                billingContact.setCompanyName("companyName");
                billingContact.setId("contactId");
                billingContact.setEmail("email");
                billingContact.setKey(123);
                billingContact.setOrgAddressUsed(true);
                billingContact.setVersion(456);
                return billingContact;
        }
}
