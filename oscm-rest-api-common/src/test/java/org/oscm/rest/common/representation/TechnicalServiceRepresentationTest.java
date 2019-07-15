package org.oscm.rest.common.representation;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.oscm.internal.types.enumtypes.ServiceAccessType;
import org.oscm.internal.vo.*;
import org.oscm.rest.common.TestContants;

import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.oscm.rest.common.TestContants.PARAMETER_DEFINITION_VOS;

public class TechnicalServiceRepresentationTest {

        private TechnicalServiceRepresentation representation;
        private VOTechnicalService vo;

        @Test
        public void shouldUpdateVO() {
             representation = new TechnicalServiceRepresentation();

             representation.setAccessInfo(TestContants.ACCESS_INFO);
             representation.setAccessType(TestContants.ACCESS_TYPE);
             representation.setBaseUrl(TestContants.BASE_URL);
             representation.setBillingIdentifier(TestContants.BILLING_ID);
             representation.setEventDefinitions(TestContants.EVENT_DEFINITIONS);
             representation.setExternalBilling(TestContants.IS_EXTERNAL_BILLING);
             representation.setId(TestContants.ID);
             representation.setLicense(TestContants.LICENSE);
             representation.setLoginPath(TestContants.LOGIN_PATH);
             representation.setParameterDefinitions(TestContants.PARAMETER_DEFINITIONS);
             representation.setProvisioningUrl(TestContants.PROVISIONING_URL);
             representation.setProvisioningVersion(TestContants.PROVISIONING_VERSION);
             representation.setRoleDefinitions(TestContants.ROLE_DEFINITIONS);
             representation.setTags(TestContants.TAGS);
             representation.setTechnicalServiceBuildId(TestContants.TS_BUILD_ID);
             representation.setTechnicalServiceDescription(TestContants.TS_DESCRIPTION);
             representation.setTechnicalServiceId(TestContants.TS_ID);
             representation.setTechnicalServiceOperations(TestContants.TS_OPERATIONS);
             representation.setETag(TestContants.ETAG);

             representation.update();

             assertThat(representation.getVO()).extracting(VOTechnicalService::getAccessInfo).isEqualTo(
                     TestContants.ACCESS_INFO);
             assertThat(representation.getVO()).extracting(VOTechnicalService::getAccessType).isEqualTo(
                     TestContants.ACCESS_TYPE);
             assertThat(representation.getVO()).extracting(VOTechnicalService::getBaseUrl).isEqualTo(
                     TestContants.BASE_URL);
             assertThat(representation.getVO()).extracting(VOTechnicalService::getBillingIdentifier).isEqualTo(
                     TestContants.BILLING_ID);
             assertThat(representation.getVO()).extracting(v -> {
                     return v.getEventDefinitions().size();
             }).isEqualTo(2);
             assertThat(representation.getVO()).extracting(VOTechnicalService::isExternalBilling).isEqualTo(
                     TestContants.IS_EXTERNAL_BILLING);
             assertThat(representation.getVO()).extracting(VOTechnicalService::getKey).isEqualTo(
                     TestContants.ID);
             assertThat(representation.getVO()).extracting(VOTechnicalService::getLicense).isEqualTo(
                     TestContants.LICENSE);
             assertThat(representation.getVO()).extracting(VOTechnicalService::getLoginPath).isEqualTo(
                     TestContants.LOGIN_PATH);
             assertThat(representation.getVO()).extracting(v -> {
                     return v.getEventDefinitions().size();
             }).isEqualTo(2);
             assertThat(representation.getVO()).extracting(VOTechnicalService::getProvisioningUrl).isEqualTo(
                     TestContants.PROVISIONING_URL);
             assertThat(representation.getVO()).extracting(VOTechnicalService::getProvisioningVersion).isEqualTo(
                     TestContants.PROVISIONING_VERSION);
             assertThat(representation.getVO()).extracting(v -> {
                     return v.getEventDefinitions().size();
             }).isEqualTo(2);
             assertThat(representation.getVO()).extracting(VOTechnicalService::getTags).isEqualTo(
                     TestContants.TAGS);
             assertThat(representation.getVO()).extracting(VOTechnicalService::getTechnicalServiceBuildId).isEqualTo(
                     TestContants.TS_BUILD_ID);
             assertThat(representation.getVO()).extracting(VOTechnicalService::getTechnicalServiceDescription).isEqualTo(
                     TestContants.TS_DESCRIPTION);
             assertThat(representation.getVO()).extracting(VOTechnicalService::getTechnicalServiceId).isEqualTo(
                     TestContants.TS_ID);
             assertThat(representation.getVO()).extracting(v -> {
                     return v.getEventDefinitions().size();
             }).isEqualTo(2);
             assertThat(representation.getVO()).extracting(VOTechnicalService::getVersion).isEqualTo(
                     TestContants.VERSION);
        }

