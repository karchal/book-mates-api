package com.codecool.bookclub.user.repository;

import com.codecool.bookclub.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findById(Long id);

    Optional<User> findByEmailIgnoreCase(String email);

    boolean existsUserByEmail(String email);

    boolean existsUserByNickname(String nickname);
}
