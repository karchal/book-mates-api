package com.codecool.bookclub.security.authentication;

import com.codecool.bookclub.security.jwt.JwtService;
import com.codecool.bookclub.user.model.User;
import com.codecool.bookclub.user.repository.UserRepository;
import com.codecool.bookclub.validation.ObjectsValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

import static com.codecool.bookclub.user.model.Role.READER;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final ObjectsValidator<RegisterRequest> validator;

    public String register(RegisterRequest request) {

        RegisterResponse response = new RegisterResponse();
        response.setEmailUnique(!userRepository.existsUserByEmail(request.getEmail()));
        response.setUsernameUnique(!userRepository.existsUserByNickname(request.getUsername()));
        if (!(response.isEmailUnique() && response.isUsernameUnique())) {
            response.setMessage("data not unique");
            return response.getMessage();
        }
        User user = User.builder()
                .nickname(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(READER)
                .build();
        userRepository.save(user);
        response.setMessage("account created");
        return response.getMessage();
    }

    public LoginResponse authenticate(LoginRequest request) {
        System.out.println("email: " + request.getEmail() + " password: " + request.getPassword());
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()
        );
        authenticationManager.authenticate(authentication);
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        String jwt = jwtService.generateToken(user);
        return LoginResponse.builder()
                .token(jwt)
                .build();
    }
}
