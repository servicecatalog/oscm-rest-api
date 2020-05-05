/**
 * *****************************************************************************
 *
 * <p>Copyright FUJITSU LIMITED 2020
 *
 * <p>Creation Date: Apr 27, 2020
 *
 * <p>*****************************************************************************
 */
package org.oscm.rest.common.validator;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.Response;
import org.apache.commons.lang3.StringUtils;
import org.oscm.rest.common.errorhandling.ErrorResponse;

public class RequiredFieldValidator implements RequestValidator {

  private static String REQUIRED_FIELD_MSG = "Field %s is required and must not be empty";

  public void validateNotBlank(String fieldName, String fieldValue) {
    if (StringUtils.isBlank(fieldValue)) {
      throw new BadRequestException(provideErrorResponse(fieldName));
    }
  }

  public void validateNotBlank(String fieldName, Object fieldValue) {
    if (fieldValue == null) {
      throw new BadRequestException(provideErrorResponse(fieldName));
    }

    if (fieldValue instanceof Object[] && ((Object[]) fieldValue).length < 1) {
      throw new BadRequestException(provideErrorResponse(fieldName));
    }
  }

  @Override
  public Response provideErrorResponse(String... responseParameters) {
    return ErrorResponse.provider()
        .build()
        .badRequest(String.format(REQUIRED_FIELD_MSG, responseParameters[0]));
  }
}
