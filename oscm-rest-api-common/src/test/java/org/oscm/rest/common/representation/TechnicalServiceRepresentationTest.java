package org.oscm.rest.common.representation;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.oscm.internal.types.enumtypes.ServiceAccessType;
import org.oscm.internal.vo.*;

import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class TechnicalServiceRepresentationTest {

        private TechnicalServiceRepresentation representation;
        private VOTechnicalService vo;

        //FIXME: Use TestDataFactory for new objects(oscm-rest-api#101)
        //FIXME: Use TestParams  Constants class to get this data (oscm-rest-api#101)
        //Test constants
        private static final String ACCESS_INFO = "ACCESS_INFO";
        private static final ServiceAccessType ACCESS_TYPE = ServiceAccessType.DIRECT;
        private static final String BASE_URL = "BASE_URL";
        private static final String BILLING_ID = "BILLING_ID";
        private static final List<EventDefinitionRepresentation> EVENT_DEFINITIONS = Lists.newArrayList(new EventDefinitionRepresentation(), new EventDefinitionRepresentation());
        private static final boolean IS_EXTERNAL_BILLING = true;
        private static final Long ID = 123L;
        private static final String LICENSE = "LICENSE";
        private static final String LOGIN_PATH = "loginpath";
        private static final List<ParameterDefinitionRepresentation> PARAMETER_DEFINITIONS = Lists.newArrayList(new ParameterDefinitionRepresentation(), new ParameterDefinitionRepresentation());
        private static final String PROVISIONING_URL = "provUrl";
        private static final String PROVISIONING_VERSION = "provVer";
        private static final List<RoleDefinitionRepresentation> ROLE_DEFINITIONS = Lists.newArrayList(new RoleDefinitionRepresentation(), new RoleDefinitionRepresentation());
        private static final List<String> TAGS = Lists.newArrayList("tag");
        private static final String TS_BUILD_ID = "TS_BUILD_ID";
        private static final String TS_DESCRIPTION = "TS_DESCRIPTION";
        private static final String TS_ID = "TS_ID";
        private static final List<OperationRepresentation> TS_OPERATIONS = Lists.newArrayList(new OperationRepresentation(), new OperationRepresentation());
        private static final Long ETAG = 123L;
        private static final int VERSION = 123;
        private static final List<VOEventDefinition> EVENT_DEFINITION_VOS = Lists.newArrayList(new VOEventDefinition(), new VOEventDefinition());
        private static final List<VOParameterDefinition> PARAMETER_DEFINITION_VOS = Lists
                .newArrayList(new VOParameterDefinition(), new VOParameterDefinition());
        private static final List<VORoleDefinition> ROLE_DEFINITION_VOS = Lists.newArrayList(new VORoleDefinition(), new VORoleDefinition());
        private static final List<VOTechnicalServiceOperation> TS_OPERATION_VOS = Lists.newArrayList(new VOTechnicalServiceOperation(), new VOTechnicalServiceOperation());

        @Test
        public void shouldUpdateVO() {
             representation = new TechnicalServiceRepresentation();

             representation.setAccessInfo(ACCESS_INFO);
             representation.setAccessType(ACCESS_TYPE);
             representation.setBaseUrl(BASE_URL);
             representation.setBillingIdentifier(BILLING_ID);
             representation.setEventDefinitions(EVENT_DEFINITIONS);
             representation.setExternalBilling(IS_EXTERNAL_BILLING);
             representation.setId(ID);
             representation.setLicense(LICENSE);
             representation.setLoginPath(LOGIN_PATH);
             representation.setParameterDefinitions(PARAMETER_DEFINITIONS);
             representation.setProvisioningUrl(PROVISIONING_URL);
             representation.setProvisioningVersion(PROVISIONING_VERSION);
             representation.setRoleDefinitions(ROLE_DEFINITIONS);
             representation.setTags(TAGS);
             representation.setTechnicalServiceBuildId(TS_BUILD_ID);
             representation.setTechnicalServiceDescription(TS_DESCRIPTION);
             representation.setTechnicalServiceId(TS_ID);
             representation.setTechnicalServiceOperations(TS_OPERATIONS);
             representation.setETag(ETAG);

             representation.update();

             assertThat(representation.getVO()).extracting(VOTechnicalService::getAccessInfo).isEqualTo(
                     ACCESS_INFO);
             assertThat(representation.getVO()).extracting(VOTechnicalService::getAccessType).isEqualTo(
                     ACCESS_TYPE);
             assertThat(representation.getVO()).extracting(VOTechnicalService::getBaseUrl).isEqualTo(
                     BASE_URL);
             assertThat(representation.getVO()).extracting(VOTechnicalService::getBillingIdentifier).isEqualTo(
                     BILLING_ID);
             assertThat(representation.getVO()).extracting(v -> {
                     return v.getEventDefinitions().size();
             }).isEqualTo(2);
             assertThat(representation.getVO()).extracting(VOTechnicalService::isExternalBilling).isEqualTo(
                     IS_EXTERNAL_BILLING);
             assertThat(representation.getVO()).extracting(VOTechnicalService::getKey).isEqualTo(
                     ID);
             assertThat(representation.getVO()).extracting(VOTechnicalService::getLicense).isEqualTo(
                     LICENSE);
             assertThat(representation.getVO()).extracting(VOTechnicalService::getLoginPath).isEqualTo(
                     LOGIN_PATH);
             assertThat(representation.getVO()).extracting(v -> {
                     return v.getEventDefinitions().size();
             }).isEqualTo(2);
             assertThat(representation.getVO()).extracting(VOTechnicalService::getProvisioningUrl).isEqualTo(
                     PROVISIONING_URL);
             assertThat(representation.getVO()).extracting(VOTechnicalService::getProvisioningVersion).isEqualTo(
                     PROVISIONING_VERSION);
             assertThat(representation.getVO()).extracting(v -> {
                     return v.getEventDefinitions().size();
             }).isEqualTo(2);
             assertThat(representation.getVO()).extracting(VOTechnicalService::getTags).isEqualTo(
                     TAGS);
             assertThat(representation.getVO()).extracting(VOTechnicalService::getTechnicalServiceBuildId).isEqualTo(
                     TS_BUILD_ID);
             assertThat(representation.getVO()).extracting(VOTechnicalService::getTechnicalServiceDescription).isEqualTo(
                     TS_DESCRIPTION);
             assertThat(representation.getVO()).extracting(VOTechnicalService::getTechnicalServiceId).isEqualTo(
                     TS_ID);
             assertThat(representation.getVO()).extracting(v -> {
                     return v.getEventDefinitions().size();
             }).isEqualTo(2);
             assertThat(representation.getVO()).extracting(VOTechnicalService::getVersion).isEqualTo(
                     VERSION);
        }

        @Test
        public void shouldConvert() {
                vo = new VOTechnicalService();
                vo.setAccessInfo(ACCESS_INFO);
                vo.setAccessType(ACCESS_TYPE);
                vo.setBaseUrl(BASE_URL);
                vo.setBillingIdentifier(BILLING_ID);
                vo.setEventDefinitions(EVENT_DEFINITION_VOS);
                vo.setExternalBilling(IS_EXTERNAL_BILLING);
                vo.setKey(ID);
                vo.setLicense(LICENSE);
                vo.setLoginPath(LOGIN_PATH);
                vo.setParameterDefinitions(PARAMETER_DEFINITION_VOS);
                vo.setProvisioningUrl(PROVISIONING_URL);
                vo.setRoleDefinitions(ROLE_DEFINITION_VOS);
                vo.setVersion(VERSION);
                vo.setTags(TAGS);
                vo.setTechnicalServiceBuildId(TS_BUILD_ID);
                vo.setTechnicalServiceDescription(TS_DESCRIPTION);
                vo.setTechnicalServiceId(TS_ID);
                vo.setTechnicalServiceOperations(TS_OPERATION_VOS);

                representation = new TechnicalServiceRepresentation(vo);
                representation.convert();

                assertThat(representation).extracting(TechnicalServiceRepresentation::getAccessInfo).isEqualTo(ACCESS_INFO);
                assertThat(representation).extracting(TechnicalServiceRepresentation::getAccessType).isEqualTo(ACCESS_TYPE);
                assertThat(representation).extracting(TechnicalServiceRepresentation::getBaseUrl).isEqualTo(BASE_URL);
                assertThat(representation).extracting(TechnicalServiceRepresentation::getBillingIdentifier).isEqualTo(BILLING_ID);
                assertThat(representation).extracting(r -> {
                        return r.getEventDefinitions().size();
                }).isEqualTo(2);
                assertThat(representation).extracting(TechnicalServiceRepresentation::isExternalBilling).isEqualTo(IS_EXTERNAL_BILLING);
                assertThat(representation).extracting(TechnicalServiceRepresentation::getId).isEqualTo(ID);
                assertThat(representation).extracting(TechnicalServiceRepresentation::getLicense).isEqualTo(LICENSE);
                assertThat(representation).extracting(TechnicalServiceRepresentation::getLoginPath).isEqualTo(LOGIN_PATH);
                assertThat(representation).extracting(r -> {
                        return r.getParameterDefinitions().size();
                }).isEqualTo(2);
                assertThat(representation).extracting(TechnicalServiceRepresentation::getProvisioningUrl).isEqualTo(PROVISIONING_URL);
                assertThat(representation).extracting(r -> {
                        return r.getRoleDefinitions().size();
                }).isEqualTo(2);
                assertThat(representation).extracting(TechnicalServiceRepresentation::getTags).isEqualTo(TAGS);
                assertThat(representation).extracting(TechnicalServiceRepresentation::getTechnicalServiceBuildId).isEqualTo(TS_BUILD_ID);
                assertThat(representation).extracting(TechnicalServiceRepresentation::getTechnicalServiceDescription).isEqualTo(TS_DESCRIPTION);
                assertThat(representation).extracting(TechnicalServiceRepresentation::getTechnicalServiceId).isEqualTo(TS_ID);
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