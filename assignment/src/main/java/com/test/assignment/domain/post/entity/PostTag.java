package com.test.assignment.domain.post.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "POST_TAG")
public class PostTag {
    @Id
    @Column(name = "BOARD_TAG_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer boardTagId;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "POST_NO", referencedColumnName = "POST_NO"),
            @JoinColumn(name = "BOARD_CD", referencedColumnName = "BOARD_CD")
    })
    private Post post; // 글번호, 게시판(분류)코드와 연관관계

    @ManyToOne
    @JoinColumn(name = "TAG_NO")
    private Tag tag;
}
