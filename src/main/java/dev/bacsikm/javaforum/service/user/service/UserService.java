package dev.bacsikm.javaforum.service.user.service;

import dev.bacsikm.javaforum.service.user.DO.UserInfoDO;

import java.util.List;

public interface UserService {
    UserInfoDO getUserInfo(long id);

    List<UserInfoDO> getAllUserInfo();
}
