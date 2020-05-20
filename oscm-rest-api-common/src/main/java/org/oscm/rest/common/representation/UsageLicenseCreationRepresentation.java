/**
 * *****************************************************************************
 *
 * <p>Copyright FUJITSU LIMITED 2020
 *
 * <p>Creation Date: 20-05-2020
 *
 * <p>*****************************************************************************
 */
package org.oscm.rest.common.representation;

public class UsageLicenseCreationRepresentation extends Representation {

  String userId;

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }
}
