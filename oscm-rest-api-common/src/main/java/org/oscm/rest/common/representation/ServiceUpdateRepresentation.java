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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.oscm.internal.vo.VOParameter;
import org.oscm.internal.vo.VOServiceDetails;
import org.oscm.rest.common.validator.ParameterValidator;
import org.oscm.rest.common.validator.RequiredFieldValidator;

@Getter
@Setter
public class ServiceUpdateRepresentation extends Representation {

  private String serviceId;
  private String name;
  private String description;
  private String shortDescription;
  private String configuratorUrl;
  private String customTabUrl;
  private String customTabName;
  private List<ServiceParameterRepresentation> parameters = new ArrayList<>();

  public void update(VOServiceDetails serviceDetails) {
    if (StringUtils.isNotBlank(serviceId)) {
      RequiredFieldValidator validator = new RequiredFieldValidator();
      validator.validateNotBlank("serviceId", serviceId);
      serviceDetails.setServiceId(serviceId);
    }

    if (StringUtils.isNotBlank(name)) {
      serviceDetails.setName(name);
    }

    if (StringUtils.isNotBlank(description)) {
      serviceDetails.setDescription(description);
    }

    if (StringUtils.isNotBlank(shortDescription)) {
      serviceDetails.setShortDescription(shortDescription);
    }
    if (StringUtils.isNotBlank(configuratorUrl)) {
      serviceDetails.setConfiguratorUrl(configuratorUrl);
    }

    if (StringUtils.isNotBlank(customTabName)) {
      serviceDetails.setCustomTabName(customTabName);
    }

    if (StringUtils.isNotBlank(customTabUrl)) {
      serviceDetails.setCustomTabUrl(customTabUrl);
    }

    if (!parameters.isEmpty()) {
      update(serviceDetails.getParameters());
    }
  }

  public void update(List<VOParameter> serviceParameters) {
    ParameterValidator parameterValidator = new ParameterValidator();
    parameters.forEach(
        parameter -> {
          String parameterId = parameter.getParameterId();
          Optional<VOParameter> foundParameter =
              serviceParameters.stream()
                  .filter(
                      serviceParameter ->
                          serviceParameter
                              .getParameterDefinition()
                              .getParameterId()
                              .equals(parameterId))
                  .findFirst();

          if (foundParameter.isPresent()) {
            VOParameter serviceParameter = foundParameter.get();
            serviceParameter.setConfigurable(parameter.isConfigurable());
            serviceParameter.setValue(parameter.getValue());

            if (!serviceParameter.isConfigurable()) {
              parameterValidator.validate(
                  serviceParameter.getParameterDefinition(), serviceParameter.getValue());
            }
          }
        });
  }
}
