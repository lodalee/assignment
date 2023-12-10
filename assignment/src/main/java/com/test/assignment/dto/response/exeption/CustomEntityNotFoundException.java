package com.test.assignment.dto.response.exeption;

import jakarta.persistence.EntityNotFoundException;

public class CustomEntityNotFoundException extends EntityNotFoundException {
    public CustomEntityNotFoundException(String message){
        super(message);
    }
}
