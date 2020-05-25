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

import lombok.Getter;
import lombok.Setter;
import org.oscm.internal.vo.VOService;
import org.oscm.rest.common.validator.RequiredFieldValidator;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ServiceCreateRepresentation extends Representation {

  private String technicalServiceId;
  private String serviceId;
  private String name;
  private String description;
  private String shortDescription;
  private String configuratorUrl;
  private String customTabUrl;
  private String customTabName;
  private List<ServiceParameterRepresentation> parameters = new ArrayList<>();
  private transient VOService vo = new VOService();

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
    vo.setConfiguratorUrl(getConfiguratorUrl());
    vo.setCustomTabName(getCustomTabName());
    vo.setCustomTabUrl(getCustomTabUrl());
  }
}
