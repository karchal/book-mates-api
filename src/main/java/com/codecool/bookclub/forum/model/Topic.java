package com.codecool.bookclub.forum.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Topic {

    private int id;
    private String title;
    private String msg;
    private LocalDateTime creationTime;
    private List<Comment> comments = new ArrayList<>();
}
