    package com.virtuallibrary.VirtualLibrary.dto;

    import io.swagger.v3.oas.annotations.media.Schema;
    import jakarta.validation.constraints.NotBlank;
    import jakarta.validation.constraints.Size;
    import lombok.AllArgsConstructor;
    import lombok.Data;
    import lombok.NoArgsConstructor;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Schema(description = "DTO for creating a new book")
    public class BookDTO {
        @NotBlank(message = "Title cannot be blank")
        @Size(min = 1, max = 255, message = "Title must be between 1 and 255 characters")
        @Schema(description = "Title of the book", example = "Effective Java")
        private String title;
        
        @NotBlank(message = "Author cannot be blank")
        @Size(min = 1, max = 255, message = "Author must be between 1 and 255 characters")
        @Schema(description = "Author of the book", example = "Joshua Bloch")
        private String author;

        @NotBlank(message = "Genre cannot be blank")
        @Size(min = 1, max = 255, message = "Genre must be between 1 and 255 characters")
        @Schema(description = "Genre of the book", example = "Programming")
        private String genre;
    }
