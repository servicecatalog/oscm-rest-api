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

import com.google.common.collect.Lists;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.oscm.internal.intf.MarketplaceService;
import org.oscm.internal.vo.VOMarketplace;
import org.oscm.rest.common.representation.RepresentationCollection;
import org.oscm.rest.common.SampleTestDataUtility;
import org.oscm.rest.marketplace.data.MarketplaceRepresentation;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MarketplaceBackendTest {

  @Mock private MarketplaceService service;
  @InjectMocks private MarketplaceBackend backend;
  private MarketplaceResource resource;

  private UriInfo uriInfo;
  private MarketplaceRepresentation representation;
  private MarketplaceParameters parameters;
  private VOMarketplace vo;

  @BeforeEach
  public void setUp() {
    resource = new MarketplaceResource();
    resource.setMb(backend);
    uriInfo = SampleTestDataUtility.createUriInfo();
    representation = createRepresentation();
    parameters = createParameters();
    vo = createVO();
  }

  @ParameterizedTest
  @EnumSource(MarketplaceListType.class)
  @SneakyThrows
  public void shouldGetMarketplacesOf(MarketplaceListType listType) {
    parameters.setListType(listType);

    lenient().when(service.getMarketplacesForOperator()).thenReturn(Lists.newArrayList(vo));
    lenient().when(service.getMarketplacesForOrganization()).thenReturn(Lists.newArrayList(vo));
    lenient().when(service.getAccessibleMarketplaces()).thenReturn(Lists.newArrayList(vo));
    lenient().when(service.getMarketplacesOwned()).thenReturn(Lists.newArrayList(vo));

    Response response = resource.getMarketplaces(uriInfo, parameters);

    assertThat(response).isNotNull();
    assertThat(response).extracting(Response::hasEntity).isEqualTo(true);
    assertThat(response)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.OK.getStatusCode());
    assertThat(response)
        .extracting(
            r -> {
              return ((RepresentationCollection) r.getEntity()).getItems().size();
            })
        .isEqualTo(1);
  }

  @Test
  @SneakyThrows
  public void shouldGetMarketplaceById() {
    when(service.getMarketplaceIdForKey(any())).thenReturn(representation.getMarketplaceId());
    when(service.getMarketplaceById(any())).thenReturn(vo);

    Response response = resource.getMarketplace(uriInfo, parameters);

    assertThat(response).isNotNull();
    assertThat(response)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.OK.getStatusCode());
    assertThat(response).extracting(Response::hasEntity).isEqualTo(true);
  }

  @Test
  @SneakyThrows
  public void shouldCreateMarketplace() {
    when(service.createMarketplace(any())).thenReturn(vo);

    Response response = resource.createMarketplace(uriInfo, representation, parameters);

    assertThat(response).isNotNull();
    assertThat(response)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.CREATED.getStatusCode());
  }

  @Test
  @SneakyThrows
  public void shouldUpdateMarketplace() {
    when(service.updateMarketplace(any())).thenReturn(vo);

    Response response = resource.updateMarketplace(uriInfo, representation, parameters);

    assertThat(response).isNotNull();
    assertThat(response)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.NO_CONTENT.getStatusCode());
  }

  @Test
  @SneakyThrows
  public void shouldDeleteMarketplace() {
    doNothing().when(service).deleteMarketplace(any());

    Response response = resource.deleteMarketplace(uriInfo, parameters);

    assertThat(response).isNotNull();
    assertThat(response)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.NO_CONTENT.getStatusCode());
  }

  private MarketplaceRepresentation createRepresentation() {
    MarketplaceRepresentation marketplaceRepresentation = new MarketplaceRepresentation();
    marketplaceRepresentation.setMarketplaceId("marketplaceId");
    return marketplaceRepresentation;
  }

  private MarketplaceParameters createParameters() {
    MarketplaceParameters parameters = new MarketplaceParameters();
    parameters.setId(123L);
    return parameters;
  }

  private VOMarketplace createVO() {
    return new VOMarketplace();
  }
}
