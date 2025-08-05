package com.virtuallibrary.VirtualLibrary.exceptions.UserBook;

public class UserBookNotFoundException extends RuntimeException {
    public UserBookNotFoundException(Long userId, Long bookId) {
        super("User id " + userId + " and book id " + bookId + " not founded in DB");
    }
}
