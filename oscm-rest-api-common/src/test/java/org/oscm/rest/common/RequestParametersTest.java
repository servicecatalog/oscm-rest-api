/**
 * *****************************************************************************
 *
 * <p>Copyright FUJITSU LIMITED 2017
 *
 * <p>Creation Date: Aug 12, 2016
 *
 * <p>*****************************************************************************
 */
package org.oscm.rest.common;

import org.junit.jupiter.api.Test;
import org.oscm.rest.common.requestparameters.RequestParameters;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response.Status;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Unit test for RequestParameters
 *
 * @author miethaner
 */
public class RequestParametersTest {

  private class TestParameters extends RequestParameters {

    @Override
    public void validateParameters() throws WebApplicationException {}

    @Override
    public void update() {}
  }

  @Test
  public void testIdValidation() throws Exception {

    RequestParameters params = new TestParameters();

    params.setId(1L);

    try {
      params.validateId();
    } catch (WebApplicationException e) {
      fail();
    }

    params.setId(null);

    try {
      params.validateId();
      fail();
    } catch (WebApplicationException e) {
      assertEquals(Status.NOT_FOUND.getStatusCode(), e.getResponse().getStatus());
    }
  }

  @Test
  public void testEtagValidation() throws Exception {

    RequestParameters params = new TestParameters();

    params.setMatch("*");
    params.setNoneMatch("*");

    try {
      params.validateETag();
      assertEquals(null, params.getETag());
    } catch (WebApplicationException e) {
      fail();
    }

    params = new TestParameters();
    params.setMatch("1");

    try {
      params.validateETag();
      assertEquals(new Long(1L), params.getETag());
    } catch (WebApplicationException e) {
      fail();
    }

    params = new TestParameters();
    params.setNoneMatch("1");

    try {
      params.validateETag();
      assertEquals(new Long(1L), params.getETag());
    } catch (WebApplicationException e) {
      fail();
    }

    params = new TestParameters();
    params.setMatch("abc");

    try {
      params.validateETag();
      fail();
    } catch (WebApplicationException e) {
      assertEquals(Status.BAD_REQUEST.getStatusCode(), e.getResponse().getStatus());
    }

    params = new TestParameters();
    params.setNoneMatch("abc");

    try {
      params.validateETag();
      fail();
    } catch (WebApplicationException e) {
      assertEquals(Status.BAD_REQUEST.getStatusCode(), e.getResponse().getStatus());
    }
  }

  @Test
  public void shouldValidateParameters() {
    RequestParameters parameters = new RequestParameters();

    parameters.validateParameters();
  }

  @Test
  public void shouldConvertIdToKey_givenNullId() {
    RequestParameters parameters = new TestParameters();

    Long result = parameters.convertIdToKey();

    assertThat(result).isEqualTo(0L);
  }

  @Test
  public void shouldConvertIdToKey_givenNoNNullId() {
    RequestParameters parameters = new TestParameters();
    parameters.setId(12345L);

    Long result = parameters.convertIdToKey();

    assertThat(result).isEqualTo(12345L);
  }

  @Test
  public void shouldConvertEtagToVersion_givenNullEtag() {
    RequestParameters parameters = new TestParameters();

    int result = parameters.convertETagToVersion();

    assertThat(result).isEqualTo(0);
  }

  @Test
  public void shouldConvertEtagToVersion_givenNonNullEtag() {
    RequestParameters parameters = new TestParameters();
    parameters.setEtag(12345L);

    int result = parameters.convertETagToVersion();

    assertThat(result).isEqualTo(12345);
  }
}
