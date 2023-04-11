package com.codecool.bookclub.user.model;

import com.codecool.bookclub.book.model.BookDetails;
import com.codecool.bookclub.event.model.Event;
import com.codecool.bookclub.forum.model.Comment;
import com.codecool.bookclub.forum.model.Topic;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "reader")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private LocalDateTime creationDate;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    private List<BookDetails> booksDetails;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "author")
    private List<Topic> topics;
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "participants")
    private List<Event> events;
    @ManyToMany
    private List<Comment> comments;
    @ManyToMany
    private Set<Role> roles = new HashSet<>();

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
