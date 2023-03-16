package com.codecool.bookclub.book.repository;

import com.codecool.bookclub.book.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepositoryJpa extends JpaRepository<Book, Long> {
}
