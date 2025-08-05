package com.virtuallibrary.VirtualLibrary.exceptions.User;

public class UserIdNotFoundException extends RuntimeException {
    public UserIdNotFoundException(Long id) {
        super("User with ID " + id + " not found");
    }
}
