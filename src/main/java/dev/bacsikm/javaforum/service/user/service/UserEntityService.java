package dev.bacsikm.javaforum.service.user.service;

import dev.bacsikm.javaforum.domain.user.entity.User;
import dev.bacsikm.javaforum.domain.user.repository.UserRepository;
import dev.bacsikm.javaforum.service.user.DO.AdditionalUserInfo;
import dev.bacsikm.javaforum.service.user.DO.UserDO;
import dev.bacsikm.javaforum.service.user.exception.UserNotFoundException;
import dev.bacsikm.javaforum.service.user.transformer.UserDOTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
    public AdditionalUserInfo getAdditionalUserIno(long id) {
        AdditionalUserInfo additionalUserInfo = new AdditionalUserInfo();
        additionalUserInfo.setNumberOfComments(getNumberOfComments(id));
        additionalUserInfo.setNumberOfPosts(getNumberOfPosts(id));
        return additionalUserInfo;
    }

    private long getNumberOfPosts(long id) {
        return userRepository.countPosts(id);
    }

    //TODO comment feature
    private long getNumberOfComments(long id) {
        return 0;
    }

    @Override
    public UserDO getUser(long id) {
        Optional<User> userOptional = userRepository.findById(id);
        User user = userOptional.orElseThrow(() -> new UserNotFoundException(String.format(USER_WITH_ID_NOT_FOUND, id)));
        return userDOTransformer.from(user);
    }
}
