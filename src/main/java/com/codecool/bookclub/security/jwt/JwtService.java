package com.codecool.bookclub.security.jwt;

import com.codecool.bookclub.security.model.RefreshToken;
import com.codecool.bookclub.security.repository.TokenRepository;
import com.codecool.bookclub.user.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

@Service
@Slf4j
public class JwtService {

    @Value("${secret.key}")
    private String secretKey;

    @Value("${refreshToken.expiry}")
    private long refreshTokenExpiration;
    @Value("${token.expiry}")
    private long tokenExpiration;

    private final TokenRepository tokenRepository;

    public JwtService(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(UserDetails userdetails) {
        return generateToken(new HashMap<>(), userdetails);
    }

    public String generateRefreshToken(UserDetails userDetails, Date expirationDate) {
        if (expirationDate == null) {
            expirationDate = new Date(System.currentTimeMillis() + 1000 * 86400 * refreshTokenExpiration);
        }
        String refreshToken = Jwts
                .builder()
                .setSubject(((User)userDetails).getId().toString())
                .setIssuedAt(new Date())
                .setExpiration(expirationDate)
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
        RefreshToken refreshTokenEntity = new RefreshToken(refreshToken, expirationDate, (User)userDetails);
        tokenRepository.save(refreshTokenEntity);
        return refreshToken;
    }

    public String generateRefreshToken(String refreshToken) {
        Optional<RefreshToken> token = tokenRepository.findById(refreshToken);
        if (token.isEmpty()) return null;
        if (token.get().getExpirationDate().before(new Date())) {
            tokenRepository.deleteById(refreshToken);
            log.debug("Token expired, removing from database");
            return null;
        }
        tokenRepository.deleteById(refreshToken);
        return generateRefreshToken(token.get().getUser(), token.get().getExpirationDate());
    }


    private String generateToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails
    ) {
        return Jwts
                .builder() // Returns a new JwtBuilder instance that can be configured and then used to create JWT compact serialized strings.
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername()) // method getUsername (from User class) returns email
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * tokenExpiration)) // 1h
                .signWith(getSigningKey(), SignatureAlgorithm.HS256) // Signs the constructed JWT with the specified key using the specified algorithm, producing a JWS (but return type is JwtBuilder)
                .compact(); // Actually builds the JWS and serializes it to a compact, URL-safe string according to the JWT Compact Serialization  rules.
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token) // tu wywala ExpiredJwtException wewnątrz jest sprawdzenie czy token wygasł
                .getBody();
    }

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
