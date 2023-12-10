package com.test.assignment.global.response.exeption;

public class InvalidInputException extends RuntimeException {
    public InvalidInputException(String message){
        super(message);
    }
}
