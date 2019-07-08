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
import org.oscm.rest.common.RepresentationCollection;
import org.oscm.rest.common.SampleTestDataUtility;
import org.oscm.rest.subscription.data.ServiceRepresentation;
import org.oscm.rest.subscription.data.SubscriptionCreationRepresentation;
import org.oscm.rest.subscription.data.UdaRepresentation;

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
    representation = createRepresentation();
    parameters = createParameters();
    voUserSubscription = createVOUserSubscription();
    voSubscriptionDetails = createVOSubscriptionDetails();
    voSubscription = createVOSubscription();
  }

  @Test
  @SneakyThrows
  public void shouldGetSubscriptionsForUser() {
    when(serviceInternal.getSubscriptionsForUser(any(), any()))
        .thenReturn(Lists.newArrayList(voUserSubscription));

    parameters.setUserId("uid");
    Response response = resource.getSubscriptions(uriInfo, parameters);

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

    Response response = resource.getSubscriptions(uriInfo, parameters);

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

    Response response = resource.getSubscription(uriInfo, parameters);

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

    Response response = resource.createSubscription(uriInfo, representation, parameters);

    assertThat(response).isNotNull();
    assertThat(response)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.CREATED.getStatusCode());
  }

  @Test
  @SneakyThrows
  public void shouldUpdateSubscription() {
    when(service.modifySubscription(any(), any(), any())).thenReturn(voSubscriptionDetails);

    Response response = resource.updateSubscription(uriInfo, representation, parameters);

    assertThat(response).isNotNull();
    assertThat(response)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.NO_CONTENT.getStatusCode());
  }

  @Test
  @SneakyThrows
  public void shouldDeleteSubscription() {
    when(service.unsubscribeFromService(anyLong())).thenReturn(true);

    Response response = resource.deleteSubscription(uriInfo, parameters);

    assertThat(response).isNotNull();
    assertThat(response)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.NO_CONTENT.getStatusCode());
  }

  private SubscriptionCreationRepresentation createRepresentation() {
    SubscriptionCreationRepresentation subscriptionCreationRepresentation =
        new SubscriptionCreationRepresentation();
    subscriptionCreationRepresentation.setService(new ServiceRepresentation());
    subscriptionCreationRepresentation.setUdas(Lists.newArrayList(new UdaRepresentation()));
    return subscriptionCreationRepresentation;
  }

  private SubscriptionParameters createParameters() {
    SubscriptionParameters subscriptionParameters = new SubscriptionParameters();
    subscriptionParameters.setId(123L);
    return subscriptionParameters;
  }

  private VOUserSubscription createVOUserSubscription() {
    VOUserSubscription voUserSubscription = new VOUserSubscription();

    VOUsageLicense voUsageLicense = new VOUsageLicense();
    voUsageLicense.setRoleDefinition(new VORoleDefinition());
    voUserSubscription.setLicense(voUsageLicense);

    voUsageLicense.setUser(new VOUser());

    return voUserSubscription;
  }

  private VOSubscriptionDetails createVOSubscriptionDetails() {
    VOSubscriptionDetails voSubscriptionDetails = new VOSubscriptionDetails();
    voSubscriptionDetails.setPriceModel(new VOPriceModel());
    voSubscriptionDetails.setSubscribedService(new VOService());
    return voSubscriptionDetails;
  }

  private VOSubscription createVOSubscription() {
    return new VOSubscription();
  }
}
