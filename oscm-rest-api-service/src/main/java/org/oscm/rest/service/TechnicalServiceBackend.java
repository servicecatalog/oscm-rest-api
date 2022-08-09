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

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.oscm.internal.intf.ServiceProvisioningService;
import org.oscm.internal.types.enumtypes.OrganizationRoleType;
import org.oscm.internal.types.exception.DomainObjectException;
import org.oscm.internal.types.exception.ObjectNotFoundException;
import org.oscm.internal.types.exception.OperationNotPermittedException;
import org.oscm.internal.types.exception.OrganizationAuthoritiesException;
import org.oscm.internal.vo.VOTechnicalService;
import org.oscm.rest.common.PostResponseBody;
import org.oscm.rest.common.RestBackend;
import org.oscm.rest.common.representation.RepresentationCollection;
import org.oscm.rest.common.representation.TechnicalServiceImportRepresentation;
import org.oscm.rest.common.representation.TechnicalServiceRepresentation;
import org.oscm.rest.common.representation.TechnicalServiceXMLRepresentation;
import org.oscm.rest.common.requestparameters.ServiceParameters;

@Stateless
public class TechnicalServiceBackend {

  @EJB ServiceProvisioningService sps;

  public RestBackend.GetCollection<TechnicalServiceRepresentation, ServiceParameters>
      getCollection() {
    return params -> {
      // TODO: role by query parameter?
      List<VOTechnicalService> technicalServices =
          sps.getTechnicalServices(OrganizationRoleType.TECHNOLOGY_PROVIDER);
      Collection<TechnicalServiceRepresentation> list =
          TechnicalServiceRepresentation.toCollection(technicalServices);

      return new RepresentationCollection<>(list);
    };
  }

  public RestBackend.Get<TechnicalServiceRepresentation, ServiceParameters> get() {
    return params -> {
      List<VOTechnicalService> technicalServices =
          sps.getTechnicalServices(OrganizationRoleType.TECHNOLOGY_PROVIDER);

      for (VOTechnicalService vo : technicalServices) {
        if (params.getId().longValue() == vo.getKey()) {
          return new TechnicalServiceRepresentation(vo);
        }
      }

      throw new ObjectNotFoundException(
          DomainObjectException.ClassEnum.TECHNICAL_SERVICE, String.valueOf(params.getId()));
    };
  }

  public RestBackend.Get<TechnicalServiceXMLRepresentation, ServiceParameters> getXML() {
    return params -> {
      List<VOTechnicalService> ts =
          sps.getTechnicalServices(OrganizationRoleType.TECHNOLOGY_PROVIDER);

      ts =
          ts.stream()
              .filter(vo -> params.getId().longValue() == vo.getKey())
              .collect(Collectors.toList());

      if (!ts.isEmpty()) {
        return new TechnicalServiceXMLRepresentation(asXML(ts));
      }
      throw new ObjectNotFoundException(
          DomainObjectException.ClassEnum.TECHNICAL_SERVICE, String.valueOf(params.getId()));
    };
  }

  public RestBackend.GetCollection<TechnicalServiceXMLRepresentation, ServiceParameters>
      getXMLCollection()
          throws OrganizationAuthoritiesException, ObjectNotFoundException,
              OperationNotPermittedException {

    return params -> {
      List<VOTechnicalService> technicalServices =
          sps.getTechnicalServices(OrganizationRoleType.TECHNOLOGY_PROVIDER);

      Collection<TechnicalServiceXMLRepresentation> list =
          TechnicalServiceXMLRepresentation.toCollection(asXML(technicalServices));
      return new RepresentationCollection<>(list);
    };
  }

  public RestBackend.Delete<ServiceParameters> delete() {
    return params -> {
      sps.deleteTechnicalService(params.getId());
      return true;
    };
  }

  public RestBackend.Post<TechnicalServiceRepresentation, ServiceParameters> post() {
    return (content, params) -> {
      VOTechnicalService ts = sps.createTechnicalService(content.getVO());
      return PostResponseBody.of()
          .createdObjectId(String.valueOf(ts.getKey()))
          .createdObjectName(ts.getTechnicalServiceId())
          .build();
    };
  }

  private String asXML(List<VOTechnicalService> technicalServices)
      throws OrganizationAuthoritiesException, ObjectNotFoundException,
          OperationNotPermittedException {
    byte[] b = sps.exportTechnicalServices(technicalServices);
    try {
      return new String(b, "UTF-8");
    } catch (UnsupportedEncodingException e) {
      throw new RuntimeException(e);
    }
  }

  public RestBackend.Put<TechnicalServiceImportRepresentation, ServiceParameters> importFromXml() {
    return (content, params) -> {
      byte[] bytes = content.getTechnicalServiceXml().getBytes(StandardCharsets.UTF_8);
      sps.importTechnicalServices(bytes);
      return true;
    };
  }
}
