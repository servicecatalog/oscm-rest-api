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

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.oscm.rest.account.data.PaymentInfoRepresentation;
import org.oscm.rest.common.RepresentationCollection;
import org.oscm.rest.common.SampleTestDataUtility;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PaymentInfoResourceTest {

  @Mock private AccountBackend backend;
  @InjectMocks @Spy private PaymentInfoResource resource;

  private PaymentInfoRepresentation paymentInfoRepresentation;
  private UriInfo uriInfo;
  private AccountParameters parameters;
  private Response result;

  @BeforeEach
  public void setUp() {
    paymentInfoRepresentation = createPIRepresentation();
    parameters = createAccountParameters();
    uriInfo = SampleTestDataUtility.createUriInfo();
  }

  @AfterEach
  public void cleanUp() {
    result = null;
  }

  @Test
  public void shouldGetPaymentInfoCollection() {
    when(backend.getPaymentInfoCollection())
        .thenReturn(
            (accountParameters1 ->
                new RepresentationCollection<>(Lists.newArrayList(paymentInfoRepresentation))));

    try {
      result = resource.getPaymentInfos(uriInfo, parameters);
    } catch (Exception e) {
      fail(e);
    }

    assertThat(result).isNotNull();
    assertThat(result)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.OK.getStatusCode());
    assertThat(result)
        .extracting(
            r -> {
              RepresentationCollection<PaymentInfoRepresentation> collection =
                  (RepresentationCollection<PaymentInfoRepresentation>) r.getEntity();
              return collection.getItems().toArray()[0];
            })
        .isEqualTo(paymentInfoRepresentation);
  }

  @Test
  public void shouldGetSinglePaymentInfo() {
    when(backend.getPaymentInfo()).thenReturn((accountParameters -> paymentInfoRepresentation));

    try {
      result = resource.getPaymentInfo(uriInfo, parameters);
    } catch (Exception e) {
      fail(e);
    }

    assertThat(result).isNotNull();
    assertThat(result)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.OK.getStatusCode());
    assertThat(result).extracting(Response::getEntity).isEqualTo(paymentInfoRepresentation);
  }

  @Test
  public void shouldUpdatePaymentInfo() {
    when(backend.putPaymentInfo())
        .thenReturn(((paymentInfoRepresentation1, accountParameters) -> true));

    try {
      result = resource.updatePaymentInfo(uriInfo, paymentInfoRepresentation, parameters);
    } catch (Exception e) {
      fail(e);
    }

    assertThat(result).isNotNull();
    assertThat(result)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.NO_CONTENT.getStatusCode());
  }

  @Test
  public void shouldDeletePaymentInfo() {
    when(backend.deletePaymentInfo()).thenReturn((accountParameters -> true));

    try {
      result = resource.deletePaymentInfo(uriInfo, parameters);
    } catch (Exception e) {
      fail(e);
    }

    assertThat(result).isNotNull();
    assertThat(result)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.NO_CONTENT.getStatusCode());
  }

  private PaymentInfoRepresentation createPIRepresentation() {
    PaymentInfoRepresentation representation = new PaymentInfoRepresentation();
    return representation;
  }

  private AccountParameters createAccountParameters() {
    AccountParameters parameters = new AccountParameters();
    parameters.setOrgId("orgId");
    return parameters;
  }
}
