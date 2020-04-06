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
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.google.common.collect.Lists;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.oscm.internal.intf.ServiceProvisioningService;
import org.oscm.internal.vo.VOTechnicalService;
import org.oscm.rest.common.SampleTestDataUtility;
import org.oscm.rest.common.representation.RepresentationCollection;
import org.oscm.rest.common.representation.TechnicalServiceRepresentation;
import org.oscm.rest.common.requestparameters.ServiceParameters;

@ExtendWith(MockitoExtension.class)
public class TechnicalServiceBackendTest {

  @Mock private ServiceProvisioningService service;
  @InjectMocks private TechnicalServiceBackend backend;
  private TechnicalServiceResource resource;
  private UriInfo uriInfo;
  private ServiceParameters parameters;
  private TechnicalServiceRepresentation representation;

  @BeforeEach
  public void setUp() {
    resource = new TechnicalServiceResource();
    uriInfo = SampleTestDataUtility.createUriInfo();
    parameters = SampleTestDataUtility.createServiceParameters();
    representation = SampleTestDataUtility.createTSRepresentation();
    resource.setTsb(backend);
  }

  @Test
  @SneakyThrows
  public void shouldGetTechnicalServices() {
    when(service.getTechnicalServices(any()))
        .thenReturn(Lists.newArrayList(new VOTechnicalService()));

    Response response = resource.getTechnicalServices(uriInfo, parameters.getEndpointVersion());

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

  @Test
  @SneakyThrows
  public void shouldCreateTechnicalService() {
    when(service.createTechnicalService(any())).thenReturn(new VOTechnicalService());

    Response response =
        resource.createTechnicalService(uriInfo, parameters.getEndpointVersion(), representation);

    assertThat(response).isNotNull();
    assertThat(response)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.CREATED.getStatusCode());
  }

  @Test
  @SneakyThrows
  public void shouldDeleteTechnicalService() {
    doNothing().when(service).deleteTechnicalService(any(Long.class));

    Response response =
        resource.deleteTechnicalService(
            uriInfo, parameters.getEndpointVersion(), parameters.getId().toString());

    assertThat(response).isNotNull();
    assertThat(response)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.NO_CONTENT.getStatusCode());
  }
}
