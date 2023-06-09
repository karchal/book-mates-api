package com.codecool.bookclub.security.model;

import com.codecool.bookclub.user.model.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class ConfirmationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String token;
    @CreationTimestamp
    private LocalDateTime creationTime;
    @OneToOne
    private User user;

    public ConfirmationToken(User user){
        this.user = user;
        token = UUID.randomUUID().toString();
    }
}
