package com.codecool.bookclub.forum.controller;

import com.codecool.bookclub.forum.dto.TopicDto;
import com.codecool.bookclub.forum.model.Topic;
import com.codecool.bookclub.forum.service.TopicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/api")
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class TopicController {

    private final TopicService topicService;

    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @PutMapping("/books/{book_id}/topics")
    public boolean updateTopic(@PathVariable("book_id") long bookId, @RequestBody Topic updatedTopic) {
        return false;
    }

    @DeleteMapping("/books/{book_id}/topics")
    public boolean deleteTopic(@PathVariable("book_id") long bookId, @RequestBody Topic topic){
        return false;
    }

    @GetMapping("/topics/top_4")
    public ResponseEntity<List<TopicDto>> getTop4Topics(){
        List<TopicDto> topics= topicService.getTopFourTopics();
        return new ResponseEntity<>(topics, HttpStatus.OK);
    }
    @GetMapping("/topics")
    public List<Topic> getAllTopics(){
        List<Topic> topics = topicService.getAllTopics();
        return new ResponseEntity<>(topics, HttpStatus.OK).getBody();
    }

    @GetMapping("/topics/{topic_id}")
    public ResponseEntity<TopicDto> getTopicById(@PathVariable("topic_id") long topicId){
        TopicDto topic = topicService.getTopicById(topicId);
        return new ResponseEntity<>(topic, HttpStatus.OK);
    }

    @GetMapping("/books/{book_id}/topics")
    public List<TopicDto> getTopicByBookExternalId(@PathVariable("book_id") String bookId) {
        List<TopicDto> topics = topicService.getTopicsByBookExternalId(bookId);
        log.debug("Topics for book: bookId={}, topics={}", bookId,topics);
        return new ResponseEntity<>(topics, HttpStatus.OK).getBody();
    }
}
