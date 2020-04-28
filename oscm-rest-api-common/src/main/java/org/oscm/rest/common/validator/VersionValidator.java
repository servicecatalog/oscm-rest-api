/**
 * *****************************************************************************
 *
 * <p>Copyright FUJITSU LIMITED 2017
 *
 * <p>Creation Date: 03.10.17 16:36
 *
 * <p>****************************************************************************
 */
package org.oscm.rest.common.validator;

import org.oscm.rest.common.CommonParams;
import org.oscm.rest.common.errorhandling.ErrorResponse;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.Response;

/** Authored by dawidch */
public class VersionValidator implements RequestValidator {

  private static String INVALID_VERSION_MSG =
      "Provided version: %s is required and must not be empty";

  /**
   * Validates the version string and compares it with the existing version numbers. Throws
   * a @{@link BadRequestException} if not valid.
   *
   * @param version the version string
   * @return the version as integer
   */
  public int validateVersion(String version) {
    if (version == null) {
      throw new BadRequestException(provideErrorResponse(null));
    }

    if (!version.matches(CommonParams.PATTERN_VERSION)) {
      throw new BadRequestException(provideErrorResponse(version));
    }
    int vnr = Integer.parseInt(version.substring(CommonParams.PATTERN_VERSION_OFFSET));

    boolean exists = false;
    for (int i : CommonParams.VERSIONS) {
      if (i == vnr) {
        exists = true;
        break;
      }
    }

    if (!exists) {
      throw new BadRequestException(provideErrorResponse(version));
    }

    return vnr;
  }

  @Override
  public Response provideErrorResponse(String version) {
    return ErrorResponse.provider()
        .errorMessage(CommonParams.ERROR_INVALID_VERSION)
        .errorDetails(String.format(INVALID_VERSION_MSG, version))
        .build()
        .badRequest();
  }
}
