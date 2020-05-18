/**
 * *****************************************************************************
 *
 * <p>Copyright FUJITSU LIMITED 2020
 *
 * <p>Creation Date: 18-05-2020
 *
 * <p>*****************************************************************************
 */
package org.oscm.rest.subscription;

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
import org.oscm.internal.vo.VOSubscriptionDetails;
import org.oscm.rest.common.SampleTestDataUtility;
import org.oscm.rest.common.representation.*;
import org.oscm.rest.common.requestparameters.SubscriptionParameters;

@ExtendWith(MockitoExtension.class)
public class SubscriptionResourceTest {

  @Mock private SubscriptionBackend subscriptionBackend;

  @Spy @InjectMocks private SubscriptionResource subscriptionResource;

  private Response response;
  private SubscriptionRepresentation subscriptionRepresentation;
  private SubscriptionDetailsRepresentation subscriptionDetailsRepresentation;
  private SubscriptionUpdateRepresentation subscriptionUpdateRepresentation;
  private SubscriptionCreationRepresentation subscriptionCreationRepresentation;
  private SubscriptionParameters subscriptionParameters;
  private UriInfo uriInfo;
  private VOSubscriptionDetails voSubscriptionDetails;

  @BeforeEach
  public void setUp() {
    voSubscriptionDetails = SampleTestDataUtility.createVOSubscriptionDetails();
    subscriptionRepresentation = SampleTestDataUtility.createSubscriptionRepresentation();
    subscriptionDetailsRepresentation =
        SampleTestDataUtility.createSubscriptionDetailsRepresentation(voSubscriptionDetails);
    subscriptionCreationRepresentation =
        SampleTestDataUtility.createSubscriptionCreationRepresentation();
    subscriptionUpdateRepresentation =
        SampleTestDataUtility.createSubscriptionUpdateRepresentation();
    subscriptionParameters = SampleTestDataUtility.createSubscriptionParameters();
    uriInfo = SampleTestDataUtility.createUriInfo();
  }

  @AfterEach
  public void cleanUp() {
    response = null;
  }

  @Test
  public void shouldGetSubscriptions() {
    when(subscriptionBackend.getCollection())
        .thenReturn(
            params ->
                new RepresentationCollection<>(Lists.newArrayList(subscriptionRepresentation)));

    try {
      response =
          subscriptionResource.getSubscriptions(
              uriInfo, subscriptionParameters.getEndpointVersion(), null);
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
              RepresentationCollection<SubscriptionRepresentation> representationCollection =
                  (RepresentationCollection<SubscriptionRepresentation>) r.getEntity();
              return representationCollection.getItems().toArray()[0];
            })
        .isEqualTo(subscriptionRepresentation);
  }

  @Test
  public void shouldCreateSubscription() {
    when(subscriptionBackend.post()).thenReturn((content, params) -> true);

    try {
      response =
          subscriptionResource.createSubscription(
              uriInfo,
              subscriptionCreationRepresentation,
              subscriptionParameters.getEndpointVersion());
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
  public void shouldGetSubscription() {
    when(subscriptionBackend.get()).thenReturn(params -> subscriptionDetailsRepresentation);

    try {
      response =
          subscriptionResource.getSubscription(
              uriInfo,
              subscriptionParameters.getEndpointVersion(),
              subscriptionParameters.getId().toString());
    } catch (Exception e) {
      fail(e);
    }

    assertThat(response).isNotNull();
    assertThat(response)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.OK.getStatusCode());
    assertThat(response).extracting(Response::hasEntity).isEqualTo(true);
    assertThat(response)
        .extracting(Response::getEntity)
        .isEqualTo(subscriptionDetailsRepresentation);
  }

  @Test
  public void shouldUpdateSubscription() {
    when(subscriptionBackend.put()).thenReturn((content, params) -> true);

    try {
      response =
          subscriptionResource.updateSubscription(
              uriInfo,
              subscriptionUpdateRepresentation,
              subscriptionParameters.getEndpointVersion(),
              subscriptionParameters.getId().toString());
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
  public void shouldDeleteSubscription() {
    when(subscriptionBackend.delete()).thenReturn(params -> true);

    try {
      response =
          subscriptionResource.deleteSubscription(
              uriInfo,
              subscriptionParameters.getEndpointVersion(),
              subscriptionParameters.getId().toString());
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
