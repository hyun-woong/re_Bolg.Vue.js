package com.example.boardapi.util.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "400_1", "잘못된 요청입니다."),
    BAD_REQUEST_PASSWORD(HttpStatus.BAD_REQUEST, "400_2", "비밀번호가 일치하지 않습니다."),
    BAD_TOKEN(HttpStatus.BAD_REQUEST, "400_52", "유효하지 않은 토큰입니다."),

    // 404 Not Found
    NOT_FOUND_USER_ID(HttpStatus.NOT_FOUND, "404_0", "유저 아이디가 존재하지 않습니다."),

    ALREADY_EMAIL(HttpStatus.INTERNAL_SERVER_ERROR, "500_1", "중복된 이메일이 존재합니다.")

    ;


    private final HttpStatus httpStatus;
    private final String errorCode;
    private final String errorMessage;

}
