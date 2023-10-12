package dev.bacsikm.javaforum.service.user.service;

import dev.bacsikm.javaforum.domain.user.projection.UserInfoProjection;
import dev.bacsikm.javaforum.domain.user.repository.UserRepository;
import dev.bacsikm.javaforum.service.user.DO.UserInfoDO;
import dev.bacsikm.javaforum.service.user.transformer.UserDOTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserEntityService implements UserService {

    public static final String USER_WITH_ID_NOT_FOUND = "User with id: %d not found!";
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
    public UserDO getUser(long id) {
        Optional<User> userOptional = userRepository.findById(id);
        User user = userOptional.orElseThrow(() -> new UserNotFoundException(String.format(USER_WITH_ID_NOT_FOUND, id)));
        return userDOTransformer.from(user);
    }
}
