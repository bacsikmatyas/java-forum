package dev.bacsikm.javaforum.web.user.RO;

import lombok.Data;

@Data
public class UserInfoResponse {
    private long id;
    private String username;
    private String roles;
    private long numberOfPosts;
}
