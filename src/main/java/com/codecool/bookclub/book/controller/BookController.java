package com.codecool.bookclub.book.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class BookController {

    @GetMapping("api/books")
    public String getAll(@RequestParam(value = "author", required = false) String author,
                         @RequestParam(value = "title", required = false) String title,
                         @RequestParam(value = "genre", required = false) String genre) {
        return "Here you'll see all books";
    }

    @GetMapping("api/books/{id}")
    public String getBookById(@PathVariable("id") String id) {
        return "The book details";
    }

    @PostMapping("api/books/{id}/rate")
    public String rate(@PathVariable("id") String id, @RequestBody int rating) {
        return "Rate a book";
    }

    /**
     * Returns the shelves. By default, only logged user shelves are returned, unless
     * userId parameter is provided.
     * @param userId If provided, the specific user's shelves are returned
     * @return
     */
    @GetMapping("")
    public String getShelves(@RequestParam(value = "userId", required = false) String userId) {
        return "";

    }






}
