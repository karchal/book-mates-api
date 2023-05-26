package com.codecool.bookclub.event.dto;

import com.codecool.bookclub.event.model.ParticipantType;
import lombok.*;

import java.time.ZonedDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventDetailsDto {
    long id;
    private long userId;
    private long eventId;
    private long bookId;
    private String title;
    private ZonedDateTime eventDate;
    private String pictureUrl;
    private ParticipantType participantType;

}
