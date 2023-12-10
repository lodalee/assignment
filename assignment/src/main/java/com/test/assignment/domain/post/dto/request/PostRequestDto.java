package com.test.assignment.domain.post.dto.request;

import com.test.assignment.global.response.exeption.constant.ExceptionMessage;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.util.List;

@Getter
public class PostRequestDto {
    private static final int MAX_TITLE_LENGTH = 50;
    private static final int MAX_AUTHOR_ID_LENGTH = 15;
    private static final int MAX_CONTENT_LENGTH = 1000;

    @NotBlank(message = ExceptionMessage.NOT_BLANK)
    @Size(max = MAX_TITLE_LENGTH, message = "제목은 최대 " + MAX_TITLE_LENGTH + "자까지 입력 가능합니다.")
    private String postSj; // 제목

    @NotBlank(message = ExceptionMessage.NOT_BLANK)
    @Size(max = MAX_CONTENT_LENGTH, message = "내용은 최대 " + MAX_CONTENT_LENGTH + "자까지 입력 가능합니다.")
    private String postCn; // 내용

    @NotBlank(message = ExceptionMessage.NOT_BLANK)
    @Size(max = MAX_AUTHOR_ID_LENGTH, message = "작성자 ID는 최대 " + MAX_AUTHOR_ID_LENGTH + "자까지 입력 가능합니다.")
    private String regstrId; // 작성자 ID

    private List<String> tags;
}
