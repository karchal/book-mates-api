package com.codecool.bookclub.user.service;

import com.codecool.bookclub.book.service.BookDetailsService;
import com.codecool.bookclub.event.service.EventService;
import com.codecool.bookclub.forum.service.TopicService;
import com.codecool.bookclub.user.dto.UserDto;
import com.codecool.bookclub.user.model.User;
import com.codecool.bookclub.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;


@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final EventService eventService;
    private final TopicService topicService;
    private final BookDetailsService bookDetailsService;

    public Optional<User> getUser(long userId){
        return userRepository.findById(userId);
    }

    public UserDto getUserDto(long userId) {
        return getUser(userId).map(this::convertToDto).orElse(null);
    }

    public boolean deleteUserById(long userId){
        return getUser(userId).map(user -> {
            userRepository.deleteById(userId);
            return true;
        }).orElse(false);
    }

    public UserDto convertToDto(User user) {
        return (user != null) ? UserDto.builder()
                .id(user.getId())
                .nickname(user.getNickname())
                .books(user.getBooksDetails()
                        .stream()
                        .map(bookDetailsService::convertToDto)
                        .collect(Collectors.toList()))
                .events(user.getEvents()
                        .stream()
                        .map(eventService::convertDetailsToDto)
                        .collect(Collectors.toList()))
                .topics(user.getTopics()
                        .stream()
                        .map(topicService::convertToDto)
                        .collect(Collectors.toList()))
                .build() : null;
    }
}
