package com.codecool.bookclub.forum.service;

import com.codecool.bookclub.book.model.Book;
import com.codecool.bookclub.book.repository.BookRepository;
import com.codecool.bookclub.forum.model.Topic;
import com.codecool.bookclub.forum.repository.TopicRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        if (optionalBook.isPresent()) {
            return topicRepository.findByBook(optionalBook.get());
        } else {
            return null;
        }
    }


    public void createTopic(long bookId, Topic topic) {
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        if (optionalBook.isPresent()) {
            topic.setBook(optionalBook.get());
            topicRepository.save(topic);
        }
    }
}
