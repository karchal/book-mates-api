package com.codecool.bookclub.book.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @EqualsAndHashCode.Include
    private String externalId;
    private String title;
    private String author;
    private int year;
    @Column(length = 4000)//columnDefinition = "text")
    private String description;

    @Column(length = 1000)
    private String pictureUrl;
    private int pages;
    private BigDecimal rating;
//    private int ratingsCount;


}
