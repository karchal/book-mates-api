package com.codecool.bookclub.book.service;

import com.codecool.bookclub.book.model.Book;
import com.codecool.bookclub.book.model.Shelf;
import com.codecool.bookclub.user.model.User;

import java.util.List;

public interface BookService {

    Book getById(Long id);// todo Optional<Book>
    List<Book> getAllBooks();
    List<Book> findTopFourBooks();
    void saveBookToShelf(Book book, Shelf shelf, String userName);
}
