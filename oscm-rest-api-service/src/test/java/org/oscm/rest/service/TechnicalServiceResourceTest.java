package org.oscm.rest.service;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.oscm.internal.intf.ServiceProvisioningService;
import org.oscm.internal.types.exception.*;
import org.oscm.rest.common.RepresentationCollection;
import org.oscm.rest.service.data.ServiceRepresentation;
import org.oscm.rest.service.data.TechnicalServiceRepresentation;

import javax.ws.rs.core.*;
import java.net.URI;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TechnicalServiceResourceTest {

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

    @Test
    public void shouldGetTechnicalServices() {
        when(technicalServiceBackend.getCollection())
                .thenReturn(serviceParameters1 ->
                        new RepresentationCollection<>(Lists.newArrayList(technicalServiceRepresentation)));

        try {
            response = technicalServiceResource.getTechnicalServices(uriInfo, serviceParameters);
        } catch (Exception e) {
            fail(e);
        }

        assertThat(response).isNotNull();
        assertThat(response)
                .extracting(Response::getStatus)
                .isEqualTo(Response.Status.OK.getStatusCode());
        assertThat(response)
                .extracting(Response::hasEntity)
                .isEqualTo(true);
        assertThat(response)
                .extracting(
                        r -> {
                            RepresentationCollection<ServiceRepresentation> representationCollection =
                                    (RepresentationCollection<ServiceRepresentation>) r.getEntity();
                            return representationCollection.getItems().toArray()[0];
                        }
                )
                .isEqualTo(technicalServiceRepresentation);
    }

    @Test
    public void shouldCreateTechnicalService() {
        when(technicalServiceBackend.post())
                .thenReturn((serviceDetailsRepresentation1, serviceParameters1) -> true);

        try {
            response = technicalServiceResource.createTechnicalService(uriInfo, technicalServiceRepresentation, serviceParameters);
        } catch (Exception e) {
            fail(e);
        }

        assertThat(response).isNotNull();
        assertThat(response)
                .extracting(Response::getStatus)
                .isEqualTo(Response.Status.CREATED.getStatusCode());
        assertThat(response)
                .extracting(Response::hasEntity)
                .isEqualTo(false);
    }

    @Test
    public void shouldDeleteTechnicalService() {
        when(technicalServiceBackend.delete())
                .thenReturn(serviceParameters1 -> true);

        try {
            response = technicalServiceResource.deleteTechnicalService(uriInfo, serviceParameters);
        } catch (Exception e) {
            fail(e);
        }

        assertThat(response).isNotNull();
        assertThat(response)
                .extracting(Response::getStatus)
                .isEqualTo(Response.Status.NO_CONTENT.getStatusCode());
        assertThat(response)
                .extracting(Response::hasEntity)
                .isEqualTo(false);
    }

    @Test
    public void shouldExportTechnicalService() {
        try {
            when(serviceProvisioningService.exportTechnicalServices(any()))
                    .thenReturn(new byte[]{1});
        } catch (OrganizationAuthoritiesException | ObjectNotFoundException | OperationNotPermittedException e) {
            fail(e);
        }

        try {
            response = technicalServiceResource.exportTechnicalService(uriInfo, serviceParameters);
        } catch (Exception e) {
            fail(e);
        }

        assertThat(response).isNotNull();
        assertThat(response)
                .extracting(Response::getStatus)
                .isEqualTo(Response.Status.OK.getStatusCode());
        assertThat(response)
                .extracting(Response::hasEntity)
                .isEqualTo(true);
        assertThat(response)
                .extracting(Response::getMediaType)
                .isEqualTo(MediaType.APPLICATION_XML_TYPE);
    }

    @Test
    public void shouldImportTechnicalService_returnBadRequestWhenInputNotEmpty() {
        String message = "msg";
        try {
            when(serviceProvisioningService.importTechnicalServices(any()))
                    .thenReturn(message);
        } catch (ImportException | OperationNotPermittedException | UpdateConstraintException |
                TechnicalServiceActiveException | BillingAdapterNotFoundException |
                TechnicalServiceMultiSubscriptions | UnchangeableAllowingOnBehalfActingException e) {
            fail(e);
        }

        try {
            response = technicalServiceResource.importTechnicalServices(uriInfo, new byte[]{1}, serviceParameters);
        } catch (Exception e) {
            fail(e);
        }

        assertThat(response).isNotNull();
        assertThat(response)
                .extracting(Response::getStatus)
                .isEqualTo(Response.Status.BAD_REQUEST.getStatusCode());
        assertThat(response)
                .extracting(Response::hasEntity)
                .isEqualTo(true);
        assertThat(response)
                .extracting(r -> r.getEntity().toString())
                .isEqualTo(message);
    }

    @Test
    public void shouldImportTechnicalService_returnNoContentWhenInputEmpty() {
        String message = "";
        try {
            when(serviceProvisioningService.importTechnicalServices(any()))
                    .thenReturn(message);
        } catch (ImportException | OperationNotPermittedException | UpdateConstraintException |
                TechnicalServiceActiveException | BillingAdapterNotFoundException |
                TechnicalServiceMultiSubscriptions | UnchangeableAllowingOnBehalfActingException e) {
            fail(e);
        }

        try {
            response = technicalServiceResource.importTechnicalServices(uriInfo, new byte[]{1}, serviceParameters);
        } catch (Exception e) {
            fail(e);
        }

        assertThat(response).isNotNull();
        assertThat(response)
                .extracting(Response::getStatus)
                .isEqualTo(Response.Status.NO_CONTENT.getStatusCode());
        assertThat(response)
                .extracting(Response::hasEntity)
                .isEqualTo(false);
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