/**
 * *****************************************************************************
 *
 * <p>Copyright FUJITSU LIMITED 2017
 *
 * <p>Creation Date: May 13, 2016
 *
 * <p>*****************************************************************************
 */
package org.oscm.rest.common;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import javax.ws.rs.core.MediaType;

/**
 * Class for common shared parameters
 *
 * @author miethaner
 */
public class CommonParams {

  // global formating
  public static final Charset CHARSET = StandardCharsets.UTF_8;
  public static final String FORMAT_DATE = "yyyy-MM-dd'T'HH:mm:ssXXX";
  public static final String JSON = MediaType.APPLICATION_JSON;

  // all available versions
  public static final int VERSION_1 = 1;
  public static final int[] VERSIONS = {VERSION_1};

  // parameter name
  public static final String PARAM_VERSION = "version";
  public static final String PARAM_ID = "id";
  public static final String PARAM_ORG_ID = "orgId";
  public static final String PARAM_USER_ID = "userId";
  public static final String PARAM_MATCH = "If-Match";
  public static final String PARAM_NONE_MATCH = "If-None-Match";
  public static final String ETAG_WILDCARD = "*";

  // path params
  public static final String PATH_VERSION = "/{" + PARAM_VERSION + "}";
  public static final String PATH_ID = "/{" + PARAM_ID + "}";
  public static final String PATH_ORG_ID = "/{" + PARAM_ORG_ID + "}";
  public static final String PATH_USER_ID = "/{" + PARAM_USER_ID + "}";

  // pattern for version validation
  public static final String PATTERN_VERSION = "v[0-9]+";
  public static final int PATTERN_VERSION_OFFSET = 1;

  // patterns for validation
  public static final String PATTERN_STRING = "^.{0,250}$";

  // error messages
  public static final String ERROR_RESOURCE_NOT_FOUND = "Requested resource was not found";
  public static final String ERROR_UNEXPECTED_EXCEPTION = "Unexpected problem occurred";
  public static final String ERROR_OUTDATED_VERSION =
      "Object to be updated is not in the latest version";
  public static final String ERROR_JSON_FORMAT = "Invalid JSON request body";
  public static final String ERROR_INVALID_ID = "ID not valid or unknown";
  public static final String ERROR_INVALID_VERSION = "Version not valid or unknown";
  public static final String ERROR_INVALID_TAG = "Invalid ETag";
  public static final String ERROR_METHOD_VERSION = "Method not available for used version";
  public static final String ERROR_BAD_PROPERTY = "Property does not match allowed pattern";
  public static final String ERROR_NOT_AUTHORIZED = "User is not authorized for the operation";
  public static final String ERROR_MISSING_CONTENT = "No Content in request while expected";
  public static final String ERROR_MANDATORY_PROPERTIES =
      "One or more mandatory properties are missing";

  // general purpose constants
  public static final String DUMMY_ID = "DummyIdentifier";

  private CommonParams() {}
}
