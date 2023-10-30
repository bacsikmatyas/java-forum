package dev.bacsikm.javaforum.service.post.exception;

public class PostNotFoundException extends RuntimeException {
    public PostNotFoundException(long id) {
        super("Post not found: " + id);
    }
}
