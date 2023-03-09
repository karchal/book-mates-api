package com.codecool.bookclub.book.controller;

import org.springframework.web.bind.annotation.*;

@RequestMapping("/api")
@RestController
public class BookController {

    @GetMapping("/books")
    public String getAll(@RequestParam(value = "author", required = false) String author,
                         @RequestParam(value = "title", required = false) String title,
                         @RequestParam(value = "genre", required = false) String genre) {
        return "Here you'll see all books";
    }

    @GetMapping("/books/{id}")
    public String getBookById(@PathVariable("id") String id) {
        return "The book details";
    }

    @PostMapping("books/{id}/rate")
    public String rate(@PathVariable("id") String id, @RequestBody int rating) {
        return "Rate a book";
    }



}
