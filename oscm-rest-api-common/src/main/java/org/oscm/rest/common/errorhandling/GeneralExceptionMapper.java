/**
 * *****************************************************************************
 *
 * <p>Copyright FUJITSU LIMITED 2020
 *
 * <p>Creation Date: April 20, 2020
 *
 * <p>*****************************************************************************
 */
package org.oscm.rest.common.errorhandling;

import lombok.extern.slf4j.Slf4j;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/** Exception handler triggered in case exception is not handled by any of other mappers */
@Slf4j
@Provider
public class GeneralExceptionMapper implements ExceptionMapper<Exception> {

  @Override
  public Response toResponse(Exception e) {
    log.info("Handling exception: " + e.getClass().getName());
    return ErrorResponse.provider().build().internalServerError(e.getMessage());
  }
}
