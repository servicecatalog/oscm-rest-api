package org.oscm.rest.subscription.data;

import org.junit.jupiter.api.Test;
import org.oscm.internal.vo.*;
import org.oscm.rest.common.representation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PricedParameterRepresentationTest {

    @Test
    public void shouldUpdateVOPricedParameter() {
        PricedParameterRepresentation representation = createRepresentation();
        representation.setETag(100L);
        representation.setId(100L);

        representation.update();
        VOPricedParameter result = representation.getVO();

        assertThat(result).isNotNull();
        assertThat(result).extracting(BaseVO::getKey).isEqualTo(representation.convertIdToKey());
        assertThat(result)
                .extracting(VOPricedParameter::getParameterKey)
                .isEqualTo(representation.getParameterKey());
        assertThat(((VOPricedOption) result.getPricedOptions().toArray()[0]).getParameterOptionKey())
                .isEqualTo(
                        ((PricedOptionRepresentation) representation.getPricedOptions().toArray()[0])
                                .getParameterOptionKey());
        assertThat(result)
                .extracting(VOPricedParameter::getPricePerUser)
                .isEqualTo(representation.getPricePerUser());
        assertThat(result)
                .extracting(VOPricedParameter::getPricePerSubscription)
                .isEqualTo(representation.getPricePerSubscription());
        assertThat(result)
                .extracting(VOPricedParameter::getPricePerUser)
                .isEqualTo(representation.getPricePerUser());
        assertThat(((VOPricedRole) result.getRoleSpecificUserPrices().toArray()[0]).getPricePerUser())
                .isEqualTo(
                        ((PricedRoleRepresentation) representation.getRoleSpecificUserPrices().toArray()[0])
                                .getPricePerUser());
        assertThat(((VOSteppedPrice) result.getSteppedPrices().toArray()[0]).getLimit())
                .isEqualTo(
                        ((SteppedPriceRepresentation) representation.getSteppedPrices().toArray()[0])
                                .getLimit());
        assertThat(result)
                .extracting(BaseVO::getVersion)
                .isEqualTo(representation.convertETagToVersion());
    }

    @Test
    public void shouldUpdateVOPricedParameter_evenIfIdAndETagIsNull() {
        PricedParameterRepresentation representation = createRepresentation();

        representation.update();
        VOPricedParameter result = representation.getVO();

        assertThat(result).isNotNull();
        assertThat(result).extracting(BaseVO::getKey).isEqualTo(representation.convertIdToKey());
        assertThat(result)
                .extracting(VOPricedParameter::getParameterKey)
                .isEqualTo(representation.getParameterKey());
        assertThat(((VOPricedOption) result.getPricedOptions().toArray()[0]).getParameterOptionKey())
                .isEqualTo(
                        ((PricedOptionRepresentation) representation.getPricedOptions().toArray()[0])
                                .getParameterOptionKey());
        assertThat(result)
                .extracting(VOPricedParameter::getPricePerUser)
                .isEqualTo(representation.getPricePerUser());
        assertThat(result)
                .extracting(VOPricedParameter::getPricePerSubscription)
                .isEqualTo(representation.getPricePerSubscription());
        assertThat(result)
                .extracting(VOPricedParameter::getPricePerUser)
                .isEqualTo(representation.getPricePerUser());
        assertThat(((VOPricedRole) result.getRoleSpecificUserPrices().toArray()[0]).getPricePerUser())
                .isEqualTo(
                        ((PricedRoleRepresentation) representation.getRoleSpecificUserPrices().toArray()[0])
                                .getPricePerUser());
        assertThat(((VOSteppedPrice) result.getSteppedPrices().toArray()[0]).getLimit())
                .isEqualTo(
                        ((SteppedPriceRepresentation) representation.getSteppedPrices().toArray()[0])
                                .getLimit());
        assertThat(result)
                .extracting(BaseVO::getVersion)
                .isEqualTo(representation.convertETagToVersion());
    }

    private PricedParameterRepresentation createRepresentation() {
        PricedParameterRepresentation representation = new PricedParameterRepresentation();
        representation.setParameterKey(100L);
        List<PricedOptionRepresentation> list = new ArrayList<>();
        PricedOptionRepresentation pricedOptionRepresentation = new PricedOptionRepresentation();
        pricedOptionRepresentation.setParameterOptionKey(123L);
        list.add(pricedOptionRepresentation);
        List<PricedRoleRepresentation> list2 = new ArrayList<>();
        PricedRoleRepresentation pricedRoleRepresentation = new PricedRoleRepresentation();
        pricedRoleRepresentation.setPricePerUser(BigDecimal.ONE);
        list2.add(pricedRoleRepresentation);
        pricedOptionRepresentation.setRoleSpecificUserPrices(list2);
        representation.setPricedOptions(list);
        representation.setPricePerSubscription(BigDecimal.TEN);
        representation.setPricePerUser(BigDecimal.TEN);
        representation.setRoleSpecificUserPrices(list2);
        List<SteppedPriceRepresentation> list3 = new ArrayList<>();
        SteppedPriceRepresentation steppedPriceRepresentation = new SteppedPriceRepresentation();
        steppedPriceRepresentation.setLimit(456L);
        list3.add(steppedPriceRepresentation);
        representation.setSteppedPrices(list3);
        ParameterDefinitionRepresentation parameterDefinitionRepresentation =
                new ParameterDefinitionRepresentation();
        representation.setParameterDef(parameterDefinitionRepresentation);
        return representation;
    }
}
