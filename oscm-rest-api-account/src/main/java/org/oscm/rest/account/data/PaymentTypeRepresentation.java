/**
 * *****************************************************************************
 *
 * <p>Copyright FUJITSU LIMITED 2019
 *
 * <p>Creation Date: 10-04-2019
 *
 * <p>*****************************************************************************
 */
package org.oscm.rest.account.data;

import lombok.Generated;
import org.oscm.internal.types.enumtypes.PaymentCollectionType;
import org.oscm.internal.vo.VOPaymentType;
import org.oscm.rest.common.Representation;

public class PaymentTypeRepresentation extends Representation {

  private transient VOPaymentType vo;

  private String name;
  private String paymentTypeId;
  private PaymentCollectionType collectionType;

  public PaymentTypeRepresentation() {
    this(new VOPaymentType());
  }

  public PaymentTypeRepresentation(VOPaymentType pt) {
    vo = pt;
  }

  @Override
  public void update() {
    vo.setCollectionType(collectionType);
    vo.setKey(convertIdToKey());
    vo.setName(name);
    vo.setPaymentTypeId(paymentTypeId);
    vo.setVersion(convertETagToVersion());
  }

  @Override
  public void convert() {
    setCollectionType(vo.getCollectionType());
    setId(Long.valueOf(vo.getKey()));
    setName(vo.getName());
    setPaymentTypeId(vo.getPaymentTypeId());
    setETag(Long.valueOf(vo.getVersion()));
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPaymentTypeId() {
    return paymentTypeId;
  }

  public void setPaymentTypeId(String paymentTypeId) {
    this.paymentTypeId = paymentTypeId;
  }

  public PaymentCollectionType getCollectionType() {
    return collectionType;
  }

  public void setCollectionType(PaymentCollectionType collectionType) {
    this.collectionType = collectionType;
  }

  public VOPaymentType getVO() {
    return vo;
  }
}
