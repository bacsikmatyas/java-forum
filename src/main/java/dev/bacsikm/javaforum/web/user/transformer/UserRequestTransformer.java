package dev.bacsikm.javaforum.web.user.transformer;

import dev.bacsikm.javaforum.service.user.DO.RegisterUserDO;
import dev.bacsikm.javaforum.service.user.DO.UpdateUserDO;
import dev.bacsikm.javaforum.web.user.RO.RegisterUserRequest;
import dev.bacsikm.javaforum.web.user.RO.UpdateUserRequest;
import org.springframework.stereotype.Component;

@Component
public class UserRequestTransformer {
    public RegisterUserDO to(RegisterUserRequest registerUserRequest) {
        RegisterUserDO registerUserDO = new RegisterUserDO();

        registerUserDO.setUsername(registerUserRequest.getUsername());
        registerUserDO.setPassword(registerUserRequest.getPassword());

        return registerUserDO;
    }

    public UpdateUserDO to(UpdateUserRequest updateUserRequest) {
        UpdateUserDO userDO = new UpdateUserDO();

        userDO.setId(updateUserRequest.getId());
        userDO.setUsername(updateUserRequest.getUsername());
        userDO.setPassword(updateUserRequest.getPassword());

        return userDO;
    }
}
