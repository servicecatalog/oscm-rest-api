/**
 * *****************************************************************************
 *
 * <p>Copyright FUJITSU LIMITED 2019
 *
 * <p>Creation Date: 16-04-2019
 *
 * <p>*****************************************************************************
 */
package org.oscm.rest.operation.data;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.oscm.internal.types.enumtypes.ConfigurationKey;
import org.oscm.internal.vo.BaseVO;
import org.oscm.internal.vo.VOConfigurationSetting;
import org.oscm.rest.common.Representation;
import org.oscm.rest.common.RepresentationCollection;

class SettingRepresentationTest {

  @Mock ConfigurationKey configurationKey;

  @Test
  public void shouldUpdateVOConfigurationSetting() {
    SettingRepresentation representation = new SettingRepresentation();
    representation.setContextId("12345");
    representation.setInformationId(configurationKey);
    representation.setId(12345L);
    representation.setValue("value");
    representation.setETag(6789L);

    representation.update();
    VOConfigurationSetting result = representation.getVO();

    assertThat(result).isNotNull();
    assertThat(result)
        .extracting(VOConfigurationSetting::getContextId)
        .isEqualTo(representation.getContextId());
    assertThat(result)
        .extracting(VOConfigurationSetting::getInformationId)
        .isEqualTo(representation.getInformationId());
    assertThat(result)
        .extracting(VOConfigurationSetting::getValue)
        .isEqualTo(representation.getValue());
    assertThat(result).extracting(BaseVO::getKey).isEqualTo(representation.getId());
    assertThat(result).extracting(r -> (long) r.getVersion()).isEqualTo(representation.getETag());
  }

  @Test
  public void shouldConvertToSettingsRepresentation() {
    VOConfigurationSetting configurationSetting = new VOConfigurationSetting();
    configurationSetting.setContextId("12345");
    configurationSetting.setInformationId(configurationKey);
    configurationSetting.setKey(12345L);
    configurationSetting.setValue("value");
    configurationSetting.setVersion(6789);

    SettingRepresentation representation = new SettingRepresentation(configurationSetting);
    representation.convert();

    assertThat(representation)
        .extracting(SettingRepresentation::getContextId)
        .isEqualTo(configurationSetting.getContextId());
    assertThat(representation)
        .extracting(SettingRepresentation::getInformationId)
        .isEqualTo(configurationSetting.getInformationId());
    assertThat(representation)
        .extracting(Representation::getId)
        .isEqualTo(configurationSetting.getKey());
    assertThat(representation)
        .extracting(SettingRepresentation::getValue)
        .isEqualTo(configurationSetting.getValue());
    assertThat(representation)
        .extracting(SettingRepresentation::convertETagToVersion)
        .isEqualTo(configurationSetting.getVersion());
  }

  @Test
  public void shouldConvertSettingsRepresentationToCollection() {
    VOConfigurationSetting voConfigurationSetting = new VOConfigurationSetting();
    voConfigurationSetting.setValue("value123");

    List<VOConfigurationSetting> settings = new ArrayList<>();
    settings.add(voConfigurationSetting);

    RepresentationCollection<SettingRepresentation> representationCollection =
        SettingRepresentation.toCollection(settings);

    assertThat(representationCollection)
        .extracting(r -> representationCollection.getItems().size())
        .isEqualTo(settings.size());
    assertThat(representationCollection)
        .extracting(r -> representationCollection.getItems().iterator().next().getVO().getValue())
        .isEqualTo(voConfigurationSetting.getValue());
  }
}
