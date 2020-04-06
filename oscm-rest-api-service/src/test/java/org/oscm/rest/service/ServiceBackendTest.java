/**
 * *****************************************************************************
 *
 * <p>Copyright FUJITSU LIMITED 2020
 *
 * <p>Creation Date: 06-04-2020
 *
 * <p>*****************************************************************************
 */
package org.oscm.rest.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.google.common.collect.Lists;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.oscm.internal.intf.ServiceProvisioningService;
import org.oscm.internal.vo.VOServiceDetails;
import org.oscm.rest.common.SampleTestDataUtility;
import org.oscm.rest.common.ServiceStatus;
import org.oscm.rest.common.representation.*;
import org.oscm.rest.common.requestparameters.ServiceParameters;

@ExtendWith(MockitoExtension.class)
public class ServiceBackendTest {

  @Mock private ServiceProvisioningService service;
  @InjectMocks private ServiceBackend backend;
  private ServiceResource resource;
  private CompatibleServiceResource compatiblesResource;
  private UriInfo uriInfo;
  private ServiceParameters parameters;
  private ServiceDetailsRepresentation representation;
  private StatusRepresentation statusRepresentation;
  private VOServiceDetails vo;
  private RepresentationCollection<ServiceRepresentation> compatiblesCollection;

  @BeforeEach
  public void setUp() {
    resource = new ServiceResource();
    compatiblesResource = new CompatibleServiceResource();
    uriInfo = SampleTestDataUtility.createUriInfo();
    parameters = SampleTestDataUtility.createServiceParameters();
    representation = SampleTestDataUtility.createServiceDetailsRepresentation(null);
    statusRepresentation = SampleTestDataUtility.createStatusRepresentation();
    compatiblesCollection = createCompatiblesCollection();
    vo = SampleTestDataUtility.createVOServiceDetails();
    resource.setSb(backend);
    compatiblesResource.setSb(backend);
  }

  @Test
  @SneakyThrows
  public void shouldGetService() {
    when(service.getServiceDetails(any())).thenReturn(vo);

    Response response =
        resource.getService(
            uriInfo, parameters.getEndpointVersion(), parameters.getId().toString());

    assertThat(response).isNotNull();
    assertThat(response)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.OK.getStatusCode());
  }

  @Test
  @SneakyThrows
  public void shouldGetServices() {
    when(service.getSuppliedServices()).thenReturn(Lists.newArrayList(vo));

    Response response = resource.getServices(uriInfo, parameters.getEndpointVersion());

    assertThat(response).isNotNull();
    assertThat(response)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.OK.getStatusCode());
    assertThat(response)
        .extracting(
            r -> {
              return ((RepresentationCollection) r.getEntity()).getItems().size();
            })
        .isEqualTo(1);
  }

  /*  @Test
  @SneakyThrows
  public void shouldCreateService() {
    when(service.createService(any(), any(), any())).thenReturn(vo);

    Response response =
        resource.createService(uriInfo, representation, parameters.getEndpointVersion());

    assertThat(response).isNotNull();
    assertThat(response)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.CREATED.getStatusCode());
  }*/

  @Test
  @SneakyThrows
  public void shouldUpdateService() {
    when(service.updateService(any(), any())).thenReturn(vo);

    Response response =
        resource.updateService(
            uriInfo,
            representation,
            parameters.getEndpointVersion(),
            parameters.getId().toString());

    assertThat(response).isNotNull();
    assertThat(response)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.NO_CONTENT.getStatusCode());
  }

  @Test
  @SneakyThrows
  public void shouldDeleteService() {
    doNothing().when(service).deleteService(any(Long.class));

    Response response =
        resource.deleteService(
            uriInfo, parameters.getEndpointVersion(), parameters.getId().toString());

    assertThat(response).isNotNull();
    assertThat(response)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.NO_CONTENT.getStatusCode());
  }

  @ParameterizedTest
  @EnumSource(ServiceStatus.class)
  @SneakyThrows
  public void shouldSetServiceStatus(ServiceStatus serviceStatus) {
    lenient().when(service.activateService(any())).thenReturn(vo);
    lenient().when(service.deactivateService(any())).thenReturn(vo);
    lenient().when(service.resumeService(any())).thenReturn(vo);
    lenient().when(service.suspendService(any(), any())).thenReturn(vo);
    statusRepresentation.setStatus(serviceStatus);

    Response response =
        resource.setServiceState(
            uriInfo,
            statusRepresentation,
            parameters.getEndpointVersion(),
            parameters.getId().toString());

    assertThat(response).isNotNull();
    assertThat(response)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.NO_CONTENT.getStatusCode());
  }

  @Test
  @SneakyThrows
  public void shouldGetCompatibleServices() {
    when(service.getCompatibleServices(any())).thenReturn(Lists.newArrayList(vo));

    Response response =
        compatiblesResource.getCompatibleServices(
            uriInfo, parameters.getEndpointVersion(), parameters.getId().toString());

    assertThat(response).isNotNull();
    assertThat(response)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.OK.getStatusCode());
  }

  @Test
  @SneakyThrows
  public void shouldUpdateCompatibleServices() {
    doNothing().when(service).setCompatibleServices(any(), any());

    Response response =
        compatiblesResource.setCompatibleServices(
            uriInfo,
            compatiblesCollection,
            parameters.getEndpointVersion(),
            parameters.getId().toString());

    assertThat(response).isNotNull();
    assertThat(response)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.NO_CONTENT.getStatusCode());
  }

  private RepresentationCollection<ServiceRepresentation> createCompatiblesCollection() {
    ServiceRepresentation serviceRepresentation =
        SampleTestDataUtility.createServiceRepresentation();

    RepresentationCollection<ServiceRepresentation> collection =
        new RepresentationCollection<>(Lists.newArrayList(serviceRepresentation));
    collection.setETag(1234L);
    return collection;
  }
}
