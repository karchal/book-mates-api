package com.codecool.bookclub.event.model;

import com.codecool.bookclub.user.model.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
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
    private String title;
    private String description;
    private LocalDateTime eventDate;

    @OneToOne //@OneToMany
    private User organizer;
    private Integer maxParticipants;

    @OneToMany //@ManyToMany
    private List<User> participants;

    @OneToMany //@ManyToMany
    private List<User> waitingList;
    private String url;

    public Event(String title, String description, LocalDateTime eventDate, Integer maxParticipants, String url) {
        this.title = title;
        this.description = description;
        this.eventDate = eventDate;
        this.maxParticipants = maxParticipants;
        this.url = url;
    }
}
