package com.codecool.bookclub.book.service;

import com.codecool.bookclub.book.model.Book;
import com.codecool.bookclub.book.model.Shelf;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookService {

    Book getById(Long id);// todo Optional<Book>
    List<Book> getAllBooks();
    List<Book> findTopFourBooks();
    void saveBookToShelf(Book book, Shelf shelf, Long userId);
    Page<Book> findAllBooks(Pageable paging);
}
