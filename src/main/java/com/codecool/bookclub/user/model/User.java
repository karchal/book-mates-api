package com.codecool.bookclub.user.model;

import com.codecool.bookclub.book.model.Book;
import com.codecool.bookclub.book.model.Shelf;
import com.codecool.bookclub.book.model.UserBook;
import com.codecool.bookclub.event.model.Event;
import com.codecool.bookclub.forum.model.Topic;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class User {
    private Long id;
    private String username;
    private String password;
    private LocalDateTime creationDate;
    //private Map<Shelf, List<Book>> books;
    private List<UserBook> books;
    private List<Topic> topics;
    private List<Event> events;
    //private Map<Book, BigDecimal> booksRatings;

}
