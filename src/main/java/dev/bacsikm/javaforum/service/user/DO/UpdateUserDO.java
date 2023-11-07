package dev.bacsikm.javaforum.service.user.DO;

import lombok.Data;

@Data
public class UpdateUserDO {
    private long id;
    private String username;
    private String password;
}
