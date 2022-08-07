package com.example.boardapi.user.service;

import com.example.boardapi.security.jwt.JwtTokenUtils;
import com.example.boardapi.user.dto.LoginDto;
import com.example.boardapi.user.dto.SignupDto;
import com.example.boardapi.user.entity.User;
import com.example.boardapi.user.repository.UserRepository;
import com.example.boardapi.util.exception.CustomException;
import com.example.boardapi.util.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    //회원가입
    @Transactional
    public ResponseEntity<User> signup(SignupDto signupDto) {
        String pw = passwordEncoder.encode(signupDto.getPassword());
        User user = new User(signupDto, pw);
        userRepository.save(user);
        return ResponseEntity.ok().body(user);
    }

    public HttpHeaders login(LoginDto loginDto) {
        User user = userRepository.findByEmail(loginDto.getUserId());
        if (!passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
            throw new CustomException(ErrorCode.NOT_FOUND_USER_ID);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "BEARER " + JwtTokenUtils.generateJwtToken(user));
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
        return headers;
    }
}
