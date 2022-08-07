package com.example.boardapi.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.boardapi.security.UserDetailsImpl;
import com.example.boardapi.user.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class JwtTokenUtils {

    // JWT 토큰의 생명: 30분만 살고 죽는다. (단위: milliseconds)
    private static final int JWT_TOKEN_VALID_MILLI_SEC = 60 * 60 * 60 * 1000; // 60 * 30 * 1000; //todo 배포할때 수정해야함
    public static final String CLAIM_EXPIRED_DATE = "EXPIRED_DATE";
    public static final String CLAIM_USER_NAME = "USER_NAME";
    public static final String CLAIM_USER_EMAIL = "USER_EMAIL";
    public static final String JWT_SECRET = "jwt_secret_!@#$%";

//    public static String generateJwtToken(UserDetailsImpl userDetails) {
//        String token = null;
//        try {
//            token = JWT.create()
//                    .withIssuer("Mr_W")
//                    .withClaim(CLAIM_USER_NAME, userDetails.getUsername())
//                    // 토큰 만료 일시 = 현재 시간 + 토큰 유효기간)
//                    .withClaim(CLAIM_EXPIRED_DATE, new Date(System.currentTimeMillis() + JWT_TOKEN_VALID_MILLI_SEC))
//                    .sign(generateAlgorithm());
//            log.info("일반유저 로그인 try 토큰 {}", token);
//        } catch (Exception e) {
//            log.info("일반유저 로그인 catch 토큰 {}", token);
//            System.out.println(e.getMessage());
//        }
//        log.info("일반유저 로그인 마지막 return 토큰 {}", token);
//        return token;
//    }
public static String generateJwtToken(User user) {
    String token = null;
    try {
        token = JWT.create()
                .withIssuer("Mr_W")
                .withClaim(CLAIM_USER_NAME, user.getUserId())
                .withClaim(CLAIM_USER_EMAIL, user.getEmail())
                // 토큰 만료 일시 = 현재 시간 + 토큰 유효기간)
                .withClaim(CLAIM_EXPIRED_DATE, new Date(System.currentTimeMillis() + JWT_TOKEN_VALID_MILLI_SEC))
                .sign(generateAlgorithm());
        log.info("일반유저 로그인 try 토큰 {}", token);
    } catch (Exception e) {
        log.info("일반유저 로그인 catch 토큰 {}", token);
        System.out.println(e.getMessage());
    }
    log.info("일반유저 로그인 마지막 return 토큰 {}", token);
    return token;
}

    private static Algorithm generateAlgorithm() {
        return Algorithm.HMAC256(JWT_SECRET);
    }

}
