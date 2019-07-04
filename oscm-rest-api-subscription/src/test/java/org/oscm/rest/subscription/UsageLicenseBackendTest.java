package org.oscm.rest.subscription;

import com.google.common.collect.Lists;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.oscm.internal.intf.SubscriptionService;
import org.oscm.internal.vo.VORoleDefinition;
import org.oscm.internal.vo.VOSubscriptionDetails;
import org.oscm.internal.vo.VOUsageLicense;
import org.oscm.internal.vo.VOUser;
import org.oscm.rest.common.RepresentationCollection;
import org.oscm.rest.common.SampleTestDataUtility;
import org.oscm.rest.subscription.data.UsageLicenseRepresentation;
import org.oscm.rest.subscription.data.UserRepresentation;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UsageLicenseBackendTest {

  @Mock private SubscriptionService service;
  @InjectMocks private UsageLicenseBackend backend;
  private UsageLicenseResource resource;

  private UriInfo uriInfo;
  private SubscriptionParameters parameters;
  private UsageLicenseRepresentation representation;
  private VOSubscriptionDetails vo;

  @BeforeEach
  public void setUp() {
    resource = new UsageLicenseResource();
    resource.setUlb(backend);
    uriInfo = SampleTestDataUtility.createUriInfo();
    parameters = createParameters();
    representation = createRepresentation();
    vo = createVO();
  }

  @Test
  @SneakyThrows
  public void shouldGetLicenses() {
    doReturn(vo).when(service).getSubscriptionDetails(anyLong());

    Response response = resource.getLicenses(uriInfo, parameters);

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

    Response response = resource.createLicense(uriInfo, representation, parameters);

    assertThat(response).isNotNull();
    assertThat(response)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.CREATED.getStatusCode());
  }

  @Test
  @SneakyThrows
  public void shouldUpdateLicense() {
    when(service.addRevokeUser(any(), any(), any())).thenReturn(true);
    when(service.getSubscriptionDetails(anyLong())).thenReturn(vo);

    Response response = resource.updateLicense(uriInfo, representation, parameters);

    assertThat(response).isNotNull();
    assertThat(response)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.NO_CONTENT.getStatusCode());
  }

  @Test
  @SneakyThrows
  public void shouldDeleteLicesne() {
    when(service.getSubscriptionDetails(anyLong())).thenReturn(vo);

    Response response = resource.deleteLicense(uriInfo, parameters);

    assertThat(response).isNotNull();
    assertThat(response)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.NO_CONTENT.getStatusCode());
  }

  private SubscriptionParameters createParameters() {
    SubscriptionParameters subscriptionParameters = new SubscriptionParameters();
    subscriptionParameters.setId(123L);
    subscriptionParameters.setLicKey(123L);
    return subscriptionParameters;
  }

  private UsageLicenseRepresentation createRepresentation() {
    UsageLicenseRepresentation usageLicenseRepresentation = new UsageLicenseRepresentation();

    UserRepresentation userRepresentation = new UserRepresentation();
    userRepresentation.setUserId("uid");
    usageLicenseRepresentation.setUser(userRepresentation);

    return usageLicenseRepresentation;
  }

  private VOSubscriptionDetails createVO() {
    VOUsageLicense voUsageLicense = new VOUsageLicense();
    voUsageLicense.setRoleDefinition(new VORoleDefinition());

    VOUser voUser = new VOUser();
    voUser.setUserId("uid");
    voUsageLicense.setUser(voUser);

    voUsageLicense.setKey(123L);

    VOSubscriptionDetails voSubscriptionDetails = new VOSubscriptionDetails();
    voSubscriptionDetails.setUsageLicenses(Lists.newArrayList(voUsageLicense));
    return voSubscriptionDetails;
  }
}
