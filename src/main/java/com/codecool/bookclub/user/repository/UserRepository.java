package com.codecool.bookclub.user.repository;

import com.codecool.bookclub.book.model.BookDetails;
import com.codecool.bookclub.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    public Optional<User> findById(Long id);

    Optional<User> findByEmail(String email);

    boolean existsUserByEmail(String email);

    boolean existsUserByNickname(String nickname);
}
