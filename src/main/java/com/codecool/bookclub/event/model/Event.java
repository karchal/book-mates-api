package com.codecool.bookclub.event.model;

import com.codecool.bookclub.book.model.Book;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

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
    private Integer maxParticipants;
    private String url;

}
