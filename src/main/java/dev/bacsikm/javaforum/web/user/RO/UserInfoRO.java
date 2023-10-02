package dev.bacsikm.javaforum.web.user.RO;

public class UserInfoRO extends UserRO {
    private long numberOfPosts;

    private long numberOfComments;

    public long getNumberOfPosts() {
        return numberOfPosts;
    }

    public void setNumberOfPosts(long numberOfPosts) {
        this.numberOfPosts = numberOfPosts;
    }

    public long getNumberOfComments() {
        return numberOfComments;
    }

    public void setNumberOfComments(long numberOfComments) {
        this.numberOfComments = numberOfComments;
    }
}
