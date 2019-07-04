/**
 * *****************************************************************************
 *
 * <p>Copyright FUJITSU LIMITED 2019
 *
 * <p>Creation Date: June 28, 2019
 *
 * <p>*****************************************************************************
 */
package org.oscm.rest.common;

import javax.ws.rs.core.*;
import java.net.URI;
import java.util.Collections;
import java.util.List;

/** Utility class to create some sample data for testing purposes */
public class SampleTestDataUtility {

  /**
   * Creates sample URIInfo object
   *
   * @return sample URIInfo
   */
  public static UriInfo createUriInfo() {
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
}
