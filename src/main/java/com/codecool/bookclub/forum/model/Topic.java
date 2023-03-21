package com.codecool.bookclub.forum.model;

import com.codecool.bookclub.book.model.Book;
import com.codecool.bookclub.user.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "topic")
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String message;
    @CreationTimestamp
    private LocalDateTime creationTime;
    @ManyToOne
    private Book book;
    @OneToMany
    private List<Comment> comments = new ArrayList<>();
    @ManyToOne
    private User author;
}
