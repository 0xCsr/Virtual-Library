package com.virtuallibrary.VirtualLibrary.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserBookDTO {
    
    @NotNull(message = "User id cannot be null")
    private Long userId;

    @NotNull(message = "Book id cannot be null")
    private Long bookId;

    @NotBlank(message = "Status cannot be null")
    @Size(min = 1, max = 255, message = "Status must be between 1 and 255 characters")
    @Schema(description = "Status of the book", example = "READING")
    private String status;
}
