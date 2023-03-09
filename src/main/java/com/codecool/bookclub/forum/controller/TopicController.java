package com.codecool.bookclub.forum.controller;

import com.codecool.bookclub.forum.model.Topic;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TopicController {

    @GetMapping("/books/{book_id}/topic")
    public List<Topic> getTopicsForBook(@PathVariable("book_id") long bookId){
        return new ArrayList<>();
    }

    @PostMapping("/books/{book_id}/topic")
    public boolean createTopic(@PathVariable("book_id") long bookId, @RequestBody Topic topic){
        return false;
    }

    @PutMapping("/books/{book_id}/topic")
    public boolean updateTopic(@PathVariable("book_id") long bookId, @RequestBody Topic updatedTopic) {
        return false;
    }

    @DeleteMapping("/books/{book_id}/topic")
    public boolean deleteTopic(@PathVariable("book_id") long bookId, @RequestBody Topic topic){
        return false;
    }
}
