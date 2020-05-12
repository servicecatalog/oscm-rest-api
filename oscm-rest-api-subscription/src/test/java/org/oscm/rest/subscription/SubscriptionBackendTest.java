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
import org.oscm.internal.intf.SubscriptionServiceInternal;
import org.oscm.internal.vo.*;
import org.oscm.rest.common.representation.RepresentationCollection;
import org.oscm.rest.common.SampleTestDataUtility;
import org.oscm.rest.common.requestparameters.SubscriptionParameters;
import org.oscm.rest.common.representation.SubscriptionCreationRepresentation;

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
  @InjectMocks private SubscriptionBackend backend;
  private SubscriptionResource resource;

  private UriInfo uriInfo;
  private SubscriptionCreationRepresentation representation;
  private SubscriptionParameters parameters;
  private VOUserSubscription voUserSubscription;
  private VOSubscriptionDetails voSubscriptionDetails;
  private VOSubscription voSubscription;

  @BeforeEach
  public void setUp() {
    resource = new SubscriptionResource();
    resource.setSb(backend);
    uriInfo = SampleTestDataUtility.createUriInfo();
    representation = SampleTestDataUtility.createSubscriptionCreationRepresentation();
    parameters = SampleTestDataUtility.createSubscriptionParameters();
    voUserSubscription = SampleTestDataUtility.createVOUserSubscription();
    voSubscriptionDetails = SampleTestDataUtility.createVOSubscriptionDetails();
    voSubscription = SampleTestDataUtility.createVOSubscription();
  }

  @Test
  @SneakyThrows
  public void shouldGetSubscriptionsForUser() {
    when(serviceInternal.getSubscriptionsForUser(any(), any()))
        .thenReturn(Lists.newArrayList(voUserSubscription));
    parameters.setUserId("uid");

    Response response = resource.getSubscriptions(uriInfo, parameters.getEndpointVersion(), parameters.getUserId());

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
            r -> {
              return ((RepresentationCollection) r.getEntity()).getItems().size();
            })
        .isEqualTo(1);
  }

  @Test
  @SneakyThrows
  public void shouldGetSubscriptionById() {
    when(service.getSubscriptionDetails(anyLong())).thenReturn(voSubscriptionDetails);

    Response response = resource.getSubscription(
            uriInfo,
            parameters.getEndpointVersion(),
            parameters.getId().toString());

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

    Response response = resource.createSubscription(uriInfo, representation, parameters.getEndpointVersion());

    assertThat(response).isNotNull();
    assertThat(response)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.CREATED.getStatusCode());
  }

/*  @Test
  @SneakyThrows
  public void shouldUpdateSubscription() {
    when(service.modifySubscription(any(), any(), any())).thenReturn(voSubscriptionDetails);

    Response response = resource.updateSubscription(
            uriInfo,
            representation,
            parameters.getEndpointVersion(),
            parameters.getId().toString());

    assertThat(response).isNotNull();
    assertThat(response)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.NO_CONTENT.getStatusCode());
  }*/

  @Test
  @SneakyThrows
  public void shouldDeleteSubscription() {
    when(service.unsubscribeFromService(anyLong())).thenReturn(true);

    Response response = resource.deleteSubscription(uriInfo, parameters.getEndpointVersion(), parameters.getId().toString());

    assertThat(response).isNotNull();
    assertThat(response)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.NO_CONTENT.getStatusCode());
  }
}
