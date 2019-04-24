/**
 * *****************************************************************************
 *
 * <p>Copyright FUJITSU LIMITED 2019
 *
 * <p>Creation Date: 10-04-2019
 *
 * <p>*****************************************************************************
 */
package org.oscm.rest.service.data;

import javax.ws.rs.WebApplicationException;

import lombok.Generated;
import org.oscm.internal.types.enumtypes.OperationParameterType;
import org.oscm.internal.vo.VOServiceOperationParameter;
import org.oscm.rest.common.Representation;

public class OperationParameterRepresentation extends Representation {

  private String parameterId;
  private String parameterName;
  private boolean mandatory;
  private OperationParameterType type;
  private String parameterValue;

  private transient VOServiceOperationParameter vo;

  public OperationParameterRepresentation() {
    this(new VOServiceOperationParameter());
  }

  public OperationParameterRepresentation(VOServiceOperationParameter op) {
    vo = op;
  }

  @Override
  public void validateContent() throws WebApplicationException {}

  // FIXME move to super class
  // FIXME excluded from code coverage due to fixme
  // TODO Remove @Generated annotation when moving to superclass
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

  @Override
  public void update() {
    vo.setKey(convertIdToKey());
    vo.setMandatory(isMandatory());
    vo.setParameterId(getParameterId());
    vo.setParameterName(getParameterName());
    vo.setParameterValue(getParameterValue());
    vo.setType(getType());
    vo.setVersion(convertETagToVersion());
  }

  @Override
  public void convert() {
    setId(Long.valueOf(vo.getKey()));
    setMandatory(vo.isMandatory());
    setParameterId(vo.getParameterId());
    setParameterName(vo.getParameterName());
    setParameterValue(vo.getParameterValue());
    setETag(Long.valueOf(vo.getVersion()));
    setType(vo.getType());
  }

  public VOServiceOperationParameter getVO() {
    return vo;
  }

  public String getParameterId() {
    return parameterId;
  }

  public void setParameterId(String parameterId) {
    this.parameterId = parameterId;
  }

  public String getParameterName() {
    return parameterName;
  }

  public void setParameterName(String parameterName) {
    this.parameterName = parameterName;
  }

  public boolean isMandatory() {
    return mandatory;
  }

  public void setMandatory(boolean mandatory) {
    this.mandatory = mandatory;
  }

  public OperationParameterType getType() {
    return type;
  }

  public void setType(OperationParameterType type) {
    this.type = type;
  }

  public String getParameterValue() {
    return parameterValue;
  }

  public void setParameterValue(String parameterValue) {
    this.parameterValue = parameterValue;
  }
}
