package com.codecool.bookclub.book.dto;

import com.codecool.bookclub.book.model.Shelf;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookDetailsDto {
    private Long bookDetailsId;
    private String externalId;
    private String title;
    private String author;
    private String pictureUrl;
    private Shelf shelf;
    private Integer userRating;

}
