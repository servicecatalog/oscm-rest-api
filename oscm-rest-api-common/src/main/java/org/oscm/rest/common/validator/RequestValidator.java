/**
 * *****************************************************************************
 *
 * <p>Copyright FUJITSU LIMITED 2020
 *
 * <p>Creation Date: Apr 28, 2020
 *
 * <p>*****************************************************************************
 */
package org.oscm.rest.common.validator;

import javax.ws.rs.core.Response;

public interface RequestValidator {
  Response provideErrorResponse(String... responseParameters);
}
