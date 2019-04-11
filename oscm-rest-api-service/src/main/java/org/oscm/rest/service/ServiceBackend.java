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

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.oscm.internal.intf.ServiceProvisioningService;
import org.oscm.internal.types.exception.DomainObjectException.ClassEnum;
import org.oscm.internal.types.exception.ObjectNotFoundException;
import org.oscm.internal.vo.VOService;
import org.oscm.internal.vo.VOServiceDetails;
import org.oscm.rest.common.RepresentationCollection;
import org.oscm.rest.common.RestBackend;
import org.oscm.rest.service.data.ServiceDetailsRepresentation;
import org.oscm.rest.service.data.ServiceRepresentation;
import org.oscm.rest.service.data.StatusRepresentation;

@Stateless
public class ServiceBackend {

  @EJB ServiceProvisioningService sps;

  public RestBackend.Delete<ServiceParameters> delete() {
    return params -> {
      sps.deleteService(params.getId());
      return true;
    };
  }

  public RestBackend.Post<ServiceDetailsRepresentation, ServiceParameters> post() {
    return (content, params) -> {
      // image will be handled in separate URL
      VOServiceDetails vo =
          sps.createService(content.getTechnicalService().getVO(), content.getVO(), null);
      return Long.valueOf(vo.getKey());
    };
  }

  public RestBackend.Put<ServiceDetailsRepresentation, ServiceParameters> put() {
    return (content, params) -> {
      // image will be handled in separate URL
      sps.updateService(content.getVO(), null);
      return true;
    };
  }

  public RestBackend.Get<ServiceDetailsRepresentation, ServiceParameters> get() {
    return params -> {
      VOService vo = new VOService();
      vo.setKey(params.getId().longValue());
      VOServiceDetails sd = sps.getServiceDetails(vo);
      if (sd == null) {
        throw new ObjectNotFoundException(ClassEnum.SERVICE, String.valueOf(vo.getKey()));
      }
      return new ServiceDetailsRepresentation(sd);
    };
  }

  public RestBackend.GetCollection<ServiceRepresentation, ServiceParameters> getCollection() {
    return params -> {
      List<VOService> list = sps.getSuppliedServices();
      return new RepresentationCollection<ServiceRepresentation>(
          ServiceRepresentation.toCollection(list));
    };
  }

  public RestBackend.GetCollection<ServiceRepresentation, ServiceParameters> getCompatibles() {
    return params -> {
      VOService vo = new VOService();
      vo.setKey(params.getId().longValue());
      List<VOService> compatibleServices = sps.getCompatibleServices(vo);
      return new RepresentationCollection<ServiceRepresentation>(
          ServiceRepresentation.toCollection(compatibleServices));
    };
  }

  public RestBackend.Put<RepresentationCollection<ServiceRepresentation>, ServiceParameters>
      putCompatibles() {
    return (content, params) -> {
      VOService vo = new VOService();
      vo.setKey(params.getId().longValue());

      vo.setVersion(params.getETag().intValue());
      sps.setCompatibleServices(vo, ServiceRepresentation.toList(content));
      return true;
    };
  }

  public RestBackend.Put<StatusRepresentation, ServiceParameters> putStatus() {
    return new RestBackend.Put<StatusRepresentation, ServiceParameters>() {

      @Override
      public boolean put(StatusRepresentation content, ServiceParameters params) throws Exception {
        VOService vo = new VOService();
        vo.setKey(params.getId().longValue());
        vo.setVersion(eTagToVersion(params));
        switch (content.getStatus()) {
          case ACTIVE:
            sps.activateService(vo);
            break;
          case INACTIVE:
            sps.deactivateService(vo);
            break;
          case RESUMED:
            sps.resumeService(vo);
            break;
          case SUSPENDED:
            sps.suspendService(vo, content.getReason());
            break;
          default:
            break;
        }
        return true;
      }
    };
  }

  // FIXME move to ServiceParameters
  protected int eTagToVersion(ServiceParameters params) {
    if (params.getETag() == null) {
      return 0;
    }
    return params.getETag().intValue();
  }
}
