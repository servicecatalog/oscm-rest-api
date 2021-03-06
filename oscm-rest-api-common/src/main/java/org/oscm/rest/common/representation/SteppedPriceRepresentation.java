/**
 * *****************************************************************************
 *
 * <p>Copyright FUJITSU LIMITED 2019
 *
 * <p>Creation Date: 10-04-2019
 *
 * <p>*****************************************************************************
 */
package org.oscm.rest.common.representation;

import org.oscm.internal.vo.VOSteppedPrice;

import javax.ws.rs.WebApplicationException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class SteppedPriceRepresentation extends Representation {

  private transient VOSteppedPrice vo;

  private Long limit;
  private BigDecimal price = BigDecimal.ZERO;

  public SteppedPriceRepresentation() {
    this(new VOSteppedPrice());
  }

  public SteppedPriceRepresentation(VOSteppedPrice sp) {
    vo = sp;
  }

  @Override
  public void validateContent() throws WebApplicationException {}

  @Override
  public void update() {
    vo.setKey(convertIdToKey());
    vo.setLimit(getLimit());
    vo.setPrice(getPrice());
    vo.setVersion(convertETagToVersion());
  }

  @Override
  public void convert() {
    setETag(Long.valueOf(vo.getVersion()));
    setId(Long.valueOf(vo.getKey()));
    setLimit(vo.getLimit());
    setPrice(vo.getPrice());
  }

  public Long getLimit() {
    return limit;
  }

  public void setLimit(Long limit) {
    this.limit = limit;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public VOSteppedPrice getVO() {
    return vo;
  }

  public static List<SteppedPriceRepresentation> convert(List<VOSteppedPrice> steppedPrices) {
    if (steppedPrices == null || steppedPrices.isEmpty()) {
      return null;
    }
    List<SteppedPriceRepresentation> result = new ArrayList<SteppedPriceRepresentation>();
    for (VOSteppedPrice sp : steppedPrices) {
      SteppedPriceRepresentation spr = new SteppedPriceRepresentation(sp);
      spr.convert();
      result.add(spr);
    }
    return result;
  }

  public static List<VOSteppedPrice> update(List<SteppedPriceRepresentation> steppedPrices) {
    List<VOSteppedPrice> result = new ArrayList<VOSteppedPrice>();
    if (steppedPrices == null) {
      return result;
    }
    for (SteppedPriceRepresentation spr : steppedPrices) {
      spr.update();
      result.add(spr.getVO());
    }
    return result;
  }
}
