package com.codecool.bookclub.security.token.repository;

import com.codecool.bookclub.security.token.model.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface TokenRepository extends JpaRepository<RefreshToken, String> {
    int deleteRefreshTokenByExpirationDateBefore(Date date);

}
