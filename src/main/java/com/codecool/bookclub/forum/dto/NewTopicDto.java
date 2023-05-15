package com.codecool.bookclub.forum.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewTopicDto {
    private String bookId;
    private String title;
    private String message;
    private long authorId;

}
