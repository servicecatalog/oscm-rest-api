/**
 * *****************************************************************************
 *
 * <p>Copyright FUJITSU LIMITED 2019
 *
 * <p>Creation Date: 10-04-2019
 *
 * <p>*****************************************************************************
 */
package org.oscm.rest.subscription.data;

import javax.ws.rs.WebApplicationException;
import org.oscm.internal.vo.VOParameterDefinition;
import org.oscm.rest.common.Representation;

public class ParameterDefinitionRepresentation extends Representation {

  private String parameterId;

  private transient VOParameterDefinition vo;

  public ParameterDefinitionRepresentation() {
    this(new VOParameterDefinition());
  }

  public ParameterDefinitionRepresentation(VOParameterDefinition def) {
    vo = def;
  }

  @Override
  public void validateContent() throws WebApplicationException {}

  @Override
  public void update() {
    vo.setKey(convertIdToKey());
    vo.setParameterId(getParameterId());
    vo.setVersion(convertETagToVersion());
  }

  @Override
  public void convert() {
    setId(Long.valueOf(vo.getKey()));
    setParameterId(vo.getParameterId());
    setETag(Long.valueOf(vo.getVersion()));
  }

  public String getParameterId() {
    return parameterId;
  }

  public void setParameterId(String parameterId) {
    this.parameterId = parameterId;
  }

  public VOParameterDefinition getVO() {
    return vo;
  }

  // FIXME move to super class
  protected long convertIdToKey() {
    if (getId() == null) {
      return 0L;
    }
    return getId().longValue();
  }

  // FIXME move to super class
  protected int convertETagToVersion() {
    if (getETag() == null) {
      return 0;
    }
    return getETag().intValue();
  }
}
