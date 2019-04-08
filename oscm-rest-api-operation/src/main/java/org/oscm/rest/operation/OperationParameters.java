package org.oscm.rest.operation;

import org.oscm.rest.common.RequestParameters;

import javax.ws.rs.WebApplicationException;

public class OperationParameters extends RequestParameters {

  @Override
  public void validateParameters() throws WebApplicationException {}

  @Override
  public void update() {}
}
