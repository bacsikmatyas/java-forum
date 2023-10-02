package dev.bacsikm.javaforum.service.user.service;

import dev.bacsikm.javaforum.service.user.DO.AdditionalUserInfo;
import dev.bacsikm.javaforum.service.user.DO.UserDO;

public interface UserService {

    AdditionalUserInfo getAdditionalUserIno(long id);

    UserDO getUser(long id);
}
