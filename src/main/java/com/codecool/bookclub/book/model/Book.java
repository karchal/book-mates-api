package com.codecool.bookclub.book.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@Entity
//@Table(name = "book")
public class Book {
    @Id
    private String id;
    private String title;
    private String author;
    private int year;
    private String description;
    private String pictureUrl;
    private int pages;
    private BigDecimal rating;
//    private String genre;
}
