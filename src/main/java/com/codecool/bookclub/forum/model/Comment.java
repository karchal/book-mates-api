package com.codecool.bookclub.forum.model;

import com.codecool.bookclub.user.model.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Comment {
    private Long id;
    private String message;
    private LocalDateTime creationTime;
    private User author;

}
