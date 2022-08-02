/**
 * *****************************************************************************
 *
 * <p>Copyright FUJITSU LIMITED 2019
 *
 * <p>Creation Date: 26-04-2019
 *
 * <p>*****************************************************************************
 */
package org.oscm.rest.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

import javax.ws.rs.core.HttpHeaders;
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
import org.oscm.internal.intf.ServiceProvisioningService;
import org.oscm.rest.common.SampleTestDataUtility;
import org.oscm.rest.common.representation.RepresentationCollection;
import org.oscm.rest.common.representation.ServiceRepresentation;
import org.oscm.rest.common.representation.TechnicalServiceImportRepresentation;
import org.oscm.rest.common.representation.TechnicalServiceRepresentation;
import org.oscm.rest.common.representation.TechnicalServiceXMLRepresentation;
import org.oscm.rest.common.requestparameters.ServiceParameters;

import com.google.common.collect.Lists;

@SuppressWarnings({"unchecked", "boxing"})
@ExtendWith(MockitoExtension.class)
public class TechnicalServiceResourceTest {

  @Mock private TechnicalServiceBackend technicalServiceBackend;
  @Mock HttpHeaders headers;

  @Mock private ServiceProvisioningService serviceProvisioningService;

  @InjectMocks @Spy TechnicalServiceResource technicalServiceResource;

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

  private Response response;
  private TechnicalServiceRepresentation technicalServiceRepresentation;
  private TechnicalServiceImportRepresentation technicalServiceImportRepresentation;
  private TechnicalServiceXMLRepresentation technicalServiceXMLRepresentation;
  private UriInfo uriInfo;
  private ServiceParameters serviceParameters;

  @BeforeEach
  public void setUp() {
    technicalServiceImportRepresentation = SampleTestDataUtility.createTSImportRepresentation();
    technicalServiceRepresentation = SampleTestDataUtility.createTSRepresentation();
    technicalServiceXMLRepresentation = SampleTestDataUtility.createTSXMLRepresentation();
    uriInfo = SampleTestDataUtility.createUriInfo();
    serviceParameters = SampleTestDataUtility.createServiceParameters();
  }

  @AfterEach
  public void cleanUp() {
    response = null;
  }

  @Test
  public void shouldGetTechnicalServices() {
    when(technicalServiceBackend.getCollection())
        .thenReturn(
            serviceParameters1 ->
                new RepresentationCollection<>(Lists.newArrayList(technicalServiceRepresentation)));

    try {
      response =
          technicalServiceResource.getTechnicalServices(
              uriInfo, headers, serviceParameters.getEndpointVersion());
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
              RepresentationCollection<ServiceRepresentation> representationCollection =
                  (RepresentationCollection<ServiceRepresentation>) r.getEntity();
              return representationCollection.getItems().toArray()[0];
            })
        .isEqualTo(technicalServiceRepresentation);
  }

  @Test
  public void shouldGetTechnicalServicesXML() throws Exception {

    // given
    when(technicalServiceBackend.getXMLCollection())
        .thenReturn(
            serviceParameters1 ->
                new RepresentationCollection<>(
                    Lists.newArrayList(technicalServiceXMLRepresentation)));
    // when
    try {
      response =
          technicalServiceResource.getTechnicalServicesAsXML(
              uriInfo, headers, serviceParameters.getEndpointVersion());
    } catch (Exception e) {
      fail(e);
    }

    // then
    assertThat(response).isNotNull();
    assertThat(response)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.OK.getStatusCode());
    assertThat(response).extracting(Response::hasEntity).isEqualTo(true);
  }

  @Test
  public void shouldGetTechnicalServiceXML() throws Exception {
    // given
    when(technicalServiceBackend.getXML())
        .thenReturn(
            serviceParameters1 -> Lists.newArrayList(technicalServiceXMLRepresentation).get(0));

    // when
    response =
        technicalServiceResource.getTechnicalServiceAsXML(
            uriInfo, headers, serviceParameters.getEndpointVersion(), serviceParameters.getId());

    // then
    assertThat(response).isNotNull();
    assertThat(response)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.OK.getStatusCode());
    assertThat(response).extracting(Response::hasEntity).isEqualTo(true);
  }

  @Test
  public void shouldGetTechnicalService() {
    when(technicalServiceBackend.get())
        .thenReturn(serviceParameters -> technicalServiceRepresentation);

    try {
      response =
          technicalServiceResource.getTechnicalService(
              uriInfo, headers, serviceParameters.getEndpointVersion(), serviceParameters.getId());
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
              return (TechnicalServiceRepresentation) r.getEntity();
            })
        .isEqualTo(technicalServiceRepresentation);
  }

  @Test
  public void shouldCreateTechnicalService() {
    when(technicalServiceBackend.post())
        .thenReturn((serviceDetailsRepresentation1, serviceParameters1) -> true);

    try {
      response =
          technicalServiceResource.createTechnicalService(
              uriInfo, serviceParameters.getEndpointVersion(), technicalServiceRepresentation);
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
  public void shouldDeleteTechnicalService() {
    when(technicalServiceBackend.delete()).thenReturn(serviceParameters1 -> true);

    try {
      response =
          technicalServiceResource.deleteTechnicalService(
              uriInfo,
              serviceParameters.getEndpointVersion(),
              serviceParameters.getId().toString());
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
  public void shouldImportTechnicalService() {
    when(technicalServiceBackend.importFromXml())
        .thenReturn((importRepresentation, serviceParameters) -> true);

    try {
      response =
          technicalServiceResource.importTechnicalService(
              uriInfo,
              serviceParameters.getEndpointVersion(),
              technicalServiceImportRepresentation);
    } catch (Exception e) {
      fail(e);
    }

    assertThat(response).isNotNull();
    assertThat(response)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.NO_CONTENT.getStatusCode());
  }
}
