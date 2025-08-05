package com.virtuallibrary.VirtualLibrary.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.virtuallibrary.VirtualLibrary.dto.BookDTO;
import com.virtuallibrary.VirtualLibrary.model.Book;
import com.virtuallibrary.VirtualLibrary.service.BookService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/books")
@Tag(name = "Book Controller", description = "Operations related to books in the virtual library")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    @Operation(
        summary = "Get all books",
        description = "Retrieves a list of all books in DB"
    )
    @ApiResponses( value = {
        @ApiResponse(responseCode = "200", description = "List of books retrieved successfully"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(bookService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(
        summary = "Get a book by ID",
        description = "Retrieves a book by ID from the DB"
    )
    @ApiResponses(value = {
        @ApiResponse(
        responseCode = "200",
        description = "Book retrieved successfully",
        content = @Content(schema = @Schema(implementation = Book.class))),
        @ApiResponse(responseCode = "404", description = "Book not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Book> findById(@PathVariable Long id) {
        Book book = bookService.findById(id);
        return ResponseEntity.ok(book);
    }

    @GetMapping("/title/{title}")
    @Operation(
        summary = "Get a book by title",
        description = "Retrieves a book by title from the DB"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Book retrieved successfully",
            content = @Content(schema = @Schema(implementation = Book.class))),
        @ApiResponse(responseCode = "404", description = "Book not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<Book>> findByTitle(@PathVariable String title) {
        List<Book> books = bookService.findByTitle(title);
        return ResponseEntity.ok(books);
    }
    
    @GetMapping("/author/{author}")
    @Operation(
        summary = "Get books by author",
        description = "Retrieves a list of books by a specific author"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Books by author retrieved successfully",
            content = @Content(schema = @Schema(implementation = Book.class))),
        @ApiResponse(responseCode = "404", description = "No books found for the specified author"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<Book>> findByAuthor(@PathVariable String author) {
        List<Book> books = bookService.findByAuthor(author);

        return (books.isEmpty())
            ? ResponseEntity.status(HttpStatus.NOT_FOUND).body(books)
            : ResponseEntity.ok(books);
    }

    @PostMapping
    @Operation(
        summary = "Save a new book",
        description = "Saves a new book to the DB"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "201", 
            description = "Book saved successfully", 
            content = @Content(schema = @Schema(implementation = Book.class))),
        @ApiResponse(responseCode = "400", description = "Invalid input data"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Book> save(@RequestBody @Valid BookDTO dto) {
        System.out.println(dto);
        Book savedBook = bookService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBook);
    }

    @PutMapping("/{id}")
    @Operation(
        summary = "Update a book",
        description = "Updates an existing book in the DB"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Book updated successfully",
            content = @Content(schema = @Schema(implementation = Book.class))
        ),
        @ApiResponse(responseCode = "404", description = "Book not found"),
        @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    public ResponseEntity<Book> update(@PathVariable Long id, @RequestBody @Valid BookDTO dto) {
        Book updatedBook = bookService.update(id, dto);
        return ResponseEntity.ok(updatedBook);
    }

    @PatchMapping("/{id}")
    @Operation(
        summary = "Partially update a book",
        description = "Partially updates an existing book in the DB"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Book partially updated successfully",
            content = @Content(schema = @Schema(implementation = Book.class))
        ),
        @ApiResponse(responseCode = "404", description = "Book not found"),
        @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    public ResponseEntity<Book> updatePartial(@PathVariable Long id, @RequestBody @Valid BookDTO dto) {
        Book updatedBook = bookService.updatePartial(id, dto);
        return ResponseEntity.ok(updatedBook);
    }

    @DeleteMapping("/{id}")
    @Operation(
        summary = "Delete a book",
        description = "Deletes a book by ID from the DB"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Book deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Book not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        bookService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
