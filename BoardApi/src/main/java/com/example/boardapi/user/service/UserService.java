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
        User findUser = userRepository.findByEmail(signupDto.getEmail());
        if (findUser != null) {
            throw new CustomException(ErrorCode.ALREADY_EMAIL);
        }
        String pw = passwordEncoder.encode(signupDto.getPassword());
        User user = new User(signupDto, pw);
        userRepository.save(user);
        return ResponseEntity.ok().body(user);
    }

    public HttpHeaders login(LoginDto loginDto) {
        User user = userRepository.findByEmail(loginDto.getUserId());
        if (user == null) {
            throw new CustomException(ErrorCode.BAD_REQUEST);
        }
        if (!passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
            throw new CustomException(ErrorCode.BAD_REQUEST_PASSWORD);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "BEARER " + JwtTokenUtils.generateJwtToken(user));
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
        return headers;
    }
}
