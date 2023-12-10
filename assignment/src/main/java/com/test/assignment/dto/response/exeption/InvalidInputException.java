package com.test.assignment.dto.response.exeption;

public class InvalidInputException extends RuntimeException {
    public InvalidInputException(String message){
        super(message);
    }
}
