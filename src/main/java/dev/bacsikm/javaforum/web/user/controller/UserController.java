package dev.bacsikm.javaforum.web.user.controller;

import dev.bacsikm.javaforum.service.user.DO.UserInfoDO;
import dev.bacsikm.javaforum.service.user.service.UserEntityService;
import dev.bacsikm.javaforum.web.user.RO.UserInfoRO;
import dev.bacsikm.javaforum.web.user.transformer.UserROTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserEntityService userEntityService;

    private final UserROTransformer userROTransformer;

    @Autowired
    public UserController(UserEntityService userEntityService, UserROTransformer userROTransformer) {
        this.userEntityService = userEntityService;
        this.userROTransformer = userROTransformer;
    }

    @GetMapping("/get/{id}")
    UserInfoRO getUserInfo(@PathVariable Long id) {
        UserInfoDO userInfo = userEntityService.getUserInfo(id);
        return userROTransformer.from(userInfo);
    }
}
