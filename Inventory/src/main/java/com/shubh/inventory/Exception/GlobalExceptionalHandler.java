package com.shubh.inventory.Exception;

import com.shubh.product.Payload.ApiResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionalHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ApiResponse handleUserNotFoundException(ProductNotFoundException userNotFoundException)
    {
        return new ApiResponse (HttpStatus.NOT_FOUND,userNotFoundException.getMessage());

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException)
    {
        Map<String,String> response=new HashMap<>();
        methodArgumentNotValidException.getBindingResult().getAllErrors().forEach((objectError -> {
            String fieldName=((FieldError)objectError).getField();
            String message=objectError.getDefaultMessage();
            response.put(fieldName,message);
        }));

        return new ResponseEntity<Map<String, String>>(response,HttpStatus.BAD_REQUEST);

    }
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        // Log the exception if needed
        ex.printStackTrace();

        // Return a meaningful error response to the client
        String errorMessage = "Malformed JSON payload: " + ex.getMessage();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }















}

