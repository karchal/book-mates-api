package com.codecool.bookclub.book.repository;

import com.codecool.bookclub.book.model.Book;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BookPaginationRepository extends PagingAndSortingRepository<Book,Long> {
}
