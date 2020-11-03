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

import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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

      for (VOTechnicalService technicalService : technicalServices) {
        if (params.getId().equals(technicalService.getKey())) {
          return new TechnicalServiceRepresentation(technicalService);
        }
      }

      throw new ObjectNotFoundException(
          DomainObjectException.ClassEnum.TECHNICAL_SERVICE, String.valueOf(params.getId()));
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

  @Produces(MediaType.APPLICATION_XML)
  public Response getXML(Long id)
      throws ObjectNotFoundException, OrganizationAuthoritiesException,
          OperationNotPermittedException {
    List<VOTechnicalService> technicalServices =
        sps.getTechnicalServices(OrganizationRoleType.TECHNOLOGY_PROVIDER);

    for (VOTechnicalService technicalService : technicalServices) {
      if (id.equals(technicalService.getKey())) {
        byte[] b = sps.exportTechnicalServices(technicalServices);
        return Response.ok(b, MediaType.APPLICATION_XML).build();
      }
    }
    throw new ObjectNotFoundException(
        DomainObjectException.ClassEnum.TECHNICAL_SERVICE, String.valueOf(id));
  }

  @Produces(MediaType.APPLICATION_XML)
  public Response getXMLCollection()
      throws OrganizationAuthoritiesException, ObjectNotFoundException,
          OperationNotPermittedException {
    List<VOTechnicalService> technicalServices =
        sps.getTechnicalServices(OrganizationRoleType.TECHNOLOGY_PROVIDER);

    byte[] b = sps.exportTechnicalServices(technicalServices);
    return Response.ok(b, MediaType.APPLICATION_XML).build();
  }

  public RestBackend.Put<TechnicalServiceImportRepresentation, ServiceParameters> importFromXml() {
    return (content, params) -> {
      byte[] bytes = content.getTechnicalServiceXml().getBytes(StandardCharsets.UTF_8);
      sps.importTechnicalServices(bytes);
      return true;
    };
  }
}
