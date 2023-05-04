package com.codecool.bookclub.book.service;

public class ApiException extends RuntimeException {
    public ApiException(String message) {
        super(message);
    }
}
