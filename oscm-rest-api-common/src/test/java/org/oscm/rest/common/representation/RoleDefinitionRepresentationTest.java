/**
 * *****************************************************************************
 *
 * <p>Copyright FUJITSU LIMITED 2019
 *
 * <p>Creation Date: 24-04-2019
 *
 * <p>*****************************************************************************
 */
package org.oscm.rest.common.representation;

import org.junit.jupiter.api.Test;
import org.oscm.internal.vo.BaseVO;
import org.oscm.internal.vo.VORoleDefinition;
import org.oscm.rest.common.representation.Representation;
import org.oscm.rest.common.representation.RoleDefinitionRepresentation;

import static org.assertj.core.api.Assertions.assertThat;

public class RoleDefinitionRepresentationTest {

  @Test
  public void shouldUpdateVORoleDefinition() {
    RoleDefinitionRepresentation representation = createRepresentation();
    representation.setId(100L);
    representation.setETag(100L);

    representation.update();
    VORoleDefinition result = representation.getVO();

    assertThat(result).isNotNull();
    assertThat(result)
        .extracting(VORoleDefinition::getDescription)
        .isEqualTo(representation.getDescription());
    assertThat(result).extracting(BaseVO::getKey).isEqualTo(representation.convertIdToKey());
    assertThat(result).extracting(VORoleDefinition::getName).isEqualTo(representation.getName());
    assertThat(result)
        .extracting(VORoleDefinition::getRoleId)
        .isEqualTo(representation.getRoleId());
    assertThat(result)
        .extracting(BaseVO::getVersion)
        .isEqualTo(representation.convertETagToVersion());
  }

  @Test
  public void shouldUpdateVORoleDefinition_evenIfIdAndETagIsNull() {
    RoleDefinitionRepresentation representation = createRepresentation();

    representation.update();
    VORoleDefinition result = representation.getVO();

    assertThat(result).isNotNull();
    assertThat(result)
        .extracting(VORoleDefinition::getDescription)
        .isEqualTo(representation.getDescription());
    assertThat(result).extracting(BaseVO::getKey).isEqualTo(representation.convertIdToKey());
    assertThat(result).extracting(VORoleDefinition::getName).isEqualTo(representation.getName());
    assertThat(result)
        .extracting(VORoleDefinition::getRoleId)
        .isEqualTo(representation.getRoleId());
    assertThat(result)
        .extracting(BaseVO::getVersion)
        .isEqualTo(representation.convertETagToVersion());
  }

  @Test
  public void shouldConvertToRoleDefinitionRepresentation() {
    VORoleDefinition voRoleDefinition = createVO();

    RoleDefinitionRepresentation representation =
        new RoleDefinitionRepresentation(voRoleDefinition);
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

  private VORoleDefinition createVO() {
    VORoleDefinition voRoleDefinition = new VORoleDefinition();
    voRoleDefinition.setDescription("Description");
    voRoleDefinition.setKey(100L);
    voRoleDefinition.setName("Name");
    voRoleDefinition.setRoleId("100");
    voRoleDefinition.setVersion(100);
    return voRoleDefinition;
  }

  private RoleDefinitionRepresentation createRepresentation() {
    RoleDefinitionRepresentation representation = new RoleDefinitionRepresentation();
    representation.setDescription("Description");
    representation.setName("Name");
    representation.setRoleId("100");
    return representation;
  }
}
