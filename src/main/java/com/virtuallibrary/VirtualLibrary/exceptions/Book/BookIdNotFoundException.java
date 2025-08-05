package com.virtuallibrary.VirtualLibrary.exceptions.Book;

public class BookIdNotFoundException extends RuntimeException {
    public BookIdNotFoundException(Long id) {
        super("Book with ID " + id + " not found.");
    }
}
