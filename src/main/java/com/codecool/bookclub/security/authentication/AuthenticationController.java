package com.codecool.bookclub.security.authentication;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/authentication")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<String> register(
            @Valid @RequestBody RegisterRequest request) {
        if (authenticationService.isEmailNotUnique(request)) {
            return ResponseEntity.status(400).body("Istnieje konto zarejestrowane przy użyciu podanego adresu email.");
        }
        if (authenticationService.isUsernameNotUnique(request)){
            return ResponseEntity.status(400).body("Podana nazwa użytkownika jest już zajęta.");
        };
        authenticationService.register(request);
        return ResponseEntity.accepted().body("user created");
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @RequestBody LoginRequest request
    ) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}
