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
import org.oscm.rest.account.data.BillingContactRepresentation;
import org.oscm.rest.common.RepresentationCollection;

import javax.ws.rs.core.*;
import java.net.URI;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BillingContactResourceTest {

  @Mock private AccountBackend accountBackend;
  @InjectMocks @Spy private BillingContactResource resource;

  private BillingContactRepresentation representation;
  private UriInfo uriInfo;
  private AccountParameters parameters;

  private Response result;

  @BeforeEach
  public void setUp() {
    representation = createBillingContactRepresentation();
    uriInfo = createUriInfo();
    parameters = createParameters();
  }

  @AfterEach
  public void cleanUp() {
    result = null;
  }

  @Test
  public void shouldGetBillingContacts() {
    when(accountBackend.getBillingContactCollection())
        .thenReturn(
            accountParameters ->
                new RepresentationCollection<>(Lists.newArrayList(representation)));

    try {
      result = resource.getBillingContacts(uriInfo, parameters);
    } catch (Exception e) {
      fail(e);
    }

    assertThat(result).isNotNull();
    assertThat(result)
        .extracting(
            r -> {
              RepresentationCollection<BillingContactRepresentation> collection =
                  (RepresentationCollection<BillingContactRepresentation>) r.getEntity();
              return collection.getItems().toArray()[0];
            })
        .isEqualTo(representation);
    assertThat(result)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.OK.getStatusCode());
  }

  @Test
  public void shouldCreateBillingContact() {
    when(accountBackend.postBillingContact())
        .thenReturn(((billingContactRepresentation, accountParameters) -> "newId"));

    try {
      result = resource.createBillingContact(uriInfo, representation, parameters);
    } catch (Exception e) {
      fail(e);
    }

    assertThat(result).isNotNull();
    assertThat(result)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.CREATED.getStatusCode());
  }

  @Test
  public void shouldGetSingleBillingContact() {
    when(accountBackend.getBillingContact()).thenReturn((accountParameters -> representation));

    try {
      result = resource.getBillingContact(uriInfo, parameters);
    } catch (Exception e) {
      fail(e);
    }

    assertThat(result).isNotNull();
    assertThat(result).extracting(Response::getEntity).isEqualTo(representation);
    assertThat(result)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.OK.getStatusCode());
  }

  @Test
  public void shouldUpdateBillingContact() {
    when(accountBackend.putBillingContact())
        .thenReturn(((billingContactRepresentation, accountParameters) -> true));

    try {
      result = resource.updateBillingContact(uriInfo, representation, parameters);
    } catch (Exception e) {
      fail(e);
    }

    assertThat(result).isNotNull();
    assertThat(result)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.NO_CONTENT.getStatusCode());
  }

  @Test
  public void shouldDeleteBillingContact() {
    when(accountBackend.deleteBillingContact()).thenReturn((accountParameters -> true));

    try {
      result = resource.deleteBillingContact(uriInfo, parameters);
    } catch (Exception e) {
      fail(e);
    }

    assertThat(result).isNotNull();
    assertThat(result)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.NO_CONTENT.getStatusCode());
  }

  private BillingContactRepresentation createBillingContactRepresentation() {
    BillingContactRepresentation representation = new BillingContactRepresentation();
    representation.setId(123L);
    return representation;
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

  private AccountParameters createParameters() {
    AccountParameters parameters = new AccountParameters();
    parameters.setOrgId("orgId");
    return parameters;
  }
}
