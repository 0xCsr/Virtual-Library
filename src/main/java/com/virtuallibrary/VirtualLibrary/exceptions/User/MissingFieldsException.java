package com.virtuallibrary.VirtualLibrary.exceptions.User;

public class MissingFieldsException extends RuntimeException {
    public MissingFieldsException() {
        super("Please fill in all fields.");
    }
}
