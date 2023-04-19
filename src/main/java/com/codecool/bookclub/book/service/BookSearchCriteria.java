package com.codecool.bookclub.book.service;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class BookSearchCriteria {
    private String author;
    private String title;
}
