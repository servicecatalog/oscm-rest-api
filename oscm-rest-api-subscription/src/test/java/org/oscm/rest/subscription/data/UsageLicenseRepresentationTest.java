package org.oscm.rest.subscription.data;

import org.junit.jupiter.api.Test;
import org.oscm.internal.vo.VORoleDefinition;
import org.oscm.internal.vo.VOUsageLicense;
import org.oscm.internal.vo.VOUser;

import static org.assertj.core.api.Assertions.assertThat;

public class UsageLicenseRepresentationTest {

    @Test
    public void shouldUpdateVOUsageLicense() {
        UsageLicenseRepresentation usageLicenseRepresentation = createRepresentation();
        usageLicenseRepresentation.setETag(100L);
        usageLicenseRepresentation.setId(100L);

        usageLicenseRepresentation.update();
        VOUsageLicense result = usageLicenseRepresentation.getVO();

        assertThat(result).isNotNull();
        assertThat(result.getRoleDefinition().getDescription()).isEqualTo(usageLicenseRepresentation.getRole().getDescription());
    }

    @Test
    public void shouldUpdateVOUsageLicense_evenIIdAndETagIsNull() {
        UsageLicenseRepresentation usageLicenseRepresentation = createRepresentation();
        usageLicenseRepresentation.setETag(100L);
        usageLicenseRepresentation.setId(100L);

        usageLicenseRepresentation.update();
        VOUsageLicense result = usageLicenseRepresentation.getVO();

        assertThat(result).isNotNull();
        assertThat(result.getRoleDefinition().getDescription()).isEqualTo(usageLicenseRepresentation.getRole().getDescription());
    }

    @Test
    public void shouldConvertToUsageLicenseRepresentation() {
        VOUsageLicense voUsageLicense = createVO();

        UsageLicenseRepresentation representation = new UsageLicenseRepresentation(voUsageLicense);
        representation.convert();

        assertThat(representation.getRole().getDescription())
                .isEqualTo(voUsageLicense.getRoleDefinition().getDescription());
    }

    private VOUsageLicense createVO() {
        VOUsageLicense voUsageLicense = new VOUsageLicense();
        VOUser voUser = new VOUser();
        voUsageLicense.setUser(voUser);
        VORoleDefinition voRoleDefinition = new VORoleDefinition();
        voRoleDefinition.setDescription("100DESC");
        voUsageLicense.setRoleDefinition(voRoleDefinition);

        return voUsageLicense;
    }

    private UsageLicenseRepresentation createRepresentation() {
        UsageLicenseRepresentation usageLicenseRepresentation = new UsageLicenseRepresentation();
        RoleDefinitionRepresentation roleDefinitionRepresentation = new RoleDefinitionRepresentation();
        roleDefinitionRepresentation.setDescription("100DESC");
        usageLicenseRepresentation.setRole(roleDefinitionRepresentation);

        return usageLicenseRepresentation;
    }

}