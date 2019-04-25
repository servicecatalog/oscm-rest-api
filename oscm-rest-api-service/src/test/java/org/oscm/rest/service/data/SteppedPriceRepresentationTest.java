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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.oscm.internal.vo.BaseVO;
import org.oscm.internal.vo.VOSteppedPrice;
import org.oscm.rest.common.Representation;

class SteppedPriceRepresentationTest {

  @Test
  public void shouldUpdateVOSteppedPrice() {
    SteppedPriceRepresentation representation = new SteppedPriceRepresentation();
    representation.setId(100L);
    representation.setLimit(100L);
    representation.setPrice(BigDecimal.TEN);
    representation.setETag(100L);

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
    SteppedPriceRepresentation representation = new SteppedPriceRepresentation();
    representation.setLimit(100L);
    representation.setPrice(BigDecimal.TEN);

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
    VOSteppedPrice voSteppedPrice = new VOSteppedPrice();
    voSteppedPrice.setVersion(100);
    voSteppedPrice.setKey(100L);
    voSteppedPrice.setLimit(100L);
    voSteppedPrice.setPrice(BigDecimal.TEN);

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
}
