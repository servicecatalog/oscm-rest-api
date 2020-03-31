/**
 * *****************************************************************************
 *
 * <p>Copyright FUJITSU LIMITED 2019
 *
 * <p>Creation Date: 16-04-2019
 *
 * <p>*****************************************************************************
 */
package org.oscm.rest.operation;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.google.common.collect.Lists;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.oscm.internal.intf.ConfigurationService;
import org.oscm.internal.intf.OperatorService;
import org.oscm.internal.vo.VOConfigurationSetting;
import org.oscm.rest.common.SampleTestDataUtility;
import org.oscm.rest.common.representation.RepresentationCollection;
import org.oscm.rest.common.representation.SettingRepresentation;
import org.oscm.rest.common.requestparameters.OperationParameters;

@ExtendWith(MockitoExtension.class)
public class SettingsBackendTest {

  @Mock private OperatorService operatorService;
  @Mock private ConfigurationService configurationService;
  @InjectMocks private SettingsBackend backend;
  private SettingsResource resource;

  private UriInfo uriInfo;
  private OperationParameters parameters;
  private SettingRepresentation representation;
  private VOConfigurationSetting vo;

  @BeforeEach
  public void setUp() {
    resource = new SettingsResource();
    resource.setSb(backend);

    uriInfo = SampleTestDataUtility.createUriInfo();
    parameters = SampleTestDataUtility.createOperationParameters();
    representation = SampleTestDataUtility.createSettingRepresentation();
    vo = SampleTestDataUtility.createVOConfigurationSetting();
  }

  @Test
  @SneakyThrows
  public void shouldGetSettings() {
    when(operatorService.getConfigurationSettings()).thenReturn(Lists.newArrayList(vo));

    Response response = resource.getSettings(uriInfo, parameters.getEndpointVersion());

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
  public void shouldGetSettingById() {
    when(operatorService.getConfigurationSetting(any())).thenReturn(vo);

    Response response = resource.getSetting(uriInfo, parameters);

    assertThat(response).isNotNull();
    assertThat(response)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.OK.getStatusCode());
    assertThat(response).extracting(Response::hasEntity).isEqualTo(true);
  }

  @Test
  @SneakyThrows
  public void shouldUpdateSetting() {
    doNothing().when(operatorService).saveConfigurationSetting(any());

    Response response = resource.updateSetting(uriInfo, representation, parameters);

    assertThat(response).isNotNull();
    assertThat(response)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.NO_CONTENT.getStatusCode());
  }

  @Test
  @SneakyThrows
  public void shouldDeleteSetting() {
    doNothing().when(operatorService).deleteConfigurationSetting(any());

    Response response = resource.deleteSetting(uriInfo, parameters);

    assertThat(response).isNotNull();
    assertThat(response)
        .extracting(Response::getStatus)
        .isEqualTo(Response.Status.NO_CONTENT.getStatusCode());
  }
}
