package com.codecool.bookclub.forum.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TopicDto {
    private Long id;
    private Long bookId;
    private String bookPictureUrl;
    private String bookTitle;
    private String bookAuthor;
    private String title;
    private String authorName;
    private LocalDateTime creationTime;

}
