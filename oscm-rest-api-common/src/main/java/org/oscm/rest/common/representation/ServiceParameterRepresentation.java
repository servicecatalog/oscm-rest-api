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

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceParameterRepresentation {

  private String parameterId;
  private String value;
  private boolean configurable;
}
