package org.oscm.rest.service.data;

import org.junit.jupiter.api.Test;
import org.oscm.internal.vo.BaseVO;
import org.oscm.internal.vo.VORoleDefinition;
import org.oscm.rest.common.Representation;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class RoleDefinitionRepresentationTest {

    @Test
    public void shouldUpdateVORoleDefinition() {
        RoleDefinitionRepresentation representation = new RoleDefinitionRepresentation();
        representation.setDescription("Description");
        representation.setId(100L);
        representation.setName("Name");
        representation.setRoleId("100");
        representation.setETag(100L);

        representation.update();
        VORoleDefinition result = representation.getVO();

        assertThat(result).isNotNull();
        assertThat(result)
                .extracting(VORoleDefinition::getDescription)
                .isEqualTo(representation.getDescription());
        assertThat(result)
                .extracting(BaseVO::getKey)
                .isEqualTo(representation.convertIdToKey());
        assertThat(result)
                .extracting(VORoleDefinition::getName)
                .isEqualTo(representation.getName());
        assertThat(result)
                .extracting(VORoleDefinition::getRoleId)
                .isEqualTo(representation.getRoleId());
        assertThat(result)
                .extracting(BaseVO::getVersion)
                .isEqualTo(representation.convertETagToVersion());
    }

    @Test
    public void shouldUpdateVORoleDefinition_evenIfIdAndETagIsNull() {
        RoleDefinitionRepresentation representation = new RoleDefinitionRepresentation();
        representation.setDescription("Description");
        representation.setName("Name");
        representation.setRoleId("100");

        representation.update();
        VORoleDefinition result = representation.getVO();

        assertThat(result).isNotNull();
        assertThat(result)
                .extracting(VORoleDefinition::getDescription)
                .isEqualTo(representation.getDescription());
        assertThat(result)
                .extracting(BaseVO::getKey)
                .isEqualTo(representation.convertIdToKey());
        assertThat(result)
                .extracting(VORoleDefinition::getName)
                .isEqualTo(representation.getName());
        assertThat(result)
                .extracting(VORoleDefinition::getRoleId)
                .isEqualTo(representation.getRoleId());
        assertThat(result)
                .extracting(BaseVO::getVersion)
                .isEqualTo(representation.convertETagToVersion());
    }

    @Test
    public void shouldConvertToRoleDefinitionRepresentation() {
        VORoleDefinition voRoleDefinition = new VORoleDefinition();
        voRoleDefinition.setDescription("Description");
        voRoleDefinition.setKey(100L);
        voRoleDefinition.setName("Name");
        voRoleDefinition.setRoleId("100");
        voRoleDefinition.setVersion(100);

        RoleDefinitionRepresentation representation = new RoleDefinitionRepresentation(voRoleDefinition);
        representation.convert();

        assertThat(representation)
                .extracting(RoleDefinitionRepresentation::getDescription)
                .isEqualTo(voRoleDefinition.getDescription());
        assertThat(representation)
                .extracting(Representation::getId)
                .isEqualTo(voRoleDefinition.getKey());
        assertThat(representation)
                .extracting(RoleDefinitionRepresentation::getName)
                .isEqualTo(voRoleDefinition.getName());
        assertThat(representation)
                .extracting(RoleDefinitionRepresentation::getRoleId)
                .isEqualTo(voRoleDefinition.getRoleId());
        assertThat(representation)
                .extracting(Representation::getETag)
                .isEqualTo((long) voRoleDefinition.getVersion());
    }
}