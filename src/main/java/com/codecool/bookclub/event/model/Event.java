package com.codecool.bookclub.event.model;


import com.codecool.bookclub.user.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@Entity
//@Table(name = "event")
public class Event {

//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;
//    @CreationTimestamp
    private LocalDateTime creationDateAndTime;
    private String title;
    private String description;
    private LocalDateTime eventDate;
    private User organizer;
    private Integer maxParticipants;
    private List<User> participants;
    private List<User> waitingList;
    private String url;


}
