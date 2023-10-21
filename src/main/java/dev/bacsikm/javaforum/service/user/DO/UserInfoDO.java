package dev.bacsikm.javaforum.service.user.DO;

import lombok.Data;

@Data
public class UserInfoDO {
    private long id;
    private String roles;
    private String username;
    private long numberOfPosts;
}
