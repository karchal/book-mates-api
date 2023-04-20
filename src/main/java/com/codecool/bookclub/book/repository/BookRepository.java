package com.codecool.bookclub.book.repository;

import com.codecool.bookclub.book.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    Book findBookById(long id);
    Book findBookByExternalId(String externalId);

    List<Book> findByAuthor(String author);

    List<Book> findByTitle(String title);

    List<Book> findAll();

    List<Book> findFirst4ByOrderByRatingDesc();

    Optional<Book> findByExternalId(String externalId);

}
