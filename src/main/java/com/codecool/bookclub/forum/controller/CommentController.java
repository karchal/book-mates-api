package com.codecool.bookclub.forum.controller;

import com.codecool.bookclub.forum.model.Comment;
import com.codecool.bookclub.forum.model.Topic;
import com.codecool.bookclub.forum.service.CommentService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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


    @PostMapping("/books/{book_id}/topics/{topic_id}")
    public void createComment(@PathVariable("book_id") long bookId, @PathVariable("topic_id") long topicId, @RequestBody Comment comment){
        commentService.createComment(topicId, comment);
    }


    @PutMapping("/books/{book_id}/topics/{topic_id}")
    public boolean updateComment(@PathVariable("book_id") long bookId, @PathVariable("topic_id") long topicId, @RequestBody Comment comment) {
        return false;
    }

    @DeleteMapping("/books/{book_id}/topics/{topic_id}")
    public boolean deleteComment(@PathVariable("book_id") long bookId, @PathVariable("topic_id") long topicId, @RequestBody Comment comment){
        return false;
    }


}
