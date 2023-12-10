package com.test.assignment.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Entity
@Getter
@Table(name = "BOARD_DEF")
public class BoardDef {
    @Id
    @Enumerated(EnumType.STRING)
    @Column(name = "BOARD_CD", unique = true)
    private BoardCdType boardCd; //게시판 코드

    @Column(name = "BOARD_NM")
    private String boardNm; //게시판 이름

    @OneToMany(mappedBy = "boardDef", cascade = CascadeType.REMOVE)
    private List<Post> posts;

    public enum BoardCdType{
        ALL, NTC, FRB, QNA
    }
}
