/**
 * *****************************************************************************
 *
 * <p>Copyright FUJITSU LIMITED 2019
 *
 * <p>Creation Date: 10-04-2019
 *
 * <p>*****************************************************************************
 */
package org.oscm.rest.common.representation;

import com.google.common.collect.Sets;
import org.junit.jupiter.api.Test;
import org.oscm.internal.types.enumtypes.UserRoleType;
import org.oscm.internal.vo.BaseVO;
import org.oscm.internal.vo.VOUser;
import org.oscm.internal.vo.VOUserDetails;
import org.oscm.rest.common.TestContants;

import static org.assertj.core.api.Assertions.assertThat;

public class RolesRepresentationTest {

  @Test
  public void shouldUpdateVOUserDetails() {
    RolesRepresentation representation = createRepresentation();

    representation.update();
    VOUserDetails result = representation.getVO();

    assertThat(result).isNotNull();
    assertThat(result).extracting(BaseVO::getKey).isEqualTo(representation.getId());
    assertThat(result).extracting(VOUser::getUserRoles).isEqualTo(representation.getUserRoles());
    assertThat(result).extracting(r -> (long) r.getVersion()).isEqualTo(representation.getETag());
  }

  @Test
  public void shouldConvertToRolesRepresentation() {
    VOUserDetails voUserDetails = createVO();

    RolesRepresentation representation = new RolesRepresentation(voUserDetails);
    representation.convert();

    assertThat(representation)
        .extracting(Representation::getId)
        .isEqualTo((long) voUserDetails.getKey());
    assertThat(representation)
        .extracting(Representation::getETag)
        .isEqualTo((long) voUserDetails.getVersion());
    assertThat(representation)
        .extracting(RolesRepresentation::getUserRoles)
        .isEqualTo(voUserDetails.getUserRoles());
  }

  private RolesRepresentation createRepresentation() {
    RolesRepresentation representation = new RolesRepresentation();
    representation.setId(TestContants.LONG_VALUE);
    representation.setUserRoles(Sets.newHashSet(UserRoleType.SERVICE_MANAGER));
    representation.setETag(TestContants.LONG_VALUE);
    return representation;
  }

  private VOUserDetails createVO() {
    VOUserDetails voUserDetails = new VOUserDetails();
    voUserDetails.setKey(TestContants.LONG_VALUE);
    voUserDetails.setVersion(TestContants.INTEGER_VALUE);
    voUserDetails.setUserRoles(Sets.newHashSet(UserRoleType.SERVICE_MANAGER));
    return voUserDetails;
  }
}
