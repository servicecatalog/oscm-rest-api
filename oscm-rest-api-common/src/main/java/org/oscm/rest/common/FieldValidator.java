package org.oscm.rest.common;

import org.apache.commons.lang3.StringUtils;

public class FieldValidator {

  public static void validateNotBlank(String fieldName, String fieldValue) {
    if (StringUtils.isBlank(fieldValue)) {
      throw WebException.badRequest()
          .message("Field " + fieldName + " is required and must not be empty")
          .build();
    }
  }

  public static void validateNotBlank(String fieldName, Object fieldValue) {
    if (fieldValue == null) {
      throw WebException.badRequest()
          .message("Field " + fieldName + " is required and must not be empty")
          .build();
    }

    if (fieldValue instanceof Object[] && ((Object[]) fieldValue).length < 1) {
      throw WebException.badRequest()
          .message("Field " + fieldName + " is required and must not be empty")
          .build();
    }
  }
}
