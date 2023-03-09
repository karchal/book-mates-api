package com.codecool.bookclub.event.dto;


import lombok.Builder;
import lombok.Data;


import java.time.LocalDateTime;

@Data
@Builder
public class EventDto {
    private long id;
    private LocalDateTime creationDateAndTime;
    private String title;
}
