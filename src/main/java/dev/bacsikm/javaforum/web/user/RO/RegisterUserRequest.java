package dev.bacsikm.javaforum.web.user.RO;

import lombok.Data;

@Data
public class RegisterUserRequest {
    private String username;
    private String password;
}
