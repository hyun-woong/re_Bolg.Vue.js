package com.example.boardapi.board.controller;

import com.example.boardapi.board.dto.BoardRequestDto;
import com.example.boardapi.board.dto.BoardResponseDto;
import com.example.boardapi.board.service.BoardService;
import com.example.boardapi.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    //게시글 목록 조회
    @GetMapping("/board")
    public ResponseEntity<Object> getBoardList(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return boardService.getBoardList(userDetails);
    }

    @PostMapping("/board")
    public ResponseEntity<Object> createPost(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                             @RequestBody BoardRequestDto requestDto) {
        return boardService.createPost(userDetails, requestDto);
    }
}
