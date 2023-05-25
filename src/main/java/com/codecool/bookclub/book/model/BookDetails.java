package com.codecool.bookclub.book.model;

import com.codecool.bookclub.user.model.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table
public class BookDetails {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookDetails that = (BookDetails) o;
        return Objects.equals(book, that.book) && Objects.equals(user, that.user) && shelf == that.shelf;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, book, user, shelf);
    }

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
