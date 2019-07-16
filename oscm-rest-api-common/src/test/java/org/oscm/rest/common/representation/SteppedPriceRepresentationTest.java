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
import org.oscm.internal.vo.VOSteppedPrice;
import org.oscm.rest.common.TestContants;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class SteppedPriceRepresentationTest {

  @Test
  public void shouldUpdateVOSteppedPrice() {
    SteppedPriceRepresentation representation = createRepresentation();
    representation.setId(TestContants.LONG_VALUE);
    representation.setETag(TestContants.LONG_VALUE);

    representation.update();
    VOSteppedPrice result = representation.getVO();

    assertThat(result).isNotNull();
    assertThat(result).extracting(BaseVO::getKey).isEqualTo(representation.convertIdToKey());
    assertThat(result).extracting(VOSteppedPrice::getLimit).isEqualTo(representation.getLimit());
    assertThat(result).extracting(VOSteppedPrice::getPrice).isEqualTo(representation.getPrice());
    assertThat(result)
        .extracting(BaseVO::getVersion)
        .isEqualTo(representation.convertETagToVersion());
  }

  @Test
  public void shouldUpdateVOSteppedPrice_evenIfIdAndETagIsNull() {
    SteppedPriceRepresentation representation = createRepresentation();

    representation.update();
    VOSteppedPrice result = representation.getVO();

    assertThat(result).isNotNull();
    assertThat(result).extracting(BaseVO::getKey).isEqualTo(representation.convertIdToKey());
    assertThat(result).extracting(VOSteppedPrice::getLimit).isEqualTo(representation.getLimit());
    assertThat(result).extracting(VOSteppedPrice::getPrice).isEqualTo(representation.getPrice());
    assertThat(result)
        .extracting(BaseVO::getVersion)
        .isEqualTo(representation.convertETagToVersion());
  }

  @Test
  public void shouldConvertToSteppedPriceRepresentation() {
    VOSteppedPrice voSteppedPrice = createVO();

    SteppedPriceRepresentation representation = new SteppedPriceRepresentation(voSteppedPrice);
    representation.convert();

    assertThat(representation).extracting(Representation::getId).isEqualTo(voSteppedPrice.getKey());
    assertThat(representation)
        .extracting(SteppedPriceRepresentation::convertETagToVersion)
        .isEqualTo(voSteppedPrice.getVersion());
    assertThat(representation)
        .extracting(SteppedPriceRepresentation::getLimit)
        .isEqualTo(voSteppedPrice.getLimit());
    assertThat(representation)
        .extracting(SteppedPriceRepresentation::getPrice)
        .isEqualTo(voSteppedPrice.getPrice());
  }

  private SteppedPriceRepresentation createRepresentation() {
    SteppedPriceRepresentation representation = new SteppedPriceRepresentation();
    representation.setLimit(TestContants.LONG_VALUE);
    representation.setPrice(BigDecimal.TEN);
    return representation;
  }

  private VOSteppedPrice createVO() {
    VOSteppedPrice voSteppedPrice = new VOSteppedPrice();
    voSteppedPrice.setVersion(TestContants.INTEGER_VALUE);
    voSteppedPrice.setKey(TestContants.LONG_VALUE);
    voSteppedPrice.setLimit(TestContants.LONG_VALUE);
    voSteppedPrice.setPrice(BigDecimal.TEN);
    return voSteppedPrice;
  }
}
