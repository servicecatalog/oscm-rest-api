package org.oscm.rest.subscription;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.oscm.internal.vo.VOPriceModel;
import org.oscm.internal.vo.VOService;
import org.oscm.internal.vo.VOSubscriptionDetails;
import org.oscm.rest.common.representation.RepresentationCollection;
import org.oscm.rest.common.SampleTestDataUtility;
import org.oscm.rest.common.requestparameters.SubscriptionParameters;
import org.oscm.rest.common.representation.SubscriptionCreationRepresentation;
import org.oscm.rest.common.representation.SubscriptionDetailsRepresentation;
import org.oscm.rest.common.representation.SubscriptionRepresentation;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SubscriptionResourceTest {

    @Mock
    private SubscriptionBackend subscriptionBackend;

    @Spy
    @InjectMocks
    private SubscriptionResource subscriptionResource;

    private Response response;
    private SubscriptionRepresentation subscriptionRepresentation;
    private SubscriptionDetailsRepresentation subscriptionDetailsRepresentation;
    private SubscriptionCreationRepresentation subscriptionCreationRepresentation;
    private SubscriptionParameters subscriptionParameters;
    private UriInfo uriInfo;
    private VOSubscriptionDetails voSubscriptionDetails;

    @BeforeEach
    public void setUp() {
        voSubscriptionDetails = SampleTestDataUtility.createVOSubscriptionDetails();
        subscriptionRepresentation = SampleTestDataUtility.createSubscriptionRepresentation();
        subscriptionDetailsRepresentation = SampleTestDataUtility.createSubscriptionDetailsRepresentation(voSubscriptionDetails);
        subscriptionCreationRepresentation = SampleTestDataUtility.createSubscriptionCreationRepresentation();
        subscriptionParameters = SampleTestDataUtility.createSubscriptionParameters();
        uriInfo = SampleTestDataUtility.createUriInfo();
    }

    private VOSubscriptionDetails setUpSubscriptionDetails() {
        VOSubscriptionDetails voSubscriptionDetails = new VOSubscriptionDetails();
        VOService voService = new VOService();
        voSubscriptionDetails.setSubscribedService(voService);
        VOPriceModel voPriceModel = new VOPriceModel();
        voSubscriptionDetails.setPriceModel(voPriceModel);
        return voSubscriptionDetails;
    }

    @AfterEach
    public void cleanUp() {
        response = null;
    }

    @Test
    public void shouldGetSubscriptions() {
        when(subscriptionBackend.getCollection())
                .thenReturn(
                        params -> new RepresentationCollection<>(Lists.newArrayList(subscriptionRepresentation)));

        try {
            response = subscriptionResource.getSubscriptions(uriInfo, subscriptionParameters);
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
        when(subscriptionBackend.post())
                .thenReturn((content, params) -> true);

        try {
            response = subscriptionResource.createSubscription(uriInfo, subscriptionCreationRepresentation, subscriptionParameters);
        } catch (Exception e) {
            fail(e);
        }

        assertThat(response).isNotNull();
        assertThat(response)
                .extracting(Response::getStatus)
                .isEqualTo(Response.Status.CREATED.getStatusCode());
        assertThat(response).extracting(Response::hasEntity).isEqualTo(false);
    }

    @Test
    public void shouldGetSubscription() {
        when(subscriptionBackend.get()).thenReturn(params -> subscriptionDetailsRepresentation);

        try {
            response = subscriptionResource.getSubscription(uriInfo, subscriptionParameters);
        } catch (Exception e) {
            fail(e);
        }

        assertThat(response).isNotNull();
        assertThat(response)
                .extracting(Response::getStatus)
                .isEqualTo(Response.Status.OK.getStatusCode());
        assertThat(response).extracting(Response::hasEntity).isEqualTo(true);
        assertThat(response).extracting(Response::getEntity).isEqualTo(subscriptionDetailsRepresentation);
    }

    @Test
    public void shouldUpdateSubscription() {
        when(subscriptionBackend.put())
                .thenReturn((content, params) -> true);

        try {
            response = subscriptionResource.updateSubscription(uriInfo, subscriptionCreationRepresentation, subscriptionParameters);
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
        when(subscriptionBackend.delete())
                .thenReturn(params -> true);

        try {
            response = subscriptionResource.deleteSubscription(uriInfo, subscriptionParameters);
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