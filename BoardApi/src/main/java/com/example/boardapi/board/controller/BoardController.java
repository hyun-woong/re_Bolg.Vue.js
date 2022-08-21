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
@RequestMapping("/api/board")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    //게시글 목록 조회
    @GetMapping
    public ResponseEntity<Object> getBoardList(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return boardService.getBoardList(userDetails);
    }

    @GetMapping("/{boardId}")
    public ResponseEntity<?> getBoard(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                      @PathVariable(value = "boardId") Long boardId) {
        return boardService.getBoard(userDetails, boardId);
    }

    @PostMapping
    public ResponseEntity<Object> createPost(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                             @RequestBody BoardRequestDto requestDto) {
        return boardService.createPost(userDetails, requestDto);
    }

    @DeleteMapping("/{boardId}")
    public ResponseEntity<?> deletePost(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                        @PathVariable Long boardId) {

        return boardService.deletePost(userDetails, boardId);
    }

    @PutMapping("/{boardId}")
    public ResponseEntity<?> updatePost(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                        @PathVariable Long boardId,
                                        @RequestBody BoardRequestDto requestDto) {
        return boardService.updatePost(userDetails, boardId, requestDto);
    }
}
