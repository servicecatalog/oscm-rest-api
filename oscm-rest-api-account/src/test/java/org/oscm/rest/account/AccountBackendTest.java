/**
 * *****************************************************************************
 *
 * <p>Copyright FUJITSU LIMITED 2019
 *
 * <p>Creation Date: 10-04-2019
 *
 * <p>*****************************************************************************
 */
package org.oscm.rest.account;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

import com.google.common.collect.Lists;
import java.util.Optional;
import java.util.stream.Stream;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.oscm.internal.intf.AccountService;
import org.oscm.internal.intf.OperatorService;
import org.oscm.internal.vo.VOBillingContact;
import org.oscm.internal.vo.VOOperatorOrganization;
import org.oscm.internal.vo.VOPaymentInfo;
import org.oscm.rest.common.PostResponseBody;
import org.oscm.rest.common.SampleTestDataUtility;
import org.oscm.rest.common.TestContants;
import org.oscm.rest.common.representation.*;
import org.oscm.rest.common.requestparameters.AccountParameters;

@ExtendWith(MockitoExtension.class)
public class AccountBackendTest {

  @Mock private AccountService accountService;
  @Mock private OperatorService operatorService;
  @InjectMocks private AccountBackend backend;
  private BillingContactResource billingContactResource;
  private OrganizationResource organizationResource;
  private PaymentInfoResource paymentInfoResource;

  private UriInfo uriInfo;
  private AccountParameters parameters;
  private BillingContactRepresentation billingContactRepresentation;
  private PaymentInfoRepresentation paymentInfoRepresentation;
  private VOBillingContact billingContactVO;
  private VOPaymentInfo paymentInfoVO;
  private VOOperatorOrganization operatorOrgVO;

  @BeforeEach
  public void setUp() {
    billingContactResource = new BillingContactResource();
    organizationResource = new OrganizationResource();
    paymentInfoResource = new PaymentInfoResource();
    billingContactResource.setAb(backend);
    organizationResource.setAb(backend);
    paymentInfoResource.setAb(backend);

    uriInfo = SampleTestDataUtility.createUriInfo();
    parameters = SampleTestDataUtility.createAccountParameters();
    billingContactRepresentation = SampleTestDataUtility.createBillingContactRepresentation();
    paymentInfoRepresentation = SampleTestDataUtility.createPaymentInfoRepresentation();
    billingContactVO = SampleTestDataUtility.createBillingContactVO();
    paymentInfoVO = SampleTestDataUtility.createPaymentInfoVO();
    operatorOrgVO = SampleTestDataUtility.createOperatorOrgVO();
  }

