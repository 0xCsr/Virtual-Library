package com.virtuallibrary.VirtualLibrary.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    @NotBlank(message = "Email is required")
    @Size(min = 1, max = 320, message = "Email must be between 1 and 320 characters")
    @Email
    @Schema(description = "User email", example = "email@email.com")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 1, max = 64, message = "Password must be between 1 ans 64 characters")
    private String password;
}