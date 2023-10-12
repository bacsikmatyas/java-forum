package dev.bacsikm.javaforum.domain.user.projection;

import java.util.Objects;

public class UserInfoProjection {

    private long id;
    private String roles;
    private String username;

    private long postCount;

    public UserInfoProjection(long id, String roles, String username, long postCount) {
        this.id = id;
        this.roles = roles;
        this.username = username;
        this.postCount = postCount;
    }

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

    public long getPostCount() {
        return postCount;
    }

    public void setPostCount(long postCount) {
        this.postCount = postCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserInfoProjection that = (UserInfoProjection) o;
        return id == that.id && postCount == that.postCount && Objects.equals(roles, that.roles) && Objects.equals(username, that.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, roles, username, postCount);
    }
}
