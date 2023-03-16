package com.codecool.bookclub.user.repository;

import com.codecool.bookclub.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
