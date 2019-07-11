package org.oscm.rest.service;

import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.oscm.internal.intf.ServiceProvisioningService;
import org.oscm.internal.vo.VOPriceModel;
import org.oscm.internal.vo.VOServiceDetails;
import org.oscm.rest.common.SampleTestDataUtility;
import org.oscm.rest.common.requestparameters.ServiceParameters;
import org.oscm.rest.common.representation.PriceModelRepresentation;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PriceModelBackendTest {

  @Mock private ServiceProvisioningService service;
  @InjectMocks private PriceModelBackend backend;
  private PriceModelResource resource;

  private UriInfo uriInfo;
  private ServiceParameters parameters;
  private PriceModelRepresentation representation;
  private VOServiceDetails vo;

  @BeforeEach
  public void setUp() {
    resource = new PriceModelResource();
    uriInfo = SampleTestDataUtility.createUriInfo();
    parameters = createParameters();
    representation = createRepresentation();
    vo = createVO();
    resource.setPmb(backend);
  }

  @Test
  @SneakyThrows
  public void shouldGetPricemodel() {
    when(service.getServiceDetails(any())).thenReturn(vo);

    Response response = resource.get(uriInfo, parameters);

    assertThat(response).isNotNull();
    assertThat(response)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.OK.getStatusCode());
  }

  @Test
  @SneakyThrows
  public void shouldGetPricemodelForCustomer() {
    when(service.getServiceForCustomer(any(), any())).thenReturn(vo);

    Response response = resource.getForCustomer(uriInfo, parameters);

    assertThat(response).isNotNull();
    assertThat(response)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.OK.getStatusCode());
  }

  @Test
  @SneakyThrows
  public void shouldUpdatePricemodel() {
    when(service.savePriceModel(any(), any())).thenReturn(vo);

    Response response = resource.update(uriInfo, representation, parameters);

    assertThat(response).isNotNull();
    assertThat(response)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.NO_CONTENT.getStatusCode());
  }

  @Test
  @SneakyThrows
  public void shouldUpdatePricemodelForCustomer() {
    when(service.savePriceModelForCustomer(any(), any(), any())).thenReturn(vo);

    Response response = resource.updateForCustomer(uriInfo, representation, parameters);

    assertThat(response).isNotNull();
    assertThat(response)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.NO_CONTENT.getStatusCode());
  }

  private ServiceParameters createParameters() {
    ServiceParameters parameters = new ServiceParameters();
    parameters.setId(1000L);
    parameters.setOrgKey(11000L);
    return parameters;
  }

  private PriceModelRepresentation createRepresentation() {
    return new PriceModelRepresentation();
  }

  private VOServiceDetails createVO() {
    VOServiceDetails vo = new VOServiceDetails();
    vo.setPriceModel(new VOPriceModel());
    return vo;
  }
}
