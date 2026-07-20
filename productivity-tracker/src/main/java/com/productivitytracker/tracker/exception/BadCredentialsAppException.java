package com.productivitytracker.tracker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class BadCredentialsAppException extends RuntimeException {
    public BadCredentialsAppException(String message) {
        super(message);
    }
}
