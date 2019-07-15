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
}
