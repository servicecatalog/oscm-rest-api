package org.oscm.rest.common.representation;

import org.junit.jupiter.api.Test;
import org.oscm.internal.types.enumtypes.SubscriptionStatus;
import org.oscm.internal.vo.*;

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
        voSubscriptionDetails.setKey(100L);
        voSubscriptionDetails.setVersion(100);
        voSubscriptionDetails.setActivationDate(100L);
        voSubscriptionDetails.setCreationDate(105L);
        voSubscriptionDetails.setDeactivationDate(110L);
        voSubscriptionDetails.setNumberOfAssignedUsers(100);
        VOPriceModel voPriceModel = new VOPriceModel();
        voPriceModel.setKey(100L);
        voPriceModel.setVersion(100);
        VOService voService = new VOService();
        voService.setKey(100L);
        voSubscriptionDetails.setSubscribedService(voService);
        voSubscriptionDetails.setPriceModel(voPriceModel);
        voSubscriptionDetails.setProvisioningProgress("100Progress");
        voSubscriptionDetails.setStatus(SubscriptionStatus.ACTIVE);
        voSubscriptionDetails.setSubscriptionId("100ID");
        voSubscriptionDetails.setSuccessInfo("Info100");
        voSubscriptionDetails.setTimeoutMailSent(true);
        voSubscriptionDetails.setUnitKey(100L);
        voSubscriptionDetails.setUnitName("Unit100");
        List<VOUsageLicense> licenses = new ArrayList<>();
        VOUsageLicense voUsageLicense = new VOUsageLicense();
        VORoleDefinition voRoleDefinition = new VORoleDefinition();
        voRoleDefinition.setDescription("Description100");
        voUsageLicense.setRoleDefinition(voRoleDefinition);
        VOUser voUser = new VOUser();
        voUser.setUserId("100U");
        voUsageLicense.setUser(voUser);
        licenses.add(voUsageLicense);
        voSubscriptionDetails.setUsageLicenses(licenses);

        return voSubscriptionDetails;
    }

}