package org.oscm.rest.service;

import com.google.common.collect.Lists;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.oscm.internal.intf.ServiceProvisioningService;
import org.oscm.internal.vo.VOTechnicalService;
import org.oscm.rest.common.representation.RepresentationCollection;
import org.oscm.rest.common.SampleTestDataUtility;
import org.oscm.rest.service.data.TechnicalServiceRepresentation;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

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
    parameters = createParameters();
    representation = createRepresentation();
    resource.setTsb(backend);
  }

  @Test
  @SneakyThrows
  public void shouldGetTechnicalServices() {
    when(service.getTechnicalServices(any()))
        .thenReturn(Lists.newArrayList(new VOTechnicalService()));

    Response response = resource.getTechnicalServices(uriInfo, parameters);

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
  @Disabled("Not implemented")
  public void shouldGetTechnicalServicesById() {
    // FIXME: To be implemented in scope of OSCM-REST-API/#92
  }

  @Test
  @SneakyThrows
  public void shouldCreateTechnicalService() {
    when(service.createTechnicalService(any())).thenReturn(new VOTechnicalService());

    Response response = resource.createTechnicalService(uriInfo, representation, parameters);

    assertThat(response).isNotNull();
    assertThat(response)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.CREATED.getStatusCode());
  }

  @Test
  @SneakyThrows
  public void shouldDeleteTechnicalService() {
    doNothing().when(service).deleteTechnicalService(any(Long.class));

    Response response = resource.deleteTechnicalService(uriInfo, parameters);

    assertThat(response).isNotNull();
    assertThat(response)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.NO_CONTENT.getStatusCode());
  }

  private TechnicalServiceRepresentation createRepresentation() {
    TechnicalServiceRepresentation representation = new TechnicalServiceRepresentation();
    return representation;
  }

  private ServiceParameters createParameters() {
    ServiceParameters parameters = new ServiceParameters();
    parameters.setId(1000L);
    return parameters;
  }
}
