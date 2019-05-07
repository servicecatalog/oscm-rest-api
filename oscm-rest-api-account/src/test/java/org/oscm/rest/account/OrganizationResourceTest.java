/**
 * *****************************************************************************
 *
 * <p>Copyright FUJITSU LIMITED 2019
 *
 * <p>Creation Date: 10-04-2019
 *
 * <p>*****************************************************************************
 */
package org.oscm.rest.account;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.oscm.rest.account.data.AccountRepresentation;
import org.oscm.rest.account.data.OrganizationRepresentation;
import org.oscm.rest.account.data.UserRepresentation;

import javax.ws.rs.core.*;
import java.net.URI;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrganizationResourceTest {

  @Mock private AccountBackend backend;
  @InjectMocks @Spy private OrganizationResource resource;

  private AccountRepresentation accountRepresentation;
  private OrganizationRepresentation orgRepresentation;
  private UriInfo uriInfo;
  private AccountParameters parameters;
  private Response result;

  @BeforeEach
  public void setUp() {
    accountRepresentation = createAccountRepresentation();
    orgRepresentation = createOrgRepresentation();
    uriInfo = createUriInfo();
    parameters = createParameters();
  }

  @AfterEach
  public void cleanUp() {
    result = null;
  }

  @Test
  public void shouldCreateOrganization() {
    when(backend.postOrganization())
        .thenReturn(((accountRepresentation, accountParameters) -> "newId"));

    try {
      result = resource.createOrganization(uriInfo, accountRepresentation, parameters);
    } catch (Exception e) {
      fail(e);
    }

    assertThat(result).isNotNull();
    assertThat(result)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.CREATED.getStatusCode());
  }

  @Test
  public void shouldGetOrganization() {
    when(backend.getOrganization()).thenReturn((accountParameters -> orgRepresentation));

    try {
      result = resource.getOrganization(uriInfo, parameters);
    } catch (Exception e) {
      fail(e);
    }

    assertThat(result).isNotNull();
    assertThat(result)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.OK.getStatusCode());
    assertThat(result).extracting(Response::getEntity).isEqualTo(orgRepresentation);
  }

  private AccountRepresentation createAccountRepresentation() {
    AccountRepresentation representation = new AccountRepresentation();
    representation.setOrganization(createOrgRepresentation());
    representation.setUser(new UserRepresentation());
    return representation;
  }

  private OrganizationRepresentation createOrgRepresentation() {
    return new OrganizationRepresentation();
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
