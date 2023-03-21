package com.codecool.bookclub.user.model;

import com.codecool.bookclub.book.model.UserBook;
import com.codecool.bookclub.event.model.Event;
import com.codecool.bookclub.forum.model.Topic;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

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
