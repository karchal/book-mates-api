package com.codecool.bookclub.user.controller;

import com.codecool.bookclub.book.model.Book;
import com.codecool.bookclub.book.model.BookDetails;
import com.codecool.bookclub.event.model.Event;
import com.codecool.bookclub.forum.model.Comment;
import com.codecool.bookclub.forum.model.Topic;
import com.codecool.bookclub.user.model.User;
import com.codecool.bookclub.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/{user_id}/books")
    public ResponseEntity<List<BookDetails>> getUserBooks(@PathVariable("user_id") long userId){
        return new ResponseEntity<>(userService.getUserBooks(userId), HttpStatus.OK);
    }


    @GetMapping("/users/{user_id}/events")
    public List<Event> getUserEvents(@PathVariable("user_id") long userId){
        return userService.getUserEvents(userId);
    }

    @GetMapping("/users/{user_id}/topics")
    public List<Topic> getUserTopics(@PathVariable("user_id") long userId){
        return userService.getUserTopics(userId);
    }

    @GetMapping("/users/{user_id}/comments")
    public List<Comment> getUserComments(@PathVariable("user_id") long userId){
        return userService.getUserComments(userId);
    }


    @GetMapping("/users/{user_id}/shelves/{shelf_id}")
    public List<Book> getUserShelf(@PathVariable("user_id") long userId, @PathVariable("shelf_id") long shelfId){
        return new ArrayList<>();
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
    public User getUserById(@PathVariable("user_id") long userId){
        return userService.getUserById(userId).get();
    }


    @PostMapping("/register")
    public void registerUser(@RequestBody User user){
        userService.addUser(user.getUsername(), user.getPassword());
    }
//
//    @PostMapping("/login")
//    public void login(@RequestBody User user){
//
//    }
//
//    @PostMapping("/logout")
//    public void logout(@RequestBody User user){
//
//    }

    @DeleteMapping("/user/{user_id}")
    public boolean deleteUserAccountByUserId(@PathVariable("user_id") long userId){
        return userService.deleteUserById(userId);
    }
}
