package dev.bacsikm.javaforum.service.user.service;

import dev.bacsikm.javaforum.service.user.DO.UserDO;
import dev.bacsikm.javaforum.service.user.DO.UserInfoDO;

import java.util.List;

public interface UserService {
    UserInfoDO getUserInfo(long id);

    List<UserInfoDO> getAllUserInfo();

    UserDO registerUser(UserDO userDO);

    void deleteUser(long id);
}
