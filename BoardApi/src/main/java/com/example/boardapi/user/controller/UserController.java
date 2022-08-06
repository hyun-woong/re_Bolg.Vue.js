package com.example.boardapi.user.controller;

import com.example.boardapi.user.dto.SignupDto;
import com.example.boardapi.user.entity.User;
import com.example.boardapi.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    private ResponseEntity<User> signup(
            @RequestBody SignupDto signupDto
    ) {
       return userService.signup(signupDto);
    }
}
