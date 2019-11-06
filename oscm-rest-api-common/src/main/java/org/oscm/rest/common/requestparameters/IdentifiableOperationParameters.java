/**
 * *****************************************************************************
 *
 * <p>Copyright FUJITSU LIMITED 2019
 *
 * <p>Creation Date: 10-04-2019
 *
 * <p>*****************************************************************************
 */
package org.oscm.rest.common.requestparameters;

import javax.ws.rs.WebApplicationException;

public class IdentifiableOperationParameters extends IdentifableRequestParameters {

  @Override
  public void validateParameters() throws WebApplicationException {}

  @Override
  public void update() {}
}
