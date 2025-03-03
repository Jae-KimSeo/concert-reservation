package com.project.concert_reservation.support.exception;

import org.springframework.http.HttpStatus;

import java.io.Serial;

public class CustomException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    private final String message;
    private final HttpStatus httpStatus;

    public CustomException(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public static class PointNotFoundException extends RuntimeException {
        public PointNotFoundException(Long userId) {
            super("Point not found for user ID: " + userId);
        }
    }
}

