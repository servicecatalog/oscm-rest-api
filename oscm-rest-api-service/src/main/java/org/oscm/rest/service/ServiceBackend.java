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
import java.util.Optional;
import java.util.stream.Collectors;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.oscm.internal.intf.MarketplaceService;
import org.oscm.internal.intf.SearchService;
import org.oscm.internal.intf.SearchServiceInternal;
import org.oscm.internal.intf.ServiceProvisioningService;
import org.oscm.internal.types.enumtypes.OrganizationRoleType;
import org.oscm.internal.types.exception.DomainObjectException.ClassEnum;
import org.oscm.internal.types.exception.InvalidPhraseException;
import org.oscm.internal.types.exception.ObjectNotFoundException;
import org.oscm.internal.vo.VOMarketplace;
import org.oscm.internal.vo.VOService;
import org.oscm.internal.vo.VOServiceDetails;
import org.oscm.internal.vo.VOTechnicalService;
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
  @EJB SearchServiceInternal searchServiceInternal;
  @EJB MarketplaceService ms;

  public RestBackend.Delete<ServiceParameters> delete() {
    return params -> {
      sps.deleteService(params.getId());
      return true;
    };
  }

  public RestBackend.Post<ServiceDetailsRepresentation, ServiceParameters> post() {
    return (content, params) -> {
      List<VOTechnicalService> technicalServices =
          sps.getTechnicalServices(OrganizationRoleType.TECHNOLOGY_PROVIDER);

      VOServiceDetails vo = new VOServiceDetails();
      for (VOTechnicalService technicalService : technicalServices) {
        Long serviceId = content.getTechnicalService().getId();
        if (Long.valueOf(technicalService.getKey()).equals(serviceId)) {
          vo = sps.createService(technicalService, content.getVO(), null);
        }
      }

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
      if (isSeachRequest(params)) {
        return createSearchResult(params);
      } else if (isPageingRequest(params)) {
        return createPagedServiceList(params);
      }
      return createServiceList();
    };
  }

  private boolean isSeachRequest(ServiceParameters params) {
    return Optional.ofNullable(params.getSearchPhrase()).isPresent();
  }

  private boolean isPageingRequest(ServiceParameters params) {
    return params.getListCriteria().getLimit() != 0 && params.getListCriteria().getOffset() != 0;
  }

  private RepresentationCollection<ServiceRepresentation> createPagedServiceList(
      ServiceParameters params) throws ObjectNotFoundException, InvalidPhraseException {
    List<VOService> services = getPagedServices(params, getLocale(params.getLocale()));
    return new RepresentationCollection<ServiceRepresentation>(
        ServiceRepresentation.toCollection(services));
  }

  protected List<VOService> getPagedServices(ServiceParameters params, String locale)
      throws ObjectNotFoundException, InvalidPhraseException {

    Optional<String> m = Optional.of(params.getMarketPlaceId());
    return m.map(
            p -> {
              return getServicesByCriteria(params, p);
            })
        .orElse(
            m.map(
                    p -> {
                      return ms.getMarketplacesOwned()
                          .stream()
                          .flatMap(
                              mp -> {
                                return getServicesByCriteria(params, mp.getMarketplaceId())
                                    .stream();
                              })
                          .collect(Collectors.toList());
                    })
                .get());
  }

  private List<VOService> getServicesByCriteria(ServiceParameters params, String mpId) {
    try {
      return searchServiceInternal
          .getServicesByCriteria(
              mpId,
              getLocale(params.getLocale()),
              params.getListCriteria(),
              params.getPerformanceHint())
          .getServices();
    } catch (ObjectNotFoundException e) {
    }
    return new ArrayList<VOService>();
  }

  private RepresentationCollection<ServiceRepresentation> createSearchResult(
      ServiceParameters params) throws ObjectNotFoundException, InvalidPhraseException {
    List<VOService> services =
        getServices(
            params.getMarketPlaceId(), getLocale(params.getLocale()), params.getSearchPhrase());
    return new RepresentationCollection<ServiceRepresentation>(
        ServiceRepresentation.toCollection(services));
  }

  private RepresentationCollection<ServiceRepresentation> createServiceList() {
    return new RepresentationCollection<ServiceRepresentation>(
        ServiceRepresentation.toCollection(sps.getSuppliedServices()));
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
    return Optional.ofNullable(locale).filter(l -> !l.isEmpty()).orElse("en");
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
