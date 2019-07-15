/**
 * *****************************************************************************
 *
 * <p>Copyright FUJITSU LIMITED 2019
 *
 * <p>Creation Date: July 15, 2019
 *
 * <p>*****************************************************************************
 */
package org.oscm.rest.common;

import com.google.common.collect.Lists;
import org.oscm.internal.types.enumtypes.ServiceAccessType;
import org.oscm.internal.vo.VOEventDefinition;
import org.oscm.internal.vo.VOParameterDefinition;
import org.oscm.internal.vo.VORoleDefinition;
import org.oscm.internal.vo.VOTechnicalServiceOperation;
import org.oscm.rest.common.representation.EventDefinitionRepresentation;
import org.oscm.rest.common.representation.OperationRepresentation;
import org.oscm.rest.common.representation.ParameterDefinitionRepresentation;
import org.oscm.rest.common.representation.RoleDefinitionRepresentation;

import java.util.List;

/**
 * Constant class for tests
 */
public class TestContants {

        public static final Long LONG_VALUE = 123L;
        public static final String STRING_VALUE = "password";
        public static final String STRING_NUM_VALUE = "123";
        public static final int INTEGER_VALUE = 123;

        public enum OrganizationRegistrationMode {
                DEFAULT,
                SELF_REGISTRATION,
                KNOWN_CUSTOMER;
        }

        public static final String ACCESS_INFO = "ACCESS_INFO";
        public static final ServiceAccessType ACCESS_TYPE = ServiceAccessType.DIRECT;
        public static final String BASE_URL = "BASE_URL";
        public static final String BILLING_ID = "BILLING_ID";
        public static final List<EventDefinitionRepresentation> EVENT_DEFINITIONS = Lists
                .newArrayList(new EventDefinitionRepresentation(), new EventDefinitionRepresentation());
        public static final boolean IS_EXTERNAL_BILLING = true;
        public static final Long ID = 123L;
        public static final String LICENSE = "LICENSE";
        public static final String LOGIN_PATH = "loginpath";
        public static final List<ParameterDefinitionRepresentation> PARAMETER_DEFINITIONS = Lists.newArrayList(new ParameterDefinitionRepresentation(), new ParameterDefinitionRepresentation());
        public static final String PROVISIONING_URL = "provUrl";
        public static final String PROVISIONING_VERSION = "provVer";
        public static final List<RoleDefinitionRepresentation> ROLE_DEFINITIONS = Lists.newArrayList(new RoleDefinitionRepresentation(), new RoleDefinitionRepresentation());
        public static final List<String> TAGS = Lists.newArrayList("tag");
        public static final String TS_BUILD_ID = "TS_BUILD_ID";
        public static final String TS_DESCRIPTION = "TS_DESCRIPTION";
        public static final String TS_ID = "TS_ID";
        public static final List<OperationRepresentation> TS_OPERATIONS = Lists.newArrayList(new OperationRepresentation(), new OperationRepresentation());
        public static final Long ETAG = 123L;
        public static final int VERSION = 123;
        public static final List<VOEventDefinition> EVENT_DEFINITION_VOS = Lists.newArrayList(new VOEventDefinition(), new VOEventDefinition());
        public static final List<VOParameterDefinition> PARAMETER_DEFINITION_VOS = Lists
                .newArrayList(new VOParameterDefinition(), new VOParameterDefinition());
        public static final List<VORoleDefinition> ROLE_DEFINITION_VOS = Lists.newArrayList(new VORoleDefinition(), new VORoleDefinition());
        public static final List<VOTechnicalServiceOperation> TS_OPERATION_VOS = Lists.newArrayList(new VOTechnicalServiceOperation(), new VOTechnicalServiceOperation());
}
