package dev.bacsikm.javaforum.service.user.service;

import dev.bacsikm.javaforum.domain.user.entity.User;
import dev.bacsikm.javaforum.domain.user.projection.UserInfoProjection;
import dev.bacsikm.javaforum.domain.user.repository.UserRepository;
import dev.bacsikm.javaforum.service.user.DO.RegisterUserDO;
import dev.bacsikm.javaforum.service.user.DO.UpdateUserDO;
import dev.bacsikm.javaforum.service.user.DO.UserDO;
import dev.bacsikm.javaforum.service.user.DO.UserInfoDO;
import dev.bacsikm.javaforum.service.user.exception.IdentityMismatchException;
import dev.bacsikm.javaforum.service.user.exception.UserNotFoundException;
import dev.bacsikm.javaforum.service.user.transformer.UserDOTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntityUserService implements UserService {

    private final UserRepository userRepository;
    private final UserDOTransformer userDOTransformer;
    Logger logger = LoggerFactory.getLogger(EntityUserService.class);

    @Autowired
    public EntityUserService(UserRepository userRepository, UserDOTransformer userDOTransformer) {
        this.userRepository = userRepository;
        this.userDOTransformer = userDOTransformer;
    }

    @Override
    public UserInfoDO getUserInfo(long id) { //TODO: check if user exists
        UserInfoProjection userInfoProjection = userRepository.findUserInfoById(id);
        logger.info("Found user with id {}", id);
        return userDOTransformer.from(userInfoProjection);
    }

    @Override
    public List<UserInfoDO> getAllUserInfo() {
        List<UserInfoProjection> allUserInfo = userRepository.findAllUserInfo();
        logger.info("Found {} users", allUserInfo.size());
        return userDOTransformer.fromList(allUserInfo);
    }

    @Override
    public UserDO registerUser(RegisterUserDO registerUserDO) {
        User savedUser = userRepository.save(userDOTransformer.to(registerUserDO));
        logger.info("Created user with id {}", savedUser.getId());
        return userDOTransformer.from(savedUser);
    }

    @Override
    public void deleteUser(long id) {
        userRepository.deleteById(id);
        logger.info("Deleted user with id {}", id);
    }

    @Override
    public UserDO updateUser(UpdateUserDO updateUserDO) {
        User user = getUserIfExists(updateUserDO.getId());

        user.setUsername(updateUserDO.getUsername());
        user.setPassword(updateUserDO.getPassword());

        User updatedUser = userRepository.save(user);
        logger.info("Updated user with id {}", updatedUser.getId());
        return userDOTransformer.from(updatedUser);
    }

    private User getUserIfExists(long id) {
        logger.info("Checking if user with id {} exists", id);
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User with id " + id + " does not exist"));
    }

    @Override
    public void checkIdentityMatch(String name, long id) {
        logger.info("Checking if user with id {} is the same as user with name {}", id, name);
        if (!userRepository.existsByUsernameAndId(name, id)) {
            throw new IdentityMismatchException("User with id " + id + " trying to modify user with name: " + name);
        }
    }
}
