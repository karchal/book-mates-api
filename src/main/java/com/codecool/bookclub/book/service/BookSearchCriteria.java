package com.codecool.bookclub.book.service;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class BookSearchCriteria {
    String author;
    String title;
}
