package com.codecool.bookclub.user.controller;

import com.codecool.bookclub.book.model.Book;
import com.codecool.bookclub.forum.model.Topic;
import com.codecool.bookclub.user.model.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController("/api")
public class UserController {


    @GetMapping("users/{user_id}/books")
    public List<Book> getUserBooks(@PathVariable("user_id") int userId){
        return new ArrayList<>();
    }

    @GetMapping("users/{user_id}/events")
    public List<String> getUserEvents(@PathVariable("user_id") int userId){
        return new ArrayList<>();
    }

    @GetMapping("users/{user_id}/topics")
    public List<Topic> getUserTopics(@PathVariable("user_id") int userId){
        return new ArrayList<>();
    }

    @PutMapping("users/{user_id}/shelves")
    public boolean updateShelfType(@PathVariable("user_id") int userId, @RequestBody Book book){
        return false;
    }

    @GetMapping("users/{user_id}/shelves/{shelf_id}")
    public List<Book> getUserShelf(@PathVariable("user_id") int userId, @PathVariable("shelf_id") int shelfId){
        return new ArrayList<>();
    }

    @DeleteMapping("users/{user_id}/books")
    public boolean deleteBook(@PathVariable("user_id") int userId, @RequestBody Book book){
        return false;
    }

    @GetMapping("user/{user_id}")
    public User getUserById(@PathVariable("user_id") int userId){
        return new User();
    }


    @PostMapping("/register")
    public void registerUser(@RequestBody User user){

    }

    @PostMapping("/login")
    public void login(@RequestBody User user){

    }

    @PostMapping("/logout")
    public void logout(@RequestBody User user){

    }

    @DeleteMapping("user/{user_id}")
    public boolean deleteUserAccount(@PathVariable("user_id") int userId){
        return false;
    }
}
