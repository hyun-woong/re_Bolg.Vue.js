package com.example.boardapi.user.entity;

import com.example.boardapi.user.dto.SignupDto;
import com.example.boardapi.util.Timestamped;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class User extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nickname;

    public User(SignupDto dto, String pw) {
        this.email = dto.getEmail();
        this.password = pw;
        this.nickname = dto.getNickname();
    }
}
