package com.codecool.bookclub.user.controller;

import com.codecool.bookclub.user.dto.UserDto;
import com.codecool.bookclub.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000/account")
@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @GetMapping("/users/{user_id}")
    public ResponseEntity<UserDto> getUserProfile(@PathVariable("user_id") long userId){
        UserDto userDto = userService.getUserDto(userId);
        if (userDto != null) {
            return new ResponseEntity<>(userDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/users/profile")
    public ResponseEntity<UserDto> getUserProfile(@AuthenticationPrincipal Long userId){
        UserDto userDto = userService.getUserDto(userId);
        if (userDto != null) {
            return new ResponseEntity<>(userDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/users/{user_id}")
    public boolean deleteUserAccountByUserId(@PathVariable("user_id") long userId){
        return userService.deleteUserById(userId);
    }
}
