package com.codecool.bookclub.forum.service;

import com.codecool.bookclub.forum.dto.CommentDto;
import com.codecool.bookclub.forum.dto.NewCommentDto;
import com.codecool.bookclub.forum.model.Comment;
import com.codecool.bookclub.forum.model.Status;
import com.codecool.bookclub.forum.model.Topic;
import com.codecool.bookclub.forum.repository.CommentRepository;
import com.codecool.bookclub.forum.repository.TopicRepository;
import com.codecool.bookclub.user.model.User;
import com.codecool.bookclub.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final TopicRepository topicRepository;
    private final UserRepository userRepository;

    public void createComment(NewCommentDto newCommentDto, long userId) {
        Topic topic = topicRepository.findById(newCommentDto.getTopicId()).orElse(null);
        User user = userRepository.findById(userId).orElse(null);
        Comment comment = new Comment();
        comment.setMessage(newCommentDto.getCommentMessage());
        comment.setTopic(topic);
        comment.setAuthor(user);
        comment.setStatus(Status.NOT_VERIFIED);
        commentRepository.save(comment);
    }

    public List<CommentDto> getCommentsForTopic(long topicId){
        return commentRepository.findAllByTopicIdOrderByCreationTimeDesc(topicId).stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public void deleteComment(long id) {
        commentRepository.deleteById(id);
    }

    public ResponseEntity<String> reportAbuse(long id) {
        Optional<Comment> optionalComment = commentRepository.findById(id);
        if (optionalComment.isEmpty()) {
            return ResponseEntity.status(404).body("Komentarz o podanym id nie istnieje.");
        }
        Comment comment = optionalComment.get();
        if (comment.getStatus() == Status.ACCEPTED) {
            return ResponseEntity.status(404).body("Komentarz został już zweryfikowany i zaakceptowany.");
        }
        if (comment.getStatus() == Status.BLOCKED) {
            return ResponseEntity.status(404).body("Komentarz został już zablokowany.");
        }
        if (comment.getStatus() == Status.NOT_VERIFIED) {
            comment.setStatus(Status.NEEDS_VERIFICATION);
            commentRepository.save(comment);
        }
        return ResponseEntity.status(202).body("Komentarz został zgłoszony do weryfikacji.");
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
    public void blockComment(Long id) {
        Comment comment = commentRepository.findById(id).orElse(null);
        if (comment != null && comment.getStatus() != Status.BLOCKED){
            comment.setStatus(Status.BLOCKED);
            commentRepository.save(comment);
        }
    }
}
