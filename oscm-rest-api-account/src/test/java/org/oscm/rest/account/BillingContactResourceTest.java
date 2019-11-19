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
import org.oscm.internal.vo.VOBillingContact;
import org.oscm.rest.common.PostResponseBody;
import org.oscm.rest.common.representation.BillingContactRepresentation;
import org.oscm.rest.common.representation.RepresentationCollection;
import org.oscm.rest.common.SampleTestDataUtility;
import org.oscm.rest.common.requestparameters.AccountParameters;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BillingContactResourceTest {

  @Mock private AccountBackend accountBackend;
  @InjectMocks @Spy private BillingContactResource resource;

  private BillingContactRepresentation representation;
  private UriInfo uriInfo;
  private AccountParameters parameters;

  private Response result;

  @BeforeEach
  public void setUp() {
    representation = SampleTestDataUtility.createBillingContactRepresentation();
    uriInfo = SampleTestDataUtility.createUriInfo();
    parameters = SampleTestDataUtility.createAccountParameters();
  }

  @AfterEach
  public void cleanUp() {
    result = null;
  }

  @Test
  public void shouldGetBillingContacts() {
    when(accountBackend.getBillingContactCollection())
        .thenReturn(
            accountParameters ->
                new RepresentationCollection<>(Lists.newArrayList(representation)));

    try {
      result = resource.getBillingContacts(uriInfo, parameters);
    } catch (Exception e) {
      fail(e);
    }

    assertThat(result).isNotNull();
    assertThat(result)
        .extracting(
            r -> {
              RepresentationCollection<BillingContactRepresentation> collection =
                  (RepresentationCollection<BillingContactRepresentation>) r.getEntity();
              return collection.getItems().toArray()[0];
            })
        .isEqualTo(representation);
    assertThat(result)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.OK.getStatusCode());
  }

  @Test
  public void shouldCreateBillingContact() {
    VOBillingContact vo = SampleTestDataUtility.createBillingContactVO();
    when(accountBackend.postBillingContact())
        .thenReturn(((billingContactRepresentation, accountParameters) -> PostResponseBody.of().createdObjectId(
                String.valueOf(vo.getKey())).createdObjectName(vo.getId()).build()));

    try {
      result = resource.createBillingContact(uriInfo, representation, parameters);
    } catch (Exception e) {
      fail(e);
    }

    assertThat(result).isNotNull();
    assertThat(result)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.CREATED.getStatusCode());
    assertThat(result).extracting(Response::hasEntity).isEqualTo(true);
    assertThat((PostResponseBody) result.getEntity()).extracting(PostResponseBody::getCreatedObjectId).isNotNull();
    assertThat((PostResponseBody) result.getEntity()).extracting(PostResponseBody::getCreatedObjectName).isNotNull();
  }

  @Test
  public void shouldGetSingleBillingContact() {
    when(accountBackend.getBillingContact()).thenReturn((accountParameters -> representation));

    try {
      result = resource.getBillingContact(uriInfo, parameters);
    } catch (Exception e) {
      fail(e);
    }

    assertThat(result).isNotNull();
    assertThat(result).extracting(Response::getEntity).isEqualTo(representation);
    assertThat(result)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.OK.getStatusCode());
  }

  @Test
  public void shouldUpdateBillingContact() {
    when(accountBackend.putBillingContact())
        .thenReturn(((billingContactRepresentation, accountParameters) -> true));

    try {
      result = resource.updateBillingContact(uriInfo, representation, parameters);
    } catch (Exception e) {
      fail(e);
    }

    assertThat(result).isNotNull();
    assertThat(result)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.NO_CONTENT.getStatusCode());
  }

  @Test
  public void shouldDeleteBillingContact() {
    when(accountBackend.deleteBillingContact()).thenReturn((accountParameters -> true));

    try {
      result = resource.deleteBillingContact(uriInfo, parameters);
    } catch (Exception e) {
      fail(e);
    }

    assertThat(result).isNotNull();
    assertThat(result)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.NO_CONTENT.getStatusCode());
  }
}
