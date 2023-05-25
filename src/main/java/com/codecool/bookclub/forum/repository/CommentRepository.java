package com.codecool.bookclub.forum.repository;

import com.codecool.bookclub.forum.model.Comment;
import com.codecool.bookclub.forum.model.Status;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByTopicIdAndStatusNot(long topicId, Status status, Sort creationTime);
}
