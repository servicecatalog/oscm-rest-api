/**
 * *****************************************************************************
 *
 * <p>Copyright FUJITSU LIMITED 2020
 *
 * <p>Creation Date: Mai 30, 2020
 *
 * <p>*****************************************************************************
 */
package org.oscm.rest.common.validator;

import org.junit.jupiter.api.Test;
import org.oscm.internal.types.enumtypes.ParameterValueType;
import org.oscm.internal.vo.VOParameterDefinition;
import org.oscm.internal.vo.VOParameterOption;
import org.oscm.rest.common.CommonParams;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

class ParameterValidatorTest {

  private static final String PARAMETER_ID = "PARAM_ID";
  private static final Function<String, VOParameterOption> PARAMETER_OPTION =
      (optionId) -> new VOParameterOption(optionId, null, null);

  @Test
  public void testValidate_throwsNoException_whenParameterValueIsValid() {

    // given
    ParameterValidator validator = new ParameterValidator();
    VOParameterDefinition definition = new VOParameterDefinition();
    definition.setParameterId(PARAMETER_ID);
    definition.setValueType(ParameterValueType.ENUMERATION);
    definition.setMandatory(true);
    List<VOParameterOption> options =
        Arrays.asList(
            PARAMETER_OPTION.apply("OPTION1"),
            PARAMETER_OPTION.apply("OPTION2"),
            PARAMETER_OPTION.apply("OPTION3"));
    definition.setParameterOptions(options);

    String value = "OPTION2";

    // when
    Throwable thrown = catchThrowable(() -> validator.validate(definition, value));

    // then
    assertThat(thrown).doesNotThrowAnyException();
  }

  @Test
  public void testValidate_throwsException_whenParameterIsMandatoryAndEmpty() {

    // given
    ParameterValidator validator = new ParameterValidator();
    VOParameterDefinition definition = new VOParameterDefinition();
    definition.setParameterId(PARAMETER_ID);
    definition.setMandatory(true);
    String value = "";

    // when
    Throwable thrown = catchThrowable(() -> validator.validate(definition, value));

    // then
    assertThat(thrown).isInstanceOf(BadRequestException.class);

    Response response = ((BadRequestException) thrown).getResponse();
    assertThat(response).extracting(Response::getStatusInfo).isEqualTo(Response.Status.BAD_REQUEST);
    assertThat(response)
        .extracting(Response::getEntity)
        .hasFieldOrPropertyWithValue("errorMessage", CommonParams.ERROR_JSON_FORMAT)
        .hasFieldOrPropertyWithValue(
            "errorDetails",
            "Invalid field parameter: " + PARAMETER_ID + " is required and must not be empty");
  }

  @Test
  public void testValidate_throwsException_whenParameterHasInvalidOption() {

    // given
    VOParameterDefinition definition = new VOParameterDefinition();
    definition.setParameterId(PARAMETER_ID);
    definition.setValueType(ParameterValueType.ENUMERATION);
    List<VOParameterOption> options =
        Arrays.asList(
            PARAMETER_OPTION.apply("OPTION1"),
            PARAMETER_OPTION.apply("OPTION2"),
            PARAMETER_OPTION.apply("OPTION3"));
    definition.setParameterOptions(options);

    String value = "INVALID_OPTION";
    ParameterValidator validator = new ParameterValidator();

    // when
    Throwable thrown =
        catchThrowable(
            () -> {
              validator.validate(definition, value);
            });

    // then
    assertThat(thrown).isInstanceOf(BadRequestException.class);

    Response response = ((BadRequestException) thrown).getResponse();
    assertThat(response).extracting(Response::getStatusInfo).isEqualTo(Response.Status.BAD_REQUEST);
    assertThat(response)
        .extracting(Response::getEntity)
        .hasFieldOrPropertyWithValue("errorMessage", CommonParams.ERROR_JSON_FORMAT)
        .hasFieldOrPropertyWithValue(
            "errorDetails",
            "Invalid field parameter: "
                + PARAMETER_ID
                + " allowed values: "
                + options.stream()
                    .map(option -> option.getOptionId())
                    .collect(Collectors.toList()));
  }
}
