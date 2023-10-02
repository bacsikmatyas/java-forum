package dev.bacsikm.javaforum.web.user.transformer;

import dev.bacsikm.javaforum.service.user.DO.AdditionalUserInfo;
import dev.bacsikm.javaforum.service.user.DO.UserDO;
import dev.bacsikm.javaforum.web.user.RO.UserInfoRO;
import dev.bacsikm.javaforum.web.user.RO.UserRO;
import org.springframework.stereotype.Component;

@Component
public class UserROTransformer {

    public UserDO to(UserRO userRO) {
        UserDO userDO = new UserDO();

        userDO.setId(userRO.getId());
        userDO.setRoles(userRO.getRoles());
        userDO.setUsername(userRO.getUsername());

        return userDO;
    }

    public UserRO from(UserDO userDO) {
        UserRO userRO = new UserRO();

        userRO.setId(userDO.getId());
        userRO.setRoles(userDO.getRoles());
        userRO.setUsername(userDO.getUsername());

        return userRO;
    }

    public UserInfoRO from(UserDO userDO, AdditionalUserInfo additionalUserInfo) {
        UserInfoRO userInfoRO = new UserInfoRO();

        userInfoRO.setId(userDO.getId());
        userInfoRO.setRoles(userDO.getRoles());
        userInfoRO.setUsername(userDO.getUsername());

        userInfoRO.setNumberOfComments(additionalUserInfo.getNumberOfComments());
        userInfoRO.setNumberOfPosts(additionalUserInfo.getNumberOfPosts());

        return userInfoRO;
    }
}
