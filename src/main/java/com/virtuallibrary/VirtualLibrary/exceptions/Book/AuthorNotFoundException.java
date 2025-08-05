package com.virtuallibrary.VirtualLibrary.exceptions.Book;

public class AuthorNotFoundException extends RuntimeException {
    public AuthorNotFoundException(String author) {
        super("Author '" + author + "' not found.");
    }
}
