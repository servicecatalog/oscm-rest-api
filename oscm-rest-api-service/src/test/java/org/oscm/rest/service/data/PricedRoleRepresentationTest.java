package org.oscm.rest.service.data;

import org.junit.jupiter.api.Test;
import org.oscm.internal.vo.BaseVO;
import org.oscm.internal.vo.VOPricedRole;
import org.oscm.internal.vo.VORoleDefinition;
import org.oscm.rest.common.Representation;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PricedRoleRepresentationTest {

    @Test
    public void shouldUpdateVOPricedRole() {
        PricedRoleRepresentation representation = new PricedRoleRepresentation();
        representation.setId(100L);
        representation.setPricePerUser(BigDecimal.TEN);
        RoleDefinitionRepresentation roleDefinitionRepresentation = new RoleDefinitionRepresentation();
        roleDefinitionRepresentation.setRoleId("Role100");
        representation.setRole(roleDefinitionRepresentation);
        representation.setETag(100L);

        representation.update();
        VOPricedRole result = representation.getVO();

        assertThat(result).isNotNull();
        assertThat(result)
                .extracting(BaseVO::getKey)
                .isEqualTo(representation.convertIdToKey());
        assertThat(result)
                .extracting(VOPricedRole::getPricePerUser)
                .isEqualTo(representation.getPricePerUser());
        assertThat(result.getRole().getRoleId()).isEqualTo(representation.getRole().getRoleId());
        assertThat(result)
                .extracting(BaseVO::getVersion)
                .isEqualTo(representation.convertETagToVersion());
    }

    @Test
    public void shouldUpdateVOPricedRole_evenIfIdAndETagIsNull() {
        PricedRoleRepresentation representation = new PricedRoleRepresentation();
        representation.setPricePerUser(BigDecimal.TEN);
        RoleDefinitionRepresentation roleDefinitionRepresentation = new RoleDefinitionRepresentation();
        roleDefinitionRepresentation.setRoleId("Role100");
        representation.setRole(roleDefinitionRepresentation);

        representation.update();
        VOPricedRole result = representation.getVO();

        assertThat(result).isNotNull();
        assertThat(result)
                .extracting(BaseVO::getKey)
                .isEqualTo(representation.convertIdToKey());
        assertThat(result)
                .extracting(VOPricedRole::getPricePerUser)
                .isEqualTo(representation.getPricePerUser());
        assertThat(result.getRole().getRoleId()).isEqualTo(representation.getRole().getRoleId());
        assertThat(result)
                .extracting(BaseVO::getVersion)
                .isEqualTo(representation.convertETagToVersion());
    }

    @Test
    public void shouldConvertToPricedRoleRepresentation() {
        VOPricedRole voPricedRole = new VOPricedRole();
        voPricedRole.setVersion(100);
        voPricedRole.setKey(100L);
        voPricedRole.setPricePerUser(BigDecimal.TEN);
        VORoleDefinition voRoleDefinition = new VORoleDefinition();
        voRoleDefinition.setDescription("Description100");
        voPricedRole.setRole(voRoleDefinition);

        PricedRoleRepresentation representation = new PricedRoleRepresentation(voPricedRole);
        representation.convert();


        assertThat(representation)
                .extracting(PricedRoleRepresentation::convertETagToVersion)
                .isEqualTo(voPricedRole.getVersion());
        assertThat(representation)
                .extracting(Representation::getId)
                .isEqualTo(voPricedRole.getKey());
        assertThat(representation)
                .extracting(PricedRoleRepresentation::getPricePerUser)
                .isEqualTo(voPricedRole.getPricePerUser());
        assertThat(representation.getRole().getDescription())
                .isEqualTo(voPricedRole.getRole().getDescription());
    }

}