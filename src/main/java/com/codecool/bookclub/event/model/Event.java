package com.codecool.bookclub.event.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Event {

    private long id;

    private LocalDateTime creationDateAndTime;
    private String title;
}
