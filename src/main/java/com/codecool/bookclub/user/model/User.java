package com.codecool.bookclub.user.model;

import com.codecool.bookclub.book.model.BookDetails;
import com.codecool.bookclub.event.model.Event;
import com.codecool.bookclub.forum.model.Comment;
import com.codecool.bookclub.forum.model.Topic;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "reader")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;
    private String password;
    private LocalDateTime creationDate;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    private List<BookDetails> booksDetails;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "author")
    private List<Topic> topics;
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "participants")
    private List<Event> events;
    @ManyToMany
    private List<Comment> comments;
    private Role role;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.<GrantedAuthority>of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
