package com.codecool.bookclub.forum.service;

import com.codecool.bookclub.book.model.Book;
import com.codecool.bookclub.book.repository.BookRepositoryJpa;
import com.codecool.bookclub.forum.model.Topic;
import com.codecool.bookclub.forum.repository.TopicRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TopicService {

    private final TopicRepository topicRepository;
    private final BookRepositoryJpa bookRepositoryJpa;

    public TopicService(TopicRepository topicRepository, BookRepositoryJpa bookRepositoryJpa) {
        this.topicRepository = topicRepository;
        this.bookRepositoryJpa = bookRepositoryJpa;
    }

    public List<Topic> getTopicsForBook(long bookId) {
        Optional<Book> optionalBook = bookRepositoryJpa.findById(bookId);
        if (optionalBook.isPresent()) {
            return topicRepository.findByBook(optionalBook.get());
        } else {
            return null;
        }
    }


    public void createTopic(long bookId, Topic topic) {
        topic.setId(bookId);
        topicRepository.save(topic);
    }
}
