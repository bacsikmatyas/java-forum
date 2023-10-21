package dev.bacsikm.javaforum.service.user.DO;

import lombok.Data;

@Data
public class UserDO {
    private long id;
    private String password;
    private String roles;
    private String username;
}
