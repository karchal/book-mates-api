package com.codecool.bookclub.event.dto;

import com.codecool.bookclub.event.model.EventType;
import lombok.*;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventDto {
    private long id;
    private LocalDateTime creationDateAndTime;
    private String title;
    private String description;

    private ZonedDateTime eventDate;

    private EventType eventType;

    private Integer maxParticipants;

    private String url;

    private long bookId;

    private String bookTitle;
    private String bookAuthor;

    private String pictureUrl;

    private Integer participants;
    private Integer waitingList;
    private String organizatorName;

}
