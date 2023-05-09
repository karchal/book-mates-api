package com.codecool.bookclub.forum.controller;

import com.codecool.bookclub.forum.dto.CommentDto;
import com.codecool.bookclub.forum.dto.NewCommentDto;
import com.codecool.bookclub.forum.model.Comment;
import com.codecool.bookclub.forum.model.Topic;
import com.codecool.bookclub.forum.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")
@RestController
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/books/{book_id}/topics/{topic_id}")
    public List<Topic> getCommentsOnTopic(@PathVariable("book_id") long bookId, @PathVariable("topic_id") long topicId){
        return commentService.getCommentsOnTopic(topicId);
    }


    @PostMapping("/topics/{topic_id}/comments")
    public void createComment(@PathVariable("topic_id") long topicId, @RequestBody NewCommentDto comment, @AuthenticationPrincipal Long userId){
        commentService.createComment(topicId, comment, userId);
    }


    @PutMapping("/books/{book_id}/topics/{topic_id}")
    public boolean updateComment(@PathVariable("book_id") long bookId, @PathVariable("topic_id") long topicId, @RequestBody Comment comment) {
        return false;
    }

    @DeleteMapping("/books/{book_id}/topics/{topic_id}")
    public boolean deleteComment(@PathVariable("book_id") long bookId, @PathVariable("topic_id") long topicId, @RequestBody Comment comment){
        return false;
    }

    @GetMapping("/topics/{topic_id}/comments")
    public ResponseEntity<List<CommentDto>> getCommentsForTopic(@PathVariable("topic_id") long topicId){
        List<CommentDto> comments= commentService.getCommentsForTopic(topicId);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }


}
