package com.codecool.bookclub.forum.repository;

import com.codecool.bookclub.book.model.Book;
import com.codecool.bookclub.forum.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface TopicRepository extends JpaRepository<Topic, Long> {

    List<Topic> findByBook(Book book);
}
