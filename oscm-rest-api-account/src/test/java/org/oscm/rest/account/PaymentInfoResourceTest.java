package org.oscm.rest.account;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.oscm.rest.account.data.PaymentInfoRepresentation;
import org.oscm.rest.common.RepresentationCollection;

import javax.ws.rs.core.*;
import java.net.URI;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PaymentInfoResourceTest {

        @Mock
        private AccountBackend backend;
        @InjectMocks
        @Spy
        private PaymentInfoResource resource;

        private PaymentInfoRepresentation paymentInfoRepresentation;
        private UriInfo uriInfo;
        private AccountParameters parameters;
        private Response result;

        @BeforeEach
        public void setUp() {
                paymentInfoRepresentation = createPIRepresentation();
                parameters = createAccountParameters();
                uriInfo = createUriInfo();
        }

        @AfterEach
        public void cleanUp() {
                result = null;
        }

        @Test
        public void shouldGetPaymentInfoCollection() {
                when(backend.getPaymentInfoCollection())
                        .thenReturn(
                                (accountParameters1 ->
                                        new RepresentationCollection<>(
                                                Lists.newArrayList(
                                                        paymentInfoRepresentation))));

                try {
                        result = resource.getPaymentInfos(uriInfo, parameters);
                } catch (Exception e) {
                        fail(e);
                }

                assertThat(result).isNotNull();
                assertThat(result)
                        .extracting(Response::getStatus)
                        .isEqualTo(Response.Status.OK.getStatusCode());
                assertThat(result)
                        .extracting(
                                r -> {
                                        RepresentationCollection<PaymentInfoRepresentation> collection =
                                                (RepresentationCollection<PaymentInfoRepresentation>) r
                                                        .getEntity();
                                        return collection.getItems()
                                                .toArray()[0];
                                })
                        .isEqualTo(paymentInfoRepresentation);
        }

        @Test
        public void shouldGetSinglePaymentInfo() {
                when(backend.getPaymentInfo()).thenReturn(
                        (accountParameters -> paymentInfoRepresentation));

                try {
                        result = resource.getPaymentInfo(uriInfo, parameters);
                } catch (Exception e) {
                        fail(e);
                }

                assertThat(result).isNotNull();
                assertThat(result)
                        .extracting(Response::getStatus)
                        .isEqualTo(Response.Status.OK.getStatusCode());
                assertThat(result).extracting(Response::getEntity)
                        .isEqualTo(paymentInfoRepresentation);
        }

        @Test
        public void shouldUpdatePaymentInfo() {
                when(backend.putPaymentInfo())
                        .thenReturn(
                                ((paymentInfoRepresentation1, accountParameters) -> true));

                try {
                        result = resource.updatePaymentInfo(uriInfo,
                                paymentInfoRepresentation, parameters);
                } catch (Exception e) {
                        fail(e);
                }

                assertThat(result).isNotNull();
                assertThat(result)
                        .extracting(Response::getStatus)
                        .isEqualTo(Response.Status.NO_CONTENT.getStatusCode());
        }

        @Test
        public void shouldDeletePaymentInfo() {
                when(backend.deletePaymentInfo())
                        .thenReturn((accountParameters -> true));

                try {
                        result = resource
                                .deletePaymentInfo(uriInfo, parameters);
                } catch (Exception e) {
                        fail(e);
                }

                assertThat(result).isNotNull();
                assertThat(result)
                        .extracting(Response::getStatus)
                        .isEqualTo(Response.Status.NO_CONTENT.getStatusCode());
        }

        private PaymentInfoRepresentation createPIRepresentation() {
                PaymentInfoRepresentation representation = new PaymentInfoRepresentation();
                return representation;
        }

        private AccountParameters createAccountParameters() {
                AccountParameters parameters = new AccountParameters();
                parameters.setOrgId("orgId");
                return parameters;
        }

        private UriInfo createUriInfo() {
                return new UriInfo() {
                        @Override
                        public String getPath() {
                                return null;
                        }

                        @Override
                        public String getPath(boolean b) {
                                return null;
                        }

                        @Override
                        public List<PathSegment> getPathSegments() {
                                return null;
                        }

                        @Override
                        public List<PathSegment> getPathSegments(boolean b) {
                                return null;
                        }

                        @Override
                        public URI getRequestUri() {
                                return null;
                        }

                        @Override
                        public UriBuilder getRequestUriBuilder() {
                                return null;
                        }

                        @Override
                        public URI getAbsolutePath() {
                                return null;
                        }

                        @Override
                        public UriBuilder getAbsolutePathBuilder() {
                                return null;
                        }

                        @Override
                        public URI getBaseUri() {
                                return null;
                        }

                        @Override
                        public UriBuilder getBaseUriBuilder() {
                                return null;
                        }

                        @Override
                        public MultivaluedMap<String, String> getPathParameters() {
                                return new MultivaluedHashMap<String, String>() {
                                        {
                                                put("version", Collections
                                                        .singletonList("v1"));
                                        }
                                };
                        }

                        @Override
                        public MultivaluedMap<String, String> getPathParameters(
                                boolean b) {
                                return null;
                        }

                        @Override
                        public MultivaluedMap<String, String> getQueryParameters() {
                                return null;
                        }

                        @Override
                        public MultivaluedMap<String, String> getQueryParameters(
                                boolean b) {
                                return null;
                        }

                        @Override
                        public List<String> getMatchedURIs() {
                                return null;
                        }

                        @Override
                        public List<String> getMatchedURIs(boolean b) {
                                return null;
                        }

                        @Override
                        public List<Object> getMatchedResources() {
                                return null;
                        }

                        @Override
                        public URI resolve(URI uri) {
                                return null;
                        }

                        @Override
                        public URI relativize(URI uri) {
                                return null;
                        }
                };
        }
}
