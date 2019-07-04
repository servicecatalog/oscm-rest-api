/**
 * *****************************************************************************
 *
 * <p>Copyright FUJITSU LIMITED 2019
 *
 * <p>Creation Date: 18-04-2019
 *
 * <p>*****************************************************************************
 */
package org.oscm.rest.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.oscm.rest.common.SampleTestDataUtility;
import org.oscm.rest.common.requestparameters.ServiceParameters;
import org.oscm.rest.common.representation.PriceModelRepresentation;
import org.oscm.rest.common.representation.ServiceRepresentation;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PriceModelResourceTest {

  @Mock private PriceModelBackend priceModelBackend;

  @InjectMocks @Spy private PriceModelResource priceModelResource;

  private Response response;
  private ServiceRepresentation serviceRepresentation;
  private PriceModelRepresentation priceModelRepresentation;
  private UriInfo uriInfo;
  private ServiceParameters serviceParameters;

  @BeforeEach
  public void setUp() {
    serviceRepresentation = new ServiceRepresentation();
    priceModelRepresentation = new PriceModelRepresentation();
    uriInfo = SampleTestDataUtility.createUriInfo();
    serviceParameters = createParameters();
  }

  @AfterEach
  public void cleanUp() {
    response = null;
  }

  @Test
  public void shouldGet() {
    when(priceModelBackend.get()).thenReturn(serviceParameters1 -> priceModelRepresentation);

    try {
      response = priceModelResource.get(uriInfo, serviceParameters);
    } catch (Exception e) {
      fail(e);
    }

    assertThat(response).isNotNull();
    assertThat(response)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.OK.getStatusCode());
    assertThat(response).extracting(Response::hasEntity).isEqualTo(true);
    assertThat(response).extracting(Response::getEntity).isEqualTo(priceModelRepresentation);
  }

  @Test
  public void shouldUpdate() {
    when(priceModelBackend.put())
        .thenReturn((priceModelRepresentation1, serviceParameters1) -> true);

    try {
      response = priceModelResource.update(uriInfo, priceModelRepresentation, serviceParameters);
    } catch (Exception e) {
      fail(e);
    }

    assertThat(response).isNotNull();
    assertThat(response)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.NO_CONTENT.getStatusCode());
    assertThat(response).extracting(Response::hasEntity).isEqualTo(false);
  }

  @Test
  public void shouldGetForCustomer() {
    when(priceModelBackend.getForCustomer())
        .thenReturn(serviceParameters1 -> priceModelRepresentation);

    try {
      response = priceModelResource.getForCustomer(uriInfo, serviceParameters);
    } catch (Exception e) {
      fail(e);
    }

    assertThat(response).isNotNull();
    assertThat(response)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.OK.getStatusCode());
    assertThat(response).extracting(Response::hasEntity).isEqualTo(true);
    assertThat(response).extracting(Response::getEntity).isEqualTo(priceModelRepresentation);
  }

  @Test
  public void shouldUpdateForCustomer() {
    when(priceModelBackend.putForCustomer())
        .thenReturn((priceModelRepresentation1, serviceParameters1) -> true);

    try {
      response =
          priceModelResource.updateForCustomer(
              uriInfo, priceModelRepresentation, serviceParameters);
    } catch (Exception e) {
      fail(e);
    }

    assertThat(response).isNotNull();
    assertThat(response)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.NO_CONTENT.getStatusCode());
    assertThat(response).extracting(Response::hasEntity).isEqualTo(false);
  }

  private ServiceParameters createParameters() {
    ServiceParameters parameters = new ServiceParameters();
    parameters.setId(100L);
    return parameters;
  }
}
