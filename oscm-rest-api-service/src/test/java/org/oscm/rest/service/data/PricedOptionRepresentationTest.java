/**
 * *****************************************************************************
 *
 * <p>Copyright FUJITSU LIMITED 2019
 *
 * <p>Creation Date: 24-04-2019
 *
 * <p>*****************************************************************************
 */
package org.oscm.rest.service.data;

import org.junit.jupiter.api.Test;
import org.oscm.internal.vo.BaseVO;
import org.oscm.internal.vo.VOPricedOption;
import org.oscm.internal.vo.VOPricedRole;
import org.oscm.internal.vo.VORoleDefinition;
import org.oscm.rest.common.Representation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PricedOptionRepresentationTest {

  @Test
  public void shouldUpdateVOPricedOption() {
    PricedOptionRepresentation representation = createRepresentation();
    representation.setId(100L);
    representation.setETag(100L);

    representation.update();
    VOPricedOption result = representation.getVO();

    assertThat(result).isNotNull();
    assertThat(result).extracting(BaseVO::getKey).isEqualTo(representation.convertIdToKey());
    assertThat(result)
        .extracting(VOPricedOption::getOptionId)
        .isEqualTo(representation.getOptionId());
    assertThat(result)
        .extracting(VOPricedOption::getParameterOptionKey)
        .isEqualTo(representation.getParameterOptionKey());
    assertThat(result)
        .extracting(VOPricedOption::getPricePerSubscription)
        .isEqualTo(representation.getPricePerSubscription());
    assertThat(result)
        .extracting(VOPricedOption::getPricePerUser)
        .isEqualTo(representation.getPricePerUser());
    assertThat(((VOPricedRole) result.getRoleSpecificUserPrices().toArray()[0]).getPricePerUser())
        .isEqualTo(
            ((PricedRoleRepresentation) representation.getRoleSpecificUserPrices().toArray()[0])
                .getPricePerUser());
    assertThat(result)
        .extracting(BaseVO::getVersion)
        .isEqualTo(representation.convertETagToVersion());
  }

  @Test
  public void shouldUpdateVOPricedOption_evenIfIdAndETagIsNull() {
    PricedOptionRepresentation representation = createRepresentation();

    representation.update();
    VOPricedOption result = representation.getVO();

    assertThat(result).isNotNull();
    assertThat(result).extracting(BaseVO::getKey).isEqualTo(representation.convertIdToKey());
    assertThat(result)
        .extracting(VOPricedOption::getOptionId)
        .isEqualTo(representation.getOptionId());
    assertThat(result)
        .extracting(VOPricedOption::getParameterOptionKey)
        .isEqualTo(representation.getParameterOptionKey());
    assertThat(result)
        .extracting(VOPricedOption::getPricePerSubscription)
        .isEqualTo(representation.getPricePerSubscription());
    assertThat(result)
        .extracting(VOPricedOption::getPricePerUser)
        .isEqualTo(representation.getPricePerUser());
    assertThat(((VOPricedRole) result.getRoleSpecificUserPrices().toArray()[0]).getPricePerUser())
        .isEqualTo(
            ((PricedRoleRepresentation) representation.getRoleSpecificUserPrices().toArray()[0])
                .getPricePerUser());
    assertThat(result)
        .extracting(BaseVO::getVersion)
        .isEqualTo(representation.convertETagToVersion());
  }

  @Test
  public void shouldConvertToPricedOptionRepresentation() {
    VOPricedOption voPricedOption = createVO();

    PricedOptionRepresentation representation = new PricedOptionRepresentation(voPricedOption);
    representation.convert();

    assertThat(representation)
        .extracting(PricedOptionRepresentation::convertETagToVersion)
        .isEqualTo(voPricedOption.getVersion());
    assertThat(representation).extracting(Representation::getId).isEqualTo(voPricedOption.getKey());
    assertThat(representation)
        .extracting(PricedOptionRepresentation::getOptionId)
        .isEqualTo(voPricedOption.getOptionId());
    assertThat(representation)
        .extracting(PricedOptionRepresentation::getParameterOptionKey)
        .isEqualTo(voPricedOption.getParameterOptionKey());
    assertThat(representation)
        .extracting(PricedOptionRepresentation::getPricePerSubscription)
        .isEqualTo(voPricedOption.getPricePerSubscription());
  }

  private PricedOptionRepresentation createRepresentation() {
    PricedOptionRepresentation representation = new PricedOptionRepresentation();
    representation.setOptionId("100");
    representation.setParameterOptionKey(100L);
    representation.setPricePerSubscription(BigDecimal.TEN);
    representation.setPricePerUser(BigDecimal.TEN);
    List<PricedRoleRepresentation> list = new ArrayList<>();
    PricedRoleRepresentation pricedRoleRepresentation = new PricedRoleRepresentation();
    pricedRoleRepresentation.setPricePerUser(BigDecimal.TEN);
    list.add(pricedRoleRepresentation);
    representation.setRoleSpecificUserPrices(list);
    return representation;
  }

  private VOPricedOption createVO() {
    VOPricedOption voPricedOption = new VOPricedOption();
    voPricedOption.setKey(100L);
    voPricedOption.setOptionId("Option100");
    voPricedOption.setParameterOptionKey(100L);
    voPricedOption.setPricePerSubscription(BigDecimal.TEN);
    voPricedOption.setPricePerUser(BigDecimal.TEN);
    List<VOPricedRole> list = new ArrayList<>();
    VOPricedRole voPricedRole = new VOPricedRole();
    voPricedRole.setPricePerUser(BigDecimal.ZERO);
    VORoleDefinition voRoleDefinition = new VORoleDefinition();
    voPricedRole.setRole(voRoleDefinition);
    list.add(voPricedRole);
    voPricedOption.setRoleSpecificUserPrices(list);
    voPricedOption.setVersion(100);
    return voPricedOption;
  }
}
