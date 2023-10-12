package dev.bacsikm.javaforum.service.user.transformer;

import dev.bacsikm.javaforum.domain.user.entity.User;
import dev.bacsikm.javaforum.domain.user.projection.UserInfoProjection;
import dev.bacsikm.javaforum.service.user.DO.UserDO;
import dev.bacsikm.javaforum.service.user.DO.UserInfoDO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDOTransformer {

    public User to(UserDO userDO) {
        User user = new User();

        user.setId(userDO.getId());
        user.setPassword(userDO.getPassword());
        user.setRoles(userDO.getRoles());
        user.setUsername(userDO.getUsername());

        return user;
    }

    public UserDO from(User user) {
        UserDO userDO = new UserDO();

        userDO.setId(user.getId());
        userDO.setPassword(user.getPassword());
        userDO.setRoles(user.getRoles());
        userDO.setUsername(user.getUsername());

        return userDO;
    }

    public UserInfoDO from(UserInfoProjection userInfoProjection) {
        UserInfoDO userInfoDO = new UserInfoDO();

        userInfoDO.setId(userInfoProjection.getId());
        userInfoDO.setRoles(userInfoProjection.getRoles());
        userInfoDO.setUsername(userInfoProjection.getUsername());
        userInfoDO.setNumberOfPosts(userInfoProjection.getPostCount());

        return userInfoDO;
    }

    public List<UserInfoDO> fromList(List<UserInfoProjection> userInfoProjections) {
        return userInfoProjections.stream().map(this::from).toList();
    }

}
