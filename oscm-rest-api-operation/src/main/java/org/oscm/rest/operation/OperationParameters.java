/**
 * *****************************************************************************
 *
 * <p>Copyright FUJITSU LIMITED 2019
 *
 * <p>Creation Date: 10-04-2019
 *
 * <p>*****************************************************************************
 */
package org.oscm.rest.operation;

import javax.ws.rs.WebApplicationException;
import org.oscm.rest.common.RequestParameters;

public class OperationParameters extends RequestParameters {

  @Override
  public void validateParameters() throws WebApplicationException {}

  @Override
  public void update() {}
}
