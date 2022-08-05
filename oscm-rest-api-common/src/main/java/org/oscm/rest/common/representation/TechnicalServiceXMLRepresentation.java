/*******************************************************************************
 *                                                                              
 *  Copyright FUJITSU LIMITED 2022                                           
 *                                                                                                                                 
 *  Creation Date: 02.08.2022                                                      
 *                                                                              
 *******************************************************************************/

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

import java.util.ArrayList;
import java.util.Collection;

import javax.ws.rs.WebApplicationException;

import org.oscm.rest.common.validator.RequiredFieldValidator;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TechnicalServiceXMLRepresentation extends Representation {

    private String technicalServiceXml;

    public TechnicalServiceXMLRepresentation(String xml) {
        technicalServiceXml = xml;
    }

    @Override
    public void validateContent() throws WebApplicationException {
        RequiredFieldValidator validator = new RequiredFieldValidator();
        validator.validateNotBlank("technicalServiceXml", technicalServiceXml);
    }

    public static Collection<TechnicalServiceXMLRepresentation> toCollection(String xml) {
        Collection<TechnicalServiceXMLRepresentation> result = new ArrayList<TechnicalServiceXMLRepresentation>();
        result.add(new TechnicalServiceXMLRepresentation(xml));
        return result;
    }

}
