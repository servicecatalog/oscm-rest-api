package org.oscm.rest.common.requestparameters;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.oscm.internal.vo.VOService;
import org.oscm.rest.common.MarketplaceListType;
import org.oscm.rest.common.TestContants;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;

public class MarketplaceParametersTest {

        @Test
        public void shouldCreate() {
                MarketplaceParameters parameters = new MarketplaceParameters();
             parameters.setListType(MarketplaceListType.OWNED);
             parameters.setMarketplaceId(TestContants.STRING_VALUE);
             parameters.setServiceKey(TestContants.LONG_VALUE);

             parameters.validateParameters();
             parameters.update();

             assertThat(parameters).extracting(MarketplaceParameters::getListType).isEqualTo(MarketplaceListType.OWNED);
             assertThat(parameters).extracting(MarketplaceParameters::getMarketplaceId).isEqualTo(TestContants.STRING_VALUE);
             assertThat(parameters).extracting(MarketplaceParameters::getServiceKey).isEqualTo(TestContants.LONG_VALUE);
        }

        @Test
        public void shouldGetService() {
                MarketplaceParameters parameters = new MarketplaceParameters();

                VOService result = parameters.getService();

                assertThat(result).isNotNull();
        }

}