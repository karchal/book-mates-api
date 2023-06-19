package com.codecool.bookclub.security.authentication;

import lombok.Data;

@Data
public class ResetPasswordRequest {
    private String newPassword;
}
