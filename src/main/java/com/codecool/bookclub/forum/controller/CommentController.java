package com.codecool.bookclub.forum.controller;

import com.codecool.bookclub.forum.dto.CommentDto;
import com.codecool.bookclub.forum.dto.NewCommentDto;
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


    @PostMapping("/comments")
    public void createComment(@RequestBody NewCommentDto comment, @AuthenticationPrincipal Long userId){
        commentService.createComment(comment, userId);
    }

    @DeleteMapping("/comments/{comment_id}")
    public void deleteComment(@PathVariable("comment_id") long commentId){
        commentService.deleteComment(commentId);
    }

    @GetMapping("/topics/{topic_id}/comments")
    public ResponseEntity<List<CommentDto>> getCommentsForTopic(@PathVariable("topic_id") long topicId){
        List<CommentDto> comments= commentService.getCommentsForTopic(topicId);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }


}
