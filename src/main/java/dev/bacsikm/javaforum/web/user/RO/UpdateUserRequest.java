package dev.bacsikm.javaforum.web.user.RO;

import lombok.Data;

@Data
public class UpdateUserRequest {
    private long id;
    private String username;
    private String password;
}
