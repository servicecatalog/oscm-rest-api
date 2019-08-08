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
import org.oscm.internal.vo.VORoleDefinition;
import org.oscm.internal.vo.VOUsageLicense;
import org.oscm.internal.vo.VOUser;
import org.oscm.rest.common.representation.RepresentationCollection;
import org.oscm.rest.common.SampleTestDataUtility;
import org.oscm.rest.common.requestparameters.SubscriptionParameters;
import org.oscm.rest.common.representation.SubscriptionRepresentation;
import org.oscm.rest.common.representation.UsageLicenseRepresentation;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UsageLicenseResourceTest {

    @Mock
    private UsageLicenseBackend usageLicenseBackend;

    @InjectMocks
    @Spy
    private UsageLicenseResource usageLicenseResource;

    private Response response;
    private UriInfo uriInfo;
    private SubscriptionParameters subscriptionParameters;
    private SubscriptionRepresentation subscriptionRepresentation;
    private UsageLicenseRepresentation usageLicenseRepresentation;

    @BeforeEach
    public void setUp() {
        subscriptionParameters = SampleTestDataUtility.createSubscriptionParameters();
        uriInfo = SampleTestDataUtility.createUriInfo();
        subscriptionRepresentation = SampleTestDataUtility.createSubscriptionRepresentation();
        VOUsageLicense voUsageLicense = SampleTestDataUtility.createVOUsageLicense();
        usageLicenseRepresentation = SampleTestDataUtility.createUsageLicenseRepresentation(voUsageLicense);
    }

    @AfterEach
    public void cleanUp() {
        response = null;
    }

    @Test
    public void shouldGetLicenses() {
        when(usageLicenseBackend.getCollection())
                .thenReturn(params -> new RepresentationCollection<>(Lists.newArrayList(usageLicenseRepresentation)));

        try {
            response = usageLicenseResource.getLicenses(uriInfo, subscriptionParameters);
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
                            RepresentationCollection<UsageLicenseRepresentation> representationCollection =
                                    (RepresentationCollection<UsageLicenseRepresentation>) r.getEntity();
                            return representationCollection.getItems().toArray()[0];
                        })
                .isEqualTo(usageLicenseRepresentation);
    }

    @Test
    public void shouldCreateLicense() {
        when(usageLicenseBackend.post())
                .thenReturn((content, params) -> true);

        try {
            response = usageLicenseResource.createLicense(uriInfo, usageLicenseRepresentation, subscriptionParameters);
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
    public void shouldUpdateLicense() {
        when(usageLicenseBackend.put())
                .thenReturn((content, params) -> true);

        try {
            response = usageLicenseResource.updateLicense(uriInfo, usageLicenseRepresentation, subscriptionParameters);
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
    public void shouldDeleteLicense() {
        when(usageLicenseBackend.delete())
                .thenReturn(params -> true);

        try {
            response = usageLicenseResource.deleteLicense(uriInfo, subscriptionParameters);
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