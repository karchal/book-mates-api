package com.codecool.bookclub.event.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@Builder
public class EventDto {
    private long id;
    private LocalDateTime creationDateAndTime;
    private String title;
}
