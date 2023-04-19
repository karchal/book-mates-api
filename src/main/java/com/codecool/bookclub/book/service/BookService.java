package com.codecool.bookclub.book.service;

import com.codecool.bookclub.book.model.Book;
import com.codecool.bookclub.book.model.Shelf;
import com.codecool.bookclub.googleapi.ReturnResults;

import java.util.List;

public interface BookService {

    Book getById(Long id);// todo Optional<Book>
    List<Book> getAllBooks();

    List<Book> findTopFourBooks();

    List<Book> searchBooks(String criteria, String query);

//    ReturnResults callApi(String query);

    Book saveBookToShelf(Book book, Shelf shelf);
    Book getBookByExternalId(String externalId);
}
