package org.oscm.rest.marketplace;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.oscm.rest.common.SampleTestDataUtility;
import org.oscm.rest.common.representation.EntryRepresentation;
import org.oscm.rest.common.requestparameters.MarketplaceParameters;

@ExtendWith(MockitoExtension.class)
class EntryResourceTest {

  @Mock private EntryBackend entryBackend;

  @InjectMocks @Spy private EntryResource entryResource;

  private Response response;
  private MarketplaceParameters marketplaceParameters;
  private UriInfo uriInfo;
  private EntryRepresentation entryRepresentation;

  @BeforeEach
  public void setUp() {
    entryRepresentation = SampleTestDataUtility.createEntryRepresentation();
    uriInfo = SampleTestDataUtility.createUriInfo();
    marketplaceParameters = SampleTestDataUtility.createMarketplaceParameters();
  }

  @AfterEach
  public void cleanUp() {
    response = null;
  }

  @Test
  public void shouldUpdateCatalogEntry() {
    try {
      when(entryBackend.put()).thenReturn((content, params) -> true);
    } catch (Exception e) {
      fail(e);
    }

    try {
      response =
          entryResource.updateCatalogEntry(
              uriInfo,
              marketplaceParameters.getEndpointVersion(),
              "1",
              marketplaceParameters.getId().toString(),
              entryRepresentation);
    } catch (Exception e) {
      fail(e);
    }

    assertThat(response).isNotNull();
    assertThat(response)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.NO_CONTENT.getStatusCode());
    assertThat(response).extracting(Response::hasEntity).isEqualTo(false);
  }
}
