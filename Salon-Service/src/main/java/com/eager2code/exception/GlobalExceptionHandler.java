package com.eager2code.exception;

import com.eager2code.pojo.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorResponse>> handleValidationErrors(
            MethodArgumentNotValidException exception
    ){
        List<ErrorResponse> errorResponse = new ArrayList<>();
        exception.getBindingResult().getFieldErrors().forEach(error->
        {
            ErrorResponse validationError = new ErrorResponse(
                    error.getDefaultMessage(),// Error message
                    error.getField(),// Field name (or object name)
                    LocalDateTime.now()
                    );
            errorResponse.add(validationError); // Add to the list
        });
        return ResponseEntity.ok(errorResponse);
    }
}
