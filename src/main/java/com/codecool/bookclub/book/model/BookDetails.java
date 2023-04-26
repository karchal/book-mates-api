package com.codecool.bookclub.book.model;

import com.codecool.bookclub.user.model.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table
public class BookDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Book book;
    @JsonIgnoreProperties
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    private Shelf shelf;
    private Integer userRating;

    @Override
    public String toString() {
        return "BookDetails{" +
                "id=" + id +
                ", book=" + book +
                ", userId=" + user.getId() +
                ", shelf=" + shelf +
                ", userRating=" + userRating +
                '}';
    }
}
