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

import org.oscm.internal.intf.ServiceProvisioningService;
import org.oscm.internal.types.exception.DomainObjectException.ClassEnum;
import org.oscm.internal.types.exception.ObjectNotFoundException;
import org.oscm.internal.vo.VOOrganization;
import org.oscm.internal.vo.VOService;
import org.oscm.internal.vo.VOServiceDetails;
import org.oscm.rest.common.RestBackend;
import org.oscm.rest.common.representation.PriceModelRepresentation;
import org.oscm.rest.common.requestparameters.ServiceParameters;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class PriceModelBackend {

  @EJB ServiceProvisioningService sps;

  public RestBackend.Put<PriceModelRepresentation, ServiceParameters> put() {
    return (content, params) -> {
      VOServiceDetails svc = new VOServiceDetails();
      svc.setKey(params.getId());

      VOServiceDetails sd = sps.getServiceDetails(svc);
      content.getVO().setKey(sd.getPriceModel().getKey());

      sps.savePriceModel(svc, content.getVO());
      return true;
    };
  }

  public RestBackend.Put<PriceModelRepresentation, ServiceParameters> putForCustomer() {
    return (content, params) -> {
      VOServiceDetails svc = new VOServiceDetails();
      svc.setKey(params.getId());
      VOOrganization customer = new VOOrganization();
      customer.setKey(params.getOrgKey());

      VOServiceDetails sd = sps.getServiceForCustomer(customer, svc);
      if (sd != null) {
        content.getVO().setKey(sd.getPriceModel().getKey());
        svc.setKey(sd.getKey());
      }

      sps.savePriceModelForCustomer(svc, content.getVO(), customer);
      return true;
    };
  }

  public RestBackend.Get<PriceModelRepresentation, ServiceParameters> get() {
    return params -> {
      VOService vo = new VOService();
      vo.setKey(params.getId());
      VOServiceDetails sd = sps.getServiceDetails(vo);
      if (sd == null) {
        throw new ObjectNotFoundException(ClassEnum.SERVICE, String.valueOf(vo.getKey()));
      }
      return new PriceModelRepresentation(sd.getPriceModel());
    };
  }

  public RestBackend.Get<PriceModelRepresentation, ServiceParameters> getForCustomer() {
    return params -> {
      VOService svc = new VOService();
      svc.setKey(params.getId());
      VOOrganization customer = new VOOrganization();
      customer.setKey(params.getOrgKey());
      VOServiceDetails sd = sps.getServiceForCustomer(customer, svc);
      if (sd == null) {
        throw new ObjectNotFoundException(ClassEnum.SERVICE, String.valueOf(svc.getKey()));
      }
      return new PriceModelRepresentation(sd.getPriceModel());
    };
  }
}
