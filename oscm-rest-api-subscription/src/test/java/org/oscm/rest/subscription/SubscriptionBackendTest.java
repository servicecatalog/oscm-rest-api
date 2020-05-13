package org.oscm.rest.subscription;

import com.google.common.collect.Lists;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.oscm.internal.intf.AccountService;
import org.oscm.internal.intf.ServiceProvisioningService;
import org.oscm.internal.intf.SubscriptionService;
import org.oscm.internal.intf.SubscriptionServiceInternal;
import org.oscm.internal.vo.VOServiceDetails;
import org.oscm.internal.vo.VOSubscription;
import org.oscm.internal.vo.VOSubscriptionDetails;
import org.oscm.internal.vo.VOUserSubscription;
import org.oscm.rest.common.SampleTestDataUtility;
import org.oscm.rest.common.representation.RepresentationCollection;
import org.oscm.rest.common.representation.SubscriptionCreationRepresentation;
import org.oscm.rest.common.representation.SubscriptionUpdateRepresentation;
import org.oscm.rest.common.requestparameters.SubscriptionParameters;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SubscriptionBackendTest {

  @Mock private SubscriptionServiceInternal serviceInternal;
  @Mock private SubscriptionService service;
  @Mock private ServiceProvisioningService sps;
  @Mock private AccountService as;
  @InjectMocks private SubscriptionBackend backend;

  private SubscriptionResource resource;

  private UriInfo uriInfo;
  private SubscriptionCreationRepresentation creationRepresentation;
  private SubscriptionUpdateRepresentation updateRepresentation;
  private SubscriptionParameters parameters;
  private VOUserSubscription voUserSubscription;
  private VOSubscriptionDetails voSubscriptionDetails;
  private VOSubscription voSubscription;
  private VOServiceDetails voServiceDetails;

  @BeforeEach
  public void setUp() {
    resource = new SubscriptionResource();
    resource.setSb(backend);
    uriInfo = SampleTestDataUtility.createUriInfo();
    creationRepresentation = SampleTestDataUtility.createSubscriptionCreationRepresentation();
    updateRepresentation = SampleTestDataUtility.createSubscriptionUpdateRepresentation();
    parameters = SampleTestDataUtility.createSubscriptionParameters();
    voUserSubscription = SampleTestDataUtility.createVOUserSubscription();
    voSubscriptionDetails = SampleTestDataUtility.createVOSubscriptionDetails();
    voSubscription = SampleTestDataUtility.createVOSubscription();
    voServiceDetails = SampleTestDataUtility.createVOServiceDetails();
  }

  @Test
  @SneakyThrows
  public void shouldGetSubscriptionsForUser() {
    when(serviceInternal.getSubscriptionsForUser(any(), any()))
        .thenReturn(Lists.newArrayList(voUserSubscription));
    parameters.setUserId("uid");

    Response response =
        resource.getSubscriptions(uriInfo, parameters.getEndpointVersion(), parameters.getUserId());

    assertThat(response).isNotNull();
    assertThat(response)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.OK.getStatusCode());
    assertThat(response).extracting(Response::hasEntity).isEqualTo(true);
    assertThat(response)
        .extracting(
            r -> ((RepresentationCollection) r.getEntity()).getItems().size())
        .isEqualTo(1);
  }

  @Test
  @SneakyThrows
  public void shouldGetSubscriptionsForOrganization() {
    when(serviceInternal.getSubscriptionsForOrganization(any()))
        .thenReturn(Lists.newArrayList(voUserSubscription));

    Response response = resource.getSubscriptions(uriInfo, parameters.getEndpointVersion(), null);

    assertThat(response).isNotNull();
    assertThat(response)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.OK.getStatusCode());
    assertThat(response).extracting(Response::hasEntity).isEqualTo(true);
    assertThat(response)
        .extracting(
            r -> ((RepresentationCollection) r.getEntity()).getItems().size())
        .isEqualTo(1);
  }

  @Test
  @SneakyThrows
  public void shouldGetSubscriptionById() {
    when(service.getSubscriptionDetails(anyLong())).thenReturn(voSubscriptionDetails);

    Response response =
        resource.getSubscription(
            uriInfo, parameters.getEndpointVersion(), parameters.getId().toString());

    assertThat(response).isNotNull();
    assertThat(response)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.OK.getStatusCode());
    assertThat(response).extracting(Response::hasEntity).isEqualTo(true);
  }

  @Test
  @SneakyThrows
  public void shouldCreateSubscription() {
    when(service.subscribeToService(any(), any(), any(), any(), any(), any()))
        .thenReturn(voSubscription);
    when(sps.getServiceDetails(any())).thenReturn(voServiceDetails);
    when(as.getUdaDefinitionsForCustomer(any())).thenReturn(Lists.newArrayList());

    Response response =
        resource.createSubscription(
            uriInfo, creationRepresentation, parameters.getEndpointVersion());

    assertThat(response).isNotNull();
    assertThat(response)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.CREATED.getStatusCode());
  }

  @Test
  @SneakyThrows
  public void shouldUpdateSubscription() {
    when(service.modifySubscription(any(), any(), any())).thenReturn(voSubscriptionDetails);
    when(service.getSubscriptionDetails(anyLong())).thenReturn(voSubscriptionDetails);

    Response response =
        resource.updateSubscription(
            uriInfo,
            updateRepresentation,
            parameters.getEndpointVersion(),
            parameters.getId().toString());

    assertThat(response).isNotNull();
    assertThat(response)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.NO_CONTENT.getStatusCode());
  }

  @Test
  @SneakyThrows
  public void shouldDeleteSubscription() {
    when(service.unsubscribeFromService(anyLong())).thenReturn(true);

    Response response =
        resource.deleteSubscription(
            uriInfo, parameters.getEndpointVersion(), parameters.getId().toString());

    assertThat(response).isNotNull();
    assertThat(response)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.NO_CONTENT.getStatusCode());
  }
}
