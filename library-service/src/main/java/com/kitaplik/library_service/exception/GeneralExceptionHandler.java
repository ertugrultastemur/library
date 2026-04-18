package com.kitaplik.library_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GeneralExceptionHandler {
    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<ExceptionMessage> handleBookNotFoundException(BookNotFoundException e) {
        return new ResponseEntity<>(e.exceptionMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(LibraryNotFoundException.class)
    public ResponseEntity<String> handleLibraryNotFoundException(LibraryNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}
