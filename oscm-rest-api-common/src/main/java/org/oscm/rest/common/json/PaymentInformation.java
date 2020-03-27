/**
 * *****************************************************************************
 *
 * <p>Copyright FUJITSU LIMITED 2016
 *
 * <p>Creation Date: Mar 26, 2020
 *
 * <p>*****************************************************************************
 */
package org.oscm.rest.common.json;

public class PaymentInformation {

  public static final String PAYMENT_INFO_EXAMPLE_RESPONSE =
      "{\"paymentType\":{\"name\":\"Invoice\",\"paymentTypeId\":\"INVOICE\",\"collectionType\":\"ORGANIZATION\",\"etag\":0,\"id\":3},\"infoId\":\"some info id\",\"etag\":0,\"id\":10000}";

  public static final String PAYMENT_INFO_LIST_EXAMPLE_RESPONSE =
      "{\"items\":[" + PaymentInformation.PAYMENT_INFO_EXAMPLE_RESPONSE + "]}";

  public static final String PAYMENT_INFO_UPDATE_EXAMPLE_REQUEST =
      "{\"paymentType\":{\"paymentTypeId\":\"INVOICE\"},\"infoId\":\"some info id\",\"etag\":0}";
}
