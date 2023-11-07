package dev.bacsikm.javaforum.web.user.transformer;

import dev.bacsikm.javaforum.service.user.DO.UserDO;
import dev.bacsikm.javaforum.service.user.DO.UserInfoDO;
import dev.bacsikm.javaforum.web.user.RO.UserInfoResponse;
import dev.bacsikm.javaforum.web.user.RO.UserResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserResponseTransformer {

    public UserDO to(UserResponse userResponse) {
        UserDO userDO = new UserDO();

        userDO.setId(userResponse.getId());
        userDO.setUsername(userResponse.getUsername());
        userDO.setPassword(userResponse.getPassword());
        userDO.setRoles(userResponse.getRoles());

        return userDO;
    }

    public UserResponse from(UserDO userDO) {
        UserResponse userResponse = new UserResponse();

        userResponse.setId(userDO.getId());
        userResponse.setUsername(userDO.getUsername());
        userDO.setPassword("--------");
        userResponse.setRoles(userDO.getRoles());

        return userResponse;
    }

    public UserInfoResponse from(UserInfoDO userInfoDO) {
        UserInfoResponse userInfoResponse = new UserInfoResponse();

        userInfoResponse.setId(userInfoDO.getId());
        userInfoResponse.setRoles(userInfoDO.getRoles());
        userInfoResponse.setUsername(userInfoDO.getUsername());
        userInfoResponse.setNumberOfPosts(userInfoDO.getNumberOfPosts());

        return userInfoResponse;
    }

    public List<UserInfoResponse> from(List<UserInfoDO> userInfoDOs) {
        return userInfoDOs.stream().map(this::from).toList();
    }
}
