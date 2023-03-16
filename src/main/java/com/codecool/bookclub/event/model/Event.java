package com.codecool.bookclub.event.model;


import com.codecool.bookclub.user.model.User;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class Event {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
