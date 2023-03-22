package com.codecool.bookclub.user.model;

import com.codecool.bookclub.book.model.UserBook;
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
//    @ManyToMany
//    private List<UserBook> books;
    @OneToMany //@ManyToMany
    private List<Topic> topics;
    @OneToMany //@ManyToMany
    private List<Event> events;
    @OneToMany //@ManyToMany
    private List<Comment> comments;
    @ManyToMany
    private Set<Role> roles = new HashSet<>();

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
