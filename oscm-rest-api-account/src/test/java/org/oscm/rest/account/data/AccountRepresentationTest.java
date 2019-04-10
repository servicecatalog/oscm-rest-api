package org.oscm.rest.account.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.oscm.internal.types.enumtypes.OrganizationRoleType;
import org.oscm.internal.vo.LdapProperties;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountRepresentationTest {

        private OrganizationRepresentation organization;
        private UserRepresentation user;
        private String password;
        private Long serviceKey;
        private String sellerId;
        private Map<String, String> ldapProperties;
        private LdapProperties voLdapProperties;
        private OrganizationRoleType[] organizationRoles;

        @BeforeEach
        public void setUp() {
                organization = new OrganizationRepresentation();
                user = new UserRepresentation();
                password = "password";
                serviceKey = 1234L;
                sellerId = "sellerId";
                ldapProperties = new HashMap<>();
                voLdapProperties = new LdapProperties();
                organizationRoles = new OrganizationRoleType[] {
                        OrganizationRoleType.BROKER };
        }

        @Test
        public void shouldCreateAccountRepresentation() {
                AccountRepresentation representation = new AccountRepresentation();
                representation.setOrganization(organization);
                representation.setUser(user);
                representation.setPassword(password);
                representation.setServiceKey(serviceKey);
                representation.setSellerId(sellerId);
                representation.setLdapProperties(ldapProperties);
                representation.setOrganizationRoles(organizationRoles);

                assertThat(representation.getOrganization())
                        .isEqualTo(organization);
                assertThat(representation.getUser()).isEqualTo(user);
                assertThat(representation.getPassword()).isEqualTo(password);
                assertThat(representation.getServiceKey())
                        .isEqualTo(serviceKey);
                assertThat(representation.getSellerId()).isEqualTo(sellerId);
                assertThat(representation.getLdapProperties())
                        .isEqualTo(ldapProperties);
                assertThat(representation.getOrganizationRoles())
                        .isEqualTo(organizationRoles);
                assertThat(representation.getOrganizationRoles())
                        .isEqualTo(organizationRoles);
                assertThat(representation.isSelfRegistration()).isEqualTo(true);
        }

        @Test
        public void shouldUpdateAccountRepresentation() {
                AccountRepresentation representation = new AccountRepresentation();
                representation.setOrganization(organization);
                representation.setUser(user);
                representation.setLdapProperties(ldapProperties);

                representation.update();

                assertThat(representation.getLdapProperties())
                        .isEqualTo(ldapProperties);
        }

        @Test
        public void shouldConvertToAccountRepresentation() {
                AccountRepresentation representation = new AccountRepresentation(
                        voLdapProperties);
                representation.setOrganization(organization);
                representation.setUser(user);

                representation.convert();

                assertThat(representation.getLdapProperties()).isNotNull();
                assertThat(representation.getLdapProperties()).isEmpty();
                assertThat(representation.getProps())
                        .isEqualTo(voLdapProperties);
        }
}
