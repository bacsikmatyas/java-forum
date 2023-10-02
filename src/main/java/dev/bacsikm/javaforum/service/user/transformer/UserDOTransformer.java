package dev.bacsikm.javaforum.service.user.transformer;

import dev.bacsikm.javaforum.domain.user.entity.User;
import dev.bacsikm.javaforum.service.user.DO.UserDO;
import org.springframework.stereotype.Component;

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

}
