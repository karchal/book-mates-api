package com.codecool.bookclub.security.authentication;

import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    @NotNull(message = "username should not be empty")
    @NotEmpty(message = "username should not be empty")
    @Pattern(regexp = "^[a-zA-Z][a-zA-Z0-9-_]{3,23}$", message = "username has wrong format")
    private String username;
    @NotNull(message = "email should not be empty")
    @NotEmpty(message = "email should not be empty")
    @Email
    private String email;
    @NotNull(message = "password should not be empty")
    @NotEmpty(message = "password should not be empty")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])[^\s'\"`&<>*]{8,24}$", message = "password has wrong format")
    private String password;

}
