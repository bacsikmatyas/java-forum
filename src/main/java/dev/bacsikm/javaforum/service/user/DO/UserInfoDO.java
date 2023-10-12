package dev.bacsikm.javaforum.service.user.DO;

public class UserInfoDO {

    private long id;
    private String roles;
    private String username;
    private long numberOfPosts;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getNumberOfPosts() {
        return numberOfPosts;
    }

    public void setNumberOfPosts(long numberOfPosts) {
        this.numberOfPosts = numberOfPosts;
    }

}
