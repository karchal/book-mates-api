package com.codecool.bookclub.security.authentication;

import com.codecool.bookclub.security.jwt.JwtService;
import com.codecool.bookclub.security.model.RefreshToken;
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

    public void register(RegisterRequest request) {
        User user = User.builder()
                .nickname(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(READER)
                .build();
        userRepository.save(user);
    }

    public LoginResponse authenticate(LoginRequest request) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()
        );
        authenticationManager.authenticate(authentication);
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        String jwt = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(user, null);
        return LoginResponse.builder()
                .token(jwt)
                .refreshToken(refreshToken)
                .build();
    }

    /*TODO*/
    public LoginResponse refresh(String refreshToken) {
        return null;
    }

    public boolean isEmailNotUnique(RegisterRequest request) {
        return userRepository.existsUserByEmail(request.getEmail());
    }

    public boolean isUsernameNotUnique(RegisterRequest request) {
        return userRepository.existsUserByNickname(request.getUsername());
    }
}
