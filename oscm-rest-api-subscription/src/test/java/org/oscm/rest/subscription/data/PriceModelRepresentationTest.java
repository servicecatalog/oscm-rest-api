package org.oscm.rest.subscription.data;

import org.junit.jupiter.api.Test;
import org.oscm.internal.types.enumtypes.PriceModelType;
import org.oscm.internal.types.enumtypes.PricingPeriod;
import org.oscm.internal.vo.*;
import org.oscm.rest.common.representation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PriceModelRepresentationTest {

    @Test
    public void shouldUpdateVOPriceModel() {
        PriceModelRepresentation representation = createRepresentation();
        representation.setId(100L);
        representation.setETag(100L);

        representation.update();
        VOPriceModel result = representation.getVO();

        assertThat(result).isNotNull();
        assertThat(
                ((VOPricedEvent) result.getConsideredEvents().toArray()[0]).getEventPrice())
                .isEqualTo(((PricedEventRepresentation)
                        representation.getConsideredEvents().toArray()[0]).getEventPrice()
                );
        assertThat(result)
                .extracting(VOPriceModel::getCurrencyISOCode)
                .isEqualTo(representation.getCurrencyISOCode());
        assertThat(result)
                .extracting(VOPriceModel::getOneTimeFee)
                .isEqualTo(representation.getOneTimeFee());
        assertThat(result)
                .extracting(VOPriceModel::getPeriod)
                .isEqualTo(representation.getPeriod());
        assertThat(result)
                .extracting(VOPriceModel::getPricePerPeriod)
                .isEqualTo(representation.getPricePerPeriod());
        assertThat(result)
                .extracting(VOPriceModel::getPricePerUserAssignment)
                .isEqualTo(representation.getPricePerUserAssignment());
        assertThat(((VOPricedRole) result.getRoleSpecificUserPrices().toArray()[0]).getPricePerUser())
                .isEqualTo(((PricedRoleRepresentation) representation.getRoleSpecificUserPrices().toArray()[0]).getPricePerUser());
        assertThat(((VOPricedParameter) result.getSelectedParameters().toArray()[0]).getParameterKey())
                .isEqualTo(((PricedParameterRepresentation) representation.getSelectedParameters().toArray()[0]).getParameterKey());
        assertThat(((VOSteppedPrice) result.getSteppedPrices().toArray()[0]).getLimit())
                .isEqualTo(((SteppedPriceRepresentation) representation.getSteppedPrices().toArray()[0]).getLimit());
        assertThat(result)
                .extracting(VOPriceModel::getType)
                .isEqualTo(representation.getType());
    }

    @Test
    public void shouldUpdateVOPriceModel_evenIfIdAndETagIsNull() {
        PriceModelRepresentation representation = createRepresentation();

        representation.update();
        VOPriceModel result = representation.getVO();

        assertThat(result).isNotNull();
        assertThat(
                ((VOPricedEvent) result.getConsideredEvents().toArray()[0]).getEventPrice())
                .isEqualTo(((PricedEventRepresentation)
                        representation.getConsideredEvents().toArray()[0]).getEventPrice()
                );
        assertThat(result)
                .extracting(VOPriceModel::getCurrencyISOCode)
                .isEqualTo(representation.getCurrencyISOCode());
        assertThat(result)
                .extracting(VOPriceModel::getOneTimeFee)
                .isEqualTo(representation.getOneTimeFee());
        assertThat(result)
                .extracting(VOPriceModel::getPeriod)
                .isEqualTo(representation.getPeriod());
        assertThat(result)
                .extracting(VOPriceModel::getPricePerPeriod)
                .isEqualTo(representation.getPricePerPeriod());
        assertThat(result)
                .extracting(VOPriceModel::getPricePerUserAssignment)
                .isEqualTo(representation.getPricePerUserAssignment());
        assertThat(((VOPricedRole) result.getRoleSpecificUserPrices().toArray()[0]).getPricePerUser())
                .isEqualTo(((PricedRoleRepresentation) representation.getRoleSpecificUserPrices().toArray()[0]).getPricePerUser());
        assertThat(((VOPricedParameter) result.getSelectedParameters().toArray()[0]).getParameterKey())
                .isEqualTo(((PricedParameterRepresentation) representation.getSelectedParameters().toArray()[0]).getParameterKey());
        assertThat(((VOSteppedPrice) result.getSteppedPrices().toArray()[0]).getLimit())
                .isEqualTo(((SteppedPriceRepresentation) representation.getSteppedPrices().toArray()[0]).getLimit());
        assertThat(result)
                .extracting(VOPriceModel::getType)
                .isEqualTo(representation.getType());
    }

    @Test
    public void shouldConvertToVOPriceModel() {
        VOPriceModel voPriceModel = createVO();

        PriceModelRepresentation representation = new PriceModelRepresentation(voPriceModel);
        representation.convert();

        assertThat(((PricedEventRepresentation) representation.getConsideredEvents().toArray()[0]).getEventPrice())
                .isEqualTo(((VOPricedEvent) voPriceModel.getConsideredEvents().toArray()[0]).getEventPrice());
        assertThat(representation)
                .extracting(PriceModelRepresentation::getCurrencyISOCode)
                .isEqualTo(voPriceModel.getCurrencyISOCode());
        assertThat(representation)
                .extracting(PriceModelRepresentation::getDescription)
                .isEqualTo(voPriceModel.getDescription());
        assertThat(representation)
                .extracting(PriceModelRepresentation::getOneTimeFee)
                .isEqualTo(voPriceModel.getOneTimeFee());
        assertThat(representation)
                .extracting(PriceModelRepresentation::getPeriod)
                .isEqualTo(voPriceModel.getPeriod());
        assertThat(representation)
                .extracting(PriceModelRepresentation::getPricePerPeriod)
                .isEqualTo(voPriceModel.getPricePerPeriod());
        assertThat(representation)
                .extracting(PriceModelRepresentation::getPricePerUserAssignment)
                .isEqualTo(voPriceModel.getPricePerUserAssignment());
        assertThat(((PricedRoleRepresentation) representation.getRoleSpecificUserPrices().toArray()[0]).getPricePerUser())
                .isEqualTo(((VOPricedRole) voPriceModel.getRoleSpecificUserPrices().toArray()[0]).getPricePerUser());
        assertThat(((PricedParameterRepresentation) representation.getSelectedParameters().toArray()[0]).getParameterKey())
                .isEqualTo(((VOPricedParameter) voPriceModel.getSelectedParameters().toArray()[0]).getParameterKey());
        assertThat(((SteppedPriceRepresentation) representation.getSteppedPrices().toArray()[0]).getLimit())
                .isEqualTo(((VOSteppedPrice) voPriceModel.getSteppedPrices().toArray()[0]).getLimit());
        assertThat(representation)
                .extracting(PriceModelRepresentation::getType)
                .isEqualTo(voPriceModel.getType());

    }

    private VOPriceModel createVO() {
        VOPriceModel voPriceModel = new VOPriceModel();

        List<VOPricedEvent> consideredEvents = new ArrayList<>();
        VOPricedEvent voPricedEvent = new VOPricedEvent();
        voPricedEvent.setEventPrice(BigDecimal.TEN);
        VOEventDefinition voEventDefinition = new VOEventDefinition();
        voPricedEvent.setEventDefinition(voEventDefinition);
        consideredEvents.add(voPricedEvent);
        voPriceModel.setConsideredEvents(consideredEvents);

        voPriceModel.setCurrencyISOCode("ISO100");
        voPriceModel.setDescription("Description");
        voPriceModel.setVersion(100);
        voPriceModel.setKey(100L);
        voPriceModel.setOneTimeFee(BigDecimal.TEN);
        voPriceModel.setPeriod(PricingPeriod.MONTH);
        voPriceModel.setPricePerUserAssignment(BigDecimal.ONE);

        List<VOPricedRole> roleSpecificUserPrices = new ArrayList<>();
        VOPricedRole voPricedRole = new VOPricedRole();
        voPricedRole.setPricePerUser(BigDecimal.ONE);
        VORoleDefinition voRoleDefinition = new VORoleDefinition();
        voRoleDefinition.setDescription("Description");
        voPricedRole.setRole(voRoleDefinition);
        roleSpecificUserPrices.add(voPricedRole);
        voPriceModel.setRoleSpecificUserPrices(roleSpecificUserPrices);

        List<VOPricedParameter> selectedParameters = new ArrayList<>();
        VOPricedParameter voPricedParameter = new VOPricedParameter();
        voPricedParameter.setParameterKey(105L);
        VOParameterDefinition voParameterDefinition = new VOParameterDefinition();
        voParameterDefinition.setKey(100L);
        voPricedParameter.setVoParameterDef(voParameterDefinition);
        selectedParameters.add(voPricedParameter);
        voPriceModel.setSelectedParameters(selectedParameters);

        List<VOSteppedPrice> steppedPrices = new ArrayList<>();
        VOSteppedPrice voSteppedPrice = new VOSteppedPrice();
        voSteppedPrice.setLimit(110L);
        steppedPrices.add(voSteppedPrice);
        voPriceModel.setSteppedPrices(steppedPrices);

        voPriceModel.setType(PriceModelType.FREE_OF_CHARGE);
        return voPriceModel;
    }

    private PriceModelRepresentation createRepresentation() {
        PriceModelRepresentation priceModelRepresentation = new PriceModelRepresentation();

        List<PricedEventRepresentation> consideredEvents = new ArrayList<>();
        PricedEventRepresentation pricedEventRepresentation = new PricedEventRepresentation();
        pricedEventRepresentation.setEventPrice(BigDecimal.TEN);
        consideredEvents.add(pricedEventRepresentation);
        priceModelRepresentation.setConsideredEvents(consideredEvents);

        priceModelRepresentation.setCurrencyISOCode("100ISO");
        priceModelRepresentation.setOneTimeFee(BigDecimal.TEN);
        priceModelRepresentation.setPeriod(PricingPeriod.HOUR);
        priceModelRepresentation.setPricePerPeriod(BigDecimal.ONE);
        priceModelRepresentation.setPricePerUserAssignment(BigDecimal.TEN);

        List<PricedRoleRepresentation> roleSpecificUserPrices = new ArrayList<>();
        PricedRoleRepresentation pricedRoleRepresentation = new PricedRoleRepresentation();
        pricedRoleRepresentation.setPricePerUser(BigDecimal.TEN);
        roleSpecificUserPrices.add(pricedRoleRepresentation);
        priceModelRepresentation.setRoleSpecificUserPrices(roleSpecificUserPrices);

        List<PricedParameterRepresentation> selectedParameters = new ArrayList<>();
        PricedParameterRepresentation pricedParameterRepresentation = new PricedParameterRepresentation();
        pricedParameterRepresentation.setParameterKey(100L);
        selectedParameters.add(pricedParameterRepresentation);
        priceModelRepresentation.setSelectedParameters(selectedParameters);

        List<SteppedPriceRepresentation> steppedPrices = new ArrayList<>();
        SteppedPriceRepresentation steppedPriceRepresentation = new SteppedPriceRepresentation();
        steppedPriceRepresentation.setLimit(100L);
        steppedPrices.add(steppedPriceRepresentation);
        priceModelRepresentation.setSteppedPrices(steppedPrices);

        priceModelRepresentation.setType(PriceModelType.FREE_OF_CHARGE);
        return priceModelRepresentation;
    }

}