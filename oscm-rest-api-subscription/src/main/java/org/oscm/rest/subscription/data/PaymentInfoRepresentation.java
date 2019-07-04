/**
 * *****************************************************************************
 *
 * <p>Copyright FUJITSU LIMITED 2019
 *
 * <p>Creation Date: 10-04-2019
 *
 * <p>*****************************************************************************
 */
package org.oscm.rest.subscription.data;

import org.oscm.internal.vo.VOPaymentInfo;
import org.oscm.rest.common.representation.Representation;

import javax.ws.rs.WebApplicationException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PaymentInfoRepresentation extends Representation {

  private transient VOPaymentInfo vo;

  private String infoId;

  public PaymentInfoRepresentation() {
    this(new VOPaymentInfo());
  }

  public PaymentInfoRepresentation(VOPaymentInfo pi) {
    vo = pi;
  }

  @Override
  public void validateContent() throws WebApplicationException {
    // nothing to do
  }

  @Override
  public void update() {
    vo.setId(infoId);
    vo.setKey(convertIdToKey());
    vo.setVersion(convertETagToVersion());
  }

  @Override
  public void convert() {
    setId(Long.valueOf(vo.getKey()));
    setInfoId(vo.getId());
    setETag(Long.valueOf(vo.getVersion()));
  }

  public String getInfoId() {
    return infoId;
  }

  public void setInfoId(String infoId) {
    this.infoId = infoId;
  }

  public VOPaymentInfo getVO() {
    return vo;
  }

  public static Collection<PaymentInfoRepresentation> convert(List<VOPaymentInfo> paymentInfos) {
    List<PaymentInfoRepresentation> result = new ArrayList<PaymentInfoRepresentation>();
    for (VOPaymentInfo pi : paymentInfos) {
      result.add(new PaymentInfoRepresentation(pi));
    }
    return result;
  }
}
