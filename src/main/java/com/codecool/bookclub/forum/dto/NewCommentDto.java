package com.codecool.bookclub.forum.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewCommentDto {
    private String commentMessage;
    private long authorId;
    private Long topicId;
}
