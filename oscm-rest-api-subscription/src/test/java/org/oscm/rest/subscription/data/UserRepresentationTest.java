package org.oscm.rest.subscription.data;

import org.junit.jupiter.api.Test;
import org.oscm.internal.vo.BaseVO;
import org.oscm.internal.vo.VOUser;
import org.oscm.rest.common.Representation;

import static org.assertj.core.api.Assertions.assertThat;

public class UserRepresentationTest {

    @Test
    public void shouldUpdateVOUser() {
        UserRepresentation userRepresentation = createRepresentation();
        userRepresentation.setId(100L);
        userRepresentation.setVersion(100);

        userRepresentation.update();
        VOUser result = userRepresentation.getVO();

        assertThat(result).isNotNull();
        assertThat(result).extracting(BaseVO::getKey).isEqualTo(userRepresentation.convertIdToKey());
        assertThat(result).extracting(BaseVO::getVersion).isEqualTo(userRepresentation.convertETagToVersion());
        assertThat(result).extracting(VOUser::getUserId).isEqualTo(userRepresentation.getUserId());
    }

    @Test
    public void shouldUpdateVOUser_evenIfIdAndETagIsNull() {
        UserRepresentation userRepresentation = createRepresentation();

        userRepresentation.update();
        VOUser result = userRepresentation.getVO();

        assertThat(result).isNotNull();
        assertThat(result).extracting(BaseVO::getKey).isEqualTo(userRepresentation.convertIdToKey());
        assertThat(result).extracting(BaseVO::getVersion).isEqualTo(userRepresentation.convertETagToVersion());
        assertThat(result).extracting(VOUser::getUserId).isEqualTo(userRepresentation.getUserId());
    }

    @Test
    public void shouldConvertToUserRepresentation() {
        VOUser voUser = createVO();

        UserRepresentation representation = new UserRepresentation(voUser);
        representation.convert();

        assertThat(representation)
                .extracting(Representation::getId)
                .isEqualTo(voUser.getKey());
        assertThat(representation)
                .extracting(Representation::getETag)
                .isEqualTo((long) voUser.getVersion());
        assertThat(representation)
                .extracting(UserRepresentation::getUserId)
                .isEqualTo(voUser.getUserId());
    }

    private VOUser createVO() {
        VOUser voUser = new VOUser();
        voUser.setUserId("100ID");
        voUser.setKey(100);
        voUser.setVersion(100);

        return voUser;
    }

    private UserRepresentation createRepresentation() {
        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setUserId("100ID");
        return userRepresentation;
    }

}