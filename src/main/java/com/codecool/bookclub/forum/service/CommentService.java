package com.codecool.bookclub.forum.service;

import com.codecool.bookclub.forum.dto.CommentDto;
import com.codecool.bookclub.forum.dto.NewCommentDto;
import com.codecool.bookclub.forum.model.Comment;
import com.codecool.bookclub.forum.model.Topic;
import com.codecool.bookclub.forum.repository.CommentRepository;
import com.codecool.bookclub.forum.repository.TopicRepository;
import com.codecool.bookclub.user.model.User;
import com.codecool.bookclub.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final TopicRepository topicRepository;
    private final UserRepository userRepository;

    public CommentService(CommentRepository commentRepository, TopicRepository topicRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.topicRepository = topicRepository;
        this.userRepository = userRepository;
    }

    public List<Topic> getCommentsOnTopic(long topicId) {
        Optional<Topic> optionalTopic = topicRepository.findById(topicId);
        if (optionalTopic.isPresent()) {
            return commentRepository.findAllByTopic(optionalTopic.get());
        }
        return null;
    }

    public void createComment(long topicId, NewCommentDto newCommentDto, long userId) {
        Topic topic = topicRepository.findById(topicId).orElse(null);
        User user = userRepository.findById(userId).orElse(null);
        Comment comment = new Comment();
        comment.setMessage(newCommentDto.getCommentMessage());
        comment.setTopic(topic);
        comment.setAuthor(user);
        commentRepository.save(comment);
    }

    public List<CommentDto> getCommentsForTopic(long topicId){
        return commentRepository.findAllByTopicIdOrderByCreationTimeDesc(topicId).stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public CommentDto convertToDto(Comment comment){
        return CommentDto.builder()
                .id(comment.getId())
                .creationTime(comment.getCreationTime())
                .commentMessage(comment.getMessage())
                .authorId(comment.getAuthor().getId())
                .topicId(comment.getTopic().getId())
                .authorName(comment.getAuthor().getNickname())
                .build();
    }
}
