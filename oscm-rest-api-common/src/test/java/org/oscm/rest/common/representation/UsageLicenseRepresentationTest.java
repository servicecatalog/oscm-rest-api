/**
 * *****************************************************************************
 *
 * <p>Copyright FUJITSU LIMITED 2020
 *
 * <p>Creation Date: 20-05-2020
 *
 * <p>*****************************************************************************
 */
package org.oscm.rest.common.representation;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.oscm.internal.vo.VORoleDefinition;
import org.oscm.internal.vo.VOUsageLicense;
import org.oscm.internal.vo.VOUser;
import org.oscm.rest.common.TestContants;

public class UsageLicenseRepresentationTest {

  @Test
  public void shouldUpdateVOUsageLicense() {
    UsageLicenseRepresentation usageLicenseRepresentation = createRepresentation();
    usageLicenseRepresentation.setETag(TestContants.LONG_VALUE);
    usageLicenseRepresentation.setId(TestContants.LONG_VALUE);

    usageLicenseRepresentation.update();
    VOUsageLicense result = usageLicenseRepresentation.getVO();

    assertThat(result).isNotNull();
    assertThat(result.getKey()).isEqualTo(usageLicenseRepresentation.getId());
  }

  @Test
  public void shouldConvertToUsageLicenseRepresentation() {
    VOUsageLicense voUsageLicense = createVO();

    UsageLicenseRepresentation representation = new UsageLicenseRepresentation(voUsageLicense);
    representation.convert();

    assertThat(representation.getId()).isEqualTo(voUsageLicense.getKey());
  }

  private VOUsageLicense createVO() {
    VOUsageLicense voUsageLicense = new VOUsageLicense();
    voUsageLicense.setKey(TestContants.LONG_VALUE);
    VOUser voUser = new VOUser();
    voUsageLicense.setUser(voUser);
    VORoleDefinition voRoleDefinition = new VORoleDefinition();
    voRoleDefinition.setDescription(TestContants.STRING_VALUE);
    voUsageLicense.setRoleDefinition(voRoleDefinition);

    return voUsageLicense;
  }

  private UsageLicenseRepresentation createRepresentation() {
    UsageLicenseRepresentation usageLicenseRepresentation = new UsageLicenseRepresentation();
    return usageLicenseRepresentation;
  }
}