        @Test
        public void shouldConvert() {
                vo = new VOTechnicalService();
                vo.setAccessInfo(TestContants.ACCESS_INFO);
                vo.setAccessType(TestContants.ACCESS_TYPE);
                vo.setBaseUrl(TestContants.BASE_URL);
                vo.setBillingIdentifier(TestContants.BILLING_ID);
                vo.setEventDefinitions(TestContants.EVENT_DEFINITION_VOS);
                vo.setExternalBilling(TestContants.IS_EXTERNAL_BILLING);
                vo.setKey(TestContants.ID);
                vo.setLicense(TestContants.LICENSE);
                vo.setLoginPath(TestContants.LOGIN_PATH);
                vo.setParameterDefinitions(TestContants.PARAMETER_DEFINITION_VOS);
                vo.setProvisioningUrl(TestContants.PROVISIONING_URL);
                vo.setRoleDefinitions(TestContants.ROLE_DEFINITION_VOS);
                vo.setVersion(TestContants.VERSION);
                vo.setTags(TestContants.TAGS);
                vo.setTechnicalServiceBuildId(TestContants.TS_BUILD_ID);
                vo.setTechnicalServiceDescription(TestContants.TS_DESCRIPTION);
                vo.setTechnicalServiceId(TestContants.TS_ID);
                vo.setTechnicalServiceOperations(TestContants.TS_OPERATION_VOS);

                representation = new TechnicalServiceRepresentation(vo);
                representation.convert();

                assertThat(representation).extracting(TechnicalServiceRepresentation::getAccessInfo).isEqualTo(TestContants.ACCESS_INFO);
                assertThat(representation).extracting(TechnicalServiceRepresentation::getAccessType).isEqualTo(TestContants.ACCESS_TYPE);
                assertThat(representation).extracting(TechnicalServiceRepresentation::getBaseUrl).isEqualTo(TestContants.BASE_URL);
                assertThat(representation).extracting(TechnicalServiceRepresentation::getBillingIdentifier).isEqualTo(TestContants.BILLING_ID);
                assertThat(representation).extracting(r -> {
                        return r.getEventDefinitions().size();
                }).isEqualTo(2);
                assertThat(representation).extracting(TechnicalServiceRepresentation::isExternalBilling).isEqualTo(TestContants.IS_EXTERNAL_BILLING);
                assertThat(representation).extracting(TechnicalServiceRepresentation::getId).isEqualTo(TestContants.ID);
                assertThat(representation).extracting(TechnicalServiceRepresentation::getLicense).isEqualTo(TestContants.LICENSE);
                assertThat(representation).extracting(TechnicalServiceRepresentation::getLoginPath).isEqualTo(TestContants.LOGIN_PATH);
                assertThat(representation).extracting(r -> {
                        return r.getParameterDefinitions().size();
                }).isEqualTo(2);
                assertThat(representation).extracting(TechnicalServiceRepresentation::getProvisioningUrl).isEqualTo(TestContants.PROVISIONING_URL);
                assertThat(representation).extracting(r -> {
                        return r.getRoleDefinitions().size();
                }).isEqualTo(2);
                assertThat(representation).extracting(TechnicalServiceRepresentation::getTags).isEqualTo(TestContants.TAGS);
                assertThat(representation).extracting(TechnicalServiceRepresentation::getTechnicalServiceBuildId).isEqualTo(TestContants.TS_BUILD_ID);
                assertThat(representation).extracting(TechnicalServiceRepresentation::getTechnicalServiceDescription).isEqualTo(TestContants.TS_DESCRIPTION);
                assertThat(representation).extracting(TechnicalServiceRepresentation::getTechnicalServiceId).isEqualTo(TestContants.TS_ID);
                assertThat(representation).extracting(r -> {
                        return r.getTechnicalServiceOperations().size();
                }).isEqualTo(2);
        }


        @Test
        public void shouldGetRepresentationCollection_givenListOfVOs() {
                representation = new TechnicalServiceRepresentation();
                List<VOTechnicalService> inputList = Lists.newArrayList(new VOTechnicalService());

                Collection<TechnicalServiceRepresentation> result = representation.toCollection(inputList);

                assertThat(result).isNotNull();
                assertThat(result.size()).isEqualTo(1);
        }
}