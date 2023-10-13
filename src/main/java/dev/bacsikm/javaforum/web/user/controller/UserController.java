package dev.bacsikm.javaforum.web.user.controller;

import dev.bacsikm.javaforum.service.user.DO.UserDO;
import dev.bacsikm.javaforum.service.user.DO.UserInfoDO;
import dev.bacsikm.javaforum.service.user.service.UserEntityService;
import dev.bacsikm.javaforum.web.user.RO.UserInfoRO;
import dev.bacsikm.javaforum.web.user.RO.UserRO;
import dev.bacsikm.javaforum.web.user.transformer.UserROTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final UserEntityService userEntityService;

    private final UserROTransformer userROTransformer;

    @Autowired
    public UserController(UserEntityService userEntityService, UserROTransformer userROTransformer) {
        this.userEntityService = userEntityService;
        this.userROTransformer = userROTransformer;
    }

    @GetMapping("/api/user/get")
    List<UserInfoRO> getAllUserInfo() {
        List<UserInfoDO> allUserInfo = userEntityService.getAllUserInfo();
        return userROTransformer.from(allUserInfo);
    }

    @GetMapping("/api/user/get/{id}")
    UserInfoRO getUserInfo(@PathVariable Long id) {
        UserInfoDO userInfo = userEntityService.getUserInfo(id);
        return userROTransformer.from(userInfo);
    }

    @PostMapping("api/public/user/register")
    UserRO registerUser(@RequestBody UserRO newUser) {
        UserDO userDO = userROTransformer.to(newUser);
        return userROTransformer.from(userEntityService.registerUser(userDO));
    }
}
