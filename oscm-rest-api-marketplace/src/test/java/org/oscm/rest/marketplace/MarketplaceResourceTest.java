/**
 * *****************************************************************************
 *
 * <p>Copyright FUJITSU LIMITED 2019
 *
 * <p>Creation Date: 24-09-2019
 *
 * <p>*****************************************************************************
 */
package org.oscm.rest.marketplace;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

import com.google.common.collect.Lists;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.oscm.rest.common.SampleTestDataUtility;
import org.oscm.rest.common.representation.MarketplaceRepresentation;
import org.oscm.rest.common.representation.RepresentationCollection;
import org.oscm.rest.common.requestparameters.MarketplaceParameters;

@ExtendWith(MockitoExtension.class)
class MarketplaceResourceTest {

  @Mock private MarketplaceBackend marketplaceBackend;

  @InjectMocks @Spy private MarketplaceResource marketplaceResource;

  private Response response;
  private MarketplaceRepresentation marketplaceRepresentation;
  private UriInfo uriInfo;
  private MarketplaceParameters marketplaceParameters;

  @BeforeEach
  public void setUp() {
    marketplaceRepresentation = SampleTestDataUtility.createMarketplaceRepresentation();
    uriInfo = SampleTestDataUtility.createUriInfo();
    marketplaceParameters = SampleTestDataUtility.createMarketplaceParameters();
  }

  @AfterEach
  public void cleanUp() {
    response = null;
  }

  @Test
  public void shouldGetMarketplaces() {
    when(marketplaceBackend.getCollection())
        .thenReturn(
            params ->
                new RepresentationCollection<>(Lists.newArrayList(marketplaceRepresentation)));

    try {
      response =
          marketplaceResource.getMarketplaces(
              uriInfo,
              marketplaceParameters.getEndpointVersion(),
              marketplaceParameters.getListType());
    } catch (Exception e) {
      fail(e);
    }

    assertThat(response).isNotNull();
    assertThat(response)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.OK.getStatusCode());
    assertThat(response).extracting(Response::hasEntity).isEqualTo(true);
    assertThat(response)
        .extracting(
            r -> {
              RepresentationCollection<MarketplaceRepresentation> representationCollection =
                  (RepresentationCollection<MarketplaceRepresentation>) r.getEntity();
              return representationCollection.getItems().toArray()[0];
            })
        .isEqualTo(marketplaceRepresentation);
  }

  @Test
  public void shouldCreateMarketplace() {
    when(marketplaceBackend.post()).thenReturn((content, params) -> true);

    try {
      response =
          marketplaceResource.createMarketplace(
              uriInfo, marketplaceRepresentation, marketplaceParameters.getEndpointVersion());
    } catch (Exception e) {
      fail(e);
    }

    assertThat(response).isNotNull();
    assertThat(response)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.CREATED.getStatusCode());
    assertThat(response).extracting(Response::hasEntity).isEqualTo(true);
  }

  @Test
  @SneakyThrows
  public void shouldGetMarketplace() {
    when(marketplaceBackend.get()).thenReturn(params -> marketplaceRepresentation);

    try {
      response =
          marketplaceResource.getMarketplace(
              uriInfo,
              marketplaceParameters.getEndpointVersion(),
              marketplaceParameters.getId().toString());
    } catch (Exception e) {
      fail(e);
    }

    assertThat(response).isNotNull();
    assertThat(response)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.OK.getStatusCode());
    assertThat(response).extracting(Response::hasEntity).isEqualTo(true);
    assertThat(response).extracting(Response::getEntity).isEqualTo(marketplaceRepresentation);
  }

  @Test
  @SneakyThrows
  public void shouldUpdateMarketplace() {
    when(marketplaceBackend.put()).thenReturn((content, params) -> true);

    try {
      response =
          marketplaceResource.updateMarketplace(
              uriInfo,
              marketplaceRepresentation,
              marketplaceParameters.getEndpointVersion(),
              marketplaceParameters.getId().toString());
    } catch (Exception e) {
      fail(e);
    }

    assertThat(response).isNotNull();
    assertThat(response)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.NO_CONTENT.getStatusCode());
    assertThat(response).extracting(Response::hasEntity).isEqualTo(false);
  }

  @Test
  @SneakyThrows
  public void shouldDeleteMarketplace() {
    when(marketplaceBackend.delete()).thenReturn(params -> true);

    try {
      response =
          marketplaceResource.deleteMarketplace(
              uriInfo,
              marketplaceParameters.getEndpointVersion(),
              marketplaceParameters.getId().toString());
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
