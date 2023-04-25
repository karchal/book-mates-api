package com.codecool.bookclub.user.service;

import com.codecool.bookclub.book.dto.BookDetailsDto;
import com.codecool.bookclub.book.model.BookDetails;
import com.codecool.bookclub.book.repository.BookDetailsRepository;
import com.codecool.bookclub.event.dto.EventDetailsDto;
import com.codecool.bookclub.event.repository.EventDetailsRepository;
import com.codecool.bookclub.event.service.EventService;
import com.codecool.bookclub.forum.dto.TopicDto;
import com.codecool.bookclub.forum.model.Comment;
import com.codecool.bookclub.forum.repository.TopicRepository;
import com.codecool.bookclub.forum.service.TopicService;
import com.codecool.bookclub.user.model.User;
import com.codecool.bookclub.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Slf4j
@Service
public class UserService {
    private final UserRepository userRepository;
    private final BookDetailsRepository bookDetailsRepository;

    private final EventDetailsRepository eventDetailsRepository;

    private final TopicRepository topicRepository;

    private final EventService eventService;

    private final TopicService topicService;

    @Autowired
    public UserService(UserRepository userRepository, BookDetailsRepository bookDetailsRepository, EventDetailsRepository eventDetailsRepository, TopicRepository topicRepository, EventService eventService, TopicService topicService) {
        this.userRepository = userRepository;
        this.bookDetailsRepository = bookDetailsRepository;
        this.eventDetailsRepository = eventDetailsRepository;
        this.topicRepository = topicRepository;
        this.eventService = eventService;
        this.topicService = topicService;
    }

    public Optional<User> getUserById(long userId){
        return userRepository.findById(userId);
    }

    public List<BookDetailsDto> getUserBooks(long userId) {
        return bookDetailsRepository.findAllByUserId(userId)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<BookDetailsDto> getUserBooks(User user){
        return getUserBooks(user.getId());
    }
    public List<EventDetailsDto> getUserEvents(long userId){
        return eventDetailsRepository.findAllByUserId(userId)
                .stream()
                .map(eventService::convertDetailsToDto)
                .collect(Collectors.toList());
    }

    public List<EventDetailsDto> getUserEvents(User user){
        return getUserEvents(user.getId());
    }

    public List<TopicDto> getUserTopics(long userId){
        return topicRepository.findAllByAuthorId(userId).stream().map(topicService::convertToDto).collect(Collectors.toList());
    }

    public List<TopicDto> getUserTopics(User user){
        return getUserTopics(user.getId());
    }

    public List<Comment> getUserComments(long userId){
        return getUserById(userId).map(User::getComments).orElse(null);
    }



    public boolean deleteUserById(long userId){
        return getUserById(userId).map(user -> {
            userRepository.deleteById(userId);
            return true;
        }).orElse(false);
    }

    private BookDetailsDto convertToDto(BookDetails bookDetails){
        return BookDetailsDto.builder()
                .bookDetailsId(bookDetails.getId())
                .externalId(bookDetails.getBook().getExternalId())
                .title(bookDetails.getBook().getTitle())
                .author(bookDetails.getBook().getAuthor())
                .pictureUrl(bookDetails.getBook().getPictureUrl())
                .shelf(bookDetails.getShelf())
                .userRating(bookDetails.getUserRating())
                .build();
    }

}
