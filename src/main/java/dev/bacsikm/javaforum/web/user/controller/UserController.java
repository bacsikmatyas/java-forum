package dev.bacsikm.javaforum.web.user.controller;

import dev.bacsikm.javaforum.service.user.DO.RegisterUserDO;
import dev.bacsikm.javaforum.service.user.DO.UpdateUserDO;
import dev.bacsikm.javaforum.service.user.DO.UserInfoDO;
import dev.bacsikm.javaforum.service.user.service.UserService;
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

    private final UserService userService;
    private final UserResponseTransformer userResponseTransformer;
    private final UserRequestTransformer userRequestTransformer;
    Logger logger = LoggerFactory.getLogger(UserController.class);


    @Autowired
    public UserController(UserService userService, UserResponseTransformer userResponseTransformer, UserRequestTransformer userRequestTransformer) {
        this.userService = userService;
        this.userResponseTransformer = userResponseTransformer;
        this.userRequestTransformer = userRequestTransformer;
    }

    @GetMapping("/api/user/get")
    List<UserInfoResponse> getAllUserInfo() {
        logger.info("Getting all users");
        List<UserInfoDO> allUserInfo = userService.getAllUserInfo();
        return userResponseTransformer.from(allUserInfo);
    }

    @GetMapping("/api/user/get/{id}")
    UserInfoResponse getUserInfo(@PathVariable Long id) {
        logger.info("Getting user with id {}", id);
        UserInfoDO userInfo = userService.getUserInfo(id);
        return userResponseTransformer.from(userInfo);
    }

    @PostMapping("api/public/user/register")
    UserResponse registerUser(@RequestBody RegisterUserRequest registerUserRequest) {
        logger.info("Registering user");
        RegisterUserDO registerUserDO = userRequestTransformer.to(registerUserRequest);
        return userResponseTransformer.from(userService.registerUser(registerUserDO));
    }

    @PutMapping("api/user/update")
    UserResponse updateUser(Principal principal, @RequestBody UpdateUserRequest updateUserRequest) {
        logger.info("Updating user");
        userService.checkIdentityMatch(principal.getName(), updateUserRequest.getId());
        UpdateUserDO updateUserDO = userRequestTransformer.to(updateUserRequest);
        return userResponseTransformer.from(userService.updateUser(updateUserDO));
    }

    @DeleteMapping("api/user/delete/{id}")
    void deleteUser(@PathVariable Long id) {
        logger.info("Deleting user with id {}", id);
        userService.deleteUser(id);
    }
}
