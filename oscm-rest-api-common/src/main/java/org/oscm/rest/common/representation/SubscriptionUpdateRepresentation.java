/**
 * *****************************************************************************
 *
 * <p>Copyright FUJITSU LIMITED 2020
 *
 * <p>Creation Date: 07-05-2020
 *
 * <p>*****************************************************************************
 */
package org.oscm.rest.common.representation;

import java.util.HashMap;
import java.util.Map;

public class SubscriptionUpdateRepresentation extends Representation {

  private String subscriptionId;
  private String purchaseOrderNumber;
  private String billingContactId;
  private String paymentInfoId;

  private Map<String, String> udas = new HashMap<>();
  private Map<String, String> parameters = new HashMap<>();

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
