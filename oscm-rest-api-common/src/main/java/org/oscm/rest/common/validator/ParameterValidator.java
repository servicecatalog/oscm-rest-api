/**
 * *****************************************************************************
 *
 * <p>Copyright FUJITSU LIMITED 2020
 *
 * <p>Creation Date: Mai 30, 2020
 *
 * <p>*****************************************************************************
 */
package org.oscm.rest.common.validator;

import java.util.List;
import java.util.stream.Collectors;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.Response;
import org.apache.commons.lang3.StringUtils;
import org.oscm.internal.vo.VOParameterDefinition;
import org.oscm.rest.common.errorhandling.ErrorResponse;

public class ParameterValidator implements RequestValidator {

  private static String INVALID_PARAMETER_MSG = "Invalid field parameter: %s %s";

  public void validate(VOParameterDefinition definition, String value) {

    String parameterId = definition.getParameterId();

    if (definition.isMandatory() && StringUtils.isBlank(value)) {
      throw new BadRequestException(
          provideErrorResponse(parameterId, "is required and must not be empty"));
    }

    if (StringUtils.isNotBlank(value) && definition.isValueTypeEnumeration()) {
      List<String> optionIds =
          definition.getParameterOptions().stream()
              .map(option -> option.getOptionId())
              .collect(Collectors.toList());

      if (!optionIds.contains(value)) {
        throw new BadRequestException(
            provideErrorResponse(parameterId, "allowed values: " + optionIds));
      }
    }
  }

  @Override
  public Response provideErrorResponse(String... responseParameters) {
    return ErrorResponse.provider()
        .build()
        .badRequest(
            String.format(INVALID_PARAMETER_MSG, responseParameters[0], responseParameters[1]));
  }
}
