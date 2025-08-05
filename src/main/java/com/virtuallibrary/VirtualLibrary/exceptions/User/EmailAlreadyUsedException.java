package com.virtuallibrary.VirtualLibrary.exceptions.User;

public class EmailAlreadyUsedException extends RuntimeException {
    public EmailAlreadyUsedException(String email) {
        super("Email: " + email + " is already used. Please enter another one.");
    }
}
