package com.codecool.bookclub.forum.service;

import com.codecool.bookclub.forum.model.Comment;
import com.codecool.bookclub.forum.model.Topic;
import com.codecool.bookclub.forum.repository.CommentRepository;
import com.codecool.bookclub.forum.repository.TopicRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final TopicRepository topicRepository;

    public CommentService(CommentRepository commentRepository, TopicRepository topicRepository) {
        this.commentRepository = commentRepository;
        this.topicRepository = topicRepository;
    }

    public List<Topic> getCommentsOnTopic(long topicId) {
        Optional<Topic> optionalTopic = topicRepository.findById(topicId);
        if (optionalTopic.isPresent()) {
            return commentRepository.findAllByTopic(optionalTopic.get());
        }
        return null;
    }

    public void createComment(long topicId, Comment comment) {
        Optional<Topic> optionalTopic = topicRepository.findById(topicId);
        if (optionalTopic.isPresent()) {
            comment.setTopic(optionalTopic.get());
            commentRepository.save(comment);
        }
    }
}
