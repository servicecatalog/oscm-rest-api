package org.oscm.rest.identity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.oscm.rest.identity.data.OnBehalfUserRepresentation;

import javax.ws.rs.core.*;
import java.net.URI;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OnBehalfUserResourceTest {

        @Mock
        private UserBackend userBackend;
        @InjectMocks
        @Spy
        private OnBehalfUserResource obUserResource;

        private UriInfo uriInfo;
        private Response result;

        @BeforeEach
        public void setUp() {
                uriInfo = createUriInfo();
        }

        @AfterEach
        public void cleanUp() {
                result = null;
        }

        @Test
        public void shouldCreateOnBehalfUser() {
                OnBehalfUserRepresentation userRepresentation = createOBUserRepresentation();
                UserParameters parameters = createParameters();

                when(userBackend.postOnBehalfUser())
                        .thenReturn(
                                (onBehalfUserRepresentation, userParameters) -> "newId");

                try {
                        result = obUserResource
                                .createOnBehalfUser(uriInfo, userRepresentation,
                                        parameters);
                } catch (Exception e) {
                        fail(e);
                }

                assertThat(result).isNotNull();
                assertThat(result)
                        .extracting(Response::getStatus)
                        .isEqualTo(Response.Status.CREATED.getStatusCode());
        }

        @Test
        public void shouldDeleteOnBehalfUser() {
                UserParameters parameters = createParametersForDeletion();

                when(userBackend.deleteOBehalfUser())
                        .thenReturn(userParameters -> true);

                try {
                        result = obUserResource
                                .deleteOnBehalfUser(uriInfo, parameters);
                } catch (Exception e) {
                        fail(e);
                }

                assertThat(result)
                        .extracting(Response::getStatus)
                        .isEqualTo(Response.Status.NO_CONTENT.getStatusCode());
        }

        private OnBehalfUserRepresentation createOBUserRepresentation() {
                OnBehalfUserRepresentation userRepresentation = new OnBehalfUserRepresentation();
                userRepresentation.setUserId("userId");
                return userRepresentation;
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

        private UserParameters createParameters() {
                return new UserParameters();
        }

        private UserParameters createParametersForDeletion() {
                UserParameters parameters = new UserParameters();
                parameters.setUserId("userId");
                return parameters;
        }
}
