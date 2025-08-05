package com.virtuallibrary.VirtualLibrary.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.virtuallibrary.VirtualLibrary.exceptions.Book.AuthorNotFoundException;
import com.virtuallibrary.VirtualLibrary.exceptions.Book.BookIdNotFoundException;
import com.virtuallibrary.VirtualLibrary.exceptions.Book.TitleNotFoundException;
import com.virtuallibrary.VirtualLibrary.exceptions.User.MissingFieldsException;
import com.virtuallibrary.VirtualLibrary.exceptions.User.UserIdNotFoundException;
import com.virtuallibrary.VirtualLibrary.exceptions.UserBook.UserBookNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    // Not Found Exception
    @ExceptionHandler({ 
        BookIdNotFoundException.class,
        AuthorNotFoundException.class,
        TitleNotFoundException.class,
        UserIdNotFoundException.class,
        UserBookNotFoundException.class
    })
    public ResponseEntity<ErrorResponse> handleNotFoundException(RuntimeException ex) {
        ErrorResponse error = new ErrorResponse(
            HttpStatus.NOT_FOUND.value(),
            ex.getMessage(),
            LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    // Book Exceptions

    // User Exception
    @ExceptionHandler(MissingFieldsException.class)
    public ResponseEntity<ErrorResponse> handleMissingFieldsException(MissingFieldsException ex) {
        ErrorResponse error = new ErrorResponse(
            HttpStatus.BAD_REQUEST.value(),
            ex.getMessage(),
            LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    // Generic Exception
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneralException(RuntimeException ex) {
        ErrorResponse error = new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                ex.getMessage(),
                LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}
