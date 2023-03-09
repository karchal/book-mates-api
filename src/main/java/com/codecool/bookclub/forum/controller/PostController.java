package com.codecool.bookclub.forum.controller;

import com.codecool.bookclub.forum.model.Comment;
import com.codecool.bookclub.forum.model.Topic;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController("/api")
public class PostController {


    @GetMapping("/books/{book_id}/topics/{topic_id}")
    public List<Topic> getCommentsToTopic(@PathVariable("book_id") long bookId, @PathVariable("topic_id") long topicId){
        return new ArrayList<>();
    }


    @PostMapping("/books/{book_id}/topics/{topic_id}")
    public boolean createComment(@PathVariable("book_id") long bookId, @PathVariable("topic_id") long topicId, @RequestBody Comment comment){
        return false;
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
