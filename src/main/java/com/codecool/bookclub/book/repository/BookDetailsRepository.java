package com.codecool.bookclub.book.repository;

import com.codecool.bookclub.book.model.BookDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookDetailsRepository extends JpaRepository<BookDetails, Long> {
    List<BookDetails> findAllByUserId(long userId);
}
