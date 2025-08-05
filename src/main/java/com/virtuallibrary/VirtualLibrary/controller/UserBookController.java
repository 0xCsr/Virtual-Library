package com.virtuallibrary.VirtualLibrary.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.virtuallibrary.VirtualLibrary.dto.UserBookDTO;
import com.virtuallibrary.VirtualLibrary.model.UserBook;
import com.virtuallibrary.VirtualLibrary.service.UserBookService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;



@RestController
@RequestMapping("/api/userbooks")
@Tag(name = "UserBook Controller", description = "Operations related to userbooks in the virtual library")
public class UserBookController {
    
    public final UserBookService userBookService;

    public UserBookController(UserBookService userBookService) {
        this.userBookService = userBookService;
    }

    @GetMapping
    @Operation(
        summary = "Get all User books",
        description = "Retrieves a list of all user books"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "List of user books retrieved successfully"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(userBookService.findAll());
    }

    @GetMapping("/{userId}")
    @Operation(
        summary = "Get user books from ID",
        description = "Retrieves a list of user books from user ID"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "List of user books retrieved successfully"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<UserBook>> findByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(userBookService.findByUserId(userId));
    }

    @PostMapping
    @Operation(
        summary = "Add a book to user list",
        description = "Add a book to user list with user id and book id + status"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Book added to user list"),
        @ApiResponse(responseCode = "404", description = "User or Book not found"),
        @ApiResponse(responseCode = "400", description = "Internal server error")
    })
    public ResponseEntity<UserBook> addBookToUser(@RequestBody @Valid UserBookDTO dto) {
        UserBook userBook = userBookService.addBookToUser(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(userBook);
    }

    @DeleteMapping("/user/{userId}/book/{bookId}")
    @Operation(
        summary = "Delete a user book",
        description = "Remove a user book from user id and book id"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "User book has been deleted"),
        @ApiResponse(responseCode = "404", description = "Not found"),
        @ApiResponse(responseCode = "400", description = "Internal server error")
    })
    public ResponseEntity<Void> deleteByUserIdAndBookId(@PathVariable Long userId, @PathVariable Long bookId) {
        userBookService.deleteByUserIdAndBookId(userId, bookId);
        return ResponseEntity.noContent().build();
    }
}
