package com.codecool.bookclub.book.controller;

import com.codecool.bookclub.book.model.Book;
import com.codecool.bookclub.book.service.BookService;
import com.codecool.bookclub.book.service.GoogleApiBookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api")
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class BookController {

    private final BookService bookService;
    private final GoogleApiBookService googleApiBookService;

    public BookController(BookService bookService, GoogleApiBookService googleApiBookService) {
        this.bookService = bookService;
        this.googleApiBookService = googleApiBookService;
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable("id") long id) {
        Book book = bookService.getById(id);
        if (book == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(book, HttpStatus.OK);
        }
        //return bookService.getById(id).ifPresent(new ResponseEntity<>(book, HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/books/{id}/rate")
    public String rate(@PathVariable("id") String id, @RequestBody int rating) {
        return "Rate a book";
    }


    @GetMapping("/books")
    public List<Book> showBooks() {
        return bookService.findTopFourBooks();
    }


    @GetMapping("/books/search")
    public List<Book> searchBooks(@RequestParam(value = "criteria", required = false) String criteria,
                                  @RequestParam(value = "query", required = false) String query) {
        return googleApiBookService.searchBooks(criteria, query);
    }

    @GetMapping("/books/search/{id}")
    public Book getBookByExternalId(@PathVariable String id) {
        return googleApiBookService.getBookByExternalId(id);
    }


}
