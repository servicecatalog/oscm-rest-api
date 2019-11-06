/**
 * *****************************************************************************
 *
 * <p>Copyright FUJITSU LIMITED 2017
 *
 * <p>Creation Date: May 9, 2016
 *
 * <p>*****************************************************************************
 */
package org.oscm.rest.common.requestparameters;

import io.swagger.v3.oas.annotations.Parameter;
import org.oscm.rest.common.CommonParams;
import org.oscm.rest.common.WebException;

import javax.ws.rs.HeaderParam;
import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;

/**
 * Base class for BeanParams
 *
 * @author miethaner
 */
public class IdentifableRequestParameters extends RequestParameters {

  @Parameter(description = "ID of a single resource", required = true)
  @PathParam(CommonParams.PARAM_ID)
  private Long id;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  /**
   * Validates the id string if it matches basic UUID format. Throws NotFoundException if not valid.
   *
   * @throws WebApplicationException
   */
  public void validateId() throws WebApplicationException {

    if (id == null) {
      throw WebException.notFound().message(CommonParams.ERROR_INVALID_ID).build();
    }
  }

  public long convertIdToKey() {
    if (getId() == null) {
      return 0L;
    }
    return getId().longValue();
  }
}
