package org.oscm.rest.common.representation;

import org.junit.jupiter.api.Test;
import org.oscm.internal.types.enumtypes.SubscriptionStatus;
import org.oscm.internal.vo.*;
import org.oscm.rest.common.TestContants;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SubscriptionDetailsRepresentationTest {

    @Test
    public void shouldConvertToVOSubscriptionDetails() {
        VOSubscriptionDetails voSubscriptionDetails = createVO();

        SubscriptionDetailsRepresentation representation = new SubscriptionDetailsRepresentation(voSubscriptionDetails);
        representation.convert();

        assertThat(representation)
                .extracting(SubscriptionDetailsRepresentation::getActivationDate)
                .isEqualTo(voSubscriptionDetails.getActivationDate());
        assertThat(representation)
                .extracting(SubscriptionDetailsRepresentation::getCreationDate)
                .isEqualTo(voSubscriptionDetails.getCreationDate());
        assertThat(representation)
                .extracting(SubscriptionDetailsRepresentation::getDeactivationDate)
                .isEqualTo(voSubscriptionDetails.getDeactivationDate());
        assertThat(representation)
                .extracting(SubscriptionDetailsRepresentation::getNumberOfAssignedUsers)
                .isEqualTo(voSubscriptionDetails.getNumberOfAssignedUsers());
        assertThat(representation)
                .extracting(SubscriptionDetailsRepresentation::getProvisioningProgress)
                .isEqualTo(voSubscriptionDetails.getProvisioningProgress());
        assertThat(representation)
                .extracting(SubscriptionDetailsRepresentation::getStatus)
                .isEqualTo(voSubscriptionDetails.getStatus());
        assertThat(representation)
                .extracting(SubscriptionDetailsRepresentation::getSubscriptionId)
                .isEqualTo(voSubscriptionDetails.getSubscriptionId());
        assertThat(representation)
                .extracting(SubscriptionDetailsRepresentation::getSuccessInfo)
                .isEqualTo(voSubscriptionDetails.getSuccessInfo());
        assertThat(representation)
                .extracting(SubscriptionDetailsRepresentation::getUnitKey)
                .isEqualTo(voSubscriptionDetails.getUnitKey());
        assertThat(representation)
                .extracting(SubscriptionDetailsRepresentation::getUnitName)
                .isEqualTo(voSubscriptionDetails.getUnitName());
        assertThat(((UsageLicenseRepresentation) representation.getUsageLicenses().toArray()[0]).getUser().getUserId())
                .isEqualTo(((VOUsageLicense) voSubscriptionDetails.getUsageLicenses().toArray()[0]).getUser().getUserId());
    }

    private VOSubscriptionDetails createVO() {
        VOSubscriptionDetails voSubscriptionDetails = new VOSubscriptionDetails();
        voSubscriptionDetails.setKey(TestContants.LONG_VALUE);
        voSubscriptionDetails.setVersion(TestContants.INTEGER_VALUE);
        voSubscriptionDetails.setActivationDate(TestContants.LONG_VALUE);
        voSubscriptionDetails.setCreationDate(TestContants.LONG_VALUE);
        voSubscriptionDetails.setDeactivationDate(TestContants.LONG_VALUE);
        voSubscriptionDetails.setNumberOfAssignedUsers(TestContants.INTEGER_VALUE);
        VOPriceModel voPriceModel = new VOPriceModel();
        voPriceModel.setKey(TestContants.LONG_VALUE);
        voPriceModel.setVersion(TestContants.INTEGER_VALUE);
        VOService voService = new VOService();
        voService.setKey(TestContants.LONG_VALUE);
        voSubscriptionDetails.setSubscribedService(voService);
        voSubscriptionDetails.setPriceModel(voPriceModel);
        voSubscriptionDetails.setProvisioningProgress(TestContants.STRING_VALUE);
        voSubscriptionDetails.setStatus(SubscriptionStatus.ACTIVE);
        voSubscriptionDetails.setSubscriptionId(TestContants.STRING_VALUE);
        voSubscriptionDetails.setSuccessInfo(TestContants.STRING_VALUE);
        voSubscriptionDetails.setTimeoutMailSent(true);
        voSubscriptionDetails.setUnitKey(TestContants.LONG_VALUE);
        voSubscriptionDetails.setUnitName(TestContants.STRING_VALUE);
        List<VOUsageLicense> licenses = new ArrayList<>();
        VOUsageLicense voUsageLicense = new VOUsageLicense();
        VORoleDefinition voRoleDefinition = new VORoleDefinition();
        voRoleDefinition.setDescription(TestContants.STRING_VALUE);
        voUsageLicense.setRoleDefinition(voRoleDefinition);
        VOUser voUser = new VOUser();
        voUser.setUserId(TestContants.STRING_VALUE);
        voUsageLicense.setUser(voUser);
        licenses.add(voUsageLicense);
        voSubscriptionDetails.setUsageLicenses(licenses);

        return voSubscriptionDetails;
    }
}