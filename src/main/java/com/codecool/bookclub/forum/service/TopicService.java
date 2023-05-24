package com.codecool.bookclub.forum.service;

import com.codecool.bookclub.book.model.Book;
import com.codecool.bookclub.book.repository.BookRepository;
import com.codecool.bookclub.book.service.BookService;
import com.codecool.bookclub.forum.dto.NewTopicDto;
import com.codecool.bookclub.forum.dto.TopicDto;
import com.codecool.bookclub.forum.model.Topic;
import com.codecool.bookclub.forum.repository.TopicRepository;
import com.codecool.bookclub.user.model.User;
import com.codecool.bookclub.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class TopicService {

    private final TopicRepository topicRepository;
    private final BookRepository bookRepository;
    private final BookService bookService;
    private final UserRepository userRepository;

    public TopicService(TopicRepository topicRepository, BookRepository bookRepository, BookService bookService, UserRepository userRepository) {
        this.topicRepository = topicRepository;
        this.bookRepository = bookRepository;
        this.bookService = bookService;
        this.userRepository = userRepository;
    }

    public TopicDto getTopicById(long topicId) {
        return topicRepository.findById(topicId).map(this::convertToDto).orElse(null);
    }

    public void createTopic(Book book, NewTopicDto newTopicDto, Long userId) {
       Book bookInDb = bookService.saveBook(book);
       log.debug("Saving book to db {}", bookInDb);
       User user = userRepository.findById(userId).orElse(null);
       log.debug("User saving book {}", user);
       if (user != null) {
           Topic topic = new Topic();
           topic.setAuthor(user);
           topic.setBook(bookInDb);
           topic.setTitle(newTopicDto.getTitle());
           topic.setMessage(newTopicDto.getMessage());
           topicRepository.save(topic);
           log.debug("Topic saved {}", topic);
       }
    }

    public List<Topic> getAllTopics(){
        return topicRepository.findAll();
    }

    public List<TopicDto> getTopFourTopics(){
        return topicRepository
                .findFirst4ByOrderByCreationTimeDesc()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }


    public TopicDto convertToDto(Topic topic) { //toDtoMapper fromDtoMapper
        return TopicDto.builder()
                .id(topic.getId())
                .bookId(topic.getBook().getId())
                .bookPictureUrl(topic.getBook().getPictureUrl())
                .bookTitle(topic.getBook().getTitle())
                .bookAuthor(topic.getBook().getAuthor())
                .title(topic.getTitle())
                .message(topic.getMessage())
                .authorName(topic.getAuthor().getNickname())
                .creationTime(topic.getCreationTime())
                .authorId(topic.getAuthor().getId())
                .numberOfComments(topic.getComments().size())
                .bookExternalId(topic.getBook().getExternalId())
                .build();
    }

    public List<TopicDto> getTopicsByBookExternalId(String bookId) {
        return topicRepository.findTopicsByBook_ExternalId(bookId).stream().map(this::convertToDto).collect(Collectors.toList());
    }
}
