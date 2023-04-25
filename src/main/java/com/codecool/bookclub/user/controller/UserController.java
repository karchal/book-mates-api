package com.codecool.bookclub.user.controller;

import com.codecool.bookclub.book.dto.BookDetailsDto;
import com.codecool.bookclub.book.model.Book;
import com.codecool.bookclub.event.dto.EventDetailsDto;
import com.codecool.bookclub.forum.dto.TopicDto;
import com.codecool.bookclub.forum.model.Comment;
import com.codecool.bookclub.user.dto.UserDto;
import com.codecool.bookclub.user.model.User;
import com.codecool.bookclub.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000/account")
@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @GetMapping("/users/{user_id}/books")
    public ResponseEntity<List<BookDetailsDto>> getUserBooks(@PathVariable("user_id") long userId){
        return new ResponseEntity<>(userService.getUserBooks(userId), HttpStatus.OK);
    }

    @GetMapping("/users/{user_id}/events")
    public ResponseEntity<List<EventDetailsDto>> getUserEvents(@PathVariable("user_id") long userId){
        return new ResponseEntity<>(userService.getUserEvents(userId), HttpStatus.OK);
    }

    @GetMapping("/users/{user_id}/topics")
    public ResponseEntity<List<TopicDto>>  getUserTopics(@PathVariable("user_id") long userId){
       return new ResponseEntity<>(userService.getUserTopics(userId), HttpStatus.OK);
    }

    @GetMapping("/users/{user_id}/comments")
    public List<Comment> getUserComments(@PathVariable("user_id") long userId){
        return userService.getUserComments(userId);
    }

    @GetMapping("/users/my_account/books")
    public ResponseEntity<List<BookDetailsDto>> getMyBooks(@AuthenticationPrincipal User user){
        return new ResponseEntity<>(userService.getUserBooks(user), HttpStatus.OK);
    }

    @PutMapping("/users/{user_id}/shelves")
    public boolean updateShelfType(@PathVariable("user_id") long userId, @RequestBody Book book){
        return false;
    }
    @DeleteMapping("/users/{user_id}/books")
    public boolean deleteBookFromShelf(@PathVariable("user_id") long userId, @RequestBody Book book){
        return false;
    }

    @GetMapping("/users/{user_id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("user_id") long userId){
        UserDto userDto = userService.getUserDto(userId);
        if (userDto != null) {
            return new ResponseEntity<>(userDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/users/{user_id}")
    public boolean deleteUserAccountByUserId(@PathVariable("user_id") long userId){
        return userService.deleteUserById(userId);
    }


}
