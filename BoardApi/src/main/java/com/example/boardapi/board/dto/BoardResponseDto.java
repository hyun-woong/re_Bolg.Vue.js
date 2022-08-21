package com.example.boardapi.board.dto;

import com.example.boardapi.board.entity.Board;
import com.example.boardapi.security.UserDetailsImpl;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BoardResponseDto {
    private String title;
    private String username;
    private LocalDateTime date;
    private Long boardId;
    private String content;

    public BoardResponseDto(Board board) {
        this.title = board.getTitle();
        this.username = board.getUser().getNickname();
        this.date = board.getCreatedAt();
        this.boardId = board.getBoardId();
    }

    public BoardResponseDto(Board board, UserDetailsImpl userDetails) {
        this.title = board.getTitle();
        this.username = userDetails.getUsername();
        this.date = board.getCreatedAt();
        this.boardId = board.getBoardId();
        this.content = board.getContent();
    }
}
