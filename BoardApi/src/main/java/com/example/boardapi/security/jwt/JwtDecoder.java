package com.example.boardapi.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.example.boardapi.util.exception.CustomException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;
import java.util.Optional;

import static com.example.boardapi.security.jwt.JwtTokenUtils.*;
import static com.example.boardapi.util.exception.ErrorCode.*;

@Component
public class JwtDecoder {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public String decodeUsername(String token) {
        DecodedJWT decodedJWT = isValidToken(token)
                .orElseThrow(() -> new CustomException(BAD_TOKEN));

        Date expiredDate = decodedJWT
                .getClaim(CLAIM_EXPIRED_DATE)
                .asDate();

        Date now = new Date();
        if (expiredDate.before(now)) {
            throw new CustomException(BAD_TOKEN);
        }

        return decodedJWT
                .getClaim(CLAIM_USER_EMAIL)
                .asString();
    }

    public Optional<DecodedJWT> isValidToken(String token) {
        DecodedJWT jwt = null;
        log.info("token : {}", token);
        try {
            Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET);
            JWTVerifier verifier = JWT
                    .require(algorithm)
                    .build();

            jwt = verifier.verify(token);
            log.info("try jwt 부분 {}", jwt);
        } catch (CustomException e) {
//            log.error(e.getMessage());
            log.info("catch jwt 부분 {}", jwt);
            return Optional.ofNullable(jwt);
        }
        log.info("맨 마지막 return jwt 부분 {}", jwt);
        return Optional.ofNullable(jwt);
    }
}
