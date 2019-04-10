package org.oscm.rest.account.data;

import org.junit.jupiter.api.Test;
import org.oscm.internal.vo.VOOrganization;

import static org.assertj.core.api.Assertions.assertThat;

public class OrganizationRepresentationTest {

        @Test
        public void shouldUpdateVOOrganization() {
                OrganizationRepresentation orgRepresentation = createOrganizationRepresentation();
                VOOrganization voOrganization = null;

                orgRepresentation.update();
                voOrganization = orgRepresentation.getVO();

                assertThat(voOrganization).isNotNull();
                assertThat(voOrganization).extracting(VOOrganization::getAddress).isEqualTo(orgRepresentation.getAddress());
                assertThat(voOrganization).extracting(VOOrganization::getDescription).isEqualTo(orgRepresentation.getDescription());
                assertThat(voOrganization).extracting(VOOrganization::getDomicileCountry).isEqualTo(orgRepresentation.getDomicileCountry());
                assertThat(voOrganization).extracting(VOOrganization::getEmail).isEqualTo(orgRepresentation.getEmail());
                assertThat(voOrganization).extracting(VOOrganization::getKey).isEqualTo(orgRepresentation.getId());
                assertThat(voOrganization).extracting(VOOrganization::getLocale).isEqualTo(orgRepresentation.getLocale());
                assertThat(voOrganization).extracting(VOOrganization::getName).isEqualTo(orgRepresentation.getName());
                assertThat(voOrganization).extracting(VOOrganization::getOrganizationId).isEqualTo(orgRepresentation.getOrganizationId());
                assertThat(voOrganization).extracting(VOOrganization::getPhone).isEqualTo(orgRepresentation.getPhone());
                assertThat(voOrganization).extracting(VOOrganization::getSupportEmail).isEqualTo(orgRepresentation.getSupportEmail());
                assertThat(voOrganization).extracting(VOOrganization::getVersion).isEqualTo(orgRepresentation.getETag().intValue());
                assertThat(voOrganization).extracting(VOOrganization::getUrl).isEqualTo(orgRepresentation.getUrl());
        }

        @Test
        public void shouldConvertToOrganizationRepresentation() {
              VOOrganization voOrganization = createVOOrganization();
              OrganizationRepresentation orgRepresentation = new OrganizationRepresentation(voOrganization);

              orgRepresentation.convert();

              assertThat(orgRepresentation).extracting(OrganizationRepresentation::getAddress).isEqualTo(voOrganization.getAddress());
              assertThat(orgRepresentation).extracting(OrganizationRepresentation::getDescription).isEqualTo(voOrganization.getDescription());
              assertThat(orgRepresentation).extracting(OrganizationRepresentation::getDomicileCountry).isEqualTo(voOrganization.getDomicileCountry());
              assertThat(orgRepresentation).extracting(OrganizationRepresentation::getEmail).isEqualTo(voOrganization.getEmail());
              assertThat(orgRepresentation).extracting(OrganizationRepresentation::getId).isEqualTo((long) voOrganization.getKey());
              assertThat(orgRepresentation).extracting(OrganizationRepresentation::getLocale).isEqualTo(voOrganization.getLocale());
              assertThat(orgRepresentation).extracting(OrganizationRepresentation::getName).isEqualTo(voOrganization.getName());
              assertThat(orgRepresentation).extracting(OrganizationRepresentation::getOrganizationId).isEqualTo(voOrganization.getOrganizationId());
              assertThat(orgRepresentation).extracting(OrganizationRepresentation::getPhone).isEqualTo(voOrganization.getPhone());
              assertThat(orgRepresentation).extracting(OrganizationRepresentation::getSupportEmail).isEqualTo(voOrganization.getSupportEmail());
              assertThat(orgRepresentation).extracting(OrganizationRepresentation::getETag).isEqualTo((long) voOrganization.getVersion());
              assertThat(orgRepresentation).extracting(OrganizationRepresentation::getUrl).isEqualTo(voOrganization.getUrl());
        }

        private OrganizationRepresentation createOrganizationRepresentation() {
                OrganizationRepresentation orgRepresentation = new OrganizationRepresentation();
                orgRepresentation.setAddress("address");
                orgRepresentation.setDescription("desc");
                orgRepresentation.setDomicileCountry("dCountry");
                orgRepresentation.setEmail("email");
                orgRepresentation.setId(123L);
                orgRepresentation.setLocale("en_US");
                orgRepresentation.setName("name");
                orgRepresentation.setOrganizationId("orgId");
                orgRepresentation.setPhone("111111111");
                orgRepresentation.setSupportEmail("a@a.com");
                orgRepresentation.setETag(456L);
                orgRepresentation.setUrl("url");
                return orgRepresentation;
        }

        private VOOrganization createVOOrganization() {
                VOOrganization voOrganization = new VOOrganization();
                voOrganization.setAddress("address");
                voOrganization.setDescription("desc");
                voOrganization.setDomicileCountry("dCountry");
                voOrganization.setEmail("email");
                voOrganization.setKey(123L);
                voOrganization.setLocale("en_US");
                voOrganization.setName("name");
                voOrganization.setOrganizationId("orgId");
                voOrganization.setPhone("111111111");
                voOrganization.setSupportEmail("a@a.com");
                voOrganization.setVersion(456);
                voOrganization.setUrl("url");
                return voOrganization;
        }
}