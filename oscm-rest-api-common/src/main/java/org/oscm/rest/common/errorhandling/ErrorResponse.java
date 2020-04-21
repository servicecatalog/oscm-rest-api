/**
 * *****************************************************************************
 *
 * <p>Copyright FUJITSU LIMITED 2020
 *
 * <p>Creation Date: April 16, 2020
 *
 * <p>*****************************************************************************
 */
package org.oscm.rest.common.errorhandling;

import lombok.Builder;
import lombok.Getter;

@Builder(builderMethodName = "of")
@Getter
public class ErrorResponse {

  private String errorMessage;
  private String errorDetails;
}
