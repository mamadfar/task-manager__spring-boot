package com.leetjourney.taskmanager.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

/**
 * The @ControllerAdvice annotation is used to create a global exception handler
 * that can be applied to all controllers in the application. It is a convenient
 * way to handle exceptions across the entire application. The @ExceptionHandler
 * annotation is used to handle specific exceptions. The WebRequest parameter is
 * used to get the request details. The ResponseEntity is used to return the
 * response to the client. The HttpStatus is used to set the status code of the
 * response. The Map is used to store the response data. The LocalDateTime is
 * used to store the timestamp of the response. The String is used to store the
 * message of the response. The HashMap is used to store the response data. The
 * HttpStatus is used to set the status code of the response. The ResponseEntity
 * is used to return the response to the client. The HttpStatus is used to set
 * the status code of the response.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleTaskNotFoundException(TaskNotFoundException ex, WebRequest request) {
        Map<String, Object> body = new HashMap<>();

        body.put("timestamp", LocalDateTime.now());
        body.put("message", ex.getMessage());
        body.put("path", ((String) request.getDescription(false)).replace("uri=", ""));

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGlobalException(Exception ex, WebRequest request) {
        Map<String, Object> body = new HashMap<>();

        body.put("timestamp", LocalDateTime.now());
        body.put("message", "An unexpected error occurred");
        body.put("details", ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
