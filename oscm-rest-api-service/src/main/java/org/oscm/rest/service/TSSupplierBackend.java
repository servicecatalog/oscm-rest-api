/**
 * *****************************************************************************
 *
 * <p>Copyright FUJITSU LIMITED 2019
 *
 * <p>Creation Date: 10-04-2019
 *
 * <p>*****************************************************************************
 */
package org.oscm.rest.service;

import java.util.Arrays;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.oscm.internal.intf.AccountService;
import org.oscm.internal.vo.VOOrganization;
import org.oscm.internal.vo.VOTechnicalService;
import org.oscm.rest.common.RestBackend;
import org.oscm.rest.common.representation.OrganizationRepresentation;
import org.oscm.rest.common.requestparameters.ServiceParameters;

@Stateless
public class TSSupplierBackend {

  @EJB AccountService as;

  public RestBackend.GetCollection<OrganizationRepresentation, ServiceParameters> getCollection() {
    return params -> {
      VOTechnicalService vo = new VOTechnicalService();
      vo.setKey(params.getId().longValue());
      List<VOOrganization> list = as.getSuppliersForTechnicalService(vo);
      return OrganizationRepresentation.toCollection(list);
    };
  }

  public RestBackend.Post<OrganizationRepresentation, ServiceParameters> post() {
    return (content, params) -> {
      VOTechnicalService vo = new VOTechnicalService();
      vo.setKey(params.getId().longValue());
      as.addSuppliersForTechnicalService(vo, Arrays.asList(content.getOrganizationId()));
      return content.getOrganizationId();
    };
  }

  public RestBackend.Delete<ServiceParameters> delete() {
    return params -> {
      VOTechnicalService vo = new VOTechnicalService();
      vo.setKey(params.getId().longValue());
      as.removeSuppliersFromTechnicalService(vo, Arrays.asList(params.getOrgId()));
      return true;
    };
  }
}
