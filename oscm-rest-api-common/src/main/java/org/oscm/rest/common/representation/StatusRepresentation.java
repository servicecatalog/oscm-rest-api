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

import org.oscm.rest.common.ServiceStatus;

import javax.ws.rs.WebApplicationException;

public class StatusRepresentation extends Representation {

  private ServiceStatus status;
  private String reason;

  @Override
  public void validateContent() throws WebApplicationException {}

  @Override
  public void update() {}

  @Override
  public void convert() {}

  public ServiceStatus getStatus() {
    return status;
  }

  public void setStatus(ServiceStatus status) {
    this.status = status;
  }

  public String getReason() {
    return reason;
  }

  public void setReason(String reason) {
    this.reason = reason;
  }
}
