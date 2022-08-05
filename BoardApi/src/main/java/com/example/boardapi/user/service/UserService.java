package com.example.boardapi.user.service;

import com.example.boardapi.user.dto.SignupDto;
import com.example.boardapi.user.entity.User;
import com.example.boardapi.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    //회원가입
    @Transactional
    public ResponseEntity<User> signup(SignupDto signupDto) {
        User user = new User(signupDto);
        userRepository.save(user);
        return ResponseEntity.ok().body(user);
    }
}
