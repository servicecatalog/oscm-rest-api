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
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.oscm.internal.intf.ServiceProvisioningService;
import org.oscm.internal.vo.VOTechnicalService;
import org.oscm.rest.common.SampleTestDataUtility;
import org.oscm.rest.common.representation.RepresentationCollection;
import org.oscm.rest.common.representation.TechnicalServiceImportRepresentation;
import org.oscm.rest.common.representation.TechnicalServiceRepresentation;
import org.oscm.rest.common.requestparameters.ServiceParameters;

import com.google.common.collect.Lists;

import lombok.SneakyThrows;

@SuppressWarnings({"boxing"})
@ExtendWith(MockitoExtension.class)
public class TechnicalServiceBackendTest {

  @Mock private ServiceProvisioningService service;
  @Mock HttpHeaders headers;
  @InjectMocks private TechnicalServiceBackend backend;
  private TechnicalServiceResource resource;
  private UriInfo uriInfo;
  private ServiceParameters parameters;
  private TechnicalServiceRepresentation representation;
  private TechnicalServiceImportRepresentation importRepresentation;

  private String TECHNICAL_SERVICE_XML_EXAMPLE_RESPONSE =
      "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
          + "  <tns:TechnicalServices xmlns:tns=\"oscm.serviceprovisioning/1.9/TechnicalService.xsd\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"oscm.serviceprovisioning/1.9/TechnicalService.xsd ../../oscm-serviceprovisioning/javares/TechnicalServices.xsd\">\n"
          + "    <tns:TechnicalService accessType=\"DIRECT\" allowingOnBehalfActing=\"false\" baseUrl=\"\" billingIdentifier=\"NATIVE_BILLING\" build=\"2019.12.13\" id=\"AppSampleService\" loginPath=\"/Dynamically provided.\" onlyOneSubscriptionPerUser=\"false\" provisioningPassword=\"\" provisioningType=\"ASYNCHRONOUS\" provisioningUrl=\"http://oscm-app:8880/oscm-app/webservices/oscm-app/oscm-app/org.oscm.app.v2_0.service.AsynchronousProvisioningProxy?wsdl\" provisioningUsername=\"\" provisioningVersion=\"1.0\">\n"
          + "      <AccessInfo locale=\"en\">Description of how to access the application.</AccessInfo>\n"
          + "      <LocalizedDescription locale=\"en\">Sample APP implementation.</LocalizedDescription>\n"
          + "      <LocalizedDescription locale=\"de\">Sample APP Implementierung.</LocalizedDescription>\n"
          + "      <LocalizedLicense locale=\"en\"/>\n"
          + "      <ParameterDefinition configurable=\"true\" id=\"PARAM_PWD\" mandatory=\"true\" valueType=\"PWD\">\n"
          + "        <LocalizedDescription locale=\"en\">IAAS password</LocalizedDescription>\n"
          + "        <LocalizedDescription locale=\"de\"/>\n"
          + "        <LocalizedDescription locale=\"ja\"/>\n"
          + "      </ParameterDefinition>\n"
          + "      <ParameterDefinition configurable=\"true\" id=\"CSSSTYLE\" mandatory=\"false\" valueType=\"STRING\">\n"
          + "        <LocalizedDescription locale=\"en\">Inline CSS for Email Notification</LocalizedDescription>\n"
          + "        <LocalizedDescription locale=\"de\"/>\n"
          + "        <LocalizedDescription locale=\"ja\"/>\n"
          + "      </ParameterDefinition>\n"
          + "    </tns:TechnicalService>\n"
          + "  </tns:TechnicalServices>";

  @BeforeEach
  public void setUp() {
    resource = new TechnicalServiceResource();
    uriInfo = SampleTestDataUtility.createUriInfo();
    parameters = SampleTestDataUtility.createServiceParameters();
    representation = SampleTestDataUtility.createTSRepresentation();
    importRepresentation = SampleTestDataUtility.createTSImportRepresentation();
    resource.setTsb(backend);
  }

  @SuppressWarnings("rawtypes")
  @Test
  @SneakyThrows
  public void shouldGetTechnicalServices() {
    when(service.getTechnicalServices(any()))
        .thenReturn(Lists.newArrayList(new VOTechnicalService()));

    Response response =
        resource.getTechnicalServices(uriInfo, headers, parameters.getEndpointVersion());

    assertThat(response).isNotNull();
    assertThat(response)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.OK.getStatusCode());
    assertThat(response)
        .extracting(r -> ((RepresentationCollection) r.getEntity()).getItems().size())
        .isEqualTo(1);
  }

  @Test
  @SneakyThrows
  public void shouldGetTechnicalService() {
    VOTechnicalService technicalService = new VOTechnicalService();
    technicalService.setKey(parameters.getId());
    when(service.getTechnicalServices(any())).thenReturn(Lists.newArrayList(technicalService));

    Response response =
        resource.getTechnicalService(
            uriInfo, headers, parameters.getEndpointVersion(), parameters.getId());

    assertThat(response).isNotNull();
    assertThat(response)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.OK.getStatusCode());
  }

  @Test
  public void shouldGetTechnicalServiceXML() throws Exception {
    // given
    VOTechnicalService technicalService = new VOTechnicalService();
    technicalService.setKey(parameters.getId());
    when(service.getTechnicalServices(any())).thenReturn(Lists.newArrayList(technicalService));

    // when
    Response response =
        resource.getTechnicalService(
            uriInfo, headers, parameters.getEndpointVersion(), parameters.getId());

    // then
    assertThat(response).isNotNull();
    assertThat(response)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.OK.getStatusCode());
  }

  @Test
  public void shouldGetTechnicalServicesXML() throws Exception {
    // given
    VOTechnicalService technicalService = new VOTechnicalService();
    when(service.getTechnicalServices(any())).thenReturn(Lists.newArrayList(technicalService));
    // when(service.exportTechnicalServices(any()))
    // .thenReturn(TECHNICAL_SERVICE_XML_EXAMPLE_RESPONSE.getBytes());

    // when
    Response response =
        resource.getTechnicalServices(uriInfo, headers, parameters.getEndpointVersion());

    // then
    assertThat(response).isNotNull();
    assertThat(response)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.OK.getStatusCode());
  }

  @Test
  @SneakyThrows
  public void shouldCreateTechnicalService() {
    when(service.createTechnicalService(any())).thenReturn(new VOTechnicalService());

    Response response =
        resource.createTechnicalService(uriInfo, parameters.getEndpointVersion(), representation);

    assertThat(response).isNotNull();
    assertThat(response)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.CREATED.getStatusCode());
  }

  @Test
  @SneakyThrows
  public void shouldDeleteTechnicalService() {
    doNothing().when(service).deleteTechnicalService(any(Long.class));

    Response response =
        resource.deleteTechnicalService(
            uriInfo, parameters.getEndpointVersion(), parameters.getId().toString());

    assertThat(response).isNotNull();
    assertThat(response)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.NO_CONTENT.getStatusCode());
  }

  @Test
  @SneakyThrows
  public void shouldImportTechnicalService() {
    when(service.importTechnicalServices(any())).thenReturn("");

    Response response =
        resource.importTechnicalService(
            uriInfo, parameters.getEndpointVersion(), importRepresentation);

    assertThat(response).isNotNull();
    assertThat(response)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.NO_CONTENT.getStatusCode());
  }
}
