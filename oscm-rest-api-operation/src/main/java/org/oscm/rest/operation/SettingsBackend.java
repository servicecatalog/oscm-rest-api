/**
 * *****************************************************************************
 *
 * <p>Copyright FUJITSU LIMITED 2019
 *
 * <p>Creation Date: 10-04-2019
 *
 * <p>*****************************************************************************
 */
package org.oscm.rest.operation;

import org.oscm.internal.intf.ConfigurationService;
import org.oscm.internal.intf.OperatorService;
import org.oscm.internal.vo.VOConfigurationSetting;
import org.oscm.rest.common.PostResponseBody;
import org.oscm.rest.common.RestBackend;
import org.oscm.rest.common.representation.SettingRepresentation;
import org.oscm.rest.common.requestparameters.OperationParameters;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class SettingsBackend {

  @EJB OperatorService os;

  @EJB ConfigurationService cs;

  public RestBackend.Delete<OperationParameters> delete() throws Exception {
    return params -> {
      os.deleteConfigurationSetting(params.getId());
      return true;
    };
  }

  public RestBackend.Post<SettingRepresentation, OperationParameters> post() throws Exception {
    return (content, params) -> {
      os.saveConfigurationSetting(content.getVO());
      VOConfigurationSetting vo =
          cs.getVOConfigurationSetting(content.getInformationId(), content.getContextId());
      return PostResponseBody.of()
          .createdObjectId(String.valueOf(vo.getKey()))
          .build();
    };
  }

  public RestBackend.Put<SettingRepresentation, OperationParameters> put() throws Exception {
    return (content, params) -> {
      os.saveConfigurationSetting(content.getVO());
      return true;
    };
  }

  public RestBackend.Get<SettingRepresentation, OperationParameters> get() throws Exception {
    return params -> {
      VOConfigurationSetting vo = os.getConfigurationSetting(params.getId());
      return new SettingRepresentation(vo);
    };
  }

  public RestBackend.GetCollection<SettingRepresentation, OperationParameters> getCollection() {
    return params -> {
      List<VOConfigurationSetting> settings = os.getConfigurationSettings();
      return SettingRepresentation.toCollection(settings);
    };
  }
}
