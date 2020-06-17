/**
 * *****************************************************************************
 *
 * <p>Copyright FUJITSU LIMITED 2020
 *
 * <p>Creation Date: 16-06-2020
 *
 * <p>*****************************************************************************
 */

package org.oscm.rest.common.representation;

import lombok.Getter;
import lombok.Setter;
import org.oscm.rest.common.validator.RequiredFieldValidator;

import javax.ws.rs.WebApplicationException;

@Getter
@Setter
public class TechnicalServiceImportRepresentation extends Representation {

  private String technicalServiceXml;

  @Override
  public void validateContent() throws WebApplicationException {
    RequiredFieldValidator validator = new RequiredFieldValidator();
    validator.validateNotBlank("technicalServiceXml", technicalServiceXml);
  }
}
