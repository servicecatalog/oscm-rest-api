/**
 * *****************************************************************************
 *
 * <p>Copyright FUJITSU LIMITED 2019
 *
 * <p>Creation Date: 10-04-2019
 *
 * <p>*****************************************************************************
 */
package org.oscm.rest.identity.data;

import com.google.common.collect.Sets;
import org.junit.jupiter.api.Test;
import org.oscm.internal.types.enumtypes.UserRoleType;
import org.oscm.internal.vo.BaseVO;
import org.oscm.internal.vo.VOUser;
import org.oscm.internal.vo.VOUserDetails;
import org.oscm.rest.common.Representation;

import static org.assertj.core.api.Assertions.assertThat;

public class RolesRepresentationTest {

  @Test
  public void shouldUpdateVOUserDetails() {
    RolesRepresentation representation = new RolesRepresentation();
    representation.setId(12345L);
    representation.setUserRoles(Sets.newHashSet(UserRoleType.SERVICE_MANAGER));
    representation.setETag(6789L);

    representation.update();
    VOUserDetails result = representation.getVO();

    assertThat(result).isNotNull();
    assertThat(result).extracting(BaseVO::getKey).isEqualTo(representation.getId());
    assertThat(result).extracting(VOUser::getUserRoles).isEqualTo(representation.getUserRoles());
    assertThat(result).extracting(r -> (long) r.getVersion()).isEqualTo(representation.getETag());
  }

  @Test
  public void shouldConvertToRolesRepresentation() {
    VOUserDetails userDetails = new VOUserDetails();
    userDetails.setKey(1234L);
    userDetails.setVersion(5678);
    userDetails.setUserRoles(Sets.newHashSet(UserRoleType.SERVICE_MANAGER));

    RolesRepresentation representation = new RolesRepresentation(userDetails);
    representation.convert();

    assertThat(representation)
        .extracting(Representation::getId)
        .isEqualTo((long) userDetails.getKey());
    assertThat(representation)
        .extracting(Representation::getETag)
        .isEqualTo((long) userDetails.getVersion());
    assertThat(representation)
        .extracting(RolesRepresentation::getUserRoles)
        .isEqualTo(userDetails.getUserRoles());
  }
}
