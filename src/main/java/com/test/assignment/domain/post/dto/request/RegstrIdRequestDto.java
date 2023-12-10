package com.test.assignment.domain.post.dto.request;

import com.test.assignment.global.response.exeption.constant.ExceptionMessage;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class RegstrIdRequestDto {
    @NotBlank(message = ExceptionMessage.NOT_BLANK)
    private String regstrId; // 작성자 ID
}
