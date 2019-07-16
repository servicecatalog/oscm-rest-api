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
import org.oscm.rest.common.TestContants;

import static org.assertj.core.api.Assertions.assertThat;

public class RoleDefinitionRepresentationTest {

  @Test
  public void shouldUpdateVORoleDefinition() {
    RoleDefinitionRepresentation representation = createRepresentation();
    representation.setId(TestContants.LONG_VALUE);
    representation.setETag(TestContants.LONG_VALUE);

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
    voRoleDefinition.setDescription(TestContants.STRING_VALUE);
    voRoleDefinition.setKey(TestContants.LONG_VALUE);
    voRoleDefinition.setName(TestContants.STRING_VALUE);
    voRoleDefinition.setRoleId(TestContants.STRING_VALUE);
    voRoleDefinition.setVersion(TestContants.INTEGER_VALUE);
    return voRoleDefinition;
  }

  private RoleDefinitionRepresentation createRepresentation() {
    RoleDefinitionRepresentation representation = new RoleDefinitionRepresentation();
    representation.setDescription(TestContants.STRING_VALUE);
    representation.setName(TestContants.STRING_VALUE);
    representation.setRoleId(TestContants.STRING_NUM_VALUE);
    return representation;
  }
}
