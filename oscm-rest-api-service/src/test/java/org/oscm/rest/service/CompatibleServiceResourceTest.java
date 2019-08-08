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

import com.google.common.collect.Lists;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.oscm.rest.common.representation.RepresentationCollection;
import org.oscm.rest.common.SampleTestDataUtility;
import org.oscm.rest.common.requestparameters.ServiceParameters;
import org.oscm.rest.common.representation.ServiceRepresentation;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CompatibleServiceResourceTest {

  @Mock private ServiceBackend serviceBackend;

  @InjectMocks @Spy private CompatibleServiceResource compatibleServiceResource;

  private Response response;
  private ServiceRepresentation serviceRepresentation;
  private UriInfo uriInfo;
  private ServiceParameters serviceParameters;

  @BeforeEach
  public void setUp() {
    serviceRepresentation = SampleTestDataUtility.createServiceRepresentation();
    uriInfo = SampleTestDataUtility.createUriInfo();
    serviceParameters = SampleTestDataUtility.createServiceParameters();
  }

  @AfterEach
  public void cleanUp() {
    response = null;
  }

  @Test
  public void shouldGetCompatibleServices() {
    when(serviceBackend.getCompatibles())
        .thenReturn(
            serviceParameters ->
                new RepresentationCollection<>(Lists.newArrayList(serviceRepresentation)));

    try {
      response = compatibleServiceResource.getCompatibleServices(uriInfo, serviceParameters);
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
  public void shouldSetCompatibleServices() {
    when(serviceBackend.putCompatibles())
        .thenReturn((serviceRepresentation, serviceParameters) -> true);

    try {
      response =
          compatibleServiceResource.setCompatibleServices(uriInfo, getContent(), serviceParameters);
    } catch (Exception e) {
      fail(e);
    }

    assertThat(response).isNotNull();
    assertThat(response)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.NO_CONTENT.getStatusCode());
    assertThat(response).extracting(Response::hasEntity).isEqualTo(false);
  }

  private RepresentationCollection<ServiceRepresentation> getContent() {
    return new RepresentationCollection<>(Lists.newArrayList(serviceRepresentation));
  }
}
