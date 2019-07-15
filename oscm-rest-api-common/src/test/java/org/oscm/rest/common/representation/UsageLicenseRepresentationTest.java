package org.oscm.rest.common.representation;

import org.junit.jupiter.api.Test;
import org.oscm.internal.vo.VORoleDefinition;
import org.oscm.internal.vo.VOUsageLicense;
import org.oscm.internal.vo.VOUser;
import org.oscm.rest.common.TestContants;

import static org.assertj.core.api.Assertions.assertThat;

public class UsageLicenseRepresentationTest {

    @Test
    public void shouldUpdateVOUsageLicense() {
        UsageLicenseRepresentation usageLicenseRepresentation = createRepresentation();
    usageLicenseRepresentation.setETag(TestContants.LONG_VALUE);
        usageLicenseRepresentation.setId(TestContants.LONG_VALUE);

        usageLicenseRepresentation.update();
        VOUsageLicense result = usageLicenseRepresentation.getVO();

        assertThat(result).isNotNull();
        assertThat(result.getRoleDefinition().getDescription()).isEqualTo(usageLicenseRepresentation.getRole().getDescription());
    }

    @Test
    public void shouldUpdateVOUsageLicense_evenIIdAndETagIsNull() {
        UsageLicenseRepresentation usageLicenseRepresentation = createRepresentation();
        usageLicenseRepresentation.setETag(TestContants.LONG_VALUE);
        usageLicenseRepresentation.setId(TestContants.LONG_VALUE);

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
        voRoleDefinition.setDescription(TestContants.STRING_VALUE);
        voUsageLicense.setRoleDefinition(voRoleDefinition);

        return voUsageLicense;
    }

    private UsageLicenseRepresentation createRepresentation() {
        UsageLicenseRepresentation usageLicenseRepresentation = new UsageLicenseRepresentation();
        RoleDefinitionRepresentation roleDefinitionRepresentation = new RoleDefinitionRepresentation();
        roleDefinitionRepresentation.setDescription(TestContants.STRING_VALUE);
        usageLicenseRepresentation.setRole(roleDefinitionRepresentation);

        return usageLicenseRepresentation;
    }
}