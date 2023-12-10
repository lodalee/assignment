package com.test.assignment.dto.response.success;

import org.springframework.http.HttpStatus;

public record MessageDto(String msg, HttpStatus status) { }
