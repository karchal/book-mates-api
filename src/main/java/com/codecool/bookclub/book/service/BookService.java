package com.codecool.bookclub.book.service;

import com.codecool.bookclub.book.model.Book;

import java.util.List;

public interface BookService {
    List<Book> getAllBooks();
    List<Book> getBooksByAuthor(String author);
    List<Book> getBooksByTitle(String title);
    List<Book> getBooksByGenre(String genre);
}
