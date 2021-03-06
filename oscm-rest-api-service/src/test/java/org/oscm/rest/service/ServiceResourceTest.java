/**
 * *****************************************************************************
 *
 * <p>Copyright FUJITSU LIMITED 2019
 *
 * <p>Creation Date: 26-04-2019
 *
 * <p>*****************************************************************************
 */
package org.oscm.rest.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

import com.google.common.collect.Lists;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.oscm.internal.types.enumtypes.Sorting;
import org.oscm.internal.vo.VOServiceDetails;
import org.oscm.rest.common.SampleTestDataUtility;
import org.oscm.rest.common.representation.*;
import org.oscm.rest.common.requestparameters.ServiceParameters;

@ExtendWith(MockitoExtension.class)
public class ServiceResourceTest {

  @Mock private ServiceBackend serviceBackend;

  @InjectMocks @Spy private ServiceResource serviceResource;

  private Response response;
  private ServiceRepresentation serviceRepresentation;
  private ServiceDetailsRepresentation serviceDetailsRepresentation;
  private ServiceCreateRepresentation serviceCreateRepresentation;
  private ServiceUpdateRepresentation serviceUpdateRepresentation;
  private UriInfo uriInfo;
  private ServiceParameters serviceParameters;
  private StatusRepresentation statusRepresentation;
  private VOServiceDetails voServiceDetails;

  @BeforeEach
  public void setUp() {
    voServiceDetails = SampleTestDataUtility.createVOServiceDetails();
    serviceRepresentation = SampleTestDataUtility.createServiceRepresentation();
    serviceDetailsRepresentation =
        SampleTestDataUtility.createServiceDetailsRepresentation(voServiceDetails);
    serviceCreateRepresentation = SampleTestDataUtility.createServiceCreateRepresentation();
    serviceUpdateRepresentation = new ServiceUpdateRepresentation();
    statusRepresentation = SampleTestDataUtility.createStatusRepresentation();
    uriInfo = SampleTestDataUtility.createUriInfo();
    serviceParameters = SampleTestDataUtility.createServiceParameters();
  }

  @AfterEach
  public void cleanUp() {
    response = null;
  }

  @Test
  public void shouldGetServices() {
    when(serviceBackend.getCollection())
        .thenReturn(
            serviceParameters1 ->
                new RepresentationCollection<>(Lists.newArrayList(serviceRepresentation)));

    try {
      response =
          serviceResource.getServices(
              uriInfo,
              serviceParameters.getEndpointVersion(),
              null,
              null,
              null,
              null,
              null,
              null,
              Sorting.RATING_ASCENDING);
    } catch (Exception e) {
      fail(e);
    }

    assertThat(response).isNotNull();
    assertThat(response)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.OK.getStatusCode());
    assertThat(response).extracting(Response::hasEntity).isEqualTo(true);
    assertThat(response)
        .extracting(
            r -> {
              RepresentationCollection<ServiceRepresentation> representationCollection =
                  (RepresentationCollection<ServiceRepresentation>) r.getEntity();
              return representationCollection.getItems().toArray()[0];
            })
        .isEqualTo(serviceRepresentation);
  }

  @Test
  public void shouldCreateService() {
    when(serviceBackend.post())
        .thenReturn((serviceDetailsRepresentation1, serviceParameters1) -> true);

    try {
      response =
          serviceResource.createService(
              uriInfo, serviceCreateRepresentation, serviceParameters.getEndpointVersion());
    } catch (Exception e) {
      fail(e);
    }

    assertThat(response).isNotNull();
    assertThat(response)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.CREATED.getStatusCode());
    assertThat(response).extracting(Response::hasEntity).isEqualTo(true);
  }

  @Test
  public void shouldGetService() {
    when(serviceBackend.get()).thenReturn(serviceParameters -> serviceDetailsRepresentation);

    try {
      response =
          serviceResource.getService(
              uriInfo,
              serviceParameters.getEndpointVersion(),
              serviceParameters.getId().toString());
    } catch (Exception e) {
      fail(e);
    }

    assertThat(response).isNotNull();
    assertThat(response)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.OK.getStatusCode());
    assertThat(response).extracting(Response::hasEntity).isEqualTo(true);
    assertThat(response).extracting(Response::getEntity).isEqualTo(serviceDetailsRepresentation);
  }

  @Test
  public void shouldUpdateService() {
    when(serviceBackend.put())
        .thenReturn((serviceDetailsRepresentation1, serviceParameters1) -> true);

    try {
      response =
          serviceResource.updateService(
              uriInfo,
              serviceUpdateRepresentation,
              serviceParameters.getEndpointVersion(),
              serviceParameters.getId().toString());
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
  public void shouldDeleteService() {
    when(serviceBackend.delete()).thenReturn(serviceParameters1 -> true);

    try {
      response =
          serviceResource.deleteService(
              uriInfo,
              serviceParameters.getEndpointVersion(),
              serviceParameters.getId().toString());
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
  public void setServiceState() {
    when(serviceBackend.putStatus())
        .thenReturn((statusRepresentation1, serviceParameters1) -> true);

    try {
      response =
          serviceResource.setServiceState(
              uriInfo,
              statusRepresentation,
              serviceParameters.getEndpointVersion(),
              serviceParameters.getId().toString());
    } catch (Exception e) {
      fail(e);
    }

    assertThat(response).isNotNull();
    assertThat(response)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.NO_CONTENT.getStatusCode());
    assertThat(response).extracting(Response::hasEntity).isEqualTo(false);
  }
}
