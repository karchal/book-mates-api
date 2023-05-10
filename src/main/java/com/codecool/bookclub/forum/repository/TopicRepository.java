package com.codecool.bookclub.forum.repository;

import com.codecool.bookclub.book.model.Book;
import com.codecool.bookclub.forum.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface TopicRepository extends JpaRepository<Topic, Long> {

    //    @Query("Select t from Topic t left join fetch t.comments where t.book = ?1")
    List<Topic> findByBook(Book book);

    List<Topic> findFirst4ByOrderByCreationTimeDesc();

    List<Topic> findAllByAuthorId(long authorId);

    List<Topic> findTopicsByBook_ExternalId(String bookId);
}

