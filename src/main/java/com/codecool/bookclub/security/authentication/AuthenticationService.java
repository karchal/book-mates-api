package com.codecool.bookclub.security.authentication;

import com.codecool.bookclub.email.EmailService;
import com.codecool.bookclub.security.jwt.JwtService;
import com.codecool.bookclub.security.model.ConfirmationToken;
import com.codecool.bookclub.security.repository.ConfirmationTokenRepository;
import com.codecool.bookclub.security.repository.TokenRepository;
import com.codecool.bookclub.user.model.User;
import com.codecool.bookclub.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.codecool.bookclub.user.model.Role.READER;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final TokenRepository tokenRepository; //TODO move to the JwtService
    private final ConfirmationTokenRepository confirmationTokenRepository;
    private final EmailService emailService;

    public void register(RegisterRequest request) {
        User user = User.builder()
                .nickname(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(READER)
                .enabled(true) //TODO change to false, when activation is implemented
                .build();
        userRepository.save(user);
        ConfirmationToken confirmationToken = new ConfirmationToken(user);
        confirmationTokenRepository.save(confirmationToken);
        emailService.sendRegistrationEmail(request.getEmail(), confirmationToken.getToken());
    }

    public LoginResponse authenticate(LoginRequest request) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()
        );
        authenticationManager.authenticate(authentication);
        User user = userRepository.findByEmailIgnoreCase(request.getEmail()).orElseThrow();
        String jwt = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(user, null);
        return LoginResponse.builder()
                .token(jwt)
                .refreshToken(refreshToken)
                .build();
    }

    public LoginResponse refresh(String refreshToken) {
        String newRefreshToken = jwtService.generateRefreshToken(refreshToken);
        if (newRefreshToken != null && tokenRepository.findById(newRefreshToken).isPresent()) {
            String newJwt = jwtService.generateToken(tokenRepository.findById(newRefreshToken).get().getUser());
            return LoginResponse.builder()
                    .token(newJwt)
                    .refreshToken(newRefreshToken)
                    .build();
        } else {
            throw new IllegalArgumentException("Wrong refresh token");
        }
    }

    public boolean isEmailNotUnique(RegisterRequest request) {
        return userRepository.existsUserByEmail(request.getEmail());
    }

    public boolean isUsernameNotUnique(RegisterRequest request) {
        return userRepository.existsUserByNickname(request.getUsername());
    }

    public void deleteRefreshToken(String refreshToken) {
        if (refreshToken != null && tokenRepository.findById(refreshToken).isPresent()) {
            tokenRepository.deleteById(refreshToken);
        }
    }
}
