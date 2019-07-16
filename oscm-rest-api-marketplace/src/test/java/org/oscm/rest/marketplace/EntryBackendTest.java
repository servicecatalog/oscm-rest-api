/**
 * *****************************************************************************
 *
 * <p>Copyright FUJITSU LIMITED 2019
 *
 * <p>Creation Date: July 3, 2019
 *
 * <p>*****************************************************************************
 */
package org.oscm.rest.marketplace;

import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.oscm.internal.intf.MarketplaceService;
import org.oscm.internal.vo.VOServiceDetails;
import org.oscm.rest.common.SampleTestDataUtility;
import org.oscm.rest.common.requestparameters.MarketplaceParameters;
import org.oscm.rest.common.representation.EntryRepresentation;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EntryBackendTest {

  @Mock private MarketplaceService service;
  @InjectMocks private EntryBackend backend;
  private EntryResource resource;

  private UriInfo uriInfo;
  private MarketplaceParameters parameters;
  private EntryRepresentation representation;
  private VOServiceDetails vo;

  @BeforeEach
  public void setUp() {
    resource = new EntryResource();
    resource.setEb(backend);
    uriInfo = SampleTestDataUtility.createUriInfo();
    parameters = SampleTestDataUtility.createMarketplaceParameters();
    representation = SampleTestDataUtility.createEntryRepresentation();
    vo = SampleTestDataUtility.createVOServiceDetails();
  }

  @Test
  @SneakyThrows
  public void shouldUpdateCatalogEntry() {
    when(service.getMarketplaceIdForKey(any())).thenReturn(representation.getId().toString());
    when(service.publishService(any(), any())).thenReturn(vo);

    Response response = resource.updateCatalogEntry(uriInfo, representation, parameters);

    assertThat(response).isNotNull();
    assertThat(response)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.NO_CONTENT.getStatusCode());
  }
}
