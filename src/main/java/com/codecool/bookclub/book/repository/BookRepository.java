package com.codecool.bookclub.book.repository;

import com.codecool.bookclub.book.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
    Book findBookById(long id);
}
