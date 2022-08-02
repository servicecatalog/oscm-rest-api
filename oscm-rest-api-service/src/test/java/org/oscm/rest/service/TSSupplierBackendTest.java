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
import org.oscm.internal.intf.AccountService;
import org.oscm.internal.vo.VOOrganization;
import org.oscm.rest.common.SampleTestDataUtility;
import org.oscm.rest.common.representation.OrganizationRepresentation;
import org.oscm.rest.common.representation.RepresentationCollection;
import org.oscm.rest.common.requestparameters.ServiceParameters;

@SuppressWarnings({"rawtypes", "boxing"})
@ExtendWith(MockitoExtension.class)
public class TSSupplierBackendTest {

  @Mock private AccountService service;
  @InjectMocks private TSSupplierBackend backend;
  private TSSupplierResource resource;

  private OrganizationRepresentation representation;
  private ServiceParameters parameters;
  private UriInfo uriInfo;
  private VOOrganization vo;

  @BeforeEach
  public void setUp() {
    resource = new TSSupplierResource();
    resource.setSb(backend);
    vo = SampleTestDataUtility.createVOOrganization();
    representation = SampleTestDataUtility.createOrgRepresentation();
    parameters = SampleTestDataUtility.createServiceParameters();
    uriInfo = SampleTestDataUtility.createUriInfo();
  }

  @Test
  @SneakyThrows
  public void shouldGetSuppliers() {
    when(service.getSuppliersForTechnicalService(any()))
        .thenReturn(Lists.newArrayList(new VOOrganization()));

    Response response =
        resource.getSuppliers(
            uriInfo, parameters.getEndpointVersion(), parameters.getId().toString());

    assertThat(response).isNotNull();
    assertThat(response)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.OK.getStatusCode());
    assertThat(response)
        .extracting(Response::getEntity)
        .isInstanceOf(RepresentationCollection.class);
    assertThat(response)
        .extracting(
            e -> {
              return ((RepresentationCollection) e.getEntity()).getItems().size();
            })
        .isEqualTo(1);
  }

  @Test
  @SneakyThrows
  public void shouldAddSupplier() {
    doNothing().when(service).addSuppliersForTechnicalService(any(), any());

    Response response =
        resource.addSupplier(
            uriInfo,
            representation,
            parameters.getEndpointVersion(),
            parameters.getId().toString());

    assertThat(response).isNotNull();
    assertThat(response)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.CREATED.getStatusCode());
  }

  @Test
  @SneakyThrows
  public void shouldDeleteSupplier() {
    doNothing().when(service).removeSuppliersFromTechnicalService(any(), any());

    Response response =
        resource.removeSupplier(
            uriInfo,
            parameters.getEndpointVersion(),
            parameters.getId().toString(),
            parameters.getOrgId());

    assertThat(response).isNotNull();
    assertThat(response)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.NO_CONTENT.getStatusCode());
  }
}
