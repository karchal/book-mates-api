package com.codecool.bookclub.book.controller;

import com.codecool.bookclub.book.model.Book;
import com.codecool.bookclub.book.model.Shelf;
import com.codecool.bookclub.book.service.BookService;
import com.codecool.bookclub.book.service.GoogleApiBookService;
import com.codecool.bookclub.event.dto.EventDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    }

    @GetMapping("/books/all")
    public ResponseEntity<List<Book>> recommendBooks() {
        List<Book> books = bookService.getAllBooks();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/books")
    public Page<Book> getAllBooks(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "12") int size){
        Pageable paging = PageRequest.of(page,size, Sort.by("id").descending());
        return bookService.findAllBooks(paging);
    }

    @GetMapping("/books/homepage")
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
                                   @PathVariable("id") String externalId,
                                   @AuthenticationPrincipal Long userId) {
        log.debug("addBookToUserShelf: shelf={}, id={}, user={}", shelfType, externalId, userId);
        Book book = googleApiBookService.getBookByExternalId(externalId);
        bookService.saveBookToShelf(book, shelfType, userId);
    }


}
