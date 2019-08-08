/**
 * *****************************************************************************
 *
 * <p>Copyright FUJITSU LIMITED 2017
 *
 * <p>Creation Date: May 19, 2016
 *
 * <p>*****************************************************************************
 */
package org.oscm.rest.common;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.oscm.rest.common.representation.Representation;
import org.oscm.rest.common.requestparameters.RequestParameters;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

/**
 * Unit test for RestEndpoint
 *
 * @author miethaner
 */
public class RestResourceTest extends RestResource {

  private class MockRepresentation extends Representation {
    @Override
    public void validateContent() throws WebApplicationException {}
  }

  private class MockRequestParameters extends RequestParameters {

    @Override
    public void validateParameters() throws WebApplicationException {}

    @Override
    public void update() {}
  }

  private RestBackend.Get<MockRepresentation, MockRequestParameters> backendGet =
          params -> {

            assertNotNull(params);

            return new MockRepresentation();
          };

  private RestBackend.Post<MockRepresentation, MockRequestParameters> backendPost =
          (content, params) -> {

            assertNotNull(content);
            assertNotNull(params);

            return UUID.randomUUID();
          };

  private RestBackend.Put<MockRepresentation, MockRequestParameters> backendPut =
          (content, params) -> {

            assertNotNull(content);
            assertNull(content.getETag());
            assertNotNull(params);
            return true;
          };

  private RestBackend.Put<MockRepresentation, MockRequestParameters> backendPutETag =
          (content, params) -> {

            assertNotNull(content);
            assertNotNull(content.getETag());
            assertNotNull(params);
            return true;
          };

  private RestBackend.Delete<MockRequestParameters> backendDelete =
          params -> {

            assertNotNull(params);
            return true;
          };

  @Test
  public void testGet() throws Exception {

    MockRequestParameters params = new MockRequestParameters();
    params.setId(1L);

    UriInfo uriinfo = mock(UriInfo.class);
    MultivaluedMap<String, String> map = new MultivaluedHashMap<>();
    map.putSingle(
        CommonParams.PARAM_VERSION, "v" + Integer.valueOf(CommonParams.VERSION_1).toString());
    Mockito.when(uriinfo.getPathParameters()).thenReturn(map);

    Response response = get(uriinfo, backendGet, params, true);

    assertEquals(Status.OK.getStatusCode(), response.getStatus());
    assertNotNull(response.getEntity());
    assertThat(response.getEntity()).isInstanceOf(MockRepresentation.class);
  }

  @Test
  public void testPost() throws Exception {

    MockRepresentation content = new MockRepresentation();
    content.setId(1L);

    MockRequestParameters params = new MockRequestParameters();
    params.setId(1L);

    UriInfo uriinfo = mock(UriInfo.class);
    MultivaluedMap<String, String> map = new MultivaluedHashMap<>();
    map.putSingle(
        CommonParams.PARAM_VERSION, "v" + Integer.valueOf(CommonParams.VERSION_1).toString());
    Mockito.when(uriinfo.getPathParameters()).thenReturn(map);
    Response response = post(uriinfo, backendPost, content, params);

    assertEquals(Status.CREATED.getStatusCode(), response.getStatus());
  }

  @Test
  public void testPut() throws Exception {

    MockRepresentation content = new MockRepresentation();
    content.setId(1L);
    content.setETag(1L);

    MockRequestParameters params = new MockRequestParameters();
    params.setId(1L);

    UriInfo uriInfo = mock(UriInfo.class);
    MultivaluedMap<String, String> map = new MultivaluedHashMap<>();
    map.putSingle(
        CommonParams.PARAM_VERSION, "v" + Integer.valueOf(CommonParams.VERSION_1).toString());
    Mockito.when(uriInfo.getPathParameters()).thenReturn(map);

    Response response = put(uriInfo, backendPut, content, params);

    assertEquals(Status.NO_CONTENT.getStatusCode(), response.getStatus());
  }

  @Test
  public void testPutWithETag() throws Exception {

    MockRepresentation content = new MockRepresentation();
    content.setId(1L);
    content.setETag(1L);

    MockRequestParameters params = new MockRequestParameters();
    params.setId(1L);
    params.setMatch("1");

    UriInfo uriinfo = mock(UriInfo.class);
    MultivaluedMap<String, String> map = new MultivaluedHashMap<>();
    map.putSingle(
        CommonParams.PARAM_VERSION, "v" + Integer.valueOf(CommonParams.VERSION_1).toString());
    Mockito.when(uriinfo.getPathParameters()).thenReturn(map);

    Response response = put(uriinfo, backendPutETag, content, params);

    assertEquals(Status.NO_CONTENT.getStatusCode(), response.getStatus());
  }

  @Test
  public void testDelete() throws Exception {

    MockRequestParameters params = new MockRequestParameters();
    params.setId(1L);
    UriInfo uriinfo = mock(UriInfo.class);
    MultivaluedMap<String, String> map = new MultivaluedHashMap<>();
    map.putSingle(
        CommonParams.PARAM_VERSION, "v" + Integer.valueOf(CommonParams.VERSION_1).toString());
    Mockito.when(uriinfo.getPathParameters()).thenReturn(map);

    Response response = delete(uriinfo, backendDelete, params);

    assertEquals(Status.NO_CONTENT.getStatusCode(), response.getStatus());
  }

  @Test
  public void testVersionAndID() throws Exception {

    UriInfo uriInfoWith = mock(UriInfo.class);
    UriInfo uriInfoWithout = mock(UriInfo.class);
    MultivaluedMap<String, String> map = new MultivaluedHashMap<>();
    map.putSingle(
        CommonParams.PARAM_VERSION, "v" + Integer.valueOf(CommonParams.VERSION_1).toString());
    Mockito.when(uriInfoWith.getPathParameters()).thenReturn(map);
    Mockito.when(uriInfoWithout.getPathParameters()).thenReturn(new MultivaluedHashMap<>());

    MockRequestParameters params = new MockRequestParameters();

    try {
      get(uriInfoWithout, backendGet, params, false);
      fail();
    } catch (WebApplicationException e) {
      assertEquals(Status.NOT_FOUND.getStatusCode(), e.getResponse().getStatus());
    }

    try {
      post(uriInfoWithout, backendPost, null, params);
      fail();
    } catch (WebApplicationException e) {
      assertEquals(Status.NOT_FOUND.getStatusCode(), e.getResponse().getStatus());
    }

    try {
      put(uriInfoWithout, backendPut, null, params);
      fail();
    } catch (WebApplicationException e) {
      assertEquals(Status.NOT_FOUND.getStatusCode(), e.getResponse().getStatus());
    }

    try {
      delete(uriInfoWithout, backendDelete, params);
      fail();
    } catch (WebApplicationException e) {
      assertEquals(Status.NOT_FOUND.getStatusCode(), e.getResponse().getStatus());
    }

    try {
      get(uriInfoWith, backendGet, params, true);
      fail();
    } catch (WebApplicationException e) {
      assertEquals(Status.NOT_FOUND.getStatusCode(), e.getResponse().getStatus());
    }

    try {
      put(uriInfoWith, backendPut, null, params);
      fail();
    } catch (WebApplicationException e) {
      assertEquals(Status.NOT_FOUND.getStatusCode(), e.getResponse().getStatus());
    }

    try {
      delete(uriInfoWith, backendDelete, params);
      fail();
    } catch (WebApplicationException e) {
      assertEquals(Status.NOT_FOUND.getStatusCode(), e.getResponse().getStatus());
    }
  }
}
