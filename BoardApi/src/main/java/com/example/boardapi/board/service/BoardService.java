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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
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

        Board board = new Board(requestDto, user);

        boardRepository.save(board);

        return ResponseEntity
                .ok()
                .body("????????? ?????? ??????!");
    }

    //dummy data
    @Transactional
    @PostConstruct
    public void postContent() {
        String pw = encoder.encode("1q2w3e!");

        User user = new User("sparta1@gmail.com", pw, "nickName");
        userRepository.save(user);

        List<Board> boardList = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            String title = "??????" + i;
            String content = "??????" + i;
            Board board = new Board(title, content, user);
            boardList.add(board);
        }

        boardRepository.saveAll(boardList);
    }

    @Transactional
    public ResponseEntity<?> deletePost(UserDetailsImpl userDetails, Long boardId) {
        Board board = boardRepository.findById(boardId).orElseThrow(
                () -> new NullPointerException("???????????? ???????????? ????????????.")
        );

        boardRepository.delete(board);

        return ResponseEntity.ok().body("?????? ??????");
    }

    public ResponseEntity<?> getBoard(UserDetailsImpl userDetails, Long boardId) {
        Board board = boardRepository.findById(boardId).orElseThrow(
                () -> new EntityNotFoundException("???????????? ???????????? ????????????.")
        );

        BoardResponseDto responseDto = new BoardResponseDto(board, userDetails);

        return ResponseEntity
                .ok()
                .body(responseDto);
    }

    @Transactional
    public ResponseEntity<?> updatePost(UserDetailsImpl userDetails, Long boardId, BoardRequestDto requestDto) {

        Board board = boardRepository.findById(boardId).orElseThrow(
                () -> new EntityNotFoundException("??????")
        );

        board.update(requestDto);

        boardRepository.save(board);
        return ResponseEntity
                .ok()
                .body("??????");
    }
}
