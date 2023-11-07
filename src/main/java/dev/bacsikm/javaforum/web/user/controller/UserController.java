package dev.bacsikm.javaforum.web.user.controller;

import dev.bacsikm.javaforum.service.user.DO.RegisterUserDO;
import dev.bacsikm.javaforum.service.user.DO.UpdateUserDO;
import dev.bacsikm.javaforum.service.user.DO.UserDO;
import dev.bacsikm.javaforum.service.user.DO.UserInfoDO;
import dev.bacsikm.javaforum.service.user.service.UserEntityService;
import dev.bacsikm.javaforum.web.user.RO.RegisterUserRequest;
import dev.bacsikm.javaforum.web.user.RO.UpdateUserRequest;
import dev.bacsikm.javaforum.web.user.RO.UserInfoResponse;
import dev.bacsikm.javaforum.web.user.RO.UserResponse;
import dev.bacsikm.javaforum.web.user.transformer.UserRequestTransformer;
import dev.bacsikm.javaforum.web.user.transformer.UserResponseTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
public class UserController {

    private final UserEntityService userEntityService;
    private final UserResponseTransformer userResponseTransformer;
    private final UserRequestTransformer userRequestTransformer;
    Logger logger = LoggerFactory.getLogger(UserController.class);


    @Autowired
    public UserController(UserEntityService userEntityService, UserResponseTransformer userResponseTransformer, UserRequestTransformer userRequestTransformer) {
        this.userEntityService = userEntityService;
        this.userResponseTransformer = userResponseTransformer;
        this.userRequestTransformer = userRequestTransformer;
    }

    @GetMapping("/api/user/get")
    List<UserInfoResponse> getAllUserInfo() {
        logger.info("Getting all users");
        List<UserInfoDO> allUserInfo = userEntityService.getAllUserInfo();
        return userResponseTransformer.from(allUserInfo);
    }

    @GetMapping("/api/user/get/{id}")
    UserInfoResponse getUserInfo(@PathVariable Long id) {
        logger.info("Getting user with id {}", id);
        UserInfoDO userInfo = userEntityService.getUserInfo(id);
        return userResponseTransformer.from(userInfo);
    }

    @PostMapping("api/public/user/register")
    UserResponse registerUser(@RequestBody RegisterUserRequest registerUserRequest) {
        logger.info("Registering user");
        RegisterUserDO registerUserDO = userRequestTransformer.to(registerUserRequest);
        return userResponseTransformer.from(userEntityService.registerUser(registerUserDO));
    }

    @PutMapping("api/user/update")
    UserResponse updateUser(Principal principal, @RequestBody UpdateUserRequest updateUserRequest) {
        logger.info("Updating user");
        userEntityService.checkIdentityMatch(principal.getName(), updateUserRequest.getId());
        UpdateUserDO updateUserDO = userRequestTransformer.to(updateUserRequest);
        return userResponseTransformer.from(userEntityService.updateUser(updateUserDO));
    }

    @DeleteMapping("api/user/delete/{id}")
    void deleteUser(@PathVariable Long id) {
        logger.info("Deleting user with id {}", id);
        userEntityService.deleteUser(id);
    }
}
