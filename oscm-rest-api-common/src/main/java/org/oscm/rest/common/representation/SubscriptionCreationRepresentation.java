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

import org.oscm.internal.vo.VOSubscription;
import org.oscm.rest.common.validator.RequiredFieldValidator;

import java.util.HashMap;
import java.util.Map;

public class SubscriptionCreationRepresentation extends Representation {

  private transient VOSubscription vo;

  private String serviceKey;
  private String subscriptionId;
  private String purchaseOrderNumber;
  private String unitName;
  private String billingContactId;
  private String paymentInfoId;

  private Map<String, String> udas = new HashMap<>();
  private Map<String, String> parameters = new HashMap<>();

  public SubscriptionCreationRepresentation() {
    this(new VOSubscription());
  }

  public SubscriptionCreationRepresentation(VOSubscription sub) {
    vo = sub;
  }

  @Override
  public void validateContent() {
    RequiredFieldValidator validator = new RequiredFieldValidator();
    validator.validateNotBlank("serviceKey", serviceKey);
    validator.validateNotBlank("subscriptionId", subscriptionId);
  }

  @Override
  public void update() {
    vo.setPurchaseOrderNumber(getPurchaseOrderNumber());
    vo.setSubscriptionId(getSubscriptionId());
    vo.setUnitName(getUnitName());
  }

  public VOSubscription getVO() {
    return vo;
  }

  public String getBillingContactId() {
    return billingContactId;
  }

  public void setBillingContactId(String billingContactId) {
    this.billingContactId = billingContactId;
  }

  public String getPaymentInfoId() {
    return paymentInfoId;
  }

  public void setPaymentInfoId(String paymentInfoId) {
    this.paymentInfoId = paymentInfoId;
  }

  public String getPurchaseOrderNumber() {
    return purchaseOrderNumber;
  }

  public void setPurchaseOrderNumber(String purchaseOrderNumber) {
    this.purchaseOrderNumber = purchaseOrderNumber;
  }

  public String getSubscriptionId() {
    return subscriptionId;
  }

  public void setSubscriptionId(String subscriptionId) {
    this.subscriptionId = subscriptionId;
  }

  public String getUnitName() {
    return unitName;
  }

  public void setUnitName(String unitName) {
    this.unitName = unitName;
  }

  public String getServiceKey() {
    return serviceKey;
  }

  public void setServiceKey(String serviceKey) {
    this.serviceKey = serviceKey;
  }

  public Map<String, String> getParameters() {
    return parameters;
  }

  public void setParameters(Map<String, String> parameters) {
    this.parameters = parameters;
  }

  public Map<String, String> getUdas() {
    return udas;
  }

  public void setUdas(Map<String, String> udas) {
    this.udas = udas;
  }
}
