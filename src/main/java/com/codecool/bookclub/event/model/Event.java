package com.codecool.bookclub.event.model;

import com.codecool.bookclub.book.model.Book;
import com.codecool.bookclub.user.model.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "event")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @CreationTimestamp
    private LocalDateTime creationDateAndTime;

    @ManyToOne
    private Book book;
    private String title;
    @Column(length = 4000)
    private String description;
    private ZonedDateTime eventDate;

    private EventType eventType;

    @ManyToOne
    private User organizer;
    private Integer maxParticipants;

    @ManyToMany
    private List<User> participants;

    @ManyToMany
    private List<User> waitingList;
    private String url;

}
