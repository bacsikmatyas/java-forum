package dev.bacsikm.javaforum.service.user.service;

import dev.bacsikm.javaforum.domain.user.entity.User;
import dev.bacsikm.javaforum.domain.user.projection.UserInfoProjection;
import dev.bacsikm.javaforum.domain.user.repository.UserRepository;
import dev.bacsikm.javaforum.service.user.DO.UserDO;
import dev.bacsikm.javaforum.service.user.DO.UserInfoDO;
import dev.bacsikm.javaforum.service.user.exception.IdentityMismatchException;
import dev.bacsikm.javaforum.service.user.exception.UserNotFoundException;
import dev.bacsikm.javaforum.service.user.transformer.UserDOTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserEntityService implements UserService {

    private final UserRepository userRepository;
    private final UserDOTransformer userDOTransformer;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserEntityService(UserRepository userRepository, UserDOTransformer userDOTransformer, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userDOTransformer = userDOTransformer;
        this.passwordEncoder = passwordEncoder;
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

    @Override
    public UserDO registerUser(UserDO userDO) {
        User user = userDOTransformer.to(userDO);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userDOTransformer.from(userRepository.save(user));
    }

    @Override
    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDO updateUser(UserDO userDO) {
        if (userRepository.existsById(userDO.getId())) {
            User user = userDOTransformer.to(userDO);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return userDOTransformer.from(userRepository.save(user));
        }
        else {
            throw new UserNotFoundException("User with id " + userDO.getId() + " does not exist");
        }
    }

    @Override
    public void checkIdentityMatch(String name, long id) {
        if (!userRepository.existsByUsernameAndId(name, id)) {
            throw new IdentityMismatchException("User with id " + id + " trying to modify user with name: " + name);
        }
    }
}
