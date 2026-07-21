package com.productivitytracker.tracker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class TaskCannotBeDeletedException extends RuntimeException {
    public TaskCannotBeDeletedException(String message) {
        super(message);
    }
}