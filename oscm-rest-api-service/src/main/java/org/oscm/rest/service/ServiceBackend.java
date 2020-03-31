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

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.oscm.internal.intf.MarketplaceService;
import org.oscm.internal.intf.SearchService;
import org.oscm.internal.intf.ServiceProvisioningService;
import org.oscm.internal.types.exception.DomainObjectException.ClassEnum;
import org.oscm.internal.types.exception.InvalidPhraseException;
import org.oscm.internal.types.exception.ObjectNotFoundException;
import org.oscm.internal.vo.VOMarketplace;
import org.oscm.internal.vo.VOService;
import org.oscm.internal.vo.VOServiceDetails;
import org.oscm.rest.common.PostResponseBody;
import org.oscm.rest.common.RestBackend;
import org.oscm.rest.common.representation.RepresentationCollection;
import org.oscm.rest.common.representation.ServiceDetailsRepresentation;
import org.oscm.rest.common.representation.ServiceRepresentation;
import org.oscm.rest.common.representation.StatusRepresentation;
import org.oscm.rest.common.requestparameters.ServiceParameters;

@Stateless
public class ServiceBackend {

  @EJB ServiceProvisioningService sps;
  @EJB SearchService searchService;
  @EJB MarketplaceService ms;

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
      return PostResponseBody.of()
          .createdObjectId(String.valueOf(vo.getKey()))
          .createdObjectName(vo.getServiceId())
          .build();
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
      if (params.getServiceName() == null || params.getServiceName().isEmpty()) {
        List<VOService> list = sps.getSuppliedServices();
        return new RepresentationCollection<ServiceRepresentation>(
            ServiceRepresentation.toCollection(list));
      } else {
        String loc = getLocale(params.getLocale());
        List<VOService> services =
            getServices(params.getMarketPlaceId(), loc, params.getServiceName());
        return new RepresentationCollection<ServiceRepresentation>(
            ServiceRepresentation.toCollection(services));
      }
    };
  }

  protected List<VOService> getServices(String mpId, String locale, String searchPhrase)
      throws ObjectNotFoundException, InvalidPhraseException {
    List<VOService> slr = new ArrayList<VOService>();
    if (mpId == null || mpId.isEmpty()) {
      List<VOMarketplace> mps = ms.getMarketplacesOwned();
      for (VOMarketplace mp : mps) {
        slr.addAll(
            searchService
                .searchServices(mp.getMarketplaceId(), locale, searchPhrase)
                .getServices());
      }

    } else {
      slr.addAll(searchService.searchServices(mpId, locale, searchPhrase).getServices());
    }
    return slr;
  }

  protected String getLocale(String locale) {
    if (locale == null || locale.isEmpty()) {
      locale = "en";
    }
    return locale;
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
    return (content, params) -> {
      VOService vo = new VOService();
      vo.setKey(params.getId().longValue());
      vo.setVersion(params.eTagToVersion());
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
    };
  }
}
