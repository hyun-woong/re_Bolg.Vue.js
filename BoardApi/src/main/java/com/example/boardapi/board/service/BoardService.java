package com.example.boardapi.board.service;

import com.example.boardapi.board.dto.BoardRequestDto;
import com.example.boardapi.board.dto.BoardResponseDto;
import com.example.boardapi.board.entity.Board;
import com.example.boardapi.board.repository.BoardRepository;
import com.example.boardapi.security.UserDetailsImpl;
import com.example.boardapi.user.entity.User;
import com.example.boardapi.user.repository.UserRepository;
import com.example.boardapi.util.exception.CustomException;
import com.example.boardapi.util.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    public ResponseEntity<Object> getBoardList(UserDetailsImpl userDetails) {
        if (userDetails == null) {
            throw new CustomException(ErrorCode.NOT_FOUND_USER_ID);
        }

        List<Board> boardList = boardRepository.findAll();
        List<BoardResponseDto> result = new ArrayList<>();

        for (Board b : boardList) {
            BoardResponseDto responseDto = new BoardResponseDto(b);
            result.add(responseDto);
        }

        return ResponseEntity
                .ok()
                .body(result);
    }

    @Transactional
    public ResponseEntity<Object> createPost(UserDetailsImpl userDetails,
                                             BoardRequestDto requestDto) {
        if (userDetails == null) {
            throw new CustomException(ErrorCode.NOT_FOUND_USER_ID);
        }
        User user = userRepository.findByEmail(userDetails.getUsername());

        userRepository.delete(user);

        Board board = new Board(requestDto, user);

        boardRepository.save(board);

        return ResponseEntity
                .ok()
                .body("게시글 작성 완료!");
    }
}
