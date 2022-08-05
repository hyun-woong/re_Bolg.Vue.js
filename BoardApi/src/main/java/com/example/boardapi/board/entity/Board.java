package com.example.boardapi.board.entity;

import com.example.boardapi.user.entity.User;
import com.example.boardapi.util.Timestamped;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Board extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardId;

    @Column
    private String title;

    @Column
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;
}
