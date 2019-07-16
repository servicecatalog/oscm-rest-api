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
import org.oscm.internal.vo.VOOrganization;
import org.oscm.rest.common.TestContants;

import static org.assertj.core.api.Assertions.assertThat;

public class OrganizationRepresentationTest {

  @Test
  public void shouldUpdateVOOrganization() {
    OrganizationRepresentation orgRepresentation = createRepresentation();
    VOOrganization voOrganization = null;

    orgRepresentation.update();
    voOrganization = orgRepresentation.getVO();

    assertThat(voOrganization).isNotNull();
    assertThat(voOrganization)
        .extracting(VOOrganization::getAddress)
        .isEqualTo(orgRepresentation.getAddress());
    assertThat(voOrganization)
        .extracting(VOOrganization::getDescription)
        .isEqualTo(orgRepresentation.getDescription());
    assertThat(voOrganization)
        .extracting(VOOrganization::getDomicileCountry)
        .isEqualTo(orgRepresentation.getDomicileCountry());
    assertThat(voOrganization)
        .extracting(VOOrganization::getEmail)
        .isEqualTo(orgRepresentation.getEmail());
    assertThat(voOrganization)
        .extracting(VOOrganization::getKey)
        .isEqualTo(orgRepresentation.getId());
    assertThat(voOrganization)
        .extracting(VOOrganization::getLocale)
        .isEqualTo(orgRepresentation.getLocale());
    assertThat(voOrganization)
        .extracting(VOOrganization::getName)
        .isEqualTo(orgRepresentation.getName());
    assertThat(voOrganization)
        .extracting(VOOrganization::getOrganizationId)
        .isEqualTo(orgRepresentation.getOrganizationId());
    assertThat(voOrganization)
        .extracting(VOOrganization::getPhone)
        .isEqualTo(orgRepresentation.getPhone());
    assertThat(voOrganization)
        .extracting(VOOrganization::getSupportEmail)
        .isEqualTo(orgRepresentation.getSupportEmail());
    assertThat(voOrganization)
        .extracting(VOOrganization::getVersion)
        .isEqualTo(orgRepresentation.getETag().intValue());
    assertThat(voOrganization)
        .extracting(VOOrganization::getUrl)
        .isEqualTo(orgRepresentation.getUrl());
  }

  @Test
  public void shouldConvertToOrganizationRepresentation() {
    VOOrganization voOrganization = createVO();
    OrganizationRepresentation orgRepresentation = new OrganizationRepresentation(voOrganization);

    orgRepresentation.convert();

    assertThat(orgRepresentation)
        .extracting(OrganizationRepresentation::getAddress)
        .isEqualTo(voOrganization.getAddress());
    assertThat(orgRepresentation)
        .extracting(OrganizationRepresentation::getDescription)
        .isEqualTo(voOrganization.getDescription());
    assertThat(orgRepresentation)
        .extracting(OrganizationRepresentation::getDomicileCountry)
        .isEqualTo(voOrganization.getDomicileCountry());
    assertThat(orgRepresentation)
        .extracting(OrganizationRepresentation::getEmail)
        .isEqualTo(voOrganization.getEmail());
    assertThat(orgRepresentation)
        .extracting(OrganizationRepresentation::getId)
        .isEqualTo((long) voOrganization.getKey());
    assertThat(orgRepresentation)
        .extracting(OrganizationRepresentation::getLocale)
        .isEqualTo(voOrganization.getLocale());
    assertThat(orgRepresentation)
        .extracting(OrganizationRepresentation::getName)
        .isEqualTo(voOrganization.getName());
    assertThat(orgRepresentation)
        .extracting(OrganizationRepresentation::getOrganizationId)
        .isEqualTo(voOrganization.getOrganizationId());
    assertThat(orgRepresentation)
        .extracting(OrganizationRepresentation::getPhone)
        .isEqualTo(voOrganization.getPhone());
    assertThat(orgRepresentation)
        .extracting(OrganizationRepresentation::getSupportEmail)
        .isEqualTo(voOrganization.getSupportEmail());
    assertThat(orgRepresentation)
        .extracting(OrganizationRepresentation::getETag)
        .isEqualTo((long) voOrganization.getVersion());
    assertThat(orgRepresentation)
        .extracting(OrganizationRepresentation::getUrl)
        .isEqualTo(voOrganization.getUrl());
  }

  private OrganizationRepresentation createRepresentation() {
    OrganizationRepresentation orgRepresentation = new OrganizationRepresentation();
    orgRepresentation.setAddress(TestContants.STRING_VALUE);
    orgRepresentation.setDescription(TestContants.STRING_VALUE);
    orgRepresentation.setDomicileCountry(TestContants.STRING_VALUE);
    orgRepresentation.setEmail(TestContants.STRING_VALUE);
    orgRepresentation.setId(TestContants.LONG_VALUE);
    orgRepresentation.setLocale(TestContants.STRING_VALUE);
    orgRepresentation.setName(TestContants.STRING_VALUE);
    orgRepresentation.setOrganizationId(TestContants.STRING_VALUE);
    orgRepresentation.setPhone(TestContants.STRING_NUM_VALUE);
    orgRepresentation.setSupportEmail(TestContants.STRING_VALUE);
    orgRepresentation.setETag(TestContants.LONG_VALUE);
    orgRepresentation.setUrl(TestContants.STRING_VALUE);
    return orgRepresentation;
  }

  private VOOrganization createVO() {
    VOOrganization voOrganization = new VOOrganization();
    voOrganization.setAddress(TestContants.STRING_VALUE);
    voOrganization.setDescription(TestContants.STRING_VALUE);
    voOrganization.setDomicileCountry(TestContants.STRING_VALUE);
    voOrganization.setEmail(TestContants.STRING_VALUE);
    voOrganization.setKey(TestContants.LONG_VALUE);
    voOrganization.setLocale(TestContants.STRING_VALUE);
    voOrganization.setName(TestContants.STRING_VALUE);
    voOrganization.setOrganizationId(TestContants.STRING_VALUE);
    voOrganization.setPhone(TestContants.STRING_NUM_VALUE);
    voOrganization.setSupportEmail(TestContants.STRING_VALUE);
    voOrganization.setVersion(TestContants.INTEGER_VALUE);
    voOrganization.setUrl(TestContants.STRING_VALUE);
    return voOrganization;
  }
}
