package com.codecool.bookclub.event.dto;

import com.codecool.bookclub.event.model.EventType;
import lombok.*;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewEventDto {
    private String title;
    private String description;
    private ZonedDateTime eventDate;
    private EventType eventType;
    private Integer maxParticipants;
    private String url;

    private String externalId;
    private String author;
    private String bookDescription;
    private String bookTitle;
    private Integer pages;
    private String pictureUrl;
    private BigDecimal rating;
    private Integer year;





}
