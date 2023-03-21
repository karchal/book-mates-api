package com.codecool.bookclub.book.model;

import java.math.BigDecimal;

//@Entity
//@Table(name = "user_book")
public class UserBook {
//    @Id
    private Long id;
    private String bookId;
    private Long userId;
    private Shelf shelf;
    private BigDecimal userRating;
}
