package com.test.assignment.entity;

import com.test.assignment.auditing.Auditing;
import com.test.assignment.dto.request.PostRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "POST")
@NoArgsConstructor
public class Post extends Auditing {
    @Id
    @Column(name = "POST_NO")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postNo; //글 번호

    @ManyToOne
    @JoinColumn(name = "BOARD_CD")
    private BoardDef boardDef;

    @Column(name = "POST_SJ")
    private String postSj; //제목

    @Column(name = "POST_CN")
    private String postCn; //내용

    @Column(name = "REGSTR_ID")
    private String regstrId; //작성자Id

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    private List<PostTag> postTags;

    public Post(BoardDef boardDef, PostRequestDto requestDto) {
        this.postSj = requestDto.getPostSj();
        this.postCn = requestDto.getPostCn();
        this.regstrId = requestDto.getRegstrId();
        this.boardDef = boardDef;
    }
}
