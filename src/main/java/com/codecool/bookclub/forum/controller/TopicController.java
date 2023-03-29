package com.codecool.bookclub.forum.controller;

import com.codecool.bookclub.forum.model.Topic;
import com.codecool.bookclub.forum.service.TopicService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/api")
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class TopicController {

    private final TopicService topicService;

    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }


    @GetMapping(value = "/books/{book_id}/topics", produces = "application/json")
    public List<Topic> getTopicsForBook(@PathVariable("book_id") long bookId){
        return topicService.getTopicsForBook(bookId);
    }

    @PostMapping("/books/{book_id}/topics")
    public void createTopic(@PathVariable("book_id") long bookId, @RequestBody Topic topic){
        topicService.createTopic(bookId, topic);
    }

    @PutMapping("/books/{book_id}/topics")
    public boolean updateTopic(@PathVariable("book_id") long bookId, @RequestBody Topic updatedTopic) {
        return false;
    }

    @DeleteMapping("/books/{book_id}/topics")
    public boolean deleteTopic(@PathVariable("book_id") long bookId, @RequestBody Topic topic){
        return false;
    }

    @GetMapping("/topics")
    public List<Topic> getAllTopics(@RequestParam(value="amount", required = false) int amount){
        List<Topic> topics;
        if (amount==4){
            topics = topicService.getTopFourTopics();
        } else {
            topics = topicService.getAllTopics();
        }
        return new ResponseEntity<>(topics, HttpStatus.OK).getBody();
    }
}
