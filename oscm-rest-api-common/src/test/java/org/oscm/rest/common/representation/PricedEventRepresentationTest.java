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
import org.oscm.internal.vo.VOEventDefinition;
import org.oscm.internal.vo.VOPricedEvent;
import org.oscm.internal.vo.VOSteppedPrice;
import org.oscm.rest.common.TestContants;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PricedEventRepresentationTest {

  @Test
  public void shouldUpdateVOPricedEvent() {
    PricedEventRepresentation representation = createRepresentation();
    representation.setId(TestContants.LONG_VALUE);
    representation.setETag(TestContants.LONG_VALUE);

    representation.update();
    VOPricedEvent result = representation.getVO();

    assertThat(result).isNotNull();
    assertThat(result.getEventDefinition().getEventId())
        .isEqualTo(representation.getEventDefinition().getEventId());
    assertThat(result)
        .extracting(VOPricedEvent::getEventPrice)
        .isEqualTo(representation.getEventPrice());
    assertThat(result).extracting(BaseVO::getKey).isEqualTo(representation.convertIdToKey());
    assertThat(
            ((SteppedPriceRepresentation) representation.getSteppedPrices().toArray()[0])
                .getLimit())
        .isEqualTo(((VOSteppedPrice) result.getSteppedPrices().toArray()[0]).getLimit());
    assertThat(result)
        .extracting(BaseVO::getVersion)
        .isEqualTo(representation.convertETagToVersion());
  }

  @Test
  public void shouldUpdateVOPricedEvent_evenIfIdAndETagIsNull() {
    PricedEventRepresentation representation = createRepresentation();

    representation.update();
    VOPricedEvent result = representation.getVO();

    assertThat(result).isNotNull();
    assertThat(result.getEventDefinition().getEventId())
        .isEqualTo(representation.getEventDefinition().getEventId());
    assertThat(result)
        .extracting(VOPricedEvent::getEventPrice)
        .isEqualTo(representation.getEventPrice());
    assertThat(result).extracting(BaseVO::getKey).isEqualTo(representation.convertIdToKey());
    assertThat(
            ((SteppedPriceRepresentation) representation.getSteppedPrices().toArray()[0])
                .getLimit())
        .isEqualTo(((VOSteppedPrice) result.getSteppedPrices().toArray()[0]).getLimit());
    assertThat(result)
        .extracting(BaseVO::getVersion)
        .isEqualTo(representation.convertETagToVersion());
  }

  @Test
  public void shouldConvertToPricedEventRepresentation() {
    VOPricedEvent voPricedEvent = createVO();

    PricedEventRepresentation representation = new PricedEventRepresentation(voPricedEvent);
    representation.convert();

    assertThat(representation).extracting(Representation::getId).isEqualTo(voPricedEvent.getKey());
    assertThat(representation.getEventDefinition().getVO())
        .isEqualTo(voPricedEvent.getEventDefinition());
    assertThat(representation)
        .extracting(PricedEventRepresentation::getEventPrice)
        .isEqualTo(voPricedEvent.getEventPrice());
    assertThat(representation)
        .extracting(PricedEventRepresentation::convertETagToVersion)
        .isEqualTo(voPricedEvent.getVersion());
  }

  private PricedEventRepresentation createRepresentation() {
    PricedEventRepresentation representation = new PricedEventRepresentation();
    EventDefinitionRepresentation eventDefinitionRepresentation =
        new EventDefinitionRepresentation();
    eventDefinitionRepresentation.setEventId(TestContants.STRING_VALUE);
    representation.setEventDefinition(eventDefinitionRepresentation);
    representation.setEventPrice(BigDecimal.TEN);
    List<SteppedPriceRepresentation> list = new ArrayList<>();
    SteppedPriceRepresentation steppedPriceRepresentation = new SteppedPriceRepresentation();
    steppedPriceRepresentation.setLimit(TestContants.LONG_VALUE);
    list.add(steppedPriceRepresentation);
    representation.setSteppedPrices(list);
    return representation;
  }

  private VOPricedEvent createVO() {
    VOPricedEvent voPricedEvent = new VOPricedEvent();
    voPricedEvent.setVersion(TestContants.INTEGER_VALUE);
    VOEventDefinition voEventDefinition = new VOEventDefinition();
    voEventDefinition.setEventId(TestContants.STRING_VALUE);
    voEventDefinition.setEventDescription(TestContants.STRING_VALUE);
    voPricedEvent.setEventDefinition(voEventDefinition);
    voPricedEvent.setEventPrice(BigDecimal.TEN);
    voPricedEvent.setKey(TestContants.LONG_VALUE);
    return voPricedEvent;
  }
}
