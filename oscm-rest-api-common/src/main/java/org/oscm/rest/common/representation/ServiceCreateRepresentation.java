/**
 * *****************************************************************************
 *
 * <p>Copyright FUJITSU LIMITED 2020
 *
 * <p>Creation Date: 05-05-2020
 *
 * <p>*****************************************************************************
 */
package org.oscm.rest.common.representation;

import org.oscm.internal.vo.VOService;
import org.oscm.rest.common.validator.RequiredFieldValidator;

import java.util.ArrayList;
import java.util.List;

public class ServiceCreateRepresentation extends Representation {

  private String technicalServiceId;
  private String serviceId;
  private String name;
  private String description;
  private String shortDescription;
  private List<ServiceParameterRepresentation> parameters = new ArrayList<>();

  private transient VOService vo = new VOService();

  public String getTechnicalServiceId() {
    return technicalServiceId;
  }

  public void setTechnicalServiceId(String technicalServiceId) {
    this.technicalServiceId = technicalServiceId;
  }

  public String getServiceId() {
    return serviceId;
  }

  public void setServiceId(String serviceId) {
    this.serviceId = serviceId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getShortDescription() {
    return shortDescription;
  }

  public void setShortDescription(String shortDescription) {
    this.shortDescription = shortDescription;
  }

  public List<ServiceParameterRepresentation> getParameters() {
    return parameters;
  }

  public void setParameters(List<ServiceParameterRepresentation> parameters) {
    this.parameters = parameters;
  }

  public VOService getVo() {
    return vo;
  }

  public void setVo(VOService vo) {
    this.vo = vo;
  }

  @Override
  public void validateContent() {
    RequiredFieldValidator validator = new RequiredFieldValidator();
    validator.validateNotBlank("technicalServiceId", technicalServiceId);
    validator.validateNotBlank("serviceId", serviceId);
  }

  @Override
  public void update() {
    vo.setDescription(getDescription());
    vo.setTechnicalId(getTechnicalServiceId());
    vo.setServiceId(getServiceId());
    vo.setName(getName());
    vo.setShortDescription(getShortDescription());
    vo.setDescription(getDescription());
  }
}
