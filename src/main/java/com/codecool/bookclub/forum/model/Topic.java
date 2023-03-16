package com.codecool.bookclub.forum.model;

import com.codecool.bookclub.user.model.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Topic {

    private Long id;
    private String title;
    private String message;
    private LocalDateTime creationTime;
    private List<Comment> comments = new ArrayList<>();
    private User author;
}