  @Test
  @SneakyThrows
  public void shouldGetBillingContactCollection() {
    when(accountService.getBillingContacts()).thenReturn(Lists.newArrayList(billingContactVO));

    Response response =
        billingContactResource.getBillingContacts(uriInfo, parameters.getEndpointVersion());

    assertThat(response).isNotNull();
    assertThat(response)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.OK.getStatusCode());
    assertThat(response).extracting(Response::hasEntity).isEqualTo(true);
    assertThat(response)
        .extracting(
            r -> {
              return ((RepresentationCollection) r.getEntity()).getItems().size();
            })
        .isEqualTo(1);
  }

  @Test
  @SneakyThrows
  public void shouldGetBillingContact() {
    when(accountService.getBillingContacts()).thenReturn(Lists.newArrayList(billingContactVO));

    Response response =
        billingContactResource.getBillingContact(
            uriInfo, parameters.getEndpointVersion(), parameters.getId().toString());

    assertThat(response).isNotNull();
    assertThat(response)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.OK.getStatusCode());
    assertThat(response).extracting(Response::hasEntity).isEqualTo(true);
  }

  @Test
  @SneakyThrows
  public void shouldPostBillingContact() {
    when(accountService.saveBillingContact(any())).thenReturn(billingContactVO);

    Response response =
        billingContactResource.createBillingContact(
            uriInfo, billingContactRepresentation, parameters.getEndpointVersion());

    assertThat(response).isNotNull();
    assertThat(response)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.CREATED.getStatusCode());
  }

  @Test
  @SneakyThrows
  public void shouldPutBillingContact() {
    when(accountService.saveBillingContact(any())).thenReturn(billingContactVO);
    when(accountService.getBillingContacts()).thenReturn(Lists.newArrayList(billingContactVO));

    Response response =
        billingContactResource.updateBillingContact(
            uriInfo,
            billingContactRepresentation,
            parameters.getEndpointVersion(),
            parameters.getId().toString());

    assertThat(response).isNotNull();
    assertThat(response)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.NO_CONTENT.getStatusCode());
  }

  @Test
  @SneakyThrows
  public void shouldDeleteBillingContact() {
    doNothing().when(accountService).deleteBillingContact(any());

    Response response =
        billingContactResource.deleteBillingContact(
            uriInfo, parameters.getEndpointVersion(), parameters.getId().toString());

    assertThat(response).isNotNull();
    assertThat(response)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.NO_CONTENT.getStatusCode());
  }

  @Test
  @SneakyThrows
  public void shouldGetPaymentInfoCollection() {
    when(accountService.getPaymentInfos()).thenReturn(Lists.newArrayList(paymentInfoVO));

    Response response =
        paymentInfoResource.getPaymentInfos(uriInfo, parameters.getEndpointVersion());

    assertThat(response).isNotNull();
    assertThat(response)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.OK.getStatusCode());
    assertThat(response).extracting(Response::hasEntity).isEqualTo(true);
    assertThat(response)
        .extracting(
            r -> {
              return ((RepresentationCollection) r.getEntity()).getItems().size();
            })
        .isEqualTo(1);
  }

  @Test
  @SneakyThrows
  public void shouldGetPaymentInfo() {
    when(accountService.getPaymentInfos()).thenReturn(Lists.newArrayList(paymentInfoVO));

    Response response =
        paymentInfoResource.getPaymentInfo(
            uriInfo, parameters.getEndpointVersion(), parameters.getId().toString());

    assertThat(response).isNotNull();
    assertThat(response)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.OK.getStatusCode());
    assertThat(response).extracting(Response::hasEntity).isEqualTo(true);
  }

  @Test
  @SneakyThrows
  public void shouldPutPaymentInfo() {
    when(accountService.savePaymentInfo(any())).thenReturn(paymentInfoVO);

    Response response =
        paymentInfoResource.updatePaymentInfo(
            uriInfo,
            paymentInfoRepresentation,
            parameters.getEndpointVersion(),
            parameters.getId().toString());

    assertThat(response).isNotNull();
    assertThat(response)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.NO_CONTENT.getStatusCode());
  }

  @Test
  @SneakyThrows
  public void shouldDeletePaymentInfo() {
    doNothing().when(accountService).deletePaymentInfo(any());

    Response response =
        paymentInfoResource.deletePaymentInfo(
            uriInfo, parameters.getEndpointVersion(), parameters.getId().toString());

    assertThat(response).isNotNull();
    assertThat(response)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.NO_CONTENT.getStatusCode());
  }

  @Test
  @SneakyThrows
  public void shouldGetOrganization() {
    when(operatorService.getOrganization(any())).thenReturn(operatorOrgVO);
    when(accountService.getOrganizationData()).thenReturn(operatorOrgVO);

    Response response =
        organizationResource.getOrganization(
            uriInfo, parameters.getEndpointVersion(), parameters.getOrgId());

    assertThat(response).isNotNull();
    assertThat(response)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.OK.getStatusCode());
    assertThat(response).extracting(Response::hasEntity).isEqualTo(true);
  }

  @Test
  @SneakyThrows
  public void shouldPostOrganization() {
    lenient()
        .when(operatorService.registerOrganization(any(), any(), any(), any(), any(), any()))
        .thenReturn(operatorOrgVO);
    CreateOrganizationRepresentation createOrganizationRepresentation =
        SampleTestDataUtility.createOrgCreateRepresentation();
    Response response =
        organizationResource.createOrganization(
            uriInfo, createOrganizationRepresentation, parameters.getEndpointVersion());

    assertThat(response).isNotNull();
    assertThat(response)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.CREATED.getStatusCode());
    assertThat(response).extracting(Response::hasEntity).isEqualTo(true);
    assertThat((PostResponseBody) response.getEntity())
        .extracting(PostResponseBody::getCreatedObjectId)
        .isNotNull();
    assertThat((PostResponseBody) response.getEntity())
        .extracting(PostResponseBody::getCreatedObjectName)
        .isNotNull();
  }

  @ParameterizedTest
  @MethodSource("provideRepresentationForCreatingOrganization")
  @SneakyThrows
  public void organizationResource_updateOrganization(AccountRepresentation representation) {

    Mockito.doNothing().when(accountService).updateAccountInformation(any(), any(), any(), any());

    Response response =
        organizationResource.updateOrganization(
            uriInfo, representation, parameters.getEndpointVersion(), null);

    assertThat(response).isNotNull();
    assertThat(response)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.NO_CONTENT.getStatusCode());
  }

  private static Stream<Arguments> provideRepresentationForCreatingOrganization() {
    AccountRepresentation representation = new AccountRepresentation();
    representation.setOrganization(new OrganizationRepresentation());
    representation.setUser(new UserRepresentation());

    return Stream.of(
        Arguments.of(
            SampleTestDataUtility.createAccountRepresentation(
                Optional.of(TestContants.OrganizationRegistrationMode.DEFAULT))),
        Arguments.of(
            SampleTestDataUtility.createAccountRepresentation(
                Optional.of(TestContants.OrganizationRegistrationMode.SELF_REGISTRATION))),
        Arguments.of(
            SampleTestDataUtility.createAccountRepresentation(
                Optional.of(TestContants.OrganizationRegistrationMode.KNOWN_CUSTOMER))));
  }
}
