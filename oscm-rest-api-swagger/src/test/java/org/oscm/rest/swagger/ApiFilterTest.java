/**
 * *****************************************************************************
 *
 * <p>Copyright FUJITSU LIMITED 2019
 *
 * <p>Creation Date: Oct 3, 2019
 *
 * <p>*****************************************************************************
 */
package org.oscm.rest.swagger;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;

class ApiFilterTest {

  @Test
  public void testDoFilter() throws IOException, ServletException {
    // given
    HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
    HttpServletResponse httpServletResponse = mock(HttpServletResponse.class);
    FilterChain filterChain = mock(FilterChain.class);

    ApiFilter apiFilter = new ApiFilter();

    // when
    apiFilter.doFilter(httpServletRequest, httpServletResponse, filterChain);

    // then
    verify(httpServletResponse, times(3)).addHeader(anyString(), anyString());
  }
}
