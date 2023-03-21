package com.codecool.bookclub.user.model;

import com.codecool.bookclub.book.model.UserBook;
import com.codecool.bookclub.event.model.Event;
import com.codecool.bookclub.forum.model.Topic;
import com.codecool.bookclub.book.model.Book;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

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
    //private List<UserBook> books;
    //private List<Topic> topics;
    //private List<Event> events;

}
