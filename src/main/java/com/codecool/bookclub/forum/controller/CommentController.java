package com.codecool.bookclub.forum.controller;

import com.codecool.bookclub.forum.dto.CommentDto;
import com.codecool.bookclub.forum.dto.NewCommentDto;
import com.codecool.bookclub.forum.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/comments/{id}")
    public ResponseEntity<CommentDto> getComment(@PathVariable long id) {
        return new ResponseEntity<>(commentService.getComment(id), HttpStatus.OK);
    }

    @DeleteMapping("/comments/{id}")
    public void deleteComment(@PathVariable long id){
        commentService.deleteComment(id);
    }



}
