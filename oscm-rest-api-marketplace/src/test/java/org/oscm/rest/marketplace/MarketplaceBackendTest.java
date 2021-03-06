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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.google.common.collect.Lists;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
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
import org.oscm.rest.common.MarketplaceListType;
import org.oscm.rest.common.SampleTestDataUtility;
import org.oscm.rest.common.representation.MarketplaceRepresentation;
import org.oscm.rest.common.representation.RepresentationCollection;
import org.oscm.rest.common.requestparameters.MarketplaceParameters;

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
    representation = SampleTestDataUtility.createMarketplaceRepresentation();
    parameters = SampleTestDataUtility.createMarketplaceParameters();
    vo = SampleTestDataUtility.createVOMarketplace();
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

    Response response =
        resource.getMarketplaces(
            uriInfo, parameters.getEndpointVersion(), parameters.getListType());

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

    Response response =
        resource.getMarketplace(
            uriInfo, parameters.getEndpointVersion(), parameters.getId().toString());

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

    Response response =
        resource.createMarketplace(uriInfo, representation, parameters.getEndpointVersion());

    assertThat(response).isNotNull();
    assertThat(response)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.CREATED.getStatusCode());
  }

  @Test
  @SneakyThrows
  public void shouldUpdateMarketplace() {
    when(service.updateMarketplace(any())).thenReturn(vo);

    Response response =
        resource.updateMarketplace(
            uriInfo,
            representation,
            parameters.getEndpointVersion(),
            parameters.getId().toString());

    assertThat(response).isNotNull();
    assertThat(response)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.NO_CONTENT.getStatusCode());
  }

  @Test
  @SneakyThrows
  public void shouldDeleteMarketplace() {
    doNothing().when(service).deleteMarketplace(any());

    Response response =
        resource.deleteMarketplace(
            uriInfo, parameters.getEndpointVersion(), parameters.getId().toString());

    assertThat(response).isNotNull();
    assertThat(response)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.NO_CONTENT.getStatusCode());
  }
}
