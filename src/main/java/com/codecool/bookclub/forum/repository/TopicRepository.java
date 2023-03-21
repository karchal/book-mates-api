package com.codecool.bookclub.forum.repository;

import com.codecool.bookclub.forum.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TopicRepository extends JpaRepository<Topic, Long> {
}
