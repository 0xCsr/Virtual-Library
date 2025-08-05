package com.virtuallibrary.VirtualLibrary.exceptions.UserBook;

public class AlreadyAddedException extends RuntimeException {
    public AlreadyAddedException() {
        super("The book is already in the user list");
    }
}
