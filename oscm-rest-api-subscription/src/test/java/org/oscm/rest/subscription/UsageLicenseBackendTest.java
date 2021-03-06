/**
 * *****************************************************************************
 *
 * <p>Copyright FUJITSU LIMITED 2020
 *
 * <p>Creation Date: 02-04-2020
 *
 * <p>*****************************************************************************
 */
package org.oscm.rest.subscription;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;
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
import org.oscm.internal.intf.IdentityService;
import org.oscm.internal.intf.SubscriptionService;
import org.oscm.internal.vo.VOSubscriptionDetails;
import org.oscm.internal.vo.VOUser;
import org.oscm.rest.common.SampleTestDataUtility;
import org.oscm.rest.common.representation.RepresentationCollection;
import org.oscm.rest.common.representation.UsageLicenseCreationRepresentation;
import org.oscm.rest.common.representation.UsageLicenseRepresentation;
import org.oscm.rest.common.requestparameters.SubscriptionParameters;

@ExtendWith(MockitoExtension.class)
public class UsageLicenseBackendTest {

  @Mock private SubscriptionService service;
  @Mock private IdentityService identityService;
  @InjectMocks private UsageLicenseBackend backend;
  private UsageLicenseResource resource;

  private UriInfo uriInfo;
  private SubscriptionParameters parameters;
  private UsageLicenseRepresentation representation;
  private UsageLicenseCreationRepresentation creationRepresentation;
  private VOSubscriptionDetails vo;
  private VOUser voUser;

  @BeforeEach
  public void setUp() {
    resource = new UsageLicenseResource();
    resource.setUlb(backend);
    uriInfo = SampleTestDataUtility.createUriInfo();
    parameters = SampleTestDataUtility.createSubscriptionParameters();
    representation = SampleTestDataUtility.createUsageLicenseRepresentation(null);
    creationRepresentation = new UsageLicenseCreationRepresentation();
    vo = SampleTestDataUtility.createVOSubscriptionDetails();
    voUser = SampleTestDataUtility.createVOUserDetails();
  }

  @Test
  @SneakyThrows
  public void shouldGetLicenses() {
    // given
    doReturn(vo).when(service).getSubscriptionDetails(anyLong());

    // when
    Response response =
        resource.getLicenses(
            uriInfo, String.valueOf(parameters.getVersion()), String.valueOf(parameters.getId()));

    // then
    assertThat(response).isNotNull();
    assertThat(response)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.OK.getStatusCode());
    assertThat(response).extracting(Response::hasEntity).isEqualTo(true);
    assertThat(response)
        .extracting(
            r -> {
              return ((RepresentationCollection) r.getEntity()).getItems().size();
            })
        .isEqualTo(1);
  }

  @Test
  @SneakyThrows
  public void shouldCreateLicense() {
    when(service.addRevokeUser(any(), any(), any())).thenReturn(true);
    when(service.getSubscriptionDetails(anyLong())).thenReturn(vo);
    when(identityService.getUser(any())).thenReturn(voUser);

    Response response =
        resource.createLicense(
            uriInfo,
            creationRepresentation,
            String.valueOf(parameters.getVersion()),
            String.valueOf(parameters.getId()));

    assertThat(response).isNotNull();
    assertThat(response)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.CREATED.getStatusCode());
  }

  @Test
  @SneakyThrows
  public void shouldDeleteLicense() {
    when(service.getSubscriptionDetails(anyLong())).thenReturn(vo);

    Response response =
        resource.deleteLicense(
            uriInfo,
            String.valueOf(parameters.getVersion()),
            String.valueOf(parameters.getId()),
            parameters.getLicKey().toString());

    assertThat(response).isNotNull();
    assertThat(response)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.NO_CONTENT.getStatusCode());
  }
}
