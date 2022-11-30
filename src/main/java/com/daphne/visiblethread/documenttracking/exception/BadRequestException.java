package com.daphne.visiblethread.documenttracking.exception;


import org.springframework.http.HttpStatus;

/**
 * Returned when user makes a bad request
 */

public class BadRequestException extends RestException {

    public BadRequestException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}