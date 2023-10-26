package dev.bacsikm.javaforum.web.user.controller;

import dev.bacsikm.javaforum.service.user.DO.UserDO;
import dev.bacsikm.javaforum.service.user.DO.UserInfoDO;
import dev.bacsikm.javaforum.service.user.service.UserEntityService;
import dev.bacsikm.javaforum.web.user.RO.UserInfoRO;
import dev.bacsikm.javaforum.web.user.RO.UserRO;
import dev.bacsikm.javaforum.web.user.transformer.UserROTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
public class UserController {

    private final UserEntityService userEntityService;
    private final UserROTransformer userROTransformer;
    Logger logger = LoggerFactory.getLogger(UserController.class);


    @Autowired
    public UserController(UserEntityService userEntityService, UserROTransformer userROTransformer) {
        this.userEntityService = userEntityService;
        this.userROTransformer = userROTransformer;
    }

    @GetMapping("/api/user/get")
    List<UserInfoRO> getAllUserInfo() {
        logger.info("Getting all users");
        List<UserInfoDO> allUserInfo = userEntityService.getAllUserInfo();
        return userROTransformer.from(allUserInfo);
    }

    @GetMapping("/api/user/get/{id}")
    UserInfoRO getUserInfo(@PathVariable Long id) {
        logger.info("Getting user with id {}", id);
        UserInfoDO userInfo = userEntityService.getUserInfo(id);
        return userROTransformer.from(userInfo);
    }

    @PostMapping("api/public/user/register")
    UserRO registerUser(@RequestBody UserRO newUser) {
        logger.info("Registering user");
        UserDO userDO = userROTransformer.to(newUser);
        return userROTransformer.from(userEntityService.registerUser(userDO));
    }

    @DeleteMapping("api/user/delete/{id}")
    void deleteUser(@PathVariable Long id) {
        logger.info("Deleting user with id {}", id);
        userEntityService.deleteUser(id);
    }

    @PutMapping("api/user/update")
    UserRO updateUser(Principal principal, @RequestBody UserRO userRO) {
        userEntityService.checkIdentityMatch(principal.getName(), userRO.getId());
        UserDO userDO = userROTransformer.to(userRO);
        return userROTransformer.from(userEntityService.updateUser(userDO));
    }
}
