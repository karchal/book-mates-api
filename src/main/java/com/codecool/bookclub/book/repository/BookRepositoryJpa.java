package com.codecool.bookclub.book.repository;

import com.codecool.bookclub.book.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface BookRepositoryJpa extends JpaRepository<Book, Long> {
}
