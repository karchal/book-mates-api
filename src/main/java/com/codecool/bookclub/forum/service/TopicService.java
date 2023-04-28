package com.codecool.bookclub.forum.service;

import com.codecool.bookclub.book.model.Book;
import com.codecool.bookclub.book.repository.BookRepository;
import com.codecool.bookclub.forum.dto.TopicDto;
import com.codecool.bookclub.forum.model.Topic;
import com.codecool.bookclub.forum.repository.TopicRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TopicService {

    private final TopicRepository topicRepository;
    private final BookRepository bookRepository;

    public TopicService(TopicRepository topicRepository, BookRepository bookRepository) {
        this.topicRepository = topicRepository;
        this.bookRepository = bookRepository;
    }

    public List<Topic> getTopicsForBook(long bookId) {
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        return optionalBook.map(topicRepository::findByBook).orElse(null);
    }

    public TopicDto getTopicById(long topicId) {
        return topicRepository.findById(topicId).map(this::convertToDto).orElse(null);
    }


    public void createTopic(long bookId, Topic topic) {
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        if (optionalBook.isPresent()) {
            topic.setBook(optionalBook.get());
            topicRepository.save(topic);
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

    public List<TopicDto> getUserTopicDtos(long userId) {
        return topicRepository
                .findAllByAuthorId(userId)
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
                .authorName(topic.getAuthor().getNickname())
                .creationTime(topic.getCreationTime())
                .authorId(topic.getAuthor().getId())
                .numberOfComments(topic.getComments().size())
                .bookExternalId(topic.getBook().getExternalId())
                .build();
    }
}
