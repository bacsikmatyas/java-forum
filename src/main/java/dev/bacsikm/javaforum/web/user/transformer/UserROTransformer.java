package dev.bacsikm.javaforum.web.user.transformer;

import dev.bacsikm.javaforum.service.user.DO.UserDO;
import dev.bacsikm.javaforum.service.user.DO.UserInfoDO;
import dev.bacsikm.javaforum.web.user.RO.UserInfoRO;
import dev.bacsikm.javaforum.web.user.RO.UserRO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserROTransformer {

    public UserDO to(UserRO userRO) {
        UserDO userDO = new UserDO();

        userDO.setId(userRO.getId());
        userDO.setUsername(userRO.getUsername());
        userDO.setPassword(userRO.getPassword());
        userDO.setRoles(userRO.getRoles());

        return userDO;
    }

    public UserRO from(UserDO userDO) {
        UserRO userRO = new UserRO();

        userRO.setId(userDO.getId());
        userRO.setUsername(userDO.getUsername());
        userDO.setPassword("--------");
        userRO.setRoles(userDO.getRoles());

        return userRO;
    }

    public UserInfoRO from(UserInfoDO userInfoDO) {
        UserInfoRO userInfoRO = new UserInfoRO();

        userInfoRO.setId(userInfoDO.getId());
        userInfoRO.setRoles(userInfoDO.getRoles());
        userInfoRO.setUsername(userInfoDO.getUsername());
        userInfoRO.setNumberOfPosts(userInfoDO.getNumberOfPosts());

        return userInfoRO;
    }

    public List<UserInfoRO> from(List<UserInfoDO> userInfoDOs) {
        return userInfoDOs.stream().map(this::from).toList();
    }
}
