package dev.bacsikm.javaforum.web.user.RO;

import lombok.Data;

@Data
public class UserResponse {
    private long id;
    private String username;
    private String password;
    private String roles;
}
