package com.codecool.bookclub.forum.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentDto {
    private Long id;
    private LocalDateTime creationTime;
    private String commentMessage;
    private long authorId;
    private Long topicId;
    private String authorName;
}
