/**
 * *****************************************************************************
 *
 * <p>Copyright FUJITSU LIMITED 2019
 *
 * <p>Creation Date: 10-04-2019
 *
 * <p>*****************************************************************************
 */
package org.oscm.rest.operation.data;

import lombok.Generated;
import org.oscm.internal.types.enumtypes.ConfigurationKey;
import org.oscm.internal.vo.VOConfigurationSetting;
import org.oscm.rest.common.Representation;
import org.oscm.rest.common.RepresentationCollection;

import javax.ws.rs.WebApplicationException;
import java.util.ArrayList;
import java.util.List;

public class SettingRepresentation extends Representation {

  private transient VOConfigurationSetting vo;

  private ConfigurationKey informationId;
  private String contextId;
  private String value;

  public SettingRepresentation() {
    this(new VOConfigurationSetting());
  }

  public SettingRepresentation(VOConfigurationSetting cs) {
    vo = cs;
  }

  @Override
  public void validateContent() throws WebApplicationException {}

  @Override
  public void update() {
    vo.setContextId(getContextId());
    vo.setInformationId(getInformationId());
    vo.setKey(convertIdToKey());
    vo.setValue(getValue());
    vo.setVersion(convertETagToVersion());
  }

  @Override
  public void convert() {
    setContextId(vo.getContextId());
    setETag(Long.valueOf(vo.getVersion()));
    setId(Long.valueOf(vo.getKey()));
    setInformationId(vo.getInformationId());
    setValue(vo.getValue());
  }

  public ConfigurationKey getInformationId() {
    return informationId;
  }

  public void setInformationId(ConfigurationKey informationId) {
    this.informationId = informationId;
  }

  public String getContextId() {
    return contextId;
  }

  public void setContextId(String contextId) {
    this.contextId = contextId;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public VOConfigurationSetting getVO() {
    return vo;
  }

  public static RepresentationCollection<SettingRepresentation> toCollection(
      List<VOConfigurationSetting> settings) {
    List<SettingRepresentation> list = new ArrayList<SettingRepresentation>();
    for (VOConfigurationSetting cs : settings) {
      list.add(new SettingRepresentation(cs));
    }
    return new RepresentationCollection<SettingRepresentation>(list);
  }

  // FIXME move to super class
  // FIXME excluded from code coverage due to fixme
  // TODO Remove @Generated annotation when moving to superclass
  @Generated
  protected long convertIdToKey() {
    if (getId() == null) {
      return 0L;
    }
    return getId().longValue();
  }

  // FIXME move to super class
  // FIXME excluded from code coverage due to fixme
  // TODO Remove @Generated annotation when moving to superclass
  @Generated
  protected int convertETagToVersion() {
    if (getETag() == null) {
      return 0;
    }
    return getETag().intValue();
  }
}
