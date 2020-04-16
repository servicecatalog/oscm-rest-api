package org.oscm.rest.common.errorhandling;

import lombok.Builder;

@Builder(builderMethodName = "of")
public class ErrorResponse {

  private String errorMessage;
  private String errorDetails;
}
