package com.suu.service.profile.exception.handler;

import com.suu.service.profile.exception.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class DefaultResponseEntityExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, WebRequest request) {

        return new ResponseEntity<>(Error.builder()
                .message(ex.getMessage())
                .code(ex.getMessage())
                .build(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(NotFoundException.class)
    protected ResponseEntity<Object> handleNotFoundException(
            NotFoundException ex, WebRequest request) {

        return new ResponseEntity<>(Error.builder()
                .message(ex.getMessage())
                .code(ex.getMessage())
                .build(), HttpStatus.NOT_FOUND);
    }


    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    private static class Error {
        private String code;
        private String message;
    }
}
