/**
 * *****************************************************************************
 *
 * <p>Copyright FUJITSU LIMITED 2020
 *
 * <p>Creation Date: April 23, 2020
 *
 * <p>*****************************************************************************
 */
package org.oscm.rest.common.representation;

import java.util.Map;
import java.util.Properties;
import org.oscm.internal.types.enumtypes.OrganizationRoleType;
import org.oscm.internal.vo.LdapProperties;
import org.oscm.rest.common.validator.RequiredFieldValidator;

public class CreateOrganizationRepresentation extends Representation {

  private OrganizationRepresentation organization;
  private UserRepresentation user;
  private String marketplaceId;
  private Map<String, String> ldapProperties;
  private OrganizationRoleType[] organizationRoles;

  public OrganizationRepresentation getOrganization() {
    return organization;
  }

  public void setOrganization(OrganizationRepresentation organization) {
    this.organization = organization;
  }

  public UserRepresentation getUser() {
    return user;
  }

  public void setUser(UserRepresentation user) {
    this.user = user;
  }

  public String getMarketplaceId() {
    return marketplaceId;
  }

  public void setMarketplaceId(String marketplaceId) {
    this.marketplaceId = marketplaceId;
  }

  public Map<String, String> getLdapProperties() {
    return ldapProperties;
  }

  public void setLdapProperties(Map<String, String> ldapProperties) {
    this.ldapProperties = ldapProperties;
  }

  public OrganizationRoleType[] getOrganizationRoles() {
    return organizationRoles;
  }

  public void setOrganizationRoles(OrganizationRoleType[] organizationRoles) {
    this.organizationRoles = organizationRoles;
  }

  @Override
  public void update() {
    organization.update();
    user.update();
  }

  @Override
  public void validateContent() {
    RequiredFieldValidator validator = new RequiredFieldValidator();
    validator.validateNotBlank("user", user);
    validator.validateNotBlank("organization", organization);
    validator.validateNotBlank("organizationRoles", organizationRoles);
  }

  public LdapProperties toProperties() {
    if (ldapProperties == null) {
      return null;
    }
    Properties properties = new Properties();
    properties.putAll(ldapProperties);
    return new LdapProperties(properties);
  }
}
