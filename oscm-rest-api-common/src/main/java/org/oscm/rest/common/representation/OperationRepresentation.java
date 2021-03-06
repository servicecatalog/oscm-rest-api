/**
 * *****************************************************************************
 *
 * <p>Copyright FUJITSU LIMITED 2019
 *
 * <p>Creation Date: 10-04-2019
 *
 * <p>*****************************************************************************
 */
package org.oscm.rest.common.representation;

import org.oscm.internal.vo.VOServiceOperationParameter;
import org.oscm.internal.vo.VOTechnicalServiceOperation;

import javax.ws.rs.WebApplicationException;
import java.util.ArrayList;
import java.util.List;

public class OperationRepresentation extends Representation {

  private String operationId;
  private String operationName;
  private String operationDescription;
  private List<OperationParameterRepresentation> operationParameters =
      new ArrayList<OperationParameterRepresentation>();

  private transient VOTechnicalServiceOperation vo;

  public OperationRepresentation() {
    this(new VOTechnicalServiceOperation());
  }

  public OperationRepresentation(VOTechnicalServiceOperation o) {
    vo = o;
  }

  @Override
  public void validateContent() throws WebApplicationException {}

  @Override
  public void update() {
    if (getId() != null) {
      vo.setKey(convertIdToKey());
    }
    if (getETag() != null) {
      vo.setVersion(convertETagToVersion());
    }
    vo.setOperationDescription(getOperationDescription());
    vo.setOperationId(getOperationId());
    vo.setOperationName(getOperationName());
    vo.setOperationParameters(updateParameters());
  }

  private List<VOServiceOperationParameter> updateParameters() {
    List<VOServiceOperationParameter> result = new ArrayList<VOServiceOperationParameter>();
    if (operationParameters == null) {
      return result;
    }
    for (OperationParameterRepresentation op : operationParameters) {
      op.update();
      result.add(op.getVO());
    }
    return result;
  }

  @Override
  public void convert() {
    setId(Long.valueOf(vo.getKey()));
    setOperationDescription(vo.getOperationDescription());
    setOperationId(vo.getOperationId());
    setOperationName(vo.getOperationName());
    setOperationParameters(convertParameters());
    setETag(Long.valueOf(vo.getVersion()));
  }

  private List<OperationParameterRepresentation> convertParameters() {
    List<OperationParameterRepresentation> result =
        new ArrayList<OperationParameterRepresentation>();
    if (vo.getOperationParameters() == null) {
      return result;
    }
    for (VOServiceOperationParameter op : vo.getOperationParameters()) {
      OperationParameterRepresentation opr = new OperationParameterRepresentation(op);
      opr.convert();
      result.add(opr);
    }
    return result;
  }

  public VOTechnicalServiceOperation getVO() {
    return vo;
  }

  public String getOperationId() {
    return operationId;
  }

  public void setOperationId(String operationId) {
    this.operationId = operationId;
  }

  public String getOperationName() {
    return operationName;
  }

  public void setOperationName(String operationName) {
    this.operationName = operationName;
  }

  public String getOperationDescription() {
    return operationDescription;
  }

  public void setOperationDescription(String operationDescription) {
    this.operationDescription = operationDescription;
  }

  public List<OperationParameterRepresentation> getOperationParameters() {
    return operationParameters;
  }

  public void setOperationParameters(List<OperationParameterRepresentation> operationParameters) {
    this.operationParameters = operationParameters;
  }

  public static List<OperationRepresentation> convert(List<VOTechnicalServiceOperation> ops) {
    if (ops == null || ops.isEmpty()) {
      return null;
    }
    List<OperationRepresentation> result = new ArrayList<OperationRepresentation>();
    for (VOTechnicalServiceOperation op : ops) {
      OperationRepresentation or = new OperationRepresentation(op);
      or.convert();
      result.add(or);
    }
    return result;
  }
}
