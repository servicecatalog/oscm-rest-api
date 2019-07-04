/**
 * *****************************************************************************
 *
 * <p>Copyright FUJITSU LIMITED 2019
 *
 * <p>Creation Date: 25-04-2019
 *
 * <p>*****************************************************************************
 */
package org.oscm.rest.service.data;

import org.junit.jupiter.api.Test;
import org.oscm.internal.vo.BaseVO;
import org.oscm.internal.vo.VOPricedRole;
import org.oscm.internal.vo.VORoleDefinition;
import org.oscm.rest.common.representation.PricedRoleRepresentation;
import org.oscm.rest.common.representation.Representation;
import org.oscm.rest.common.representation.RoleDefinitionRepresentation;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class PricedRoleRepresentationTest {

  @Test
  public void shouldUpdateVOPricedRole() {
    PricedRoleRepresentation representation = createRepresentation();
    representation.setId(100L);
    representation.setETag(100L);

    representation.update();
    VOPricedRole result = representation.getVO();

    assertThat(result).isNotNull();
    assertThat(result).extracting(BaseVO::getKey).isEqualTo(representation.convertIdToKey());
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
    PricedRoleRepresentation representation = createRepresentation();

    representation.update();
    VOPricedRole result = representation.getVO();

    assertThat(result).isNotNull();
    assertThat(result).extracting(BaseVO::getKey).isEqualTo(representation.convertIdToKey());
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
    VOPricedRole voPricedRole = createVO();

    PricedRoleRepresentation representation = new PricedRoleRepresentation(voPricedRole);
    representation.convert();

    assertThat(representation)
        .extracting(PricedRoleRepresentation::convertETagToVersion)
        .isEqualTo(voPricedRole.getVersion());
    assertThat(representation).extracting(Representation::getId).isEqualTo(voPricedRole.getKey());
    assertThat(representation)
        .extracting(PricedRoleRepresentation::getPricePerUser)
        .isEqualTo(voPricedRole.getPricePerUser());
    assertThat(representation.getRole().getDescription())
        .isEqualTo(voPricedRole.getRole().getDescription());
  }

  private PricedRoleRepresentation createRepresentation() {
    PricedRoleRepresentation representation = new PricedRoleRepresentation();
    representation.setPricePerUser(BigDecimal.TEN);
    RoleDefinitionRepresentation roleDefinitionRepresentation = new RoleDefinitionRepresentation();
    roleDefinitionRepresentation.setRoleId("Role100");
    representation.setRole(roleDefinitionRepresentation);
    return representation;
  }

  private VOPricedRole createVO() {
    VOPricedRole voPricedRole = new VOPricedRole();
    voPricedRole.setVersion(100);
    voPricedRole.setKey(100L);
    voPricedRole.setPricePerUser(BigDecimal.TEN);
    VORoleDefinition voRoleDefinition = new VORoleDefinition();
    voRoleDefinition.setDescription("Description100");
    voPricedRole.setRole(voRoleDefinition);
    return voPricedRole;
  }
}
