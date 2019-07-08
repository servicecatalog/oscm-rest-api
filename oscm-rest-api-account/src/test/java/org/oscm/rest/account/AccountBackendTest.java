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

import com.google.common.collect.Lists;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.oscm.internal.intf.AccountService;
import org.oscm.internal.intf.OperatorService;
import org.oscm.internal.types.enumtypes.OrganizationRoleType;
import org.oscm.internal.vo.VOBillingContact;
import org.oscm.internal.vo.VOOperatorOrganization;
import org.oscm.internal.vo.VOPaymentInfo;
import org.oscm.internal.vo.VOPaymentType;
import org.oscm.rest.account.data.*;
import org.oscm.rest.common.RepresentationCollection;
import org.oscm.rest.common.SampleTestDataUtility;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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
    parameters = createParameters();
    billingContactRepresentation = createBilingContactRepresentation();
    paymentInfoRepresentation = createPaymentInfoRepresentation();
    billingContactVO = createBillingContactVO();
    paymentInfoVO = createPaymentInfoVO();
    operatorOrgVO = createOperatorOrgVO();
  }

  @Test
  @SneakyThrows
  public void shouldGetBillingContactCollection() {
    when(accountService.getBillingContacts()).thenReturn(Lists.newArrayList(billingContactVO));

    Response response = billingContactResource.getBillingContacts(uriInfo, parameters);

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

    Response response = billingContactResource.getBillingContact(uriInfo, parameters);

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
            uriInfo, billingContactRepresentation, parameters);

    assertThat(response).isNotNull();
    assertThat(response)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.CREATED.getStatusCode());
  }

  @Test
  @SneakyThrows
  public void shouldPutBillingContact() {
    when(accountService.saveBillingContact(any())).thenReturn(billingContactVO);

    Response response =
        billingContactResource.updateBillingContact(
            uriInfo, billingContactRepresentation, parameters);

    assertThat(response).isNotNull();
    assertThat(response)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.NO_CONTENT.getStatusCode());
  }

  @Test
  @SneakyThrows
  public void shouldDeleteBillingContact() {
    doNothing().when(accountService).deleteBillingContact(any());

    Response response = billingContactResource.deleteBillingContact(uriInfo, parameters);

    assertThat(response).isNotNull();
    assertThat(response)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.NO_CONTENT.getStatusCode());
  }

  @Test
  @SneakyThrows
  public void shouldGetPaymentInfoCollection() {
    when(accountService.getPaymentInfos()).thenReturn(Lists.newArrayList(paymentInfoVO));

    Response response = paymentInfoResource.getPaymentInfos(uriInfo, parameters);

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

    Response response = paymentInfoResource.getPaymentInfo(uriInfo, parameters);

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
        paymentInfoResource.updatePaymentInfo(uriInfo, paymentInfoRepresentation, parameters);

    assertThat(response).isNotNull();
    assertThat(response)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.NO_CONTENT.getStatusCode());
  }

  @Test
  @SneakyThrows
  public void shouldDeletePaymentInfo() {
    doNothing().when(accountService).deletePaymentInfo(any());

    Response response = paymentInfoResource.deletePaymentInfo(uriInfo, parameters);

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

    Response response = organizationResource.getOrganization(uriInfo, parameters);

    assertThat(response).isNotNull();
    assertThat(response)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.OK.getStatusCode());
    assertThat(response).extracting(Response::hasEntity).isEqualTo(true);
  }

  @ParameterizedTest
  @MethodSource("provideRepresentationForCreatingOrganization")
  @SneakyThrows
  public void shouldPostOrganization(AccountRepresentation representation) {
    lenient()
        .when(accountService.registerCustomer(any(), any(), any(), any(), any(), any()))
        .thenReturn(operatorOrgVO);
    lenient()
        .when(accountService.registerKnownCustomer(any(), any(), any(), any()))
        .thenReturn(operatorOrgVO);
    lenient()
        .when(operatorService.registerOrganization(any(), any(), any(), any(), any(), any()))
        .thenReturn(operatorOrgVO);

    Response response =
        organizationResource.createOrganization(uriInfo, representation, parameters);

    assertThat(response).isNotNull();
    assertThat(response)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.CREATED.getStatusCode());
  }

  private static Stream<Arguments> provideRepresentationForCreatingOrganization() {
    AccountRepresentation representation = new AccountRepresentation();
    representation.setOrganization(new OrganizationRepresentation());
    representation.setUser(new UserRepresentation());

    return Stream.of(
        Arguments.of(createAccountRepresentation(OrganizationRegistrationMode.DEFAULT)),
        Arguments.of(createAccountRepresentation(OrganizationRegistrationMode.SELF_REGISTRATION)),
        Arguments.of(createAccountRepresentation(OrganizationRegistrationMode.KNOWN_CUSTOMER)));
  }

  private static AccountRepresentation createAccountRepresentation(
      OrganizationRegistrationMode mode) {
    AccountRepresentation representation = new AccountRepresentation();
    representation.setOrganization(new OrganizationRepresentation());
    representation.setUser(new UserRepresentation());

    if (!OrganizationRegistrationMode.KNOWN_CUSTOMER.equals(mode)) {
      representation.setOrganizationRoles(
          new OrganizationRoleType[] {OrganizationRoleType.SUPPLIER});
    }
    if (OrganizationRegistrationMode.SELF_REGISTRATION.equals(mode))
      representation.setPassword("password");

    return representation;
  }

  private AccountParameters createParameters() {
    AccountParameters parameters = new AccountParameters();
    parameters.setId(123L);
    return parameters;
  }

  private BillingContactRepresentation createBilingContactRepresentation() {
    return new BillingContactRepresentation();
  }

  private PaymentInfoRepresentation createPaymentInfoRepresentation() {
    return new PaymentInfoRepresentation();
  }

  private VOBillingContact createBillingContactVO() {
    VOBillingContact vo = new VOBillingContact();
    vo.setKey(123L);
    return vo;
  }

  private VOPaymentInfo createPaymentInfoVO() {
    VOPaymentInfo vo = new VOPaymentInfo();
    vo.setKey(123L);
    vo.setPaymentType(new VOPaymentType());
    return vo;
  }

  private VOOperatorOrganization createOperatorOrgVO() {
    VOOperatorOrganization vo = new VOOperatorOrganization();
    vo.setOrganizationId("orgid");
    return vo;
  }

  private enum OrganizationRegistrationMode {
    DEFAULT,
    SELF_REGISTRATION,
    KNOWN_CUSTOMER
  }
}
