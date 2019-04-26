/**
 * *****************************************************************************
 *
 * <p>Copyright FUJITSU LIMITED 2019
 *
 * <p>Creation Date: 26-04-2019
 *
 * <p>*****************************************************************************
 */
package org.oscm.rest.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

import com.google.common.collect.Lists;
import java.net.URI;
import java.util.Collections;
import java.util.List;
import javax.ws.rs.core.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.oscm.internal.vo.VOServiceDetails;
import org.oscm.internal.vo.VOTechnicalService;
import org.oscm.rest.common.RepresentationCollection;
import org.oscm.rest.service.data.ServiceDetailsRepresentation;
import org.oscm.rest.service.data.ServiceRepresentation;
import org.oscm.rest.service.data.StatusRepresentation;

@ExtendWith(MockitoExtension.class)
public class ServiceResourceTest {

  @Mock private ServiceBackend serviceBackend;

  @InjectMocks @Spy private ServiceResource serviceResource;

  private Response response;
  private ServiceRepresentation serviceRepresentation;
  private ServiceDetailsRepresentation serviceDetailsRepresentation;
  private UriInfo uriInfo;
  private ServiceParameters serviceParameters;
  private StatusRepresentation statusRepresentation;
  private VOServiceDetails voServiceDetails;

  @BeforeEach
  public void setUp() {
    voServiceDetails = setUpServiceDetails();
    serviceRepresentation = new ServiceRepresentation();
    serviceDetailsRepresentation = new ServiceDetailsRepresentation(voServiceDetails);
    statusRepresentation = new StatusRepresentation();
    uriInfo = createUriInfo();
    serviceParameters = createParameters();
  }

  private VOServiceDetails setUpServiceDetails() {
    VOServiceDetails voServiceDetails = new VOServiceDetails();
    VOTechnicalService voTechnicalService = new VOTechnicalService();
    voServiceDetails.setTechnicalService(voTechnicalService);
    return voServiceDetails;
  }

  @AfterEach
  public void cleanUp() {
    response = null;
  }

  @Test
  public void shouldGetServices() {
    when(serviceBackend.getCollection())
        .thenReturn(
            serviceParameters1 ->
                new RepresentationCollection<>(Lists.newArrayList(serviceRepresentation)));

    try {
      response = serviceResource.getServices(uriInfo, serviceParameters);
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
              RepresentationCollection<ServiceRepresentation> representationCollection =
                  (RepresentationCollection<ServiceRepresentation>) r.getEntity();
              return representationCollection.getItems().toArray()[0];
            })
        .isEqualTo(serviceRepresentation);
  }

  @Test
  public void shouldCreateService() {
    when(serviceBackend.post())
        .thenReturn((serviceDetailsRepresentation1, serviceParameters1) -> true);

    try {
      response =
          serviceResource.createService(uriInfo, serviceDetailsRepresentation, serviceParameters);
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
  public void shouldGetService() {
    when(serviceBackend.get()).thenReturn(serviceParameters1 -> serviceDetailsRepresentation);

    try {
      response = serviceResource.getService(uriInfo, serviceParameters);
    } catch (Exception e) {
      fail(e);
    }

    assertThat(response).isNotNull();
    assertThat(response)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.OK.getStatusCode());
    assertThat(response).extracting(Response::hasEntity).isEqualTo(true);
    assertThat(response).extracting(Response::getEntity).isEqualTo(serviceDetailsRepresentation);
  }

  @Test
  public void shouldUpdateService() {
    when(serviceBackend.put())
        .thenReturn((serviceDetailsRepresentation1, serviceParameters1) -> true);

    try {
      response =
          serviceResource.updateService(uriInfo, serviceDetailsRepresentation, serviceParameters);
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
  public void shouldDeleteService() {
    when(serviceBackend.delete()).thenReturn(serviceParameters1 -> true);

    try {
      response = serviceResource.deleteService(uriInfo, serviceParameters);
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
  public void setServiceState() {
    when(serviceBackend.putStatus())
        .thenReturn((statusRepresentation1, serviceParameters1) -> true);

    try {
      response = serviceResource.setServiceState(uriInfo, statusRepresentation, serviceParameters);
    } catch (Exception e) {
      fail(e);
    }

    assertThat(response).isNotNull();
    assertThat(response)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.NO_CONTENT.getStatusCode());
    assertThat(response).extracting(Response::hasEntity).isEqualTo(false);
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
