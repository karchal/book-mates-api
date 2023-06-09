package com.codecool.bookclub.forum.controller;

import com.codecool.bookclub.book.model.Book;
import com.codecool.bookclub.book.service.GoogleApiBookService;
import com.codecool.bookclub.forum.dto.CommentDto;
import com.codecool.bookclub.forum.dto.NewTopicDto;
import com.codecool.bookclub.forum.dto.TopicDto;
import com.codecool.bookclub.forum.service.CommentService;
import com.codecool.bookclub.forum.service.TopicService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/api")
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class TopicController {

    private final TopicService topicService;
    private final GoogleApiBookService googleApiBookService;
    private final CommentService commentService;

    @GetMapping("/topics/top_4")
    public ResponseEntity<List<TopicDto>> getTop4Topics(){
        List<TopicDto> topics= topicService.getTopFourTopics();
        return new ResponseEntity<>(topics, HttpStatus.OK);
    }

    @GetMapping("/topics/{topic_id}") // used in Topic.js
    public ResponseEntity<TopicDto> getTopicById(@PathVariable("topic_id") long topicId){
        TopicDto topic = topicService.getTopicById(topicId);
        return new ResponseEntity<>(topic, HttpStatus.OK);
    }

    @DeleteMapping("/topics/{topic_id}" )
    public void deleteTopic(@PathVariable("topic_id") long topicId){
        topicService.deleteTopicById(topicId);
    }

    @GetMapping("/topics/{topic_id}/comments") // used in Topic.js
    public ResponseEntity<List<CommentDto>> getCommentsForTopic(@PathVariable("topic_id") long topicId){
        List<CommentDto> comments = commentService.getCommentsForTopic(topicId);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @GetMapping("/books/{book_id}/topics")
    public List<TopicDto> getTopicByBookExternalId(@PathVariable("book_id") String bookId) {
        List<TopicDto> topics = topicService.getTopicsByBookExternalId(bookId);
        log.debug("Topics for book: bookId={}, topics={}", bookId,topics);
        return new ResponseEntity<>(topics, HttpStatus.OK).getBody();
    }

    @PostMapping("/books/{book_id}/topics")
    public void createTopic(@PathVariable("book_id") String bookId,
                            @RequestBody NewTopicDto topic,
                            @AuthenticationPrincipal Long userId) {
        Book book = googleApiBookService.getBookByExternalId(bookId);
        topicService.createTopic(book, topic, userId);
    }
}
