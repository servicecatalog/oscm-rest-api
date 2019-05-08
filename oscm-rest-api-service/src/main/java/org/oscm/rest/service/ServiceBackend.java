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
import org.oscm.internal.vo.VOService;
import org.oscm.internal.vo.VOServiceDetails;
import org.oscm.rest.common.RepresentationCollection;
import org.oscm.rest.common.RestBackend;
import org.oscm.rest.service.data.ServiceDetailsRepresentation;
import org.oscm.rest.service.data.ServiceRepresentation;
import org.oscm.rest.service.data.StatusRepresentation;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class ServiceBackend {

  @EJB ServiceProvisioningService sps;

  public RestBackend.Delete<ServiceParameters> delete() {
    return new RestBackend.Delete<ServiceParameters>() {

      @Override
      public boolean delete(ServiceParameters params) throws Exception {
        sps.deleteService(params.getId());
        return true;
      }
    };
  }

  public RestBackend.Post<ServiceDetailsRepresentation, ServiceParameters> post() {
    return new RestBackend.Post<ServiceDetailsRepresentation, ServiceParameters>() {

      @Override
      public Object post(ServiceDetailsRepresentation content, ServiceParameters params)
          throws Exception {
        // image will be handled in separate URL
        VOServiceDetails vo =
            sps.createService(content.getTechnicalService().getVO(), content.getVO(), null);
        return Long.valueOf(vo.getKey());
      }
    };
  }

  public RestBackend.Put<ServiceDetailsRepresentation, ServiceParameters> put() {
    return new RestBackend.Put<ServiceDetailsRepresentation, ServiceParameters>() {

      @Override
      public boolean put(ServiceDetailsRepresentation content, ServiceParameters params)
          throws Exception {
        // image will be handled in separate URL
        sps.updateService(content.getVO(), null);
        return true;
      }
    };
  }

  public RestBackend.Get<ServiceDetailsRepresentation, ServiceParameters> get() {
    return new RestBackend.Get<ServiceDetailsRepresentation, ServiceParameters>() {

      @Override
      public ServiceDetailsRepresentation get(ServiceParameters params) throws Exception {
        VOService vo = new VOService();
        vo.setKey(params.getId().longValue());
        VOServiceDetails sd = sps.getServiceDetails(vo);
        if (sd == null) {
          throw new ObjectNotFoundException(ClassEnum.SERVICE, String.valueOf(vo.getKey()));
        }
        return new ServiceDetailsRepresentation(sd);
      }
    };
  }

  public RestBackend.GetCollection<ServiceRepresentation, ServiceParameters> getCollection() {
    return new RestBackend.GetCollection<ServiceRepresentation, ServiceParameters>() {

      @Override
      public RepresentationCollection<ServiceRepresentation> getCollection(ServiceParameters params)
          throws Exception {
        List<VOService> list = sps.getSuppliedServices();
        return new RepresentationCollection<ServiceRepresentation>(
            ServiceRepresentation.toCollection(list));
      }
    };
  }

  public RestBackend.GetCollection<ServiceRepresentation, ServiceParameters> getCompatibles() {
    return new RestBackend.GetCollection<ServiceRepresentation, ServiceParameters>() {

      @Override
      public RepresentationCollection<ServiceRepresentation> getCollection(ServiceParameters params)
          throws Exception {
        VOService vo = new VOService();
        vo.setKey(params.getId().longValue());
        List<VOService> compatibleServices = sps.getCompatibleServices(vo);
        return new RepresentationCollection<ServiceRepresentation>(
            ServiceRepresentation.toCollection(compatibleServices));
      }
    };
  }

  public RestBackend.Put<RepresentationCollection<ServiceRepresentation>, ServiceParameters>
      putCompatibles() {
    return new RestBackend.Put<
        RepresentationCollection<ServiceRepresentation>, ServiceParameters>() {

      @Override
      public boolean put(
          RepresentationCollection<ServiceRepresentation> content, ServiceParameters params)
          throws Exception {
        VOService vo = new VOService();
        vo.setKey(params.getId().longValue());

        vo.setVersion(params.getETag().intValue());
        sps.setCompatibleServices(vo, ServiceRepresentation.toList(content));
        return true;
      }
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
