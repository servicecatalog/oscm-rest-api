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
import static org.mockito.Mockito.when;

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
import org.oscm.internal.vo.VOServiceDetails;
import org.oscm.rest.common.SampleTestDataUtility;
import org.oscm.rest.common.representation.PriceModelRepresentation;
import org.oscm.rest.common.requestparameters.ServiceParameters;

@SuppressWarnings({"boxing"})
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
    parameters = SampleTestDataUtility.createServiceParameters();
    representation = SampleTestDataUtility.createPriceModelRepresentation();
    vo = SampleTestDataUtility.createVOServiceDetails();
    resource.setPmb(backend);
  }

  @Test
  @SneakyThrows
  public void shouldGetPricemodel() {
    when(service.getServiceDetails(any())).thenReturn(vo);

    Response response =
        resource.get(uriInfo, parameters.getEndpointVersion(), parameters.getId().toString());

    assertThat(response).isNotNull();
    assertThat(response)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.OK.getStatusCode());
  }

  @Test
  @SneakyThrows
  public void shouldGetPricemodelForCustomer() {
    when(service.getServiceForCustomer(any(), any())).thenReturn(vo);

    Response response =
        resource.getForCustomer(
            uriInfo,
            parameters.getEndpointVersion(),
            parameters.getId().toString(),
            parameters.getOrgKey().toString());

    assertThat(response).isNotNull();
    assertThat(response)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.OK.getStatusCode());
  }

  @Test
  @SneakyThrows
  public void shouldUpdatePricemodel() {
    when(service.savePriceModel(any(), any())).thenReturn(vo);
    when(service.getServiceDetails(any())).thenReturn(vo);

    Response response =
        resource.update(
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
  public void shouldUpdatePricemodelForCustomer() {
    when(service.savePriceModelForCustomer(any(), any(), any())).thenReturn(vo);
    when(service.getServiceForCustomer(any(), any())).thenReturn(vo);

    Response response =
        resource.updateForCustomer(
            uriInfo,
            representation,
            parameters.getEndpointVersion(),
            parameters.getId().toString(),
            parameters.getOrgKey().toString());

    assertThat(response).isNotNull();
    assertThat(response)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.NO_CONTENT.getStatusCode());
  }
}
