package com.codecool.bookclub.security.token.model;

import com.codecool.bookclub.user.model.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class RefreshToken {

    @Id
    @Column(length = 1000)
    private String token;

    private Date expirationDate;

    @ManyToOne
    private User user;


}
