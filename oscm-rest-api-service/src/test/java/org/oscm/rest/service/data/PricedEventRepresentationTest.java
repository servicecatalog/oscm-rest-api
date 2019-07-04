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
import org.oscm.internal.vo.VOEventDefinition;
import org.oscm.internal.vo.VOPricedEvent;
import org.oscm.internal.vo.VOSteppedPrice;
import org.oscm.rest.common.representation.Representation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PricedEventRepresentationTest {

  @Test
  public void shouldUpdateVOPricedEvent() {
    PricedEventRepresentation representation = createRepresentation();
    representation.setId(100L);
    representation.setETag(100L);

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
    eventDefinitionRepresentation.setEventId("Event123");
    representation.setEventDefinition(eventDefinitionRepresentation);
    representation.setEventPrice(BigDecimal.TEN);
    List<SteppedPriceRepresentation> list = new ArrayList<>();
    SteppedPriceRepresentation steppedPriceRepresentation = new SteppedPriceRepresentation();
    steppedPriceRepresentation.setLimit(123L);
    list.add(steppedPriceRepresentation);
    representation.setSteppedPrices(list);
    return representation;
  }

  private VOPricedEvent createVO() {
    VOPricedEvent voPricedEvent = new VOPricedEvent();
    voPricedEvent.setVersion(100);
    VOEventDefinition voEventDefinition = new VOEventDefinition();
    voEventDefinition.setEventId("Event123");
    voEventDefinition.setEventDescription("Description");
    voPricedEvent.setEventDefinition(voEventDefinition);
    voPricedEvent.setEventPrice(BigDecimal.TEN);
    voPricedEvent.setKey(100L);
    return voPricedEvent;
  }
}
