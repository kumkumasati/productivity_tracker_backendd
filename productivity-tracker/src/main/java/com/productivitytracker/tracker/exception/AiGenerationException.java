package com.productivitytracker.tracker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.SERVICE_UNAVAILABLE)
public class AiGenerationException extends RuntimeException {
    public AiGenerationException(String message) {
        super(message);
    }

    public AiGenerationException(String message, Throwable cause) {
        super(message, cause);
    }
}
