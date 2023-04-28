package com.codecool.bookclub.book.controller;

import com.codecool.bookclub.book.model.Book;
import com.codecool.bookclub.book.model.Shelf;
import com.codecool.bookclub.book.service.BookService;
import com.codecool.bookclub.book.service.GoogleApiBookService;
import com.codecool.bookclub.user.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
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

//    @PostMapping("/books/{id}/rate")
//    public String rate(@PathVariable("id") String id, @RequestBody int rating) {
//        return "Rate a book";
//    }


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


    @PostMapping("/books/shelves/{shelfType}/{id}")
    public void addBookToUserShelf(@PathVariable("shelfType") Shelf shelfType,
                                   @PathVariable("id") String externalId) {
        String userName = ((User)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmail();
        log.debug("addBookToUserShelf: shelf={}, id={}, user={}", shelfType, externalId, userName);
        Book book = googleApiBookService.getBookByExternalId(externalId);
        bookService.saveBookToShelf(book, shelfType, userName);
    }


}
