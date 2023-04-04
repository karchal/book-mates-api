package com.codecool.bookclub.user.service;

import com.codecool.bookclub.book.dto.BookDetailsDto;
import com.codecool.bookclub.book.model.Book;
import com.codecool.bookclub.book.model.BookDetails;
import com.codecool.bookclub.book.repository.BookDetailsRepository;
import com.codecool.bookclub.event.model.Event;
import com.codecool.bookclub.forum.model.Comment;
import com.codecool.bookclub.forum.model.Topic;
import com.codecool.bookclub.user.model.User;
import com.codecool.bookclub.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final BookDetailsRepository bookDetailsRepository;

    @Autowired
    public UserService(UserRepository userRepository, BookDetailsRepository bookDetailsRepository) {
        this.userRepository = userRepository;
        this.bookDetailsRepository = bookDetailsRepository;
    }

//    public List<UserBook> getUserBooks(long userId){
//        return userRepository.findById(userId).map(user -> user.getBooks()).orElse(null);
//    }

    public Optional<User> getUserById(long userId){
        return userRepository.findById(userId);
    }

    public List<Event> getUserEvents(long userId){
        return getUserById(userId).map(User::getEvents).orElse(null);
    }//pusta lista

    public List<Topic> getUserTopics(long userId){
        return getUserById(userId).map(User::getTopics).orElse(null);
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

    public void addUser(String username, String password){
        userRepository.save(new User(username, password));
    }


    public List<BookDetailsDto> getUserBooks(long userId) {
        return bookDetailsRepository.findAllByUserId(userId)
                .stream()
                .map(b -> new BookDetailsDto(b))
                .collect(Collectors.toList());

    }
}
