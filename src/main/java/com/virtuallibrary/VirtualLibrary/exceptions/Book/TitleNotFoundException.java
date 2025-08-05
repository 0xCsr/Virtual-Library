package com.virtuallibrary.VirtualLibrary.exceptions.Book;

public class TitleNotFoundException extends RuntimeException {
    public TitleNotFoundException(String title) {
        super("Book with title '" + title + "' not found.");
    }
}
