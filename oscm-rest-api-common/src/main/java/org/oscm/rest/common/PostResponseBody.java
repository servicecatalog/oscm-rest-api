/**
 * *****************************************************************************
 *
 * <p>Copyright FUJITSU LIMITED 2019
 *
 * <p>Creation Date: Aug 27, 2019
 *
 * <p>*****************************************************************************
 */
package org.oscm.rest.common;

import lombok.Builder;
import lombok.Value;

/**
 * Wrapper class for presenting data about newly created object to the user
 */
@Value
@Builder(builderMethodName = "of")
public class PostResponseBody {
        private String createdObjectId;
}
