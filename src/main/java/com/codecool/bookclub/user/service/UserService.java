package com.codecool.bookclub.user.service;

import com.codecool.bookclub.book.dto.BookDetailsDto;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public List<EventDetailsDto> getUserEvents(long userId){
        return eventDetailsRepository.findAllByUserId(userId).stream().map(eventService::convertDetailsToDto).collect(Collectors.toList());
    }

    public List<TopicDto> getUserTopics(long userId){
        return topicRepository.findAllByAuthorId(userId).stream().map(topicService::convertToDto).collect(Collectors.toList());
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

    public List<BookDetailsDto> getUserBooks(long userId) {
        return bookDetailsRepository.findAllByUserId(userId)
                .stream()
                .map(b -> new BookDetailsDto(b))
                .collect(Collectors.toList());
    }

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return userRepository
//                .findByEmail(username)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
//    }
}
