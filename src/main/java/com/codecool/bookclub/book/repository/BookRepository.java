package com.codecool.bookclub.book.repository;

import com.codecool.bookclub.book.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    Book findBookById(long id);

    List<Book> findByAuthor(String author);

    List<Book> findByTitle(String title);

    List<Book> findAll();
}
