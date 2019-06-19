/**
 * *****************************************************************************
 *
 * <p>Copyright FUJITSU LIMITED 2019
 *
 * <p>Creation Date: June 19, 2019
 *
 * <p>*****************************************************************************
 */
package org.oscm.rest.common;

import java.util.Set;

/**
 * Provides interface for module enpoint/resource reigstration
 */
public interface ResourceConfiguration {

        public Set<Class<?>> getClassesToRegister();
}
