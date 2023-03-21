package com.codecool.bookclub.user.model;

import com.codecool.bookclub.book.model.Book;
import com.codecool.bookclub.book.model.Shelf;
import com.codecool.bookclub.event.model.Event;
import com.codecool.bookclub.forum.model.Topic;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "readers")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private LocalDateTime creationDate;
//    @JoinTable
//    @OneToMany
//    private Map<Shelf, List<Book>> books;
//    @JoinTable
//    @OneToMany
//    private List<Topic> topics;
    @JoinTable
    @OneToMany
    private List<Event> events;
//    @JoinTable
//    @OneToMany
//    private Map<Book, BigDecimal> booksRatings;

}
