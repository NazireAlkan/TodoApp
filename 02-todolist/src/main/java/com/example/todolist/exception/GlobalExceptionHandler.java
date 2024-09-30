package com.example.todolist.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    //Validation handler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ItemsNotFound.class)
    public ResponseEntity<ItemsErrorResponse> handleException(ItemsNotFound exc){

        ItemsErrorResponse itemsErrorResponse = new ItemsErrorResponse();

        itemsErrorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        itemsErrorResponse.setMessage(exc.getMessage());
        itemsErrorResponse.setTimeStamp(System.currentTimeMillis());


        return new ResponseEntity<>(itemsErrorResponse,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ItemsErrorResponse> globalExceptionsHandler(Exception exc){

        ItemsErrorResponse itemsErrorResponse = new ItemsErrorResponse();

        itemsErrorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        itemsErrorResponse.setMessage("invalid request");
        itemsErrorResponse.setTimeStamp(System.currentTimeMillis());


        return new ResponseEntity<>(itemsErrorResponse,HttpStatus.BAD_REQUEST);
    }


}
