package dev.bacsikm.javaforum.web.user.RO;

import lombok.Data;

@Data
public class UserInfoRO {//todo fix this
    private long id;
    private String username;
    private String roles;
    private long numberOfPosts;
}
