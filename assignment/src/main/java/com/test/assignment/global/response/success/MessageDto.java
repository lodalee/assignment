package com.test.assignment.global.response.success;

import org.springframework.http.HttpStatus;

public record MessageDto(String msg, HttpStatus status) { }
