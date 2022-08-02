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
import org.oscm.rest.common.SampleTestDataUtility;
import org.oscm.rest.common.representation.OrganizationRepresentation;
import org.oscm.rest.common.representation.RepresentationCollection;
import org.oscm.rest.common.representation.ServiceRepresentation;
import org.oscm.rest.common.requestparameters.ServiceParameters;

@SuppressWarnings({"unchecked", "boxing"})
@ExtendWith(MockitoExtension.class)
public class TSSupplierResourceTest {

  @Mock private TSSupplierBackend tsSupplierBackend;

  @InjectMocks @Spy private TSSupplierResource tsSupplierResource;

  private Response response;
  private OrganizationRepresentation organizationRepresentation;
  private UriInfo uriInfo;
  private ServiceParameters serviceParameters;

  @BeforeEach
  public void setUp() {
    organizationRepresentation = SampleTestDataUtility.createOrgRepresentation();
    uriInfo = SampleTestDataUtility.createUriInfo();
    serviceParameters = SampleTestDataUtility.createServiceParameters();
  }

  @AfterEach
  public void cleanUp() {
    response = null;
  }

  @Test
  public void shouldGetSuppliers() {
    when(tsSupplierBackend.getCollection())
        .thenReturn(
            serviceParameters1 ->
                new RepresentationCollection<>(Lists.newArrayList(organizationRepresentation)));

    try {
      response =
          tsSupplierResource.getSuppliers(
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
    assertThat(response)
        .extracting(
            r -> {
              RepresentationCollection<ServiceRepresentation> representationCollection =
                  (RepresentationCollection<ServiceRepresentation>) r.getEntity();
              return representationCollection.getItems().toArray()[0];
            })
        .isEqualTo(organizationRepresentation);
  }

  @Test
  public void shouldAddSupplier() {
    when(tsSupplierBackend.post())
        .thenReturn((organizationRepresentation1, serviceParameters1) -> true);

    try {
      response =
          tsSupplierResource.addSupplier(
              uriInfo,
              organizationRepresentation,
              serviceParameters.getEndpointVersion(),
              serviceParameters.getId().toString());
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
  public void shouldDeleteSupplier() {
    when(tsSupplierBackend.delete()).thenReturn(serviceParameters1 -> true);

    try {
      response =
          tsSupplierResource.removeSupplier(
              uriInfo,
              serviceParameters.getEndpointVersion(),
              serviceParameters.getId().toString(),
              serviceParameters.getOrgId());
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
