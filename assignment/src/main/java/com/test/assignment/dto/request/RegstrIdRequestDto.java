package com.test.assignment.dto.request;

import com.test.assignment.dto.response.exeption.constant.ExceptionMessage;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class RegstrIdRequestDto {
    @NotBlank(message = ExceptionMessage.NOT_BLANK)
    private String regstrId; // 작성자 ID
}
