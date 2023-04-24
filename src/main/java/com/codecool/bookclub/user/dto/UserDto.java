package com.codecool.bookclub.user.dto;

import com.codecool.bookclub.book.dto.BookDetailsDto;
import com.codecool.bookclub.event.dto.EventDetailsDto;
import com.codecool.bookclub.forum.dto.TopicDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private Long id;
    private String nickname;
    private LocalDateTime creationDate;
    private List<BookDetailsDto> booksDetails;
    private List<EventDetailsDto> events;
    private List<TopicDto> topics;
}
