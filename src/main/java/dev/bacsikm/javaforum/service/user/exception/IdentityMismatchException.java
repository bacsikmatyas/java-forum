package dev.bacsikm.javaforum.service.user.exception;

public class IdentityMismatchException extends RuntimeException {
    public IdentityMismatchException(String message) {
        super(message);
    }
}