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

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.oscm.rest.common.representation.AccountRepresentation;
import org.oscm.rest.common.representation.OrganizationRepresentation;
import org.oscm.rest.common.representation.UserRepresentation;
import org.oscm.rest.common.SampleTestDataUtility;
import org.oscm.rest.common.requestparameters.AccountParameters;
import org.oscm.rest.common.requestparameters.IdentifiableAccountParameters;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrganizationResourceTest {

  @Mock private AccountBackend backend;
  @InjectMocks @Spy private OrganizationResource resource;

  private AccountRepresentation accountRepresentation;
  private OrganizationRepresentation orgRepresentation;
  private UriInfo uriInfo;
  private AccountParameters parameters;
  private IdentifiableAccountParameters indentifiableParameters;
  private Response result;

  @BeforeEach
  public void setUp() {
    accountRepresentation = SampleTestDataUtility.createAccountRepresentation(Optional.empty());
    orgRepresentation = SampleTestDataUtility.createOrgRepresentation();
    uriInfo = SampleTestDataUtility.createUriInfo();
    parameters = SampleTestDataUtility.createAccountParameters();
    indentifiableParameters = SampleTestDataUtility.createIdentifiableAccountParameters();
  }

  @AfterEach
  public void cleanUp() {
    result = null;
  }

  @Test
  public void shouldCreateOrganization() {
    when(backend.postOrganization())
        .thenReturn(((accountRepresentation, accountParameters) -> "newId"));

    try {
      result = resource.createOrganization(uriInfo, accountRepresentation, parameters);
    } catch (Exception e) {
      fail(e);
    }

    assertThat(result).isNotNull();
    assertThat(result)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.CREATED.getStatusCode());
  }

  @Test
  public void shouldGetOrganization() {
    when(backend.getOrganization()).thenReturn((accountParameters -> orgRepresentation));

    try {
      result = resource.getOrganization(uriInfo, indentifiableParameters);
    } catch (Exception e) {
      fail(e);
    }

    assertThat(result).isNotNull();
    assertThat(result)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.OK.getStatusCode());
    assertThat(result).extracting(Response::getEntity).isEqualTo(orgRepresentation);
  }
}
