package dev.bacsikm.javaforum.service.user.service;

import dev.bacsikm.javaforum.domain.user.projection.UserInfoProjection;
import dev.bacsikm.javaforum.domain.user.repository.UserRepository;
import dev.bacsikm.javaforum.service.user.DO.UserInfoDO;
import dev.bacsikm.javaforum.service.user.transformer.UserDOTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserEntityService implements UserService {

    private final UserRepository userRepository;
    private final UserDOTransformer userDOTransformer;

    @Autowired
    public UserEntityService(UserRepository userRepository, UserDOTransformer userDOTransformer) {
        this.userRepository = userRepository;
        this.userDOTransformer = userDOTransformer;
    }

    @Override
    public UserInfoDO getUserInfo(long id) {
        UserInfoProjection userInfoProjection = userRepository.findUserInfoById(id);
        return userDOTransformer.from(userInfoProjection);
    }

    @Override
    public List<UserInfoDO> getAllUserInfo() {
        List<UserInfoProjection> allUserInfo = userRepository.findAllUserInfo();
        return userDOTransformer.fromList(allUserInfo);
    }
}
