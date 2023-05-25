package com.codecool.bookclub.forum.repository;

import com.codecool.bookclub.forum.model.Status;
import com.codecool.bookclub.forum.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface TopicRepository extends JpaRepository<Topic, Long> {

    @Query("SELECT t FROM Topic t WHERE t.status <> 3 ORDER BY t.creationTime DESC limit 4")
    List<Topic> find4LatestNonBlockedTopics(Status status);

    List<Topic> findTopicsByBookExternalId(String bookId);

    Optional<Topic> findByIdAndStatusNot(Long id, Status status);
}

