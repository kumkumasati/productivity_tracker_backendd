package com.productivitytracker.tracker.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> notFound(ResourceNotFoundException e) {
        return ResponseEntity.status(404).body(new ApiError("NOT_FOUND", e.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> badRequest(MethodArgumentNotValidException e) {
        String msg = e.getBindingResult().getFieldErrors().stream()
                .map(f -> f.getField() + ": " + f.getDefaultMessage())
                .collect(Collectors.joining(", "));
        return ResponseEntity.status(400).body(new ApiError("VALIDATION_ERROR", msg));
    }

    @ExceptionHandler(AiGenerationException.class)
    public ResponseEntity<ApiError> aiFailure(AiGenerationException e) {
        return ResponseEntity.status(503).body(new ApiError("AI_UNAVAILABLE", e.getMessage()));
    }

    @ExceptionHandler(org.springframework.security.access.AccessDeniedException.class)
    public ResponseEntity<ApiError> forbidden(org.springframework.security.access.AccessDeniedException e) {
        return ResponseEntity.status(403).body(new ApiError("FORBIDDEN", "You do not have access to this resource"));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> unexpected(Exception e) {
        return ResponseEntity.status(500).body(new ApiError("INTERNAL_ERROR", "Something went wrong"));
    }

    public record ApiError(String code, String message) {}
}