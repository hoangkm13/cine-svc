package com.cinema.exception;

import com.cinema.entity.ErrorDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(value = {HttpException.class})
    public ResponseEntity<ErrorDTO> httpExceptionHandler(HttpException exception) {
        return ResponseEntity.status(exception.getStatus()).body(new ErrorDTO(exception.getMessage()));
    }
}
