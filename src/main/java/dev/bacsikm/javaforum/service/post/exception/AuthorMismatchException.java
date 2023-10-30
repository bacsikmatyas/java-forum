package dev.bacsikm.javaforum.service.post.exception;

public class AuthorMismatchException extends RuntimeException {
    public AuthorMismatchException(long id, String author) {
        super(author + " is not the author of post with id: " + id);
    }

    public AuthorMismatchException(String principal) {
        super(principal + " should be the author of the post");
    }
}
