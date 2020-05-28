/**
 * *****************************************************************************
 *
 * <p>Copyright FUJITSU LIMITED 2020
 *
 * <p>Creation Date: 06-04-2020
 *
 * <p>*****************************************************************************
 */
package org.oscm.rest.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.List;
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
import org.oscm.internal.intf.SearchService;
import org.oscm.internal.intf.SearchServiceInternal;
import org.oscm.internal.intf.ServiceProvisioningService;
import org.oscm.internal.types.enumtypes.PerformanceHint;
import org.oscm.internal.types.enumtypes.Sorting;
import org.oscm.internal.types.exception.InvalidPhraseException;
import org.oscm.internal.types.exception.ObjectNotFoundException;
import org.oscm.internal.vo.*;
import org.oscm.rest.common.SampleTestDataUtility;
import org.oscm.rest.common.ServiceStatus;
import org.oscm.rest.common.representation.*;
import org.oscm.rest.common.requestparameters.ServiceParameters;

@ExtendWith(MockitoExtension.class)
public class ServiceBackendTest {

  @Mock private ServiceProvisioningService service;
  @Mock private SearchService searchService;
  @Mock private SearchServiceInternal searchServiceInternal;
  @Mock private MarketplaceService ms;
  @InjectMocks private ServiceBackend backend;
  private ServiceResource resource;
  private CompatibleServiceResource compatiblesResource;
  private UriInfo uriInfo;
  private ServiceParameters parameters;
  private ServiceDetailsRepresentation representation;
  private ServiceCreateRepresentation serviceCreateRepresentation;
  private ServiceUpdateRepresentation serviceUpdateRepresentation;
  private StatusRepresentation statusRepresentation;
  private VOServiceDetails vo;
  private RepresentationCollection<ServiceRepresentation> compatiblesCollection;

  @BeforeEach
  public void setUp() {
    resource = new ServiceResource();
    compatiblesResource = new CompatibleServiceResource();
    uriInfo = SampleTestDataUtility.createUriInfo();
    parameters = SampleTestDataUtility.createServiceParameters();
    representation = SampleTestDataUtility.createServiceDetailsRepresentation(null);
    serviceCreateRepresentation = SampleTestDataUtility.createServiceCreateRepresentation();
    serviceUpdateRepresentation = new ServiceUpdateRepresentation();
    statusRepresentation = SampleTestDataUtility.createStatusRepresentation();
    compatiblesCollection = createCompatiblesCollection();
    vo = SampleTestDataUtility.createVOServiceDetails();
    resource.setSb(backend);
    compatiblesResource.setSb(backend);
  }

