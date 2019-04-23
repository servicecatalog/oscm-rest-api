/**
 * *****************************************************************************
 *
 * <p>Copyright FUJITSU LIMITED 2019
 *
 * <p>Creation Date: 18-04-2019
 *
 * <p>*****************************************************************************
 */
package org.oscm.rest.service;

import static org.mockito.Mockito.when;

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
import org.oscm.rest.service.data.PriceModelRepresentation;
import org.oscm.rest.service.data.ServiceRepresentation;

<<<<<<< HEAD
import javax.ws.rs.core.*;
import java.net.URI;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PriceModelResourceTest {


    @Mock
    private PriceModelBackend priceModelBackend;

    @InjectMocks
    @Spy
    private PriceModelResource priceModelResource;

    private Response response;
    private ServiceRepresentation serviceRepresentation;
    private PriceModelRepresentation priceModelRepresentation;
    private UriInfo uriInfo;
    private ServiceParameters serviceParameters;

    @BeforeEach
    public void setUp() {
        serviceRepresentation = new ServiceRepresentation();
        priceModelRepresentation = new PriceModelRepresentation();
        uriInfo = createUriInfo();
        serviceParameters = createParameters();
    }

    @AfterEach
    public void cleanUp() {
        response = null;

    }

    @Test
    public void shouldGet() {
        when(priceModelBackend.get())
                .thenReturn(
                        serviceParameters1 -> priceModelRepresentation);

        try {
            response = priceModelResource.get(uriInfo, serviceParameters);
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
                .extracting(Response::getEntity)
                .isEqualTo(priceModelRepresentation);
    }

    @Test
    public void shouldUpdate() {
        when(priceModelBackend.put())
                .thenReturn((priceModelRepresentation1, serviceParameters1) -> true);

        try {
            response = priceModelResource.update(uriInfo, priceModelRepresentation, serviceParameters);
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
    public void shouldGetForCustomer() {
        when(priceModelBackend.getForCustomer())
                .thenReturn(serviceParameters1 -> priceModelRepresentation);

        try {
            response = priceModelResource.getForCustomer(uriInfo, serviceParameters);
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
                .extracting(Response::getEntity)
                .isEqualTo(priceModelRepresentation);
    }

    @Test
    public void shouldUpdateForCustomer() {
        when(priceModelBackend.putForCustomer())
                .thenReturn((priceModelRepresentation1, serviceParameters1) -> true);

        try {
            response = priceModelResource.updateForCustomer(uriInfo, priceModelRepresentation, serviceParameters);
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
=======
@ExtendWith(MockitoExtension.class)
class PriceModelResourceTest {

  @Mock private PriceModelBackend priceModelBackend;

  @InjectMocks @Spy private PriceModelResource priceModelResource;

  private Response response;
  private ServiceRepresentation serviceRepresentation;
  private PriceModelRepresentation priceModelRepresentation;
  private UriInfo uriInfo;
  private ServiceParameters serviceParameters;

  @BeforeEach
  public void setUp() {
    serviceRepresentation = new ServiceRepresentation();
    priceModelRepresentation = new PriceModelRepresentation();
    uriInfo = createUriInfo();
    serviceParameters = createParameters();
  }

  @AfterEach
  public void cleanUp() {
    response = null;
  }

  @Test
  public void shouldGet() {
    when(priceModelBackend.get()).thenReturn(serviceParameters1 -> priceModelRepresentation);
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
>>>>>>> 8499d363d4cc705f5b3169bfa26dfb677594d3a7
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
