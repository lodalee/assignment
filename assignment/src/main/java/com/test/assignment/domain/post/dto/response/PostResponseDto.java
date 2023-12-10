package com.test.assignment.domain.post.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.test.assignment.domain.post.entity.Post;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class PostResponseDto {
    private Integer postNo;
    private String boardNm;
    private String postSj;
    private String postCn;
    private String regstrId;
    private List<String> postTags;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime regDt;

    public PostResponseDto(Post post){
        this.postNo = post.getPostNo();
        this.boardNm = post.getBoardDef().getBoardNm().toString();
        this.postSj = post.getPostSj();
        this.postCn = post.getPostCn();
        this.regstrId = post.getRegstrId();
        this.regDt = post.getRegDt();

        this.postTags = post.getPostTags().stream()
                .map(postTag -> postTag.getTag().getTag())
                .collect(Collectors.toList());
    }
}
