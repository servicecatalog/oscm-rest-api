package org.oscm.rest.common;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import static org.assertj.core.api.Assertions.assertThat;

public class ResponseFactoryTest {

  private Response response = null;

  @BeforeEach
  public void cleanUp() {
    response = null;
  }

  @Test
  public void shouldGetOKResponse() {
    Response resultResponse = ResponseFactory.getInstance(ResponseType.OK);

    assertThat(resultResponse).isNotNull();
    assertThat(resultResponse).extracting(r -> r.getStatus()).isEqualTo(200);
    checkNotUesdFields(resultResponse);
  }

  @Test
  public void shouldGetCreatedResponse() {
    Response resultResponse = ResponseFactory.getInstance(ResponseType.CREATED);

    assertThat(resultResponse).isNotNull();
    assertThat(resultResponse).extracting(r -> r.getStatus()).isEqualTo(201);
    checkNotUesdFields(resultResponse);
  }

  @Test
  public void shouldGetNotImplementedResponse() {
    Response resultResponse = ResponseFactory.getInstance(ResponseType.NOT_IMPLEMENTED);

    assertThat(resultResponse).isNotNull();
    assertThat(resultResponse).extracting(r -> r.getStatus()).isEqualTo(501);
    checkNotUesdFields(resultResponse);
  }

  @Test
  public void shouldGetForbiddenResponse() {
    Response resultResponse = ResponseFactory.getInstance(ResponseType.FORBIDDEN);

    assertThat(resultResponse).isNotNull();
    assertThat(resultResponse).extracting(r -> r.getStatus()).isEqualTo(403);
    checkNotUesdFields(resultResponse);
  }

  private void checkNotUesdFields(Response responseToCheck) {
    assertThat(responseToCheck).extracting(r -> r.getEntity()).isEqualTo(null);
    assertThat(responseToCheck).extracting(r -> r.readEntity(Class.class)).isEqualTo(null);
    assertThat(responseToCheck).extracting(r -> r.readEntity(GenericType.class)).isEqualTo(null);
    assertThat(responseToCheck).extracting(r -> r.readEntity(Class.class, null)).isEqualTo(null);
    assertThat(responseToCheck).extracting(r -> r.readEntity(GenericType.class, null)).isEqualTo(null);
    assertThat(responseToCheck).extracting(r -> r.hasEntity()).isEqualTo(false);
    assertThat(responseToCheck).extracting(r -> r.bufferEntity()).isEqualTo(false);
    assertThat(responseToCheck).extracting(r -> r.getMediaType()).isEqualTo(null);
    assertThat(responseToCheck).extracting(r -> r.getLanguage()).isEqualTo(null);
    assertThat(responseToCheck).extracting(r -> r.getLength()).isEqualTo(0);
    assertThat(responseToCheck).extracting(r -> r.getAllowedMethods()).isEqualTo(null);
    assertThat(responseToCheck).extracting(r -> r.getCookies()).isEqualTo(null);
    assertThat(responseToCheck).extracting(r -> r.getEntityTag()).isEqualTo(null);
    assertThat(responseToCheck).extracting(r -> r.getDate()).isEqualTo(null);
    assertThat(responseToCheck).extracting(r -> r.getLastModified()).isEqualTo(null);
    assertThat(responseToCheck).extracting(r -> r.getLocation()).isEqualTo(null);
    assertThat(responseToCheck).extracting(r -> r.getLinks()).isEqualTo(null);
    assertThat(responseToCheck).extracting(r -> r.hasLink(null)).isEqualTo(false);
    assertThat(responseToCheck).extracting(r -> r.getLink(null)).isEqualTo(null);
    assertThat(responseToCheck).extracting(r -> r.getLinkBuilder(null)).isEqualTo(null);
    assertThat(responseToCheck).extracting(r -> r.getMetadata()).isEqualTo(null);
    assertThat(responseToCheck).extracting(r -> r.getStringHeaders()).isEqualTo(null);
    assertThat(responseToCheck).extracting(r -> r.getHeaderString(null)).isEqualTo(null);
  }
}
