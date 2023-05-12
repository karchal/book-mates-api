package com.codecool.bookclub.user.model;

import com.codecool.bookclub.book.model.BookDetails;
import com.codecool.bookclub.event.model.EventDetails;
import com.codecool.bookclub.forum.model.Comment;
import com.codecool.bookclub.forum.model.Topic;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "reader")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @NotEmpty
    @Column(unique=true)
    private String nickname;
    @NotNull
    @NotEmpty
    @Column(unique=true)
    private String email;
    @NotNull
    @NotEmpty
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    @CreationTimestamp
    private LocalDateTime creationDate;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<BookDetails> booksDetails;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<EventDetails> events;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "author")
    private List<Topic> topics;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "author")
    private List<Comment> comments;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + role.name()));
    }

    @Override
    public String getUsername() {
        return email;
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
        return true;
    }
}
