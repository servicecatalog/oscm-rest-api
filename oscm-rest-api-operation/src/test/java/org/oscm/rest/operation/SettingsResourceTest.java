/**
 * *****************************************************************************
 *
 * <p>Copyright FUJITSU LIMITED 2019
 *
 * <p>Creation Date: 15-04-2019
 *
 * <p>*****************************************************************************
 */
package org.oscm.rest.operation;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.oscm.rest.common.representation.RepresentationCollection;
import org.oscm.rest.common.SampleTestDataUtility;
import org.oscm.rest.operation.data.SettingRepresentation;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SettingsResourceTest {

  @Mock private SettingsBackend settingsBackend;

  @InjectMocks @Spy private SettingsResource settingsResource;

  private Response response;
  private SettingRepresentation settingRepresentation;
  private UriInfo uriInfo;
  private OperationParameters operationParameters;

  @BeforeEach
  public void setUp() {
    settingRepresentation = new SettingRepresentation();
    uriInfo = SampleTestDataUtility.createUriInfo();
    operationParameters = createParameters();
  }

  @AfterEach
  public void cleanUp() {
    response = null;
  }

  @Test
  public void shouldGetSettings() {
    when(settingsBackend.getCollection())
        .thenReturn(
            operationParameters ->
                new RepresentationCollection<>(Lists.newArrayList(settingRepresentation)));

    try {
      response = settingsResource.getSettings(uriInfo, operationParameters);
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
              RepresentationCollection<SettingRepresentation> representationCollection =
                  (RepresentationCollection<SettingRepresentation>) r.getEntity();
              return representationCollection.getItems().toArray()[0];
            })
        .isEqualTo(settingRepresentation);
  }

  @Test
  public void shouldCreateSettings() {
    try {
      when(settingsBackend.post())
          .thenReturn((settingRepresentation1, operationParameters) -> 100L);
    } catch (Exception e) {
      fail(e);
    }

    try {
      response =
          settingsResource.createSetting(uriInfo, settingRepresentation, operationParameters);
    } catch (Exception e) {
      fail(e);
    }

    assertThat(response).isNotNull();
    assertThat(response)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.CREATED.getStatusCode());
    assertThat(response).extracting(Response::hasEntity).isEqualTo(false);
  }

  @Test
  public void shouldGetSetting() {
    try {
      when(settingsBackend.get()).thenReturn(operationParameters -> settingRepresentation);
    } catch (Exception e) {
      e.printStackTrace();
    }

    try {
      response = settingsResource.getSetting(uriInfo, operationParameters);
    } catch (Exception e) {
      fail(e);
    }

    assertThat(response).isNotNull();
    assertThat(response)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.OK.getStatusCode());
    assertThat(response).extracting(Response::hasEntity).isEqualTo(true);
    assertThat(response).extracting(Response::getEntity).isEqualTo(settingRepresentation);
  }

  @Test
  public void shouldUpdateSettings() {
    try {
      when(settingsBackend.put()).thenReturn((settingRepresentation1, operationParameters) -> true);
    } catch (Exception e) {
      fail(e);
    }

    try {
      response =
          settingsResource.updateSetting(uriInfo, settingRepresentation, operationParameters);
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
  public void shouldDeleteSetting() {
    try {
      when(settingsBackend.delete()).thenReturn(operationParameters -> true);
    } catch (Exception e) {
      fail(e);
    }

    try {
      response = settingsResource.deleteSetting(uriInfo, operationParameters);
    } catch (Exception e) {
      fail(e);
    }

    assertThat(response).isNotNull();
    assertThat(response)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.NO_CONTENT.getStatusCode());
    assertThat(response).extracting(Response::hasEntity).isEqualTo(false);
  }

  private OperationParameters createParameters() {
    OperationParameters parameters = new OperationParameters();
    parameters.setId(100L);
    return parameters;
  }
}
