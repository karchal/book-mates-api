package com.codecool.bookclub.security.repository;

import com.codecool.bookclub.security.model.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface TokenRepository extends JpaRepository<RefreshToken, String> {
    int deleteRefreshTokenByExpirationDateBefore(Date date);

}
