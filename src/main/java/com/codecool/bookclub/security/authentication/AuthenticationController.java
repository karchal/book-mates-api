package com.codecool.bookclub.security.authentication;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/authentication")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
@Slf4j
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
        }
        authenticationService.register(request);
        return ResponseEntity.accepted().body("Konto zostało utworzone. Na twój adres email został wysłany link aktywacyjny.");
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @RequestBody LoginRequest request
    ) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @PostMapping("/refresh")
    public ResponseEntity<LoginResponse> refresh(@RequestBody String refreshToken) {
        log.debug("Refresh token request: {}", refreshToken);
        try {
            return ResponseEntity.ok(authenticationService.refresh(refreshToken));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    @PostMapping("/logout_user")
    public void logoutUser(@RequestBody String refreshToken) {
        authenticationService.deleteRefreshToken(refreshToken);
    }

    @GetMapping("/confirm-account")
    public ResponseEntity<String> confirmAccount(@RequestParam String token){
        return authenticationService.confirmAccount(token);
    }

    @PostMapping("/reset_password_confirmation")
    public ResponseEntity<String> confirmResetPassword(@RequestParam String email){
        return ResponseEntity.ok(authenticationService.confirmResetPassword(email));
    }
    @PutMapping("/reset_password")
    public ResponseEntity<String> resetPassword(@RequestParam String token, @RequestBody ResetPasswordRequest password){
        return ResponseEntity.ok(authenticationService.resetPassword(token, password));
    }

}
