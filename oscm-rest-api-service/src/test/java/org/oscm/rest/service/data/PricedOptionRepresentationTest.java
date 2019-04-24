package org.oscm.rest.service.data;

import org.junit.jupiter.api.Test;
import org.oscm.internal.vo.VOPricedOption;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PricedOptionRepresentationTest {

    @Test
    public void shouldUpdateVOPricedOption() {
        PricedOptionRepresentation representation = new PricedOptionRepresentation();
        representation.setId(100L);
        representation.setOptionId("100");
        representation.setParameterOptionKey(100L);
        representation.setPricePerSubscription(BigDecimal.TEN);
        representation.setPricePerUser(BigDecimal.TEN);
        List<PricedRoleRepresentation> list = new ArrayList<>();
        PricedRoleRepresentation pricedRoleRepresentation =
                new PricedRoleRepresentation();
        pricedRoleRepresentation.setPricePerUser(BigDecimal.ONE);
        list.add(pricedRoleRepresentation);
        representation.setRoleSpecificUserPrices(list);
        representation.setETag(100L);

        representation.update();
        VOPricedOption result = representation.getVO();

        assertThat(result).isNotNull();
    }

}