package org.oscm.rest.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.oscm.internal.intf.ServiceProvisioningService;
import org.oscm.rest.service.data.PriceModelRepresentation;
import org.oscm.rest.service.data.ServiceRepresentation;
import org.oscm.rest.service.data.TechnicalServiceRepresentation;

import javax.ws.rs.core.*;
import java.net.URI;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TechnicalServiceResourceTest {

    @Mock
    private TechnicalServiceBackend technicalServiceBackend;

    @Mock
    private ServiceProvisioningService serviceProvisioningService;

    @InjectMocks
    @Spy
    TechnicalServiceResource technicalServiceResource;

    private Response response;
    private TechnicalServiceRepresentation technicalServiceRepresentation;
    private UriInfo uriInfo;
    private ServiceParameters serviceParameters;

    @BeforeEach
    public void setUp() {
        technicalServiceRepresentation = new TechnicalServiceRepresentation();
        uriInfo = createUriInfo();
        serviceParameters = createParameters();
    }

    @AfterEach
    public void cleanUp() {
        response = null;
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
                        put("version", Collections.singletonList("v1"));
                    }
                };
            }

            @Override
            public MultivaluedMap<String, String> getPathParameters(boolean b) {
                return null;
            }

            @Override
            public MultivaluedMap<String, String> getQueryParameters() {
                return null;
            }

            @Override
            public MultivaluedMap<String, String> getQueryParameters(boolean b) {
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

    private ServiceParameters createParameters() {
        ServiceParameters parameters = new ServiceParameters();
        parameters.setId(100L);
        return parameters;
    }

}