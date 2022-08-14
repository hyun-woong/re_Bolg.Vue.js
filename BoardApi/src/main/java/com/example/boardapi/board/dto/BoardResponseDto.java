package com.example.boardapi.board.dto;

import com.example.boardapi.board.entity.Board;
import lombok.Getter;

@Getter
public class BoardResponseDto {
    private String title;
    private String username;
    private String date;

    public BoardResponseDto(Board board) {
        this.title = board.getTitle();
        this.username = board.getUser().getNickname();
        this.date = board.getCreatedAt();
    }

    public BoardResponseDto(BoardRequestDto dto) {
        this.title = dto.getTitle();
    }
}
