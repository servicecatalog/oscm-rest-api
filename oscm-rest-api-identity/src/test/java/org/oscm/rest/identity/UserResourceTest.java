package org.oscm.rest.identity;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.oscm.rest.common.RepresentationCollection;
import org.oscm.rest.identity.data.UserRepresentation;

import javax.ws.rs.core.*;
import java.net.URI;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserResourceTest {

  @Mock private UserBackend userBackend;
  @InjectMocks @Spy private UserResource userResource;

  private UserRepresentation userRepresentation;
  private UriInfo uriInfo;
  private UserParameters parameters;
  private Response result;

  @BeforeEach
  public void setUp() {
    userRepresentation = createUserRepresentation();
    uriInfo = createUriInfo();
    parameters = createParameters();
  }

  @AfterEach
  public void cleanUp() {
    result = null;
  }

  @Test
  public void shouldGetUsers() {
    when(userBackend.getUsers())
        .thenReturn(
            userParameters ->
                new RepresentationCollection<>(Lists.newArrayList(userRepresentation)));

    try {
      result = userResource.getUsers(uriInfo, parameters);
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
              RepresentationCollection<UserRepresentation> representationCollection =
                  (RepresentationCollection<UserRepresentation>) r.getEntity();
              return representationCollection.getItems().toArray()[0];
            })
        .isEqualTo(userRepresentation);
  }

  @Test
  public void shouldCreateUser() {
    when(userBackend.postUser()).thenReturn((userRepresentation1, userParameters) -> "newId");

    try {
      result = userResource.createUser(uriInfo, userRepresentation, parameters);
    } catch (Exception e) {
      fail(e);
    }

    assertThat(result).isNotNull();
    assertThat(result)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.CREATED.getStatusCode());
  }

  @Test
  public void shouldGetSingleUser() {
    when(userBackend.getUser()).thenReturn(userParameters -> userRepresentation);

    try {
      result = userResource.getUser(uriInfo, parameters);
    } catch (Exception e) {
      fail(e);
    }

    assertThat(result).isNotNull();
    assertThat(result)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.OK.getStatusCode());
    assertThat(result).extracting(Response::getEntity).isEqualTo(userRepresentation);
  }

  @Test
  public void shouldUpdateUser() {
    when(userBackend.putUser()).thenReturn((userRepresentation1, userParameters) -> true);

    try {
      result = userResource.updateUser(uriInfo, userRepresentation, parameters);
    } catch (Exception e) {
      fail(e);
    }

    assertThat(result).isNotNull();
    assertThat(result)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.NO_CONTENT.getStatusCode());
  }

  @Test
  public void shouldDeleteUser() {
    when(userBackend.deleteUser()).thenReturn(userParameters -> true);

    try {
      result = userResource.deleteUser(uriInfo, parameters);
    } catch (Exception e) {
      fail(e);
    }

    assertThat(result).isNotNull();
    assertThat(result)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.NO_CONTENT.getStatusCode());
  }

  private UserRepresentation createUserRepresentation() {
    return new UserRepresentation();
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

  private UserParameters createParameters() {
    UserParameters userParameters = new UserParameters();
    userParameters.setUserId("userId");
    return userParameters;
  }
}
