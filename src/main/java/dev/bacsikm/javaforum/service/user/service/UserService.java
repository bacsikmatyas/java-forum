package dev.bacsikm.javaforum.service.user.service;

import dev.bacsikm.javaforum.service.user.DO.RegisterUserDO;
import dev.bacsikm.javaforum.service.user.DO.UserDO;
import dev.bacsikm.javaforum.service.user.DO.UserInfoDO;

import java.util.List;

public interface UserService {
    UserInfoDO getUserInfo(long id);

    List<UserInfoDO> getAllUserInfo();

    UserDO registerUser(RegisterUserDO registerUserDO);

    void deleteUser(long id);

    UserDO updateUser(UserDO userDO);

    void checkIdentityMatch(String name, long id);
}
