package com.codecool.bookclub.book.dto;

import com.codecool.bookclub.book.model.Book;
import com.codecool.bookclub.book.model.BookDetails;
import com.codecool.bookclub.book.model.Shelf;
import lombok.*;


public class BookDetailsDto {
    private Long id;
    private Long bookId;
    private String title;
    private String author;
    private String pictureUrl;
    private Shelf shelf;
    private Integer userRating;

    public BookDetailsDto(BookDetails bookDetails){
        this.id = bookDetails.getId();
        this.bookId = bookDetails.getBook().getId();
        this.title = bookDetails.getBook().getTitle();
        this.author = bookDetails.getBook().getAuthor();
        this.pictureUrl = bookDetails.getBook().getPictureUrl();
        this.shelf =  bookDetails.getShelf();
        this.userRating = bookDetails.getUserRating();
    }

}
