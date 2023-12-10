package com.test.assignment.global.response.exeption;

import java.nio.file.AccessDeniedException;

public class CustomAccessDeniedException extends AccessDeniedException {
    public CustomAccessDeniedException(String message){
        super(message);
    }
}
