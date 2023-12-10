package com.test.assignment.dto.response.exeption;

import java.nio.file.AccessDeniedException;

public class CustomAccessDeniedException extends AccessDeniedException {
    public CustomAccessDeniedException(String message){
        super(message);
    }
}
