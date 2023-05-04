package com.codecool.bookclub.forum.model;

import com.codecool.bookclub.user.model.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 1000)
    private String message;
    @CreationTimestamp
    private LocalDateTime creationTime;
    @ManyToOne
    private User author;
    @JsonIgnoreProperties
    @ManyToOne(fetch = FetchType.LAZY)
    private Topic topic;

}