  @Test
  @SneakyThrows
  public void shouldGetService() {
    when(service.getServiceDetails(any())).thenReturn(vo);

    Response response =
        resource.getService(
            uriInfo, parameters.getEndpointVersion(), parameters.getId().toString());

    assertThat(response).isNotNull();
    assertThat(response)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.OK.getStatusCode());
  }

  @Test
  @SneakyThrows
  public void getCollection_getServices() {
    when(service.getSuppliedServices()).thenReturn(Lists.newArrayList(vo));

    Response response =
        resource.getServices(
            uriInfo,
            parameters.getEndpointVersion(),
            null,
            null,
            null,
            null,
            null,
            null,
            Sorting.RATING_ASCENDING);

    assertThat(response).isNotNull();
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
  public void getCollection_getServicesForPagingWithGivenMarketplace() {
    // given
    mockPagingService();

    // when
    Response response =
        resource.getServices(
            uriInfo,
            parameters.getEndpointVersion(),
            null,
            "123456789",
            null,
            "5",
            "0",
            null,
            Sorting.RATING_ASCENDING);

    // then
    assertThat(response).isNotNull();
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
  public void getCollection_getServicesForPagingWithoutMarketplace() {
    // given
    mockPagingService();
    mockMarketPlace();

    // when
    Response response =
        resource.getServices(
            uriInfo,
            parameters.getEndpointVersion(),
            null,
            null,
            null,
            "5",
            "0",
            null,
            Sorting.RATING_ASCENDING);

    // then
    assertThat(response).isNotNull();
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

  private void mockMarketPlace() {
    List<VOMarketplace> mpl = new ArrayList<VOMarketplace>();
    VOMarketplace mp = SampleTestDataUtility.createVOMarketplace();
    mp.setMarketplaceId("123456789");
    mpl.add(mp);

    when(ms.getMarketplacesOwned()).thenReturn(mpl);
  }

  private void mockPagingService() throws ObjectNotFoundException, InvalidPhraseException {
    List<VOService> slr = new ArrayList<VOService>();
    VOServiceListResult VOslr = new VOServiceListResult();
    VOService s = new VOService();

    s.setServiceId("testId");
    slr.add(s);
    VOslr.setServices(slr);

    when(searchServiceInternal.getServicesByCriteria(any(), any(), any(), any())).thenReturn(VOslr);
  }

  @Test
  @SneakyThrows
  public void getCollection_getServicesForSearchPhrase() {

    // given
    mockSearchService();

    // when
    Response response =
        resource.getServices(
            uriInfo,
            String.valueOf(parameters.getVersion()),
            "testService",
            "en",
            "123456789",
            null,
            null,
            null,
            Sorting.RATING_ASCENDING);

    // then
    assertThat(response).isNotNull();
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
  public void post_createService() {
    VOTechnicalService technicalService = new VOTechnicalService();
    technicalService.setTechnicalServiceId("technicalServiceId");
    ArrayList<VOTechnicalService> list = new ArrayList<>();
    list.add(technicalService);
    when(service.getTechnicalServices(any())).thenReturn(list);
    when(service.createService(any(), any(), any())).thenReturn(vo);

    Response response =
        resource.createService(
            uriInfo, serviceCreateRepresentation, parameters.getEndpointVersion());

    assertThat(response).isNotNull();
    assertThat(response)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.CREATED.getStatusCode());
  }

  @Test
  @SneakyThrows
  public void put_updateService() {
    when(service.updateService(any(), any())).thenReturn(vo);

    Response response =
        resource.updateService(
            uriInfo,
            serviceUpdateRepresentation,
            parameters.getEndpointVersion(),
            parameters.getId().toString());

    assertThat(response).isNotNull();
    assertThat(response)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.NO_CONTENT.getStatusCode());
  }

  @Test
  @SneakyThrows
  public void delete_Service() {
    doNothing().when(service).deleteService(any(Long.class));

    Response response =
        resource.deleteService(
            uriInfo, parameters.getEndpointVersion(), parameters.getId().toString());

    assertThat(response).isNotNull();
    assertThat(response)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.NO_CONTENT.getStatusCode());
  }

  @ParameterizedTest
  @EnumSource(ServiceStatus.class)
  @SneakyThrows
  public void putStatus_setServiceStatus(ServiceStatus serviceStatus) {
    lenient().when(service.activateService(any())).thenReturn(vo);
    lenient().when(service.deactivateService(any())).thenReturn(vo);
    lenient().when(service.resumeService(any())).thenReturn(vo);
    lenient().when(service.suspendService(any(), any())).thenReturn(vo);
    statusRepresentation.setStatus(serviceStatus);

    Response response =
        resource.setServiceState(
            uriInfo,
            statusRepresentation,
            parameters.getEndpointVersion(),
            parameters.getId().toString());

    assertThat(response).isNotNull();
    assertThat(response)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.NO_CONTENT.getStatusCode());
  }

  @Test
  @SneakyThrows
  public void getCompatibles_forStatus() {
    when(service.getCompatibleServices(any())).thenReturn(Lists.newArrayList(vo));

    Response response =
        compatiblesResource.getCompatibleServices(
            uriInfo, parameters.getEndpointVersion(), parameters.getId().toString());

    assertThat(response).isNotNull();
    assertThat(response)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.OK.getStatusCode());
  }

  @Test
  @SneakyThrows
  public void putCompatibles_updateCompatibleServices() {
    doNothing().when(service).setCompatibleServices(any(), any());

    Response response =
        compatiblesResource.setCompatibleServices(
            uriInfo,
            compatiblesCollection,
            parameters.getEndpointVersion(),
            parameters.getId().toString());

    assertThat(response).isNotNull();
    assertThat(response)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.NO_CONTENT.getStatusCode());
  }

  @Test
  public void getLocale_default() {
    // given
    String expected = "en";

    // when
    String result = backend.getLocale("");

    // then
    assertThat(expected).isEqualTo(result);
  }

  @Test
  public void getLocale_given() {
    // given
    String expected = "de";

    // when
    String result = backend.getLocale("de");

    // then
    assertThat(expected).isEqualTo(result);
  }

  @Test
  public void getServices() throws ObjectNotFoundException, InvalidPhraseException {
    // given
    mockSearchService();

    // when
    List<VOService> slr = backend.getServices("12345678", "en", "test");

    // then
    assertThat(slr.get(0).getServiceId()).isEqualTo("testId");
  }

  @Test
  public void getPerformanceHint_given() {
    // given
    PerformanceHint expected = PerformanceHint.ONLY_IDENTIFYING_FIELDS;

    // when
    PerformanceHint result = backend.getPerformanceHint(expected);

    // then
    assertThat(expected).isEqualTo(result);
  }

  @Test
  public void getPerformanceHint_default() {
    // given
    PerformanceHint expected = PerformanceHint.ALL_FIELDS;

    // when
    PerformanceHint result = backend.getPerformanceHint(null);

    // then
    assertThat(expected).isEqualTo(result);
  }

  private void mockSearchService() throws ObjectNotFoundException, InvalidPhraseException {
    List<VOService> slr = new ArrayList<VOService>();
    VOServiceListResult VOslr = new VOServiceListResult();
    VOService s = new VOService();

    s.setServiceId("testId");
    slr.add(s);
    VOslr.setServices(slr);

    when(searchService.searchServices(any(), any(), any())).thenReturn(VOslr);
  }

  private RepresentationCollection<ServiceRepresentation> createCompatiblesCollection() {
    ServiceRepresentation serviceRepresentation =
        SampleTestDataUtility.createServiceRepresentation();

    RepresentationCollection<ServiceRepresentation> collection =
        new RepresentationCollection<>(Lists.newArrayList(serviceRepresentation));
    collection.setETag(1234L);
    return collection;
  }
}
